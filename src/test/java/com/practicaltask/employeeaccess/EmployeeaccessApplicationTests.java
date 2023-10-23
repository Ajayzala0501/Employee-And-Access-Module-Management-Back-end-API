package com.practicaltask.employeeaccess;

import com.practicaltask.employeeaccess.controller.EmployeeController;
import com.practicaltask.employeeaccess.controller.ModuleController;
import com.practicaltask.employeeaccess.entity.Employee;
import com.practicaltask.employeeaccess.entity.Modules;
import com.practicaltask.employeeaccess.service.EmployeeService;
import com.practicaltask.employeeaccess.service.ModuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeaccessApplicationTests {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private ModuleController moduleController;

	@Mock
	private ModuleService moduleService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	// Test Case For AddNewEmployeeDetails
	@Test
	public void testAddNewEmployeeDetailsSuccess() {
		Employee employee = new Employee();// Create a sample Employee object for testing
		employee.setId(1);
		employee.setFirstName("Ajay");
		employee.setLastName("Zala");
		employee.setDepartment("Computer");
		when(employeeService.addNewEmployeeDetails(employee)).thenReturn(employee);
		ResponseEntity<Object> response = employeeController.addNewEmployeeDetails(employee);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(employee, response.getBody());
	}

	@Test
	public void testAddNewEmployeeDetailsError() {
		Employee employee = new Employee();
		employee = null;
		when(employeeService.addNewEmployeeDetails(employee)).thenReturn(null);
		ResponseEntity<Object> response = employeeController.addNewEmployeeDetails(employee);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Internal Server Error", response.getBody());
	}

	// Test Case For Get Employee Details Method

	@Test
	public void testGetEmployeesSuccess() {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee(1,"Ajay","Zala","Computer"));
		employeeList.add(new Employee(1,"Jay","Zala","EC"));
		when(employeeService.getEmployees()).thenReturn(employeeList);
		ResponseEntity<Object> response = employeeController.getEmployees();


		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(employeeList, response.getBody());
	}

	@Test
	public void testGetEmployeesNoContent() {
		when(employeeService.getEmployees()).thenReturn(Collections.emptyList());
		ResponseEntity<Object> response = employeeController.getEmployees();

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertEquals("No Record Found", response.getBody());
	}

	// Test Case For Get Employee By ID Method
	@Test
	public void testGetEmployeeByIdSuccess() {
		long employeeId = 1;
		Employee employee = new Employee(1,"Ajay","Zala","Computer");
		when(employeeService.getEmployeeById(employeeId)).thenReturn(employee);

		ResponseEntity<Object> response = employeeController.getEmployeeById(employeeId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(employee, response.getBody());
	}

	@Test
	public void testDeleteEmployeeDetailsSuccess() {
		long employeeId = 1;
		ResponseEntity<Object> response = employeeController.deleteEmployeeDetails(employeeId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Employee Record Deleted", response.getBody());

		// Verify that the employeeService.deleteEmployeeDetails method was called with the correct ID
		verify(employeeService).deleteEmployeeDetails(employeeId);
	}


	// For Module Controller Test Case
	@Test
	public void testGetAllModulesSuccess() {
		List<Modules> modulesList = new ArrayList<>(); // Create a sample list of modules for testing
		modulesList.add(new Modules(1,"Reports"));
		modulesList.add(new Modules(2,"Dashboard"));
		// Mock the behavior of the moduleService.getAllModules method to return the list of modules
		when(moduleService.getAllModules()).thenReturn(modulesList);
		ResponseEntity<Object> response = moduleController.getAllModules();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(modulesList, response.getBody());
	}

	@Test
	public void testRemoveModuleAccessFromEmployeeSuccess() {
		long empId = 1;
		long moduleId = 1;

		doNothing().when(moduleService).removeModuleAccessFromEmployee(empId, moduleId);

		ResponseEntity<Object> response = moduleController.removeModuleAccessFromEmployee(empId, moduleId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Module Access Removed From Employee", response.getBody());
	}
}