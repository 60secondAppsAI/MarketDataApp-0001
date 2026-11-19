package com.MarketDataApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseMarketQuoteDTO {

	private Integer ownerId;

	private Integer marketQuoteId;

	private Integer marketQuoteStatus;
	
	private Integer nextOrPrevious;
}

