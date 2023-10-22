package com.practicaltask.employeeaccess.repository;

import com.practicaltask.employeeaccess.entity.Employee;
import com.practicaltask.employeeaccess.entity.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Employee entities, extending JpaRepository for CRUD operations.
 *
 * Provides methods to interact with the 'Employee' entity, including finding employees by associated module ID.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findEmployessByModulesId(long id);
}
