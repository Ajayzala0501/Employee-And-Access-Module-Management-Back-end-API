package com.practicaltask.employeeaccess.controller;

import com.practicaltask.employeeaccess.entity.Employee;
import com.practicaltask.employeeaccess.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    /**
     * Endpoint: POST /apis/employee/addNewEmployeeDetails
     * Description: Add new employee details to the system.
     *
     * @param  e The employee details to be added, provided in the request body as JSON.
     * @return If the employee details are successfully saved, it returns a ResponseEntity with the saved employee data and HTTP status 201 (Created).
     *         If an error occurs during the operation, it returns a ResponseEntity with an error message and HTTP status 500 (Internal Server Error).
     */
    @PostMapping("/addNewEmployeeDetails")
    public ResponseEntity<Object> addNewEmployeeDetails(@RequestBody Employee e){
        logger.info("Add New Employee Details API");
        Employee saveData = employeeService.addNewEmployeeDetails(e);
        if(saveData!=null){
            logger.info("Saved New Employee Details");
            return new ResponseEntity<>(saveData, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Endpoint: GET /apis/employee/getEmployees
     * Description: Retrieve a list of all employee details from the system.
     *
     * @return If employee records are found, it returns a ResponseEntity with the list of employees and HTTP status 200 (OK).
     *         If no records are found, it returns a ResponseEntity with an informational message and HTTP status 204 (No Content).
     */
    @GetMapping("/getEmployees")
    public ResponseEntity<Object> getEmployees(){
        logger.info("Get All Employee API");
        List<Employee> employeeList = employeeService.getEmployees();
        if(employeeList.size()>0){
            logger.info("Getting All Employee List");
            return new ResponseEntity<>(employeeList,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Record Found", HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint: GET /apis/employee/getEmployeeById/{id}
     * Description: Retrieve employee details based on a specific employee ID.
     *
     * @param id The unique identifier of the employee to retrieve.
     * @return If an employee with the provided ID is found, it returns a ResponseEntity with the employee's details and HTTP status 200 (OK).
     *         If no employee is found with the specified ID, it returns a ResponseEntity with an error message and HTTP status 404 (Not Found).
     */
    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable(value = "id") long id){
        logger.info("Get Employee Based On Employee ID API");
        Employee employeeData = employeeService.getEmployeeById(id);
        if(employeeData != null){
            return new ResponseEntity<>(employeeData,HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found Employee With ID:"+id,HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint: PUT /apis/employee/updateEmployeeDetails/{id}
     * Description: Update employee details based on a specific employee ID.
     *
     * @param id The unique identifier of the employee to update.
     * @param e The updated employee details provided in the request body as JSON.
     * @return If an employee with the provided ID is found and updated, it returns a ResponseEntity with the updated employee details and HTTP status 200 (OK).
     *         If no employee is found with the specified ID or if the update operation fails, it returns a ResponseEntity with an error message and HTTP status 304 (Not Modified).
     */
    @PutMapping("/updateEmployeeDetails/{id}")
    public ResponseEntity<Object> updateEmployeeDetails(@PathVariable(value = "id") long id, @RequestBody Employee e){
        logger.info("Update Employee Details Based On ID API");
        Employee updatedEmployee =  employeeService.updateEmployeeDetails(id,e);
        if(updatedEmployee!= null){
            return new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Record Update",HttpStatus.NOT_MODIFIED);
    }

    /**
     * Endpoint: DELETE /apis/employee/deleteEmployeeDetails/{id}
     * Description: Delete an employee's details based on a specific employee ID.
     *
     * @param id The unique identifier of the employee to delete.
     * @return If an employee with the provided ID is found and successfully deleted, it returns a ResponseEntity with a success message and HTTP status 200 (OK).
     *         If no employee is found with the specified ID, it returns a ResponseEntity with an error message and HTTP status 404 (Not Found).
     */
    @DeleteMapping("/deleteEmployeeDetails/{id}")
    public ResponseEntity<Object> deleteEmployeeDetails(@PathVariable(value = "id") long id){
        logger.info("Delete Employee Details Based On ID API");
        employeeService.deleteEmployeeDetails(id);
        return new ResponseEntity<>("Employee Record Deleted", HttpStatus.OK);
    }

    /**
     * Endpoint: DELETE /apis/employee/deleteAllEmployeeDetails
     * Description: Delete all employee records in the system.
     *
     * @return If all employee records are successfully deleted, it returns a ResponseEntity with a success message and HTTP status 200 (OK).
     *         If there are no employee records to delete, it returns a ResponseEntity with an informational message and HTTP status 204 (No Content).
     */
    @DeleteMapping("/deleteAllEmployeeDetails")
    public ResponseEntity<Object> deleteAllEmployeeDetails(){
        logger.info("Delete All Employees Details");
        employeeService.deleteAllEmployeeDetails();
        return new ResponseEntity<>("All Employees Records Deleted", HttpStatus.OK);
    }
}
