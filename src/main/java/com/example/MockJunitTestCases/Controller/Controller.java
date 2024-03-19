package com.example.MockJunitTestCases.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MockJunitTestCases.Entity.ZooAnimalData;
import com.example.MockJunitTestCases.Service.ZooAnimalDataService;

@RestController
public class Controller {

	@Autowired
	ZooAnimalDataService zooAnimalService;
	
	@GetMapping("/getAll")
	public List<ZooAnimalData> getAllAnimal() {
		return this.zooAnimalService.getAllAnimal();
	}
	
	@PostMapping("/addAnimal")
	public ZooAnimalData saveAnimal(@RequestBody ZooAnimalData zooAnimal ) {
		return this.zooAnimalService.addAnimal(zooAnimal);
	}
	
	@GetMapping("/get/{id}")
	public ZooAnimalData getAnimalById(@PathVariable long id) {
		return this.zooAnimalService.getAnimalById(id);
	}
	
	@PutMapping("/update/{id}")
	public ZooAnimalData updateAnimal(@PathVariable long id, @RequestBody ZooAnimalData zooAnimal) {
		return this.zooAnimalService.updateAnimalById(id,zooAnimal);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteAnimal(@PathVariable long id) {
		this.zooAnimalService.deleteAnimal(id);
	}
}
