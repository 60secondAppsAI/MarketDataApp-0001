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
@Table(name="market_bars")
@Getter @Setter @NoArgsConstructor
public class MarketBar {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="market_bar_id")
	private Integer marketBarId;
    
  	@Column(name="instrument_id")
	private Integer instrumentId;
    
  	@Column(name="open_price")
	private Double openPrice;
    
  	@Column(name="high_price")
	private Double highPrice;
    
  	@Column(name="low_price")
	private Double lowPrice;
    
  	@Column(name="close_price")
	private Double closePrice;
    
  	@Column(name="volume")
	private Double volume;
    
  	@Column(name="bar_time")
	private Date barTime;
    

}
