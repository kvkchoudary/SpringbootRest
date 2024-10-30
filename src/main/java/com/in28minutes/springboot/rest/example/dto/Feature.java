package com.in28minutes.springboot.rest.example.dto;

import jakarta.persistence.Column;
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
public class Feature {
	
		  @Id
		  @GeneratedValue
		  int id;
		  
		  @NotNull
		  String abbreviation;
		  
		  @NotNull
		  String description;
		  
		  @NotNull
		  boolean purged;
		  
		  @NotNull
		  @Column(name = "dateoverlapmode")
		  String dateOverlapMode;
		  
		  @NotNull
		  boolean featurecontrol;
		  
		  @NotNull		 
		  boolean featurelocked;
		  
		  @NotNull
		  int applicationid;
}




