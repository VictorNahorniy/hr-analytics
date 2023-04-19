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

    @Query(value = "select * " +
            "from employee e " +
            "where e.employee_id in (select h.employee_id " +
            "                        from hiring h " +
            "                                 left join firing f on h.employee_id = f.employee_id " +
            "                        where f.employee_id is null)" ,
            nativeQuery = true)
    List<Employee> findFireableEmployees();

    @Query("SELECT e FROM Employee e where e.employeeId not in (SELECT h.employeeId FROM Hiring h)")
    List<Employee> findUnhiredEmployees();
}
