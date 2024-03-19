package com.example.MockJunitTestCases.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MockJunitTestCases.Entity.ZooAnimalData;

@Repository
public interface ZooAnimalDataRepo extends JpaRepository<ZooAnimalData, Long>{

}
