package com.in28minutes.springboot.rest.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.dao.controldao;
import com.in28minutes.springboot.rest.example.dao.featuredao;
import com.in28minutes.springboot.rest.example.dao.vasuidao;
import com.in28minutes.springboot.rest.example.dto.Application;
import com.in28minutes.springboot.rest.example.dto.Control;
import com.in28minutes.springboot.rest.example.dto.Feature;

@Service
public class vasuiserviceimpl implements vasuiservice{
	
	@Autowired
	vasuidao vasuidao;	
	
	@Autowired
	controldao controlVasuidao;	
	
	@Autowired
	featuredao featureVasuidao;
	
	
	public List<Application> findAll(){
		return vasuidao.findAll();
	}
	
	public List<Control> getallcontrols(){
		return controlVasuidao.findAll();
	}
	
	public List<Feature> getallfeatures(){
		return featureVasuidao.findAll();
	}

}
