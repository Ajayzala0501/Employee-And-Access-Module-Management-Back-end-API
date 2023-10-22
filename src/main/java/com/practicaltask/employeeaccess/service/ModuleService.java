package com.practicaltask.employeeaccess.service;

import com.practicaltask.employeeaccess.entity.Employee;
import com.practicaltask.employeeaccess.entity.Modules;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing module-related operations.
 *
 * Defines methods for adding default module records, retrieving all modules, adding module access to employees,
 * retrieving modules associated with a specific employee, getting module details by ID, retrieving employees by module ID,
 * updating module details, removing module access from an employee, and deleting a module by its ID.
 */
@Service
public interface ModuleService {

    public List<Modules> getAllModules();

    public Modules addModuleAccessToEmployee(long empId, long moduleId);

    List<Modules> getAllModulesByEmployeeId(long empId);

    Optional<Modules> getModuleById(long moduleId);

    List<Employee> getAllEmployeesByModuleId(long moduleId);

    Modules updateModuleDetails(long moduleId, Modules modules);

    void removeModuleAccessFromEmployee(long empId, long moduleId);

    void deleteModuleById(long moduleId);
}
