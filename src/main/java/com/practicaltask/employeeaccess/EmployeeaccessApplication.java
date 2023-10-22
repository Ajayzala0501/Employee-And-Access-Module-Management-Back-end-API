package com.practicaltask.employeeaccess;

import com.practicaltask.employeeaccess.service.ModuleService;
import com.practicaltask.employeeaccess.service.impl.ModuleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.practicaltask.employeeaccess.repository")
@ComponentScan(basePackages = { "com.practicaltask.employeeaccess.controller","com.practicaltask.employeeaccess.exceptionhandler","com.practicaltask.employeeaccess.service","com.practicaltask.employeeaccess.service.impl" })
@EntityScan("com.practicaltask.employeeaccess.entity")
public class EmployeeaccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeaccessApplication.class, args);
	}

}
