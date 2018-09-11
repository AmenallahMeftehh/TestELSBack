package com.testELS.testELS.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testELS.testELS.entities.Employee;
import com.testELS.testELS.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    EmployeeRepository employeesRepository;

    @Override
    public List<Employee> extractData() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Employee>> mapType = new TypeReference<List<Employee>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/static/employee.json");
        try {
            List<Employee> employees = mapper.readValue(inputStream, mapType);
            employeesRepository.saveAll(employees);
            return employees;
        } catch (IOException e) {
            log.debug("Unable to get employees: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;

    }

    public static String getValues(Employee e, String criteria) {

        switch (criteria) {
            case "firstName":
                return e.getFirstName();
            case "lastName":
                return e.getLastName();
            case "country":
                return e.getCountry();
            default:
                return e.getFirstName();
        }
    }

    @Override
    public List<Employee> getUniqueEmployeeListByCriteria(String criteria) {
        List<Employee> employeesDistinct = employeesRepository.findAll().stream().
                filter(distinctByKey(E -> getValues(E, criteria))).collect(Collectors.toList());
        return employeesDistinct;
    }

}
