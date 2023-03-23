package com.blago.hranalytics.services;

import com.blago.hranalytics.models.MedicalExamination;
import com.blago.hranalytics.repositories.MedicalExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalExaminationService {
    @Autowired
    MedicalExaminationRepository medicalExaminationRepository;

    public List<MedicalExamination> getValidMedicalExaminationsByEmployeeId(Integer employeeId) {
        return medicalExaminationRepository
                .findMedicalExaminationsByEmployeeIdAndAndExpirationGreaterThanEqual(employeeId, new java.util.Date());
    }

    public boolean save(MedicalExamination examination) {
        if (isExaminationDataNull(examination)) {
            return false;
        }
        medicalExaminationRepository.save(examination);
        return true;
    }

    boolean isExaminationDataNull(MedicalExamination examination) {
        return examination.getEmployeeId() == null || examination.getExpiration() == null;
    }

    public List<MedicalExamination> getAllByEmployeeId(Integer employeeId) {
        return medicalExaminationRepository.findMedicalExaminationsByEmployeeId(employeeId);
    }

    public Optional<MedicalExamination> getById(Integer id) {
        return medicalExaminationRepository.findById(id);
    }

    public boolean update(MedicalExamination medicalExamination) {
        if (isExaminationDataNull(medicalExamination)) {
            return false;
        }
        medicalExaminationRepository.save(medicalExamination);
        return true;
    }

    public boolean delete(Integer id) {
        if (medicalExaminationRepository.existsById(id)) {
            medicalExaminationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
