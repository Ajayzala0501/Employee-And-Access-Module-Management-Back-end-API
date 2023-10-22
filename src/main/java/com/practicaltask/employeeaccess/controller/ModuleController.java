package com.practicaltask.employeeaccess.controller;

import com.practicaltask.employeeaccess.entity.Employee;
import com.practicaltask.employeeaccess.entity.Modules;
import com.practicaltask.employeeaccess.exceptionhandler.ResourceNotFoundExpection;
import com.practicaltask.employeeaccess.service.EmployeeService;
import com.practicaltask.employeeaccess.service.ModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apis/modules")
public class ModuleController {

    @Autowired
    ModuleService moduleService;

    @Autowired
    EmployeeService employeeService;

    Logger logger = LoggerFactory.getLogger(ModuleController.class);

    /**
     * Endpoint: GET /apis/modules/getAllModules
     * Description: Retrieve details of all modules in the system.
     *
     * @return If module records are found, it returns a ResponseEntity with a list of all module details and HTTP status 200 (OK).
     *         If no module records are found, it returns a ResponseEntity with an informational message and HTTP status 204 (No Content).
     */
    @GetMapping("/getAllModules")
    public ResponseEntity<Object> getAllModules(){
        logger.info("Get All Modules Details API");
        List<Modules> allModuleDetails =   moduleService.getAllModules();
      if(allModuleDetails.isEmpty()){
          return new ResponseEntity<>("No record found",HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(allModuleDetails,HttpStatus.OK);
    }

    /**
     * Endpoint: POST /apis/modules/addModuleAccessToEmployee/{empId}/module/{moduleId}
     * Description: Allow module access to a specific employee by their IDs.
     *
     * @param empId The unique identifier of the employee to grant module access.
     * @param moduleId The unique identifier of the module to be granted access.
     * @return If the specified employee exists and module access is successfully granted, it returns a ResponseEntity with the added module details and HTTP status 200 (OK).
     *         If no employee is found with the provided empId, it returns a ResponseEntity with an error message and HTTP status 404 (Not Found).
     */
    @PostMapping("/addModuleAccessToEmployee/{empId}/module/{moduleId}")
    public ResponseEntity<Object> addModuleAccessToEmployee(@PathVariable(value = "empId") long empId,@PathVariable(value = "moduleId") long moduleId){
        logger.info("Allow Module Access To Employee API");
        Employee checkIfEmployeeExits = employeeService.getEmployeeById(empId);
        if(checkIfEmployeeExits != null){
               Modules addedModule =  moduleService.addModuleAccessToEmployee(empId,moduleId);

            return new ResponseEntity<>(addedModule,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Record Found With ID:"+empId,HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint: GET /apis/modules/getAllModulesByEmployeeId/{empId}
     * Description: Retrieve all modules associated with a specific employee based on their ID.
     *
     * @param empId The unique identifier of the employee for whom modules are to be retrieved.
     * @return If modules are associated with the provided employee ID, it returns a ResponseEntity with a list of module details and HTTP status 200 (OK).
     *         If no modules are associated with the specified employee, it returns a ResponseEntity with an informational message and HTTP status 204 (No Content).
     */
    @GetMapping("/getAllModulesByEmployeeId/{empId}")
    public ResponseEntity<Object> getAllModulesByEmployeeId(@PathVariable("empId") long empId){
        logger.info("Get All Modules Information Associate With Specific Employee ID API");
        List<Modules> getAllModuleById =  moduleService.getAllModulesByEmployeeId(empId);
       if(getAllModuleById.size()>0){
           return new ResponseEntity<>(getAllModuleById,HttpStatus.OK);
       }
       return new ResponseEntity<>("No Record Found",HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint: GET /apis/modules/getModuleById/{moduleId}
     * Description: Retrieve module details based on a specific module ID.
     *
     * @param moduleId The unique identifier of the module to retrieve.
     * @return If a module with the provided ID is found, it returns a ResponseEntity with the module details and HTTP status 200 (OK).
     *         If no module is found with the specified ID, it returns a ResponseEntity with an informational message and HTTP status 204 (No Content).
     */
    @GetMapping("/getModuleById/{moduleId}")
    public ResponseEntity<Object> getModuleById(@PathVariable("moduleId") long moduleId){
        logger.info("Get Module Details Based On ID API");
        Optional<Modules> getModuleById  = moduleService.getModuleById(moduleId);
        if(getModuleById.isPresent()){
            return new ResponseEntity<>(getModuleById,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Record Found",HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint: GET /apis/modules/getAllEmployeesByModuleId/{moduleId}
     * Description: Retrieve all employees associated with a specific module based on its ID.
     *
     * @param moduleId The unique identifier of the module for which employees are to be retrieved.
     * @return If employees are associated with the provided module ID, it returns a ResponseEntity with a list of employee details and HTTP status 200 (OK).
     *         If no employees are associated with the specified module, it returns a ResponseEntity with an informational message and HTTP status 204 (No Content).
     */
    @GetMapping("/getAllEmployeesByModuleId/{moduleId}")
    public ResponseEntity<Object> getAllEmployeesByModuleId(@PathVariable("moduleId") long moduleId){
        logger.info("Get All Employee Information Associate With Specific Module ID API");
        List<Employee> getAllEmployeeById =  moduleService.getAllEmployeesByModuleId(moduleId);
        if(getAllEmployeeById.size()>0){
            return new ResponseEntity<>(getAllEmployeeById,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Record Found",HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint: PUT /apis/modules/updateModuleDetails/{moduleId}
     * Description: Update module details based on a specific module ID.
     *
     * @param moduleId The unique identifier of the module to update.
     * @param modules The updated module details provided in the request body as JSON.
     * @return If a module with the provided ID is found and its details are successfully updated, it returns a ResponseEntity with the updated module details and HTTP status 200 (OK).
     *         If no module is found with the specified ID or if the update operation fails, it returns a ResponseEntity with an error message and HTTP status 304 (Not Modified).
     */
    @PutMapping("/updateModuleDetails/{moduleId}")
    public ResponseEntity<Object> updateModuleDetails(@PathVariable(value = "moduleId") long moduleId,@RequestBody Modules modules){
        logger.info("Update Module Information API");
        Modules updatedModuleDetails =  moduleService.updateModuleDetails(moduleId,modules);
        if(updatedModuleDetails != null){
            return new ResponseEntity<>(updatedModuleDetails,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Record Update",HttpStatus.NOT_MODIFIED);
    }

    /**
     * Endpoint: DELETE /apis/modules/removeModuleAccessFromEmployee/{empId}/Module/{moduleId}
     * Description: Remove access to a specific module from an employee based on their IDs.
     *
     * @param empId The unique identifier of the employee from whom module access is to be removed.
     * @param moduleId The unique identifier of the module for which access is to be removed.
     * @return A ResponseEntity with a success message indicating that module access has been removed from the employee and HTTP status 200 (OK).
     */
    @DeleteMapping("/removeModuleAccessFromEmployee/{empId}/Module/{moduleId}")
    public ResponseEntity<Object> removeModuleAccessFromEmployee(@PathVariable(value = "empId")long empId,@PathVariable(value = "moduleId") long moduleId){
        logger.info("Remove Access Of Specific Module From Employee API");
        moduleService.removeModuleAccessFromEmployee(empId,moduleId);
        return new ResponseEntity<>("Module Access Removed From Employee",HttpStatus.OK);
    }

    /**
     * Endpoint: DELETE /apis/modules/deleteModuleById/{moduleId}
     * Description: Delete module details based on a specific module ID.
     *
     * @param moduleId The unique identifier of the module to delete.
     * @return A ResponseEntity with a success message indicating that the module record has been deleted and HTTP status 200 (OK).
     */
    @DeleteMapping("/deleteModuleById/{moduleId}")
    public ResponseEntity<Object> deleteModuleById(@PathVariable(value = "moduleId") long moduleId){
        logger.info("Delete Module Details Based On Module ID API");
        moduleService.deleteModuleById(moduleId);
        return new ResponseEntity<>("Module Record Deleted",HttpStatus.OK);
    }
}
