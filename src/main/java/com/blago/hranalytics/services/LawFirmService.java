package com.blago.hranalytics.services;

import com.blago.hranalytics.models.LawFirm;
import com.blago.hranalytics.repositories.LawFirmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LawFirmService {
    @Autowired
    private LawFirmRepository repository;


    public List<LawFirm> findAll() {
        return repository.findAll();
    }

    public void save(LawFirm lawFirm) {
        repository.save(lawFirm);
    }

    public LawFirm findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public String getNameById(Integer lawFirmId) {
        return repository.findLawFirmNameById(lawFirmId);
    }
}
