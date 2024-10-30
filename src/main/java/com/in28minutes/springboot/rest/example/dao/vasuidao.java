package com.in28minutes.springboot.rest.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.dto.Application;

@Repository
public interface vasuidao extends JpaRepository<Application, Integer>{

}
