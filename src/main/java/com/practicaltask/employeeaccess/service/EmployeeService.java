package com.practicaltask.employeeaccess.service;

import com.practicaltask.employeeaccess.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing employee-related operations.
 *
 * Defines methods for adding new employee details, retrieving all employees, getting an employee by ID,
 * updating employee details, deleting a specific employee by ID, and deleting all employee details.
 */
@Service
public interface EmployeeService {

   public Employee addNewEmployeeDetails(Employee e);

   public List<Employee> getEmployees();

   public Employee getEmployeeById(long id);

   public Employee updateEmployeeDetails(long id, Employee e);

   public void deleteEmployeeDetails(long id);

   public void deleteAllEmployeeDetails();
}
