package com.blago.hranalytics.services;

import com.blago.hranalytics.models.Child;
import com.blago.hranalytics.repositories.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChildService {

    @Autowired
    private ChildRepository childRepository;

    public boolean save(Child child) {
        childRepository.save(child);
        return true;
    }

    public Optional<Child> getById(Integer id) {
        return childRepository.findById(id);
    }
    public List<Child> getChildrenByParentId(Integer parentId) {
        return childRepository.findChildByEmployeeId(parentId);
    }

    public void deleteById(Integer id) {
        childRepository.deleteById(id);
    }
}
