package com.blago.hranalytics.services;

import com.blago.hranalytics.models.Employee;
import com.blago.hranalytics.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public boolean save(Employee employee) {
        List<Employee> employees = repository.findEmployeesByFirstNameAndLastNameAndPatronymic(employee.getFirstName(),
                employee.getLastName(),
                employee.getPatronymic());
        if (employees.size() == 0) {
            repository.save(employee);
            return true;
        }
        return false;
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Optional<Employee> getById(Integer id) {
        return repository.findById(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public boolean update(Employee employee) {
        Optional<Employee> employeeOptional = repository.findById(employee.getEmployeeId());
        if (employeeOptional.isPresent()) {
            repository.save(employee);
            return true;
        }
        return false;
    }

    public Optional<String> getFullNameById(Integer id) {
        Optional<Employee> employeeOptional = repository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            return Optional.of(employee.getLastName() + " " + employee.getFirstName() + " " + employee.getPatronymic());
        }
        return Optional.empty();
    }

    public List<Employee> getHiredEmployees() {
        return repository.findHiredEmployees();
    }
}
