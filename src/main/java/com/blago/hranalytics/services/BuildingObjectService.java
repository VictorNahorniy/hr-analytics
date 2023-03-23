package com.blago.hranalytics.services;

import com.blago.hranalytics.models.BuildingObject;
import com.blago.hranalytics.repositories.BuildingObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingObjectService {

    @Autowired
    private BuildingObjectRepository repository;

    public BuildingObject saveBuildingObject(BuildingObject buildingObject) {
        return repository.save(buildingObject);
    }

    public List<BuildingObject> getAll() {
        return repository.findAll();
    }

    public BuildingObject getById(Integer id) {
        return repository.findById(id).get();
    }

    public void deleteById(Integer id){
        repository.deleteById(id);
    }

}
