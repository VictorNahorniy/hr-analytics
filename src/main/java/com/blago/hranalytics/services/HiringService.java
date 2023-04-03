package com.blago.hranalytics.services;

import com.blago.hranalytics.models.Hiring;
import com.blago.hranalytics.models.dto.HiringEmploymentDTO;
import com.blago.hranalytics.models.dto.HiringTransferDTO;
import com.blago.hranalytics.repositories.HiringRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class HiringService {
    private static final String EMPLOYMENT = "Прийняття";
    private static final String TRANSFER = "Переведення";
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
    @Autowired
    private HiringTypeService hiringTypeService;

    public Hiring createHiring() {
        return new Hiring();
    }

    public boolean saveEmployment(Hiring hiring) throws SQLException {
        if (!hiring.getHiringTypeId().equals(hiringTypeService.getByName(EMPLOYMENT).getTypeId())) {
            log.error("Hiring entity is not correct, need TRANSFER, but is "
                    + hiringTypeService.getNameById(hiring.getHiringTypeId()));
            return false;
        }
        hiring.setHiringDate(new Timestamp(System.currentTimeMillis()));
        hiringRepository.save(hiring);
        return true;
    }

    public List<HiringEmploymentDTO> getAllEmploymentsDTO() throws SQLException {
        List<HiringEmploymentDTO> hiringEmploymentDTOList = new ArrayList<>();
        for (Hiring hiring : hiringRepository.findHiringByHiringTypeId(
                hiringTypeService.getByName(EMPLOYMENT).getTypeId())) {
            hiringEmploymentDTOList.add(parseHiringToHiringEmploymentDTO(hiring));
        }
        return hiringEmploymentDTOList;
    }

    public boolean isEmployeeHired(Integer employeeId) {
        return hiringRepository.existsHiringByEmployeeId(employeeId);
    }

    public void deleteById(Integer hiringId) {
        hiringRepository.deleteById(hiringId);
    }

    public Optional<Hiring> getHiringById(Integer id) {
        if (hiringRepository.existsById(id)) {
            return hiringRepository.findById(id);
        }
        return Optional.empty();
    }

    private HiringEmploymentDTO parseHiringToHiringEmploymentDTO(Hiring hiring) {
        return new HiringEmploymentDTO(
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
        );
    }
    
    private HiringTransferDTO parseHiringToHiringTransferDTO(Hiring hiring){
        return new HiringTransferDTO(
                hiring.getHiringId(),
                hiring.getWage().floatValue(),
                hiring.getPbi().floatValue(),
                employeeService.getFullNameById(hiring.getEmployeeId()).get(),
                jobPositionService.getNameById(hiring.getJobPositionId()),
                employeeDepartmentService.getNameById(hiring.getEmployeeDepartmentId()),
                buildingObjectService.getNameById(hiring.getObjectId()),
                lawFirmService.getNameById(hiring.getLawFirmId()),
                employmentTypeService.getNameById(hiring.getEmploymentTypeId()),
                hiring.getHiringDate(),
                parseHiringToHiringEmploymentDTO(hiringRepository.findById(hiring.getPreviousHiringId()).get())
        );
    }

    public boolean update(Hiring hiring) {
        hiringRepository.save(hiring);
        return true;
    }

    public boolean exists(Hiring hiring) {
        return hiringRepository.existsById(hiring.getHiringId());

    }

    public boolean saveTransfer(Hiring hiring) throws SQLException {
        if (!hiring.getHiringTypeId().equals(hiringTypeService.getByName(TRANSFER).getTypeId())) {
            log.error("Hiring entity is not correct, need TRANSFER, but is "
                    + hiringTypeService.getNameById(hiring.getHiringTypeId()));
            return false;
        }
        hiring.setHiringDate(new Timestamp(System.currentTimeMillis()));
        hiring.setPreviousHiringId(getPreviousHiringId(hiring.getEmployeeId()));
        hiringRepository.save(hiring);
        return true;
    }

    private Integer getPreviousHiringId(Integer employeeId) {
        return hiringRepository.findLastHiringByEmployeeId(employeeId).getHiringId();
    }

    public List<HiringTransferDTO> getAllTransfersDTO() throws SQLException {
        List<HiringTransferDTO> hiringTransferDTOList = new ArrayList<>();
        for (Hiring hiring : hiringRepository.findHiringByHiringTypeId(
                hiringTypeService.getByName(TRANSFER).getTypeId())) {
            hiringTransferDTOList.add(parseHiringToHiringTransferDTO(hiring));
        }
        return hiringTransferDTOList;
    }

    public boolean isPreviousHiring(Integer id) {
        return hiringRepository.existsByPreviousHiringId(id);
    }
}
