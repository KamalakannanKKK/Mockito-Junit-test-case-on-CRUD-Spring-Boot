package com.example.MockJunitTestCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.MockJunitTestCases.Controller.Controller;
import com.example.MockJunitTestCases.Entity.ZooAnimalData;
import com.example.MockJunitTestCases.Repository.ZooAnimalDataRepo;
import com.example.MockJunitTestCases.Service.ZooAnimalDataService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class MockJunitTestCasesApplicationTests {

	//Allow us to test Controller Logic
	private MockMvc mockmvc;

	//creating a mock object
	@Mock
	ZooAnimalDataService zooAnimalService;
	
	@Mock 
	ZooAnimalDataRepo repo;

	//injecting Mock dependencies
	@InjectMocks
	Controller controller;

	ZooAnimalData animal1 = new ZooAnimalData(1, "Seeta-Lion", "Carnivore", "Orange");
	ZooAnimalData animal2 = new ZooAnimalData(2, "akbar-Cheetah", "Carnivore", "pale Orange with black dots");
	ZooAnimalData animal3 = new ZooAnimalData(3, "elepant", "Herbivore", "black");

	@BeforeEach
	public void setUp() {
		
		//Allowing Mockito annotation like @Mock and @InjectMocks to be processed
		MockitoAnnotations.openMocks(this);
		
		//Simulate HTTP Request and test your controller without Starting a full Spring Application Context
		this.mockmvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	// Checking Size of Animal Data
	@Test
	void getAllAnimal() throws Exception {

		List<ZooAnimalData> animal = new ArrayList<ZooAnimalData>(Arrays.asList(animal1, animal2, animal3));

		// using When
		when(repo.findAll()).thenReturn(animal);
       // when(zooAnimalService.getAllAnimal()).thenReturn(animal);
		
		//Assertion
		List<ZooAnimalData> animalData=zooAnimalService.getAllAnimal();
		//assertThat(animalData.size()).isSameAs(3);
		assertThat(animalData).isNotNull();
		 assertEquals(animal,zooAnimalService.getAllAnimal());
		//COntorller Testing
		mockmvc.perform(MockMvcRequestBuilders.get("/getAll").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));

	}

	// Checking the Animal Name by passing Animal Id
	@Test
	public void getAnimalById() throws Exception {
		//using When
		when(zooAnimalService.getAnimalById(animal1.getId())).thenReturn(animal1);

		//Assertion
		String expected = zooAnimalService.getAnimalById(animal1.getId()).getAnimalName();
		assertThat(expected).isSameAs("Seeta-Lion");
		
		//Controller testing
		mockmvc.perform(MockMvcRequestBuilders.get("/get/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.animalName", is("Seeta-Lion")));

	}

	// Add Animal
	@Test
	public void addAnimal() throws Exception {
		ZooAnimalData animal4 = new ZooAnimalData(4, "camel", "Herbivore", "orange");

		//Mockito to return animal4 value when addAnimal method of the zooAnimalService mock
		when(zooAnimalService.addAnimal(animal4)).thenReturn(animal4);
		
		//Assertion
		ZooAnimalData animalData=zooAnimalService.addAnimal(animal4);
		assertThat(animalData).isNotNull();

		//Controller testing
		mockmvc.perform(MockMvcRequestBuilders.post("/addAnimal").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(animal4)))
				.andExpect(status().isOk());

	}

	// update Animal
	@Test
	public void updateMovie() throws Exception {
		ZooAnimalData animal4 = new ZooAnimalData(4, "camel", "Herbivore", "pale-orange");

		when(zooAnimalService.getAnimalById(animal4.getId())).thenReturn(animal4);
	    when(zooAnimalService.updateAnimalById(animal4.getId(), animal4)).thenReturn(animal4);
	    
	    //Assertion
		ZooAnimalData updatedanimalData=zooAnimalService.updateAnimalById(4, animal4);
		assertThat(updatedanimalData).isNotNull();
		
		//Controller Testing
		mockmvc.perform(MockMvcRequestBuilders.put("/update/4").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(animal4)))
				.andExpect(status().isOk());
		
	}

	// Delete Animal
	@Test
	public void DeleteMovie() throws Exception {
  
		//Cannot perform when due to return type of method is null
		
		//Controller Testing
		mockmvc.perform(MockMvcRequestBuilders.delete("/delete/{id}", 1)).andExpect(status().isOk());

		//Checking whether the method is called Exactly once.
		verify(zooAnimalService, times(1)).deleteAnimal(1);
	}

}
