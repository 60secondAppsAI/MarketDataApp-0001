package com.MarketDataApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MarketQuoteSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer marketQuoteId;
	
	private Integer instrumentId;
	
	private Double bidPrice;
	
	private Double bidSize;
	
	private Double askPrice;
	
	private Double askSize;
	
	private Date quotedAt;
	
}
