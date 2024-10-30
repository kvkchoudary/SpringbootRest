package com.in28minutes.springboot.rest.example.service;

import com.in28minutes.springboot.rest.example.exception.InternalServiceException;
import com.in28minutes.springboot.rest.example.model.FlmAddressUpdateRequest;
import com.in28minutes.springboot.rest.example.model.FlmAddressUpdateResponse;

public interface FlmAddressService {

	public FlmAddressUpdateResponse flmAddressUpdateData(FlmAddressUpdateRequest flmAddressUpdateDataRequest,
			FlmAddressUpdateResponse response) throws InternalServiceException;
}
