package com.in28minutes.springboot.rest.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.dto.Control;

@Repository
public interface controldao extends JpaRepository<Control, Integer>{

}
