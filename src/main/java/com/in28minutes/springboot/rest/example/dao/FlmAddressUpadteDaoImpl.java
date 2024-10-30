package com.in28minutes.springboot.rest.example.dao;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.configuration.FlmAddressUpdateConstants;
import com.in28minutes.springboot.rest.example.exception.InternalServiceException;
import com.in28minutes.springboot.rest.example.model.FlmAddressApiResponse;
import com.in28minutes.springboot.rest.example.model.FlmAddressUpdateRequest;
import com.in28minutes.springboot.rest.example.model.StoreProcedureRequestDTO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class FlmAddressUpadteDaoImpl implements FlmAddressUpadteDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Calling DIST_RDS010F stored procedure with cleansed address
	 * 
	 * @param starttimer
	 * @return Boolean
	 * @throws FlmAdminInternalServiceException
	 */
	@Override
	public boolean callStoredPorc(FlmAddressApiResponse respEntity, FlmAddressUpdateRequest flmAddressUpdateDataRequest)
			throws InternalServiceException {
		log.info("Call Stored procedure method Started ::: TrackID:" + flmAddressUpdateDataRequest.getTrackId());
		StoreProcedureRequestDTO storeProcData = dataMapping(respEntity, flmAddressUpdateDataRequest);
		List<SqlParameter> declaredParameters = buildSQlParameter();
		int returncode = 1;
		try {
			Map<String, Object> status = jdbcTemplate.call(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection con) throws SQLException {
					CallableStatement cstmt = con.prepareCall(FlmAddressUpdateConstants.DISTRDSCALLSQL);
					buildStoreProcRequest(cstmt, storeProcData);
					return cstmt;
				}
			}, declaredParameters);
			returncode = status.isEmpty() ? 1 : Integer.parseInt(status.get("#update-count-1").toString());
			log.info("Stored Proc Request Data : {}" , storeProcData.toString());
		} catch (Exception errorMsg) {
			log.error(FlmAddressUpdateConstants.EXCEPTION_SQL + errorMsg.toString());
			throw new InternalServiceException(FlmAddressUpdateConstants.EXCEPTION_SQL);
		}
		if (returncode == 0) {
			log.info("***** Stored procedure Executed successfully ****" + "TrackID:"
					+ flmAddressUpdateDataRequest.getTrackId());
			return true;
		} else {
			log.error(FlmAddressUpdateConstants.EXCEPTION_SQL_SP);
			throw new InternalServiceException(FlmAddressUpdateConstants.EXCEPTION_SQL_SP);
		}
	}

	/**
	 * build Store Procedure Request
	 * 
	 * @param cstmt
	 * @param storeProcData
	 * @return CallableStatement
	 * @throws SQLException
	 */
	public CallableStatement buildStoreProcRequest(CallableStatement cstmt, StoreProcedureRequestDTO storeProcData)
			throws SQLException {
		log.info("buildStoreProcStatement Data");
		cstmt.setLong("@CSGSHP", storeProcData.getCsgShp());
		cstmt.setString("@CSGTYP", storeProcData.getCsgTyp());
		cstmt.setString("@CSGTID", storeProcData.getCsgTid());
		cstmt.setString("@CSGLBL", storeProcData.getCsgLbl());
		cstmt.setString("@CSGSVC", storeProcData.getCsgSvc());
		cstmt.setString("@CSGSOURCE", storeProcData.getCsgSource());
		cstmt.setLong("@CSGCID", storeProcData.getCsgCid());
		cstmt.setString("@CSGCNTC", storeProcData.getCsgCntc());
		cstmt.setString("@CSGNAM", storeProcData.getCsgNam());
		cstmt.setString("@CSGIDQ", storeProcData.getCsgIdq());
		cstmt.setString("@CSGIDC", storeProcData.getCsgIdc());
		cstmt.setString("@CSGAD1", storeProcData.getCsgAd1());
		cstmt.setString("@CSGAD2", storeProcData.getCsgAd2());
		cstmt.setString("@CSGCTY", storeProcData.getCsgCty());
		cstmt.setString("@CSGSTA", storeProcData.getCsgSta());
		cstmt.setString("@CSGZIP", storeProcData.getCsgZip());
		cstmt.setInt("@CSGCTR", storeProcData.getCsgCtr());
		cstmt.setString("@CSGPHONE", storeProcData.getCsgPhone());
		cstmt.setString("@CSGSTORE", storeProcData.getCsgStore());
		cstmt.setString("@CSGAUR", storeProcData.getCsgAur());
		cstmt.setString("@CSGEID", storeProcData.getCsgEid());
		cstmt.setString("@CSGPDS", storeProcData.getCsgPds());
		cstmt.setString("@CSGPCI", storeProcData.getCsgPci());
		cstmt.setString("@CSGPCS", storeProcData.getCsgPcs());
		cstmt.setInt("@CSGTERM", storeProcData.getCsgTerm());
		cstmt.setTimestamp("@CSGDATE", storeProcData.getCsgDate());
		cstmt.setString("@RAWADRID", storeProcData.getRawAdrId());
		cstmt.setString("@SHAREID", storeProcData.getShareId());
		cstmt.setString("@BUSRESIND", storeProcData.getBusResInd());
		cstmt.setInt("@SHARECONF", storeProcData.getShareConf());
		cstmt.setString("@ADDRTYPE", storeProcData.getAddrType());
		cstmt.setString("@SOURCEMAP", storeProcData.getSourceMap());
		cstmt.setLong("@SHPR_NBR_2", storeProcData.getShpr_nbr_2());
		cstmt.setString("@TSSEND#Q", storeProcData.getTssEndQ());
		cstmt.setString("@BUSRESCLS", storeProcData.getBusResCls());
		return cstmt;
	}

	/**
	 * build SQlParameter request
	 * 
	 * @return List<SqlParameter>
	 */
	private List<SqlParameter> buildSQlParameter() {
		List<SqlParameter> declaredParameters = new ArrayList<>();
		declaredParameters.add(new SqlParameter("@CSGTYP", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGTID", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGLBL", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGSVC", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGSOURCE", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGCID", Types.DOUBLE));
		declaredParameters.add(new SqlParameter("@CSGCNTC", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGNAM", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGIDQ", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGIDC", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGAD1", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGAD2", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGCTY", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGSTA", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGZIP", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGCTR", Types.INTEGER));
		declaredParameters.add(new SqlParameter("@CSGPHONE", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGSTORE", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGAUR", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGEID", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGPDS", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGPCI", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGPCS", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@CSGTERM", Types.INTEGER));
		declaredParameters.add(new SqlParameter("@CSGDATE", Types.TIMESTAMP));
		declaredParameters.add(new SqlParameter("@RAWADRID", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@SHAREID", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@BUSRESIND", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@SHARECONF", Types.INTEGER));
		declaredParameters.add(new SqlParameter("@ADDRTYPE", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@SOURCEMAP", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@SHPR_NBR_2", Types.DOUBLE));
		declaredParameters.add(new SqlParameter("@TSSEND#Q", Types.VARCHAR));
		declaredParameters.add(new SqlParameter("@BUSRESCLS", Types.VARCHAR));
		return declaredParameters;
	}

	/**
	 * data Mapping for StoreProcedureRequestDTO
	 * 
	 * @param respEntity
	 * @param flmAddressUpdateDataRequest
	 * @return StoreProcedureRequestDTO
	 */
	private StoreProcedureRequestDTO dataMapping(FlmAddressApiResponse respEntity,
			FlmAddressUpdateRequest flmAddressUpdateDataRequest) {
		log.info("build Data for store proc");
		StoreProcedureRequestDTO spRequestData = new StoreProcedureRequestDTO();
		spRequestData.setCsgTid(flmAddressUpdateDataRequest.getTrackId());
		spRequestData.setCsgSource("AU");
		spRequestData.setCsgCntc(respEntity.getAddressInformation().getContactName());
		spRequestData.setCsgNam(respEntity.getAddressInformation().getFirmName());
		spRequestData.setCsgAd1(respEntity.getAddressInformation().getAddress1());
		spRequestData.setCsgAd2(respEntity.getAddressInformation().getAddress2());
		spRequestData.setCsgCty(respEntity.getAddressInformation().getCity());
		spRequestData.setCsgSta(respEntity.getAddressInformation().getStateProvince());
		if ("840".equalsIgnoreCase(flmAddressUpdateDataRequest.getCountryCode())) {
			spRequestData.setCsgZip(StringUtils.isNotBlank(respEntity.getAddressInformation().getZip5())
					? respEntity.getAddressInformation().getZip5().trim()
					: StringUtils.EMPTY);
		} else {
			spRequestData.setCsgZip(StringUtils.isNotBlank(respEntity.getAddressInformation().getPostalCode())
					? respEntity.getAddressInformation().getPostalCode().trim()
					: StringUtils.EMPTY);
		}
		spRequestData.setCsgCtr(Integer.parseInt(respEntity.getAddressInformation().getCountryCode()));
		spRequestData.setCsgTerm(flmAddressUpdateDataRequest.getPicsTerminal());
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		spRequestData.setCsgDate(time);
		return spRequestData;
	}

}
