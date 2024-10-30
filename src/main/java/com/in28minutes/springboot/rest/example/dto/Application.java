package com.in28minutes.springboot.rest.example.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {
	@Id
	@GeneratedValue
	int number;
	
	@NotNull
	String abbreviation;
	
	@NotNull
	String description;
	
	@NotNull
	boolean purged;	
}
