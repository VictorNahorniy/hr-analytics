package com.blago.hranalytics.models.dto;

import com.blago.hranalytics.models.EmploymentLevel;
import com.blago.hranalytics.models.JobCategory;
import com.blago.hranalytics.models.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@AllArgsConstructor
public class JobPositionDTO {
    private Integer id;
    private String positionName;
    private String jobCategoryName;
    private String employmentLevelName;
}
