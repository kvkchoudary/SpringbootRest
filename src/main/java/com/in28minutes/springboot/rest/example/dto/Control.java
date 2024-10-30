package com.in28minutes.springboot.rest.example.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Control {

		  @Id
		  @GeneratedValue
		  int id;	
		  
		  @NotNull
		  String entity;	
		  
		  @NotNull
		  String val;
		  
		  @NotNull
		  String startdate;
		  
		  @NotNull
		  String enddate;
		  
		  @NotNull
		  boolean purged;
		  
		  @NotNull
		  int featureid;
		 
}


