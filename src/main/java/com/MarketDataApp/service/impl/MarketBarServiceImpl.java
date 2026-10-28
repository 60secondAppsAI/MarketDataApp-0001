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
import com.MarketDataApp.dao.MarketBarDAO;
import com.MarketDataApp.domain.MarketBar;
import com.MarketDataApp.dto.MarketBarDTO;
import com.MarketDataApp.dto.MarketBarSearchDTO;
import com.MarketDataApp.dto.MarketBarPageDTO;
import com.MarketDataApp.dto.MarketBarConvertCriteriaDTO;
import com.MarketDataApp.dto.common.RequestDTO;
import com.MarketDataApp.dto.common.ResultDTO;
import com.MarketDataApp.service.MarketBarService;
import com.MarketDataApp.util.ControllerUtils;


@Service
public class MarketBarServiceImpl extends GenericServiceImpl<MarketBar, Integer> implements MarketBarService {

    private final static Logger logger = LoggerFactory.getLogger(MarketBarServiceImpl.class);

	@Autowired
	MarketBarDAO marketBarDao;

	

	@Override
	public GenericDAO<MarketBar, Integer> getDAO() {
		return (GenericDAO<MarketBar, Integer>) marketBarDao;
	}
	
	public List<MarketBar> findAll () {
		List<MarketBar> marketBars = marketBarDao.findAll();
		
		return marketBars;	
		
	}

	public ResultDTO addMarketBar(MarketBarDTO marketBarDTO, RequestDTO requestDTO) {

		MarketBar marketBar = new MarketBar();

		marketBar.setMarketBarId(marketBarDTO.getMarketBarId());

		marketBar.setInstrumentId(marketBarDTO.getInstrumentId());

		marketBar.setOpenPrice(marketBarDTO.getOpenPrice());

		marketBar.setHighPrice(marketBarDTO.getHighPrice());

		marketBar.setLowPrice(marketBarDTO.getLowPrice());

		marketBar.setClosePrice(marketBarDTO.getClosePrice());

		marketBar.setVolume(marketBarDTO.getVolume());

		marketBar.setBarTime(marketBarDTO.getBarTime());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		marketBar = marketBarDao.save(marketBar);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MarketBar> getAllMarketBars(Pageable pageable) {
		return marketBarDao.findAll(pageable);
	}

	public Page<MarketBar> getAllMarketBars(Specification<MarketBar> spec, Pageable pageable) {
		return marketBarDao.findAll(spec, pageable);
	}

	public ResponseEntity<MarketBarPageDTO> getMarketBars(MarketBarSearchDTO marketBarSearchDTO) {
	
			Integer marketBarId = marketBarSearchDTO.getMarketBarId(); 
 			Integer instrumentId = marketBarSearchDTO.getInstrumentId(); 
      			Date barTime = marketBarSearchDTO.getBarTime();
 			String sortBy = marketBarSearchDTO.getSortBy();
			String sortOrder = marketBarSearchDTO.getSortOrder();
			String searchQuery = marketBarSearchDTO.getSearchQuery();
			Integer page = marketBarSearchDTO.getPage();
			Integer size = marketBarSearchDTO.getSize();

	        Specification<MarketBar> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, marketBarId, "marketBarId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, instrumentId, "instrumentId"); 
			
			
			
			
			
			
			spec = ControllerUtils.andIfNecessary(spec, barTime, "barTime");
			

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

		Page<MarketBar> marketBars = this.getAllMarketBars(spec, pageable);
		
		//System.out.println(String.valueOf(marketBars.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(marketBars.getTotalPages()));
		
		List<MarketBar> marketBarsList = marketBars.getContent();
		
		MarketBarConvertCriteriaDTO convertCriteria = new MarketBarConvertCriteriaDTO();
		List<MarketBarDTO> marketBarDTOs = this.convertMarketBarsToMarketBarDTOs(marketBarsList,convertCriteria);
		
		MarketBarPageDTO marketBarPageDTO = new MarketBarPageDTO();
		marketBarPageDTO.setMarketBars(marketBarDTOs);
		marketBarPageDTO.setTotalElements(marketBars.getTotalElements());
		return ResponseEntity.ok(marketBarPageDTO);
	}

	public List<MarketBarDTO> convertMarketBarsToMarketBarDTOs(List<MarketBar> marketBars, MarketBarConvertCriteriaDTO convertCriteria) {
		
		List<MarketBarDTO> marketBarDTOs = new ArrayList<MarketBarDTO>();
		
		for (MarketBar marketBar : marketBars) {
			marketBarDTOs.add(convertMarketBarToMarketBarDTO(marketBar,convertCriteria));
		}
		
		return marketBarDTOs;

	}
	
	public MarketBarDTO convertMarketBarToMarketBarDTO(MarketBar marketBar, MarketBarConvertCriteriaDTO convertCriteria) {
		
		MarketBarDTO marketBarDTO = new MarketBarDTO();

		marketBarDTO.setMarketBarId(marketBar.getMarketBarId());

		marketBarDTO.setInstrumentId(marketBar.getInstrumentId());

		marketBarDTO.setOpenPrice(marketBar.getOpenPrice());

		marketBarDTO.setHighPrice(marketBar.getHighPrice());

		marketBarDTO.setLowPrice(marketBar.getLowPrice());

		marketBarDTO.setClosePrice(marketBar.getClosePrice());

		marketBarDTO.setVolume(marketBar.getVolume());

		marketBarDTO.setBarTime(marketBar.getBarTime());
		
		return marketBarDTO;
	}

	public ResultDTO updateMarketBar(MarketBarDTO marketBarDTO, RequestDTO requestDTO) {
		
		MarketBar marketBar = marketBarDao.getById(marketBarDTO.getMarketBarId());
		
		marketBar.setMarketBarId(ControllerUtils.setValue(marketBar.getMarketBarId(), marketBarDTO.getMarketBarId()));
		
		marketBar.setInstrumentId(ControllerUtils.setValue(marketBar.getInstrumentId(), marketBarDTO.getInstrumentId()));
		
		marketBar.setOpenPrice(ControllerUtils.setValue(marketBar.getOpenPrice(), marketBarDTO.getOpenPrice()));
		
		marketBar.setHighPrice(ControllerUtils.setValue(marketBar.getHighPrice(), marketBarDTO.getHighPrice()));
		
		marketBar.setLowPrice(ControllerUtils.setValue(marketBar.getLowPrice(), marketBarDTO.getLowPrice()));
		
		marketBar.setClosePrice(ControllerUtils.setValue(marketBar.getClosePrice(), marketBarDTO.getClosePrice()));
		
		marketBar.setVolume(ControllerUtils.setValue(marketBar.getVolume(), marketBarDTO.getVolume()));
		
		marketBar.setBarTime(ControllerUtils.setValue(marketBar.getBarTime(), marketBarDTO.getBarTime()));

        marketBar = marketBarDao.save(marketBar);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MarketBarDTO getMarketBarDTOById(Integer marketBarId) {
	
		MarketBar marketBar = marketBarDao.getById(marketBarId);
		
		MarketBarConvertCriteriaDTO convertCriteria = new MarketBarConvertCriteriaDTO();
		return(this.convertMarketBarToMarketBarDTO(marketBar,convertCriteria));
	}

}
