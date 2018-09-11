package com.testELS.testELS;

import com.testELS.testELS.controller.EmployeeController;
import com.testELS.testELS.entities.Employee;
import com.testELS.testELS.repository.EmployeeRepository;
import com.testELS.testELS.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TestElsControllerTest {
  @Mock
  EmployeeService employeeService;
  @Mock
  EmployeeRepository employeeRepository;

  @InjectMocks
  EmployeeController employeeController;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
      .build();
  }

  @Test
  public void testGetUniqueEmployeeListByCriteria() throws Exception {

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

    // when
    when(employeeRepository.findAll()).thenReturn(employeeList);

    // then
    mockMvc.perform(get("/api/employees/" + criteriaFirstName))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(2)));

    mockMvc.perform(get("/api/employees/" + criteriaLastName))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(3)));

    mockMvc.perform(get("/api/employees/" + criteriaCountry))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$",hasSize(1)));


    verify(employeeService, times(3)).getUniqueEmployeeListByCriteria(any());
    verifyNoMoreInteractions(employeeService);

  }


}
