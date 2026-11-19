package com.MarketDataApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseMarketBarDTO {

	private Integer ownerId;

	private Integer marketBarId;

	private Integer marketBarStatus;
	
	private Integer nextOrPrevious;
}

