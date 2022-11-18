package com.example.demo.repository;

import com.example.demo.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//This repository holds our car records using JPA

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {



}
