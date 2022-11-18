package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//HTTP requests are handled by our controller

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {
    //https://www.bezkoder.com/angular-11-spring-boot-crud/


    @Autowired
    private CarRepository carRepository;

    //get a list of all cars
    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    //create a car
    @PostMapping("/cars")
    public Car createCar(@RequestBody Car car) {return carRepository.save(car);
    }

    //delete a car by id
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCar(@PathVariable Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car by id" + id + "not found"));

        carRepository.delete(car);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //get car by id
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Car by id" + id + "not found"));
        return ResponseEntity.ok(car);
//        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    //update car by id
    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails){
        Car car = carRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Car not found:" + id));

        car.setBrand(carDetails.getBrand());
        car.setModel(carDetails.getModel());
        car.setFirstRegistration(carDetails.getFirstRegistration());
        car.setLocation(carDetails.getLocation());
        car.setImageUrl(carDetails.getImageUrl());

        Car updatedCar = carRepository.save(car);
        return ResponseEntity.ok(updatedCar);



    }
}
