package com.testELS.testELS.services;

import com.mongodb.DBObject;
import com.testELS.testELS.entities.Employee;

import java.util.List;

public interface EmployeeService {

  List<Employee> getUniqueEmployeeListByCriteria (String criteria);
  List<Employee> extractData ();


}
