package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.Employee;
import com.blago.hranalytics.models.EmployeeDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDepartmentRepository extends JpaRepository<EmployeeDepartment, Integer> {
    List<EmployeeDepartment> findAll();
}
