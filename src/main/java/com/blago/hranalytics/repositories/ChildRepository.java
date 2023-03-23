package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.Child;
import com.blago.hranalytics.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChildRepository extends JpaRepository<Child, Integer> {

    List<Child> findChildByEmployeeId(Integer parentId);
}
