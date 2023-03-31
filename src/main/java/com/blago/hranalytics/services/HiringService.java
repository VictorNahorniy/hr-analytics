package com.blago.hranalytics.services;

import com.blago.hranalytics.controllers.HiringController;
import com.blago.hranalytics.models.Hiring;
import com.blago.hranalytics.models.dto.HiringDTO;
import com.blago.hranalytics.repositories.HiringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class HiringService {
    @Autowired
    private HiringRepository hiringRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JobPositionService jobPositionService;
    @Autowired
    private EmployeeDepartmentService employeeDepartmentService;
    @Autowired
    private BuildingObjectService buildingObjectService;
    @Autowired
    private LawFirmService lawFirmService;
    @Autowired
    private EmploymentTypeService employmentTypeService;

    public Hiring createHiring() {
        return new Hiring();
    }

    public boolean save(Hiring hiring) {
           if (hiring.getHiringTypeId() != null && hiring.getJobPositionId() != null) {
               hiring.setHiringDate(new Date(System.currentTimeMillis()));
               hiringRepository.save(hiring);
                return true;
            }
            return false;
    }

    public List<HiringDTO> getAllDTO() {
        List<HiringDTO> hiringDTOList = new ArrayList<>();
        for(Hiring hiring : hiringRepository.findAll()){
            hiringDTOList.add(new HiringDTO(
                    hiring.getHiringId(),
                    hiring.getWage().floatValue(),
                    hiring.getPbi().floatValue(),
                    employeeService.getFullNameById(hiring.getEmployeeId()).get(),
                    jobPositionService.getNameById(hiring.getJobPositionId()),
                    employeeDepartmentService.getNameById(hiring.getEmployeeDepartmentId()),
                    buildingObjectService.getNameById(hiring.getObjectId()),
                    lawFirmService.getNameById(hiring.getLawFirmId()),
                    employmentTypeService.getNameById(hiring.getEmploymentTypeId()),
                    hiring.getHiringDate()
                    ));
        }
        return hiringDTOList;
    }
}
