package com.in28minutes.springboot.rest.example.client;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import com.in28minutes.springboot.rest.example.configuration.FlmAddressUpdateConfig;
import com.in28minutes.springboot.rest.example.configuration.FlmAddressUpdateConstants;
import com.in28minutes.springboot.rest.example.exception.InternalServiceException;
import com.in28minutes.springboot.rest.example.exception.ResourceNotFoundException;
import com.in28minutes.springboot.rest.example.exception.RestTemplateException;
import com.in28minutes.springboot.rest.example.model.FlmAddressApiRequest;
import com.in28minutes.springboot.rest.example.model.FlmAddressApiResponse;
import com.in28minutes.springboot.rest.example.model.FlmAddressUpdateRequest;
import com.in28minutes.springboot.rest.example.model.TokenData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FlmAddressClient {

	@Autowired
	FlmAddressUpdateConfig config;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Gson gson;

	private String flmAddressApiTokenTimer = StringUtils.EMPTY;

	private String flmAddressApiOktaTokenValue = StringUtils.EMPTY;

	/**
	 * call the Address API and cleanse with input address and return cleansed
	 * address
	 * 
	 * @param flmAddressUpdateDataRequest
	 * @return FlmAddressApiResponse
	 * @throws InternalServiceException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FlmAddressApiResponse flmAddresscleansing(com.in28minutes.springboot.rest.example.model.FlmAddressUpdateRequest flmAddressUpdateDataRequest)
			throws InternalServiceException {
		FlmAddressApiRequest flmAddressRequest = buildRequest(flmAddressUpdateDataRequest);
		ResponseEntity<FlmAddressApiResponse> respEntity = null;
		FlmAddressApiResponse addressResponse = null;
		verifyToken();
		try {
			HttpHeaders header = new HttpHeaders();
			header.setBearerAuth(flmAddressApiOktaTokenValue);
			HttpEntity entity = new HttpEntity(flmAddressRequest, header);
			log.info("Flm-addressApi Request ::: " + gson.toJson(flmAddressUpdateDataRequest));
			log.info("Flm-AddressApi URL: " + config.getFlmAddressApiURL());
			respEntity = restTemplate.exchange(config.getFlmAddressApiURL(), HttpMethod.POST, entity,
					FlmAddressApiResponse.class);
			log.info("Flm-AddressApi Response ::: {}", gson.toJson(respEntity));
		} catch (HttpStatusCodeException errorMsg) {
			log.error(FlmAddressUpdateConstants.EXCEPTION_TAG,
					FlmAddressUpdateConstants.EXCEPTION_HTTPSTATUSCODEEXCEPTION + errorMsg.toString());
			throw new RestTemplateException(errorMsg.toString());
		} catch (ResourceNotFoundException errorMsg) {
			log.error(FlmAddressUpdateConstants.RESOURCENOTFOUNDEXCEPTION_LABEL + errorMsg.toString());
			throw new ResourceNotFoundException(FlmAddressUpdateConstants.RESOURCENOTFOUNDEXCEPTION_LABEL);
		} catch (Exception errorMsg) {
			log.error(FlmAddressUpdateConstants.EXCEPTION_TAG,
					FlmAddressUpdateConstants.EXCEPTION_LABEL + errorMsg.toString());
			throw new InternalServiceException(errorMsg.toString());
		}
		if (respEntity.getBody() != null) {
			addressResponse = respEntity.getBody();
		} else {
			log.error(FlmAddressUpdateConstants.EMPTY_RESPONSE_ADDRESS_API_TAG);
			throw new InternalServiceException(FlmAddressUpdateConstants.EMPTY_RESPONSE_ADDRESS_API_TAG);
		}
		log.info("Flm-AddressApi Response :::::::" + gson.toJson(addressResponse));
		return addressResponse;
	}

	/**
	 * buildRequest for addressApi request
	 * 
	 * @param flmAddressUpdateDataRequest
	 * @return FlmAddressApiRequest
	 */
	private FlmAddressApiRequest buildRequest(FlmAddressUpdateRequest flmAddressUpdateDataRequest) {

		return FlmAddressApiRequest.builder().address1(flmAddressUpdateDataRequest.getAddress1())
				.address2(flmAddressUpdateDataRequest.getAddress2()).address3(flmAddressUpdateDataRequest.getAddress3())
				.city(flmAddressUpdateDataRequest.getCity())
				.stateProvince(flmAddressUpdateDataRequest.getStateProvince())
				.postalCode(flmAddressUpdateDataRequest.getPostalCode())
				.countryCode(flmAddressUpdateDataRequest.getCountryCode())
				.vintage(flmAddressUpdateDataRequest.getVintage())
				.contactName(flmAddressUpdateDataRequest.getContactName())
				.firmName(flmAddressUpdateDataRequest.getFirmName()).build();
	}

	/**
	 * Generating the new token for Address api call
	 * 
	 * @return String
	 * @throws InternalServiceException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String getAddressApiOktaToken() throws InternalServiceException {
		TokenData response = new TokenData();
		response.setAccessToken(StringUtils.EMPTY);
		try {
			if (config.isOktaProxySettings()) {
				log.info(FlmAddressUpdateConstants.PROXY_SETTING_LABEL + config.getProxyHost()
						+ FlmAddressUpdateConstants.PORT_LABEL + config.getProxyPort());
				Properties props = System.getProperties();
				props.put(FlmAddressUpdateConstants.PROXYHOST, config.getProxyHost());
				props.put(FlmAddressUpdateConstants.PROXYPORT, config.getProxyPort());
			}
			log.info(FlmAddressUpdateConstants.OKTA_TOKEN_GENERATING_LABEL);
			String tokenUri = config.getAddressApiOktaIssuer() + FlmAddressUpdateConstants.OKTATOKEN_ISSUE
					+ config.getAddressApiScope();
			log.info(FlmAddressUpdateConstants.OKTATOKEN_LABEL + tokenUri);
			HttpHeaders headers = new HttpHeaders();
			headers.setBasicAuth(config.getAddressApiClientid(), config.getAddressApiClientSecret());
			HttpEntity request = new HttpEntity(headers);
			response = restTemplate.postForObject(tokenUri, request, TokenData.class);

		} catch (HttpStatusCodeException errorMsg) {
			log.error(FlmAddressUpdateConstants.EXCEPTION_IN_ADDRESS_API_TOKEN_SERVICE
					+ errorMsg.toString());
			throw new RestTemplateException(errorMsg.toString());
		} catch (RestClientException errorMsg) {
			log.error(FlmAddressUpdateConstants.REST_CLIENT_EXCEPTION + FlmAddressUpdateConstants.OKTA_TOKEN_ERROR
					+ errorMsg.toString());
			throw new RestTemplateException(errorMsg.toString());
		} catch (Exception errorMsg) {
			log.error(FlmAddressUpdateConstants.EXCEPTION_LABEL + FlmAddressUpdateConstants.OKTA_TOKEN_ERROR
					+ errorMsg.toString());
			throw new InternalServiceException(errorMsg.toString());
		}
		if (null != response && null != response.getAccessToken()) {
			log.info(FlmAddressUpdateConstants.OKTA_TOKEN_GENERATED_LABEL);
			return response.getAccessToken();
		} else {
			log.error(FlmAddressUpdateConstants.EMPTY_ACCESS_TOKEN_SERVICE);
			throw new InternalServiceException(FlmAddressUpdateConstants.EMPTY_ACCESS_TOKEN_SERVICE);
		}
	}

	/**
	 * verify the token expaire status
	 * 
	 * @param serviceName
	 * @throws FlmAdminInternalServiceException
	 */
	private boolean verifyToken() throws InternalServiceException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FlmAddressUpdateConstants.DATE_FORMAT_YYYY_MM_DD_HH);
		if (StringUtils.EMPTY.equalsIgnoreCase(flmAddressApiOktaTokenValue)) {
			flmAddressApiTokenTimer = dtf.format(LocalDateTime.now());
			log.info("Generating New token for Flm-address service: Time- " + flmAddressApiTokenTimer);
			flmAddressApiOktaTokenValue = getAddressApiOktaToken();
			return true;
		} else {
			boolean newtoken = oktaTokentimediff(flmAddressApiTokenTimer);
			if (newtoken) {
				flmAddressApiTokenTimer = dtf.format(LocalDateTime.now());
				log.info("Re-Generating New token for Flm-address service: Time-" + flmAddressApiTokenTimer);
				flmAddressApiOktaTokenValue = getAddressApiOktaToken();
			}
			log.info("using old token value for Flm-address service: Time-" + flmAddressApiTokenTimer);
			return true;
		}
	}

	/**
	 * token validation for 60 min
	 * 
	 * @param starttimer
	 * @return Boolean
	 */
	private Boolean oktaTokentimediff(String starttimer) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FlmAddressUpdateConstants.DATE_FORMAT_YYYY_MM_DD_HH);
			SimpleDateFormat format = new SimpleDateFormat(FlmAddressUpdateConstants.DATE_FORMAT_YY_MM_DD_HH);
			long diff = format.parse(dtf.format(LocalDateTime.now())).getTime() - format.parse(starttimer).getTime();
			long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
			if (60 < minutes) {
				return true;
			}
		} catch (ParseException e) {
			log.error(FlmAddressUpdateConstants.TIMERVALIDATION_ERROR);
			return false;
		}
		return false;
	}

}
