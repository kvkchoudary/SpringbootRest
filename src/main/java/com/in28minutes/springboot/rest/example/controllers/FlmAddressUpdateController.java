package com.in28minutes.springboot.rest.example.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.in28minutes.springboot.rest.example.exception.ErrorMessage;
import com.in28minutes.springboot.rest.example.exception.InternalServiceException;
import com.in28minutes.springboot.rest.example.model.FlmAddressUpdateRequest;
import com.in28minutes.springboot.rest.example.model.FlmAddressUpdateResponse;
import com.in28minutes.springboot.rest.example.service.FlmAddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class FlmAddressUpdateController {

	@Autowired
	FlmAddressService flmAddressService;

	@Autowired
	Gson gson;

	/**
	 * Address Update API will cleanse the input address and update the address in
	 * Vision system by calling DIST_RDS010F stored procedure.
	 * 
	 * @param flmAddressUpdateDataRequest
	 * @return FlmAddressUpdateResponse
	 * @throws InternalServiceException
	 */
	@Operation(description = " Address Update API will cleanse the input address and update the address in Vision system by calling DIST_RDS010F stored procedure.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Request completed successfully.", content = {
					@Content(array = @ArraySchema(schema = @Schema(implementation = FlmAddressUpdateResponse.class))) }),
			@ApiResponse(responseCode = "400", description = "Bad request.", content = {
					@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessage.class))) }),
			@ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = {
					@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessage.class))) }),
			@ApiResponse(responseCode = "405", description = "Method Not Allowed.", content = {
					@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessage.class))) }),
			@ApiResponse(responseCode = "401", description = "Unauthorized."),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessage.class))) }) })
	@PostMapping(value = "/addressupdate")
	public ResponseEntity<FlmAddressUpdateResponse> addressUpdate(
			@RequestBody @Valid FlmAddressUpdateRequest flmAddressUpdateDataRequest) throws InternalServiceException {
		log.info("Initial request : " + gson.toJson(flmAddressUpdateDataRequest));
		FlmAddressUpdateResponse response = FlmAddressUpdateResponse.builder().build();
		response = this.flmAddressService.flmAddressUpdateData(flmAddressUpdateDataRequest, response);
		log.info("Final Response : {}", gson.toJson(response));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
