package com.blago.hranalytics.services;

import com.blago.hranalytics.models.JobPosition;
import com.blago.hranalytics.models.dto.JobPositionDTO;
import com.blago.hranalytics.repositories.JobPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobPositionService {
    @Autowired
    private JobPositionRepository jobPositionRepository;
    @Autowired
    private JobCategoryService jobCategoryService;
    @Autowired
    private EmploymentLevelService employmentLevelService;

    private boolean isJobPositionExists(JobPosition jobPosition) {
        return jobPositionRepository.findByPositionName(jobPosition.getPositionName()) != null;
    }

    private boolean isJobPositionFieldEmpty(JobPosition jobPosition) {
        return jobPosition.getPositionName().isEmpty() && jobPosition.getJobCategoryId() == null && jobPosition.getEmploymentLevelId() == null;
    }

    public boolean save(JobPosition jobPosition) {
        if (!isJobPositionFieldEmpty(jobPosition)) {
            if (!isJobPositionExists(jobPosition)) {
                jobPositionRepository.save(jobPosition);
                return true;
            }
        }
        return false;
    }

    public List<JobPositionDTO> getAllDTO() {
        List<JobPositionDTO> jobPositionDTOList = new ArrayList<>();
        for (JobPosition jobPosition : jobPositionRepository.findAll()) {
            jobPositionDTOList.add(new JobPositionDTO(
                    jobPosition.getJobPositionId(),
                    jobPosition.getPositionName(),
                    jobCategoryService.findById(jobPosition.getJobCategoryId()).getCategoryName(),
                    employmentLevelService.findById(jobPosition.getEmploymentLevelId()).getLevelName()));
        }
        return jobPositionDTOList;
    }

    public List<JobPosition> getAll() {
        return jobPositionRepository.findAll();
    }
    public String getNameById(Integer id){
        return jobPositionRepository.findJobPositionNameById(id);
    }

    public Optional<JobPosition> findById(Integer id) {
        return jobPositionRepository.findById(id);
    }

    public void deleteById(Integer id) {
        jobPositionRepository.deleteById(id);
    }

    public void update(JobPosition jobPosition) {
        jobPositionRepository.save(jobPosition);
    }
}
