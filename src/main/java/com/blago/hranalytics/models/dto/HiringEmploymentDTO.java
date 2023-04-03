package com.blago.hranalytics.models.dto;

import com.blago.hranalytics.models.Employee;
import com.blago.hranalytics.models.EmployeeDepartment;
import com.blago.hranalytics.models.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class HiringDTO {
    private Integer id;
    private Float wage;
    private Float pbi;
    private String employeeFullName;
    private String jobPositionName;
    private String employeeDepartmentName;
    private String objectName;
    private String lawFirmName;
    private String employmentTypeName;
    private Date hiringDate;
}
