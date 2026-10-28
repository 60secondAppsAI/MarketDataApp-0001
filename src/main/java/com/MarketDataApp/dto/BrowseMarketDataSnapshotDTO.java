package com.MarketDataApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseMarketDataSnapshotDTO {

	private Integer ownerId;

	private Integer marketDataSnapshotId;

	private Integer marketDataSnapshotStatus;
	
	private Integer nextOrPrevious;
}

