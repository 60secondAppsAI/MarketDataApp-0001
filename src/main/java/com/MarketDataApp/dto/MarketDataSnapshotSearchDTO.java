package com.MarketDataApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MarketDataSnapshotSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer marketDataSnapshotId;
	
	private Integer instrumentId;
	
	private Integer exchangeId;
	
	private Double bidPrice;
	
	private Double askPrice;
	
	private Double lastPrice;
	
	private Date capturedAt;
	
}
