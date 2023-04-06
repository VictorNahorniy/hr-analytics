package com.blago.hranalytics.models.dto;

import com.blago.hranalytics.models.Firing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FiringDTO {
    private String employeeFullName;
    private Firing firing;
}
