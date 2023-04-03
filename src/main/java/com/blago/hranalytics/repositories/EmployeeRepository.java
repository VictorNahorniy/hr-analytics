package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findEmployeesByFirstNameAndLastNameAndPatronymic(String firstName, String lastName, String patronymic);

    @Query("SELECT e FROM Employee e where e.employeeId in (SELECT h.employeeId FROM Hiring h)")
            List<Employee> findHiredEmployees();
}
