package com.in28minutes.springboot.rest.example.dao;

import com.in28minutes.springboot.rest.example.exception.InternalServiceException;
import com.in28minutes.springboot.rest.example.model.FlmAddressApiResponse;
import com.in28minutes.springboot.rest.example.model.FlmAddressUpdateRequest;

public interface FlmAddressUpadteDao {

	public boolean callStoredPorc(FlmAddressApiResponse respEntity,
			FlmAddressUpdateRequest flmAddressUpdateDataRequest) throws InternalServiceException;

}
