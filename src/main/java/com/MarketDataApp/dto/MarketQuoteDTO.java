package com.MarketDataApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class MarketQuoteDTO {

	private Integer marketQuoteId;

	private Integer instrumentId;

	private Double bidPrice;

	private Double bidSize;

	private Double askPrice;

	private Double askSize;

	private Date quotedAt;






}
