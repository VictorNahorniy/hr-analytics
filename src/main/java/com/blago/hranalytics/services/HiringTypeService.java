package com.blago.hranalytics.services;

import com.blago.hranalytics.models.HiringType;
import com.blago.hranalytics.repositories.HiringTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class HiringTypeService {
    @Autowired
    private HiringTypeRepository hiringTypeRepository;

    public List<HiringType> findAll() {
        return hiringTypeRepository.findAll();
    }

    public HiringType getByName(String name) throws SQLException {
        if(hiringTypeRepository.findHiringTypeByNameType(name).isPresent()){
            return hiringTypeRepository.findHiringTypeByNameType(name).get();
        }
        throw new SQLException("Hiring type not found");
    }

    public String getNameById(Integer hiringTypeId) {
        return hiringTypeRepository.findById(hiringTypeId).get().getNameType();
    }
}
