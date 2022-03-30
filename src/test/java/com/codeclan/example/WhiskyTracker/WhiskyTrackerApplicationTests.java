package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	DistilleryRepository distilleryRepository;

	@Autowired
	WhiskyRepository whiskyRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canFindWhiskyByYear(){
		List<Whisky> found = whiskyRepository.findWhiskiesByYear(1995);
		assertEquals(1, found.size());
	}

	@Test
	public void canFindByRegion(){
		List<Distillery> foundDistillery = distilleryRepository.findByRegion("Speyside");
		assertEquals(3, foundDistillery.size());
	}

	@Test
	public void canFindWhiskyFromSpecificAge(){
		Distillery foundDistillery = distilleryRepository.getById(1L);
		List<Whisky> foundWhisky = whiskyRepository.findByAgeAndDistilleryName(15, foundDistillery.getName());
		assertEquals(1, foundWhisky.size());
	}


}
