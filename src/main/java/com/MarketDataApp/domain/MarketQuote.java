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
@Table(name="market_quotes")
@Getter @Setter @NoArgsConstructor
public class MarketQuote {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="market_quote_id")
	private Integer marketQuoteId;
    
  	@Column(name="instrument_id")
	private Integer instrumentId;
    
  	@Column(name="bid_price")
	private Double bidPrice;
    
  	@Column(name="bid_size")
	private Double bidSize;
    
  	@Column(name="ask_price")
	private Double askPrice;
    
  	@Column(name="ask_size")
	private Double askSize;
    
  	@Column(name="quoted_at")
	private Date quotedAt;
    

}
