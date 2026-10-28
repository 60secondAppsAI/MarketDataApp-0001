package com.MarketDataApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MarketStatusSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer marketStatusId;
	
	private Integer exchangeId;
	
	private String status;
	
	private Date changedAt;
	
}
