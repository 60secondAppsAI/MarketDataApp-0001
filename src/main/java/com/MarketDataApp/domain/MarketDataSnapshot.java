package com.MarketDataApp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;

@Entity
@Table(name="market_data_snapshots")
@Getter @Setter @NoArgsConstructor
public class MarketDataSnapshot {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="market_data_snapshot_id")
	private Integer marketDataSnapshotId;
    
  	@Column(name="instrument_id")
	private Integer instrumentId;
    
  	@Column(name="exchange_id")
	private Integer exchangeId;
    
  	@Column(name="bid_price")
	private Double bidPrice;
    
  	@Column(name="ask_price")
	private Double askPrice;
    
  	@Column(name="last_price")
	private Double lastPrice;
    
  	@Column(name="captured_at")
	private Date capturedAt;
    

}
