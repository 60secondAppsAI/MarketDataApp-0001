package com.MarketDataApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.MarketDataApp.domain.MarketQuote;
import com.MarketDataApp.dto.MarketQuoteDTO;
import com.MarketDataApp.dto.MarketQuoteSearchDTO;
import com.MarketDataApp.dto.MarketQuotePageDTO;
import com.MarketDataApp.dto.MarketQuoteConvertCriteriaDTO;
import com.MarketDataApp.service.GenericService;
import com.MarketDataApp.dto.common.RequestDTO;
import com.MarketDataApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MarketQuoteService extends GenericService<MarketQuote, Integer> {

	List<MarketQuote> findAll();

	ResultDTO addMarketQuote(MarketQuoteDTO marketQuoteDTO, RequestDTO requestDTO);

	ResultDTO updateMarketQuote(MarketQuoteDTO marketQuoteDTO, RequestDTO requestDTO);

    Page<MarketQuote> getAllMarketQuotes(Pageable pageable);

    Page<MarketQuote> getAllMarketQuotes(Specification<MarketQuote> spec, Pageable pageable);

	ResponseEntity<MarketQuotePageDTO> getMarketQuotes(MarketQuoteSearchDTO marketQuoteSearchDTO);
	
	List<MarketQuoteDTO> convertMarketQuotesToMarketQuoteDTOs(List<MarketQuote> marketQuotes, MarketQuoteConvertCriteriaDTO convertCriteria);

	MarketQuoteDTO getMarketQuoteDTOById(Integer marketQuoteId);



	
}
