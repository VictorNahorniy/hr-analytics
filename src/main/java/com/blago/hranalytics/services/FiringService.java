package com.blago.hranalytics.services;

import com.blago.hranalytics.models.Firing;
import com.blago.hranalytics.models.dto.FiringDTO;
import com.blago.hranalytics.repositories.FiringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FiringService {
    @Autowired
    private FiringRepository firingRepository;
    @Autowired
    private EmployeeService employeeService;

    public boolean isEmployeeFiredById(Integer employeeId){
        return firingRepository.existsByEmployeeId(employeeId);
    }

    public boolean save(Firing firing) {
        return firingRepository.save(firing).equals(firing);
    }

    public List<FiringDTO> getAllDTO() {
        return firingRepository.findAll().stream().map(this::parseFiringToFiringDTO).collect(Collectors.toList());
    }

    private FiringDTO parseFiringToFiringDTO(Firing firing){
        return new FiringDTO(
                employeeService.getFullNameById(firing.getEmployeeId()).get(),
                firing
        );
    }
}
