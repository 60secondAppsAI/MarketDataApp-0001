package com.MarketDataApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseMarketStatusDTO {

	private Integer ownerId;

	private Integer marketStatusId;

	private Integer marketStatusStatus;
	
	private Integer nextOrPrevious;
}

