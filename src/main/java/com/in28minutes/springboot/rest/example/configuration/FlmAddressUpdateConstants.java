package com.in28minutes.springboot.rest.example.configuration;

public class FlmAddressUpdateConstants {

	private FlmAddressUpdateConstants() {

	}

	public static final String DISTRDSCALLSQL = "CALL DIST_RDS010F(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String EXCEPTION_LABEL = "** Exception: ";
	public static final String RESOURCENOTFOUNDEXCEPTION_LABEL = "ResourceNotFoundException";
	public static final String REST_CLIENT_EXCEPTION = "** Rest Client Exception in payload";
	public static final String EXCEPTION_IN_ADDRESS_API_TOKEN_SERVICE = "** Exception in Address Api okta token Service   ";
	public static final String EMPTY_ACCESS_TOKEN_SERVICE = "OKTA token values Empty";
	public static final String DATE_FORMAT_YYYY_MM_DD_HH = "yyyy/MM/dd HH:mm:ss";
	public static final String DATE_FORMAT_YY_MM_DD_HH = "yy/MM/dd HH:mm:ss";
	public static final String BAD_REQUEST_400 = "400 Bad Request";
	public static final String PROXYHOST = "https.proxyHost";
	public static final String PROXYPORT = "https.proxyPort";
	public static final String OKTATOKEN_ISSUE = "/v1/token"
			+ "?grant_type=client_credentials&response_type=token&scope=";
	public static final String TIMERVALIDATION_ERROR = "okta token validation failure in date format";
	public static final String OKTA_TOKEN_ERROR = "Okta Token service failure ";
	public static final String EXCEPTION_TAG = "Not able connect to Address api due to {}";
	public static final String EMPTY_RESPONSE_ADDRESS_API_TAG = "Empty Data Received from FLM-Address-APi";
	public static final String JSON_ERROR = "JSON parse error:";
	public static final String REQUEST_MISSING_ERROR = "request body is missing:";
	public static final String EXCEPTION_HTTPSTATUSCODEEXCEPTION = "HttpStatusCodeException";
	public static final String EXCEPTION_SQL = "SQL Exception while processing Stored procedure ";
	public static final String EXCEPTION_SQL_SP = "SQL Exception : Stored procedure not executed";
	public static final String EXCEPTION_SQL_CON = "SQL Exception while closing the connection";
	public static final String PROXY_SETTING_LABEL = "Proxy setting added- host:";
	public static final String PORT_LABEL = " :::: Port:";
	public static final String OKTA_TOKEN_GENERATING_LABEL = "Generating New token for Okta Token service";
	public static final String OKTATOKEN_LABEL = "Okta token Endpoint:";
	public static final String OKTA_TOKEN_GENERATED_LABEL = "New token generated from Okta Token service ";
	public static final String ERROR_LABEL = "Error";
	public static final String REQUEST_METHOD_LABLE = "Request method";
	public static final String NOT_SUPPORTED_LABEL = "not supported";
	public static final String VINTAGE_LABEL = "Vintage";
	public static final String VINTAGE_ERROR = "Data does not pass validation : Provided vintage is not supported";

}
