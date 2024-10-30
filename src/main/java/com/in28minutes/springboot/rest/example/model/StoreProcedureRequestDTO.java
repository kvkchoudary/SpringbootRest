package com.in28minutes.springboot.rest.example.model;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class StoreProcedureRequestDTO {
	
	private String csgTid;
	private long csgShp;
	private String csgTyp;
	private String csgLbl;
	private String csgSvc;
	private String csgSource;
	private long csgCid;
	private String csgCntc;
	private String csgNam;
	private String csgIdq;
	private String csgIdc;
	private String csgAd1;
	private String csgAd2;
	private String csgCty;
	private String csgSta;
	private String csgZip;
	private int csgCtr;
	private String csgPhone;
	private String csgStore;
	private String csgAur;
	private String csgEid;
	private String csgPds;
	private String csgPci;
	private String csgPcs;
	private int csgTerm;
	private Timestamp csgDate;
	private String rawAdrId;
	private String shareId;
	private String busResInd;
	private int shareConf;
	private String addrType;
	private String sourceMap;
	private long shpr_nbr_2;
	private String tssEndQ;
	private String busResCls;

}
