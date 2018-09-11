package com.testELS.testELS.repository;

import com.testELS.testELS.entities.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
  List<Employee> findDistinctByFirstName();
  List<Employee> findDistinctByLastName();
  List<Employee> findDistinctByCountry();
  List<Employee> getDistinctByFirstName();
  List<Employee> queryDistinctByCountry();

}
