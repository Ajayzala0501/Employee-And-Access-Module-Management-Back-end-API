package com.practicaltask.employeeaccess.service.impl;

import com.practicaltask.employeeaccess.entity.Employee;
import com.practicaltask.employeeaccess.exceptionhandler.ResourceNotFoundExpection;
import com.practicaltask.employeeaccess.repository.EmployeeRepository;
import com.practicaltask.employeeaccess.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    
    @Override
    public Employee addNewEmployeeDetails(Employee e) {
        logger.info("Inside addNewEmployeeDetails Service Method");
        return employeeRepository.save(e);
    }

    @Override
    public List<Employee> getEmployees() {
        logger.info("Inside getEmployees Service Method");
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        logger.info("Inside getEmployeeById Service Method");
        Employee employeeData = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExpection("Not Found Employee With ID:"+id));
        return employeeData;
    }

    @Override
    @Transactional
    public Employee updateEmployeeDetails(long id, Employee e) {
        logger.info("Inside updateEmployeeDetails Service Method");
        Employee getExistingEmployee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundExpection("Not Found Employee With ID:"+id));

        if(!e.getFirstName().isEmpty()){
            getExistingEmployee.setFirstName(e.getFirstName());
        }
        if(!e.getLastName().isEmpty()){
            getExistingEmployee.setLastName(e.getLastName());
        }
        if(!e.getDepartment().isEmpty()){
            getExistingEmployee.setDepartment(e.getDepartment());
        }
        return employeeRepository.save(getExistingEmployee);
    }

    @Override
    public void deleteEmployeeDetails(long id) {
        logger.info("Inside deleteEmployeeDetails Service Method");
        Employee employeeData = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExpection("Not Found Employee With ID:"+id));
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAllEmployeeDetails() {
        logger.info("Inside deleteAllEmployeeDetails Service Method");
        employeeRepository.deleteAll();
    }
}
