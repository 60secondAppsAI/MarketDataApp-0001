package com.MarketDataApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class MarketBarDTO {

	private Integer marketBarId;

	private Integer instrumentId;

	private Double openPrice;

	private Double highPrice;

	private Double lowPrice;

	private Double closePrice;

	private Double volume;

	private Date barTime;






}
