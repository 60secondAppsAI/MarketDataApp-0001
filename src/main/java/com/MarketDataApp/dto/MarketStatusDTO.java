package com.MarketDataApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class MarketStatusDTO {

	private Integer marketStatusId;

	private Integer exchangeId;

	private String status;

	private Date changedAt;






}
