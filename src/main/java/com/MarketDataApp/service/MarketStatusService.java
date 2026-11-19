package com.MarketDataApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.MarketDataApp.domain.MarketStatus;
import com.MarketDataApp.dto.MarketStatusDTO;
import com.MarketDataApp.dto.MarketStatusSearchDTO;
import com.MarketDataApp.dto.MarketStatusPageDTO;
import com.MarketDataApp.dto.MarketStatusConvertCriteriaDTO;
import com.MarketDataApp.service.GenericService;
import com.MarketDataApp.dto.common.RequestDTO;
import com.MarketDataApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MarketStatusService extends GenericService<MarketStatus, Integer> {

	List<MarketStatus> findAll();

	ResultDTO addMarketStatus(MarketStatusDTO marketStatusDTO, RequestDTO requestDTO);

	ResultDTO updateMarketStatus(MarketStatusDTO marketStatusDTO, RequestDTO requestDTO);

    Page<MarketStatus> getAllMarketStatuss(Pageable pageable);

    Page<MarketStatus> getAllMarketStatuss(Specification<MarketStatus> spec, Pageable pageable);

	ResponseEntity<MarketStatusPageDTO> getMarketStatuss(MarketStatusSearchDTO marketStatusSearchDTO);
	
	List<MarketStatusDTO> convertMarketStatussToMarketStatusDTOs(List<MarketStatus> marketStatuss, MarketStatusConvertCriteriaDTO convertCriteria);

	MarketStatusDTO getMarketStatusDTOById(Integer marketStatusId);



	
}
