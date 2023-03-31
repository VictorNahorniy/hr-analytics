package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.Employee;
import com.blago.hranalytics.models.EmployeeDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDepartmentRepository extends JpaRepository<EmployeeDepartment, Integer> {
    List<EmployeeDepartment> findAll();

    @Query(value = "select departmentName from EmployeeDepartment where employeeDepartmentId = ?1")
    String findEmployeeDepartmentNameById(Integer employeeDepartmentId);
}
