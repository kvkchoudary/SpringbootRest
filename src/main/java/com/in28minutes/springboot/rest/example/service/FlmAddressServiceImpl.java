package com.in28minutes.springboot.rest.example.service;

import org.apache.commons.lang3.StringUtils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.rest.example.client.FlmAddressClient;
import com.in28minutes.springboot.rest.example.dao.FlmAddressUpadteDao;
import com.in28minutes.springboot.rest.example.exception.InternalServiceException;
import com.in28minutes.springboot.rest.example.model.FlmAddressApiResponse;
import com.in28minutes.springboot.rest.example.model.FlmAddressUpdateRequest;
import com.in28minutes.springboot.rest.example.model.FlmAddressUpdateResponse;

@Service
public class FlmAddressServiceImpl implements FlmAddressService {

	@Autowired
	FlmAddressClient flmAddressApiClient;

	@Autowired
	FlmAddressUpadteDao flmAddressUpadteDao;

	/**
	 * Call the Address api and cleanse the input address and return cleansed
	 * address calling DIST_RDS010F stored procedure.
	 */
	@Override
	public FlmAddressUpdateResponse flmAddressUpdateData(FlmAddressUpdateRequest flmAddressUpdateDataRequest,
			FlmAddressUpdateResponse response) throws InternalServiceException {
		FlmAddressApiResponse addressresponse = flmAddressApiClient.flmAddresscleansing(flmAddressUpdateDataRequest);

		if (flmAddressUpadteDao.callStoredPorc(addressresponse, flmAddressUpdateDataRequest)) {
			response.setStopId(addressresponse.getMatchedStop().getStopId());
			response.setStopIdType(addressresponse.getMatchedStop().getStopIdType());
			response.setAddressLine1(StringUtils.isNotBlank(addressresponse.getAddressInformation().getAddress1())
					? addressresponse.getAddressInformation().getAddress1()
					: StringUtils.EMPTY);
			response.setAddressLine2(StringUtils.isNotBlank(addressresponse.getAddressInformation().getAddress2())
					? addressresponse.getAddressInformation().getAddress2()
					: StringUtils.EMPTY);
			response.setAddressLine3(StringUtils.isNotBlank(addressresponse.getAddressInformation().getAddress3())
					? addressresponse.getAddressInformation().getAddress3()
					: StringUtils.EMPTY);
			response.setCity(StringUtils.isNotBlank(addressresponse.getAddressInformation().getCity())
					? addressresponse.getAddressInformation().getCity()
					: StringUtils.EMPTY);
			response.setState(StringUtils.isNotBlank(addressresponse.getAddressInformation().getStateProvince())
					? addressresponse.getAddressInformation().getStateProvince()
					: StringUtils.EMPTY);
			if ("840".equalsIgnoreCase(flmAddressUpdateDataRequest.getCountryCode())) {
				response.setZipCode(StringUtils.isNotBlank(addressresponse.getAddressInformation().getZip5())
						? addressresponse.getAddressInformation().getZip5().trim()
						: StringUtils.EMPTY);
			} else {
				response.setZipCode(StringUtils.isNotBlank(addressresponse.getAddressInformation().getPostalCode())
						? addressresponse.getAddressInformation().getPostalCode().trim()
						: StringUtils.EMPTY);
			}
		}
		return response;
	}
}
