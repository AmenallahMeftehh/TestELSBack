package com.testELS.testELS.controller;

import com.testELS.testELS.entities.Employee;
import com.testELS.testELS.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    /**
     * Returns the list of customers
     *
     * @return
     */
    @RequestMapping(value = "/employees/{criteria}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getUniqueEmployeeListByCriteria(@PathVariable(value = "criteria") String criteria) {

        List<Employee> employeesDistinct = employeeService.getUniqueEmployeeListByCriteria(criteria);

        return new ResponseEntity<>(employeesDistinct, HttpStatus.OK);
    }

}
