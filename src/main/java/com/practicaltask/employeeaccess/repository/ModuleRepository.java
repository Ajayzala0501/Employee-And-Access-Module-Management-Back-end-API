package com.practicaltask.employeeaccess.repository;

import com.practicaltask.employeeaccess.entity.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Modules, extending JpaRepository for CRUD operations.
 *
 * Provides methods to interact with the 'Modules' entity, including finding modules by associated employee ID.
 */

@Repository
public interface ModuleRepository extends JpaRepository<Modules,Long> {

    List<Modules> findModulesByEmployeesId(long id);
}
