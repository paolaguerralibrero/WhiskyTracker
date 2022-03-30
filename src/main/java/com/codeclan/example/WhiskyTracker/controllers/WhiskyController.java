package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;


    @GetMapping(value="/whiskies")
    public ResponseEntity<List<Whisky>> findWhiskiesFilteredByYear(
            @RequestParam(name="year", required = false) Integer year,
            @RequestParam(name="distilleryName", required = false) String distName,
            @RequestParam(name="age", required = false) Integer age,
            @RequestParam(name="distilleryRegion", required = false) String distRegion
            ){
        if(year != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
        }
        if (distName != null && age != null) {
            List<Whisky> foundWhisky = whiskyRepository.findByAgeAndDistilleryName(age, distName);
            return new ResponseEntity<>(foundWhisky, HttpStatus.OK);
        }
        if(distRegion != null) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(distRegion), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }


}
