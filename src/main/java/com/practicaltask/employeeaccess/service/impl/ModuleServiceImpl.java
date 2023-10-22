package com.practicaltask.employeeaccess.service.impl;

import com.practicaltask.employeeaccess.entity.Employee;
import com.practicaltask.employeeaccess.entity.Modules;
import com.practicaltask.employeeaccess.exceptionhandler.ResourceNotFoundExpection;
import com.practicaltask.employeeaccess.repository.EmployeeRepository;
import com.practicaltask.employeeaccess.repository.ModuleRepository;
import com.practicaltask.employeeaccess.service.ModuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    Logger logger = LoggerFactory.getLogger(ModuleServiceImpl.class);

    @Override
    public List<Modules> getAllModules() {
        logger.info("Inside getAllModules Service Method");
        return moduleRepository.findAll();
    }

    @Override
    public Modules addModuleAccessToEmployee(long empId, long moduleId) {
        logger.info("Inside addModuleAccessToEmployee Service Method");
        Modules getModule = employeeRepository.findById(empId).map(e->{
            Modules getModuleById =  null;
            if(moduleId != 0l){
                 getModuleById = moduleRepository.findById(moduleId).orElseThrow(()-> new ResourceNotFoundExpection("No Module Found With ID:"+moduleId));
                e.add(getModuleById);
                employeeRepository.save(e);

            }
            return getModuleById;
        }).orElseThrow(()-> new ResourceNotFoundExpection("No Record Found With ID:"+empId));
        return getModule;
    }

    @Override
    public  List<Modules> getAllModulesByEmployeeId(long empId) {
        logger.info("Inside getAllModulesByEmployeeId Service Method");
        if(!employeeRepository.existsById(empId)){
            throw new ResourceNotFoundExpection("No Record Found With ID:"+empId);
        }
        return moduleRepository.findModulesByEmployeesId(empId);
    }

    @Override
    public Optional<Modules> getModuleById(long moduleId) {
        logger.info("Inside getModuleById Service Method");
        if(!moduleRepository.existsById(moduleId)){
            throw new ResourceNotFoundExpection("Mo Record Found With ID:"+moduleId);
        }
        return moduleRepository.findById(moduleId);
    }

    @Override
    public List<Employee> getAllEmployeesByModuleId(long moduleId) {
        logger.info("Inside getAllEmployeesByModuleId Service Method");
        if(!moduleRepository.existsById(moduleId)){
            throw new ResourceNotFoundExpection("No Record Found With ID:"+moduleId);
        }
        return employeeRepository.findEmployessByModulesId(moduleId);
    }

    @Override
    public Modules updateModuleDetails(long moduleId, Modules modules) {
        logger.info("Inside updateModuleDetails Service Method");
        if(!moduleRepository.existsById(moduleId)){
            throw new ResourceNotFoundExpection("Mo Record Found With ID:"+moduleId);
        }
        Optional<Modules> getModuleById = moduleRepository.findById(moduleId);
        if(getModuleById.isPresent()){
            if(!modules.getModuleName().equals("")){
                getModuleById.get().setModuleName(modules.getModuleName());
            }
        }

        return moduleRepository.save(getModuleById.get());
    }

    @Override
    public void removeModuleAccessFromEmployee(long empId, long moduleId) {
        logger.info("Inside removeModuleAccessFromEmployee Service Method");
        if(!employeeRepository.existsById(empId)){
            throw new ResourceNotFoundExpection("No Employee Record Found With ID:"+empId);
        }
        if(!moduleRepository.existsById(moduleId)){
            throw new ResourceNotFoundExpection("No Module Record Found With ID:"+moduleId);
        }
        Optional<Employee> getEmployeeById = employeeRepository.findById(empId);
        if(getEmployeeById.isPresent()){
            getEmployeeById.get().removeModule(empId);
            employeeRepository.save(getEmployeeById.get());
        }
    }

    @Override
    public void deleteModuleById(long moduleId) {
        logger.info("Inside deleteModuleById Service Method");

        if(!moduleRepository.existsById(moduleId)){
            throw new ResourceNotFoundExpection("No Module Record Found With ID:"+moduleId);
        }
        moduleRepository.deleteById(moduleId);
    }


}
