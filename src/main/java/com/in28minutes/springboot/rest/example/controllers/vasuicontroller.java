package com.in28minutes.springboot.rest.example.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.rest.example.dto.Application;
import com.in28minutes.springboot.rest.example.dto.Control;
import com.in28minutes.springboot.rest.example.dto.Feature;
import com.in28minutes.springboot.rest.example.service.vasuiservice;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class vasuicontroller {
	
	
	@Autowired
	vasuiservice vasuiservice;
	
	@GetMapping(path="/application")
	public ResponseEntity<List<Application>> applicationsList(){		
		
		List<Application>  applist = vasuiservice.findAll();
		
		return new ResponseEntity<>(applist,HttpStatus.OK);		  
		
	}
	
	@GetMapping(path="/control")
	public ResponseEntity<List<Control>> controllist(){		
		
		List<Control>  applist = vasuiservice.getallcontrols();
		
		return new ResponseEntity<>(applist,HttpStatus.OK);		  
		
	}
	
	
	@GetMapping(path="/feature")
	public ResponseEntity<List<Feature>> featurelist(){		
		
		List<Feature>  applist = vasuiservice.getallfeatures();
		
		return new ResponseEntity<>(applist,HttpStatus.OK);
		  
		
	}

}
