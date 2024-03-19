package com.example.MockJunitTestCases.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MockJunitTestCases.Entity.ZooAnimalData;
import com.example.MockJunitTestCases.Repository.ZooAnimalDataRepo;

@Service
public class ZooAnimalDataService {

	@Autowired
	ZooAnimalDataRepo zooRepo;
	
	public ZooAnimalData addAnimal(ZooAnimalData zooAnimal) {
		return this.zooRepo.save(zooAnimal);
	}
	
	public List<ZooAnimalData> getAllAnimal(){
		return this.zooRepo.findAll();
	}
	
	public ZooAnimalData getAnimalById(long id) {
		return this.zooRepo.findById(id).get();
	}
	
	public void deleteAnimal(long id) {
		 this.zooRepo.deleteById(id);
	}

	public ZooAnimalData updateAnimalById(long id, ZooAnimalData zooAnimal) {
		ZooAnimalData old=zooRepo.findById(id).get();
		old.setAnimalColor(zooAnimal.getAnimalColor());
		old.setAnimalName(zooAnimal.getAnimalName());
		old.setAnimalType(zooAnimal.getAnimalName());
		return this.zooRepo.save(old);
	}
}
