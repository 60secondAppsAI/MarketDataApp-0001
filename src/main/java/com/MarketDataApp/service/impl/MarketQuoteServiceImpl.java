package com.MarketDataApp.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.MarketDataApp.dao.GenericDAO;
import com.MarketDataApp.service.GenericService;
import com.MarketDataApp.service.impl.GenericServiceImpl;
import com.MarketDataApp.dao.MarketQuoteDAO;
import com.MarketDataApp.domain.MarketQuote;
import com.MarketDataApp.dto.MarketQuoteDTO;
import com.MarketDataApp.dto.MarketQuoteSearchDTO;
import com.MarketDataApp.dto.MarketQuotePageDTO;
import com.MarketDataApp.dto.MarketQuoteConvertCriteriaDTO;
import com.MarketDataApp.dto.common.RequestDTO;
import com.MarketDataApp.dto.common.ResultDTO;
import com.MarketDataApp.service.MarketQuoteService;
import com.MarketDataApp.util.ControllerUtils;


@Service
public class MarketQuoteServiceImpl extends GenericServiceImpl<MarketQuote, Integer> implements MarketQuoteService {

    private final static Logger logger = LoggerFactory.getLogger(MarketQuoteServiceImpl.class);

	@Autowired
	MarketQuoteDAO marketQuoteDao;

	

	@Override
	public GenericDAO<MarketQuote, Integer> getDAO() {
		return (GenericDAO<MarketQuote, Integer>) marketQuoteDao;
	}
	
	public List<MarketQuote> findAll () {
		List<MarketQuote> marketQuotes = marketQuoteDao.findAll();
		
		return marketQuotes;	
		
	}

	public ResultDTO addMarketQuote(MarketQuoteDTO marketQuoteDTO, RequestDTO requestDTO) {

		MarketQuote marketQuote = new MarketQuote();

		marketQuote.setMarketQuoteId(marketQuoteDTO.getMarketQuoteId());

		marketQuote.setInstrumentId(marketQuoteDTO.getInstrumentId());

		marketQuote.setBidPrice(marketQuoteDTO.getBidPrice());

		marketQuote.setBidSize(marketQuoteDTO.getBidSize());

		marketQuote.setAskPrice(marketQuoteDTO.getAskPrice());

		marketQuote.setAskSize(marketQuoteDTO.getAskSize());

		marketQuote.setQuotedAt(marketQuoteDTO.getQuotedAt());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		marketQuote = marketQuoteDao.save(marketQuote);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MarketQuote> getAllMarketQuotes(Pageable pageable) {
		return marketQuoteDao.findAll(pageable);
	}

	public Page<MarketQuote> getAllMarketQuotes(Specification<MarketQuote> spec, Pageable pageable) {
		return marketQuoteDao.findAll(spec, pageable);
	}

	public ResponseEntity<MarketQuotePageDTO> getMarketQuotes(MarketQuoteSearchDTO marketQuoteSearchDTO) {
	
			Integer marketQuoteId = marketQuoteSearchDTO.getMarketQuoteId(); 
 			Integer instrumentId = marketQuoteSearchDTO.getInstrumentId(); 
     			Date quotedAt = marketQuoteSearchDTO.getQuotedAt();
 			String sortBy = marketQuoteSearchDTO.getSortBy();
			String sortOrder = marketQuoteSearchDTO.getSortOrder();
			String searchQuery = marketQuoteSearchDTO.getSearchQuery();
			Integer page = marketQuoteSearchDTO.getPage();
			Integer size = marketQuoteSearchDTO.getSize();

	        Specification<MarketQuote> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, marketQuoteId, "marketQuoteId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, instrumentId, "instrumentId"); 
			
			
			
			
			
			spec = ControllerUtils.andIfNecessary(spec, quotedAt, "quotedAt");
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<MarketQuote> marketQuotes = this.getAllMarketQuotes(spec, pageable);
		
		//System.out.println(String.valueOf(marketQuotes.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(marketQuotes.getTotalPages()));
		
		List<MarketQuote> marketQuotesList = marketQuotes.getContent();
		
		MarketQuoteConvertCriteriaDTO convertCriteria = new MarketQuoteConvertCriteriaDTO();
		List<MarketQuoteDTO> marketQuoteDTOs = this.convertMarketQuotesToMarketQuoteDTOs(marketQuotesList,convertCriteria);
		
		MarketQuotePageDTO marketQuotePageDTO = new MarketQuotePageDTO();
		marketQuotePageDTO.setMarketQuotes(marketQuoteDTOs);
		marketQuotePageDTO.setTotalElements(marketQuotes.getTotalElements());
		return ResponseEntity.ok(marketQuotePageDTO);
	}

	public List<MarketQuoteDTO> convertMarketQuotesToMarketQuoteDTOs(List<MarketQuote> marketQuotes, MarketQuoteConvertCriteriaDTO convertCriteria) {
		
		List<MarketQuoteDTO> marketQuoteDTOs = new ArrayList<MarketQuoteDTO>();
		
		for (MarketQuote marketQuote : marketQuotes) {
			marketQuoteDTOs.add(convertMarketQuoteToMarketQuoteDTO(marketQuote,convertCriteria));
		}
		
		return marketQuoteDTOs;

	}
	
	public MarketQuoteDTO convertMarketQuoteToMarketQuoteDTO(MarketQuote marketQuote, MarketQuoteConvertCriteriaDTO convertCriteria) {
		
		MarketQuoteDTO marketQuoteDTO = new MarketQuoteDTO();

		marketQuoteDTO.setMarketQuoteId(marketQuote.getMarketQuoteId());

		marketQuoteDTO.setInstrumentId(marketQuote.getInstrumentId());

		marketQuoteDTO.setBidPrice(marketQuote.getBidPrice());

		marketQuoteDTO.setBidSize(marketQuote.getBidSize());

		marketQuoteDTO.setAskPrice(marketQuote.getAskPrice());

		marketQuoteDTO.setAskSize(marketQuote.getAskSize());

		marketQuoteDTO.setQuotedAt(marketQuote.getQuotedAt());
		
		return marketQuoteDTO;
	}

	public ResultDTO updateMarketQuote(MarketQuoteDTO marketQuoteDTO, RequestDTO requestDTO) {
		
		MarketQuote marketQuote = marketQuoteDao.getById(marketQuoteDTO.getMarketQuoteId());
		
		marketQuote.setMarketQuoteId(ControllerUtils.setValue(marketQuote.getMarketQuoteId(), marketQuoteDTO.getMarketQuoteId()));
		
		marketQuote.setInstrumentId(ControllerUtils.setValue(marketQuote.getInstrumentId(), marketQuoteDTO.getInstrumentId()));
		
		marketQuote.setBidPrice(ControllerUtils.setValue(marketQuote.getBidPrice(), marketQuoteDTO.getBidPrice()));
		
		marketQuote.setBidSize(ControllerUtils.setValue(marketQuote.getBidSize(), marketQuoteDTO.getBidSize()));
		
		marketQuote.setAskPrice(ControllerUtils.setValue(marketQuote.getAskPrice(), marketQuoteDTO.getAskPrice()));
		
		marketQuote.setAskSize(ControllerUtils.setValue(marketQuote.getAskSize(), marketQuoteDTO.getAskSize()));
		
		marketQuote.setQuotedAt(ControllerUtils.setValue(marketQuote.getQuotedAt(), marketQuoteDTO.getQuotedAt()));

        marketQuote = marketQuoteDao.save(marketQuote);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MarketQuoteDTO getMarketQuoteDTOById(Integer marketQuoteId) {
	
		MarketQuote marketQuote = marketQuoteDao.getById(marketQuoteId);
		
		MarketQuoteConvertCriteriaDTO convertCriteria = new MarketQuoteConvertCriteriaDTO();
		return(this.convertMarketQuoteToMarketQuoteDTO(marketQuote,convertCriteria));
	}

}
