package com.blago.hranalytics.services;

import com.blago.hranalytics.models.JobCategory;
import com.blago.hranalytics.repositories.JobCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobCategoryService {
    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    public List<JobCategory> findAll() {
        return jobCategoryRepository.findAll();
    }

    public JobCategory findById(Integer jobCategoryId) {
        return jobCategoryRepository.findById(jobCategoryId).orElse(null);
    }
}
