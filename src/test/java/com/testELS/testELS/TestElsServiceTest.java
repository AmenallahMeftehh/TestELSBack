package com.testELS.testELS;

import com.testELS.testELS.entities.Employee;
import com.testELS.testELS.repository.EmployeeRepository;
import com.testELS.testELS.services.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestElsServiceTest {
  @InjectMocks
  EmployeeServiceImpl employeeService;

  @Mock
  EmployeeRepository employeeRepository;

  @Test
  public void testGetUniqueEmployeeListByCriteria() {
    //given
    String criteriaFirstName = "firstName";
    String criteriaLastName = "lastName";
    String criteriaCountry = "country";
    Employee employee1 = new Employee("1", "david", "alex", "france");
    Employee employee2 = new Employee("2", "mickel", "alexi", "france");
    Employee employee3 = new Employee("3", "mickel", "yoyo", "france");
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(employee1);
    employeeList.add(employee2);
    employeeList.add(employee3);
    //when
    when(employeeRepository.findAll()).thenReturn(employeeList);
    int listSize = employeeService.getUniqueEmployeeListByCriteria(criteriaFirstName).size();
    //then
    assertEquals(2, listSize);
    //when
    when(employeeRepository.findAll()).thenReturn(employeeList);
    int listSize2 = employeeService.getUniqueEmployeeListByCriteria(criteriaLastName).size();
    //then
    assertEquals(3, listSize2);
    //when
    when(employeeRepository.findAll()).thenReturn(employeeList);
    int listSize3 = employeeService.getUniqueEmployeeListByCriteria(criteriaCountry).size();
    //then
    assertEquals(1, listSize3);
  }

}
