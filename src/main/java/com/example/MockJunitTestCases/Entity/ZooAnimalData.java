package com.example.MockJunitTestCases.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ZooAnimalData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String animalName;
	private String animalType;
	private String animalColor;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAnimalName() {
		return animalName;
	}
	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}
	public String getAnimalType() {
		return animalType;
	}
	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}
	public String getAnimalColor() {
		return animalColor;
	}
	public void setAnimalColor(String animalColor) {
		this.animalColor = animalColor;
	}

	public ZooAnimalData(long id, String animalName, String animalType, String animalColor) {
		super();
		this.id = id;
		this.animalName = animalName;
		this.animalType = animalType;
		this.animalColor = animalColor;
	}
	public ZooAnimalData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
