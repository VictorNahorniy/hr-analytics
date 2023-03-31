package com.blago.hranalytics.services;

import com.blago.hranalytics.exceptions.EmployeeDepartmentException;
import com.blago.hranalytics.models.EmployeeDepartment;
import com.blago.hranalytics.repositories.EmployeeDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDepartmentService {
    @Autowired
    private EmployeeDepartmentRepository repository;


    public List<EmployeeDepartment> getAll() {
        return repository.findAll();
    }

    public boolean save(EmployeeDepartment employeeDepartment) {
        try {
            repository.save(employeeDepartment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existsById(Integer id){
        return repository.existsById(id);
    }

    public EmployeeDepartment getById(Integer id) throws EmployeeDepartmentException {
        return repository.findById(id).orElseThrow(() -> new EmployeeDepartmentException("Department with id " + id + " does not exist"));
    }

    public boolean update(EmployeeDepartment employeeDepartment) {
        try {
            repository.save(employeeDepartment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public String getNameById(Integer employeeDepartmentId) {
        return repository.findEmployeeDepartmentNameById(employeeDepartmentId);
    }
}
