package com.MarketDataApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.MarketDataApp.domain.MarketBar;
import com.MarketDataApp.dto.MarketBarDTO;
import com.MarketDataApp.dto.MarketBarSearchDTO;
import com.MarketDataApp.dto.MarketBarPageDTO;
import com.MarketDataApp.dto.MarketBarConvertCriteriaDTO;
import com.MarketDataApp.service.GenericService;
import com.MarketDataApp.dto.common.RequestDTO;
import com.MarketDataApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MarketBarService extends GenericService<MarketBar, Integer> {

	List<MarketBar> findAll();

	ResultDTO addMarketBar(MarketBarDTO marketBarDTO, RequestDTO requestDTO);

	ResultDTO updateMarketBar(MarketBarDTO marketBarDTO, RequestDTO requestDTO);

    Page<MarketBar> getAllMarketBars(Pageable pageable);

    Page<MarketBar> getAllMarketBars(Specification<MarketBar> spec, Pageable pageable);

	ResponseEntity<MarketBarPageDTO> getMarketBars(MarketBarSearchDTO marketBarSearchDTO);
	
	List<MarketBarDTO> convertMarketBarsToMarketBarDTOs(List<MarketBar> marketBars, MarketBarConvertCriteriaDTO convertCriteria);

	MarketBarDTO getMarketBarDTOById(Integer marketBarId);



	
}
