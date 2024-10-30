package com.in28minutes.springboot.rest.example.service;

import java.util.List;

import com.in28minutes.springboot.rest.example.dto.Application;
import com.in28minutes.springboot.rest.example.dto.Control;
import com.in28minutes.springboot.rest.example.dto.Feature;

public interface vasuiservice{	
	
	List<Application> findAll();
	List<Feature> getallfeatures();
	List<Control> getallcontrols();
}

