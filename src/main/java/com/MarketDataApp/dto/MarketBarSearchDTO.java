package com.MarketDataApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MarketBarSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer marketBarId;
	
	private Integer instrumentId;
	
	private Double openPrice;
	
	private Double highPrice;
	
	private Double lowPrice;
	
	private Double closePrice;
	
	private Double volume;
	
	private Date barTime;
	
}
