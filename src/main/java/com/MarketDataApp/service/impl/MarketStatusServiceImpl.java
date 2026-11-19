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
import com.MarketDataApp.dao.MarketStatusDAO;
import com.MarketDataApp.domain.MarketStatus;
import com.MarketDataApp.dto.MarketStatusDTO;
import com.MarketDataApp.dto.MarketStatusSearchDTO;
import com.MarketDataApp.dto.MarketStatusPageDTO;
import com.MarketDataApp.dto.MarketStatusConvertCriteriaDTO;
import com.MarketDataApp.dto.common.RequestDTO;
import com.MarketDataApp.dto.common.ResultDTO;
import com.MarketDataApp.service.MarketStatusService;
import com.MarketDataApp.util.ControllerUtils;


@Service
public class MarketStatusServiceImpl extends GenericServiceImpl<MarketStatus, Integer> implements MarketStatusService {

    private final static Logger logger = LoggerFactory.getLogger(MarketStatusServiceImpl.class);

	@Autowired
	MarketStatusDAO marketStatusDao;

	

	@Override
	public GenericDAO<MarketStatus, Integer> getDAO() {
		return (GenericDAO<MarketStatus, Integer>) marketStatusDao;
	}
	
	public List<MarketStatus> findAll () {
		List<MarketStatus> marketStatuss = marketStatusDao.findAll();
		
		return marketStatuss;	
		
	}

	public ResultDTO addMarketStatus(MarketStatusDTO marketStatusDTO, RequestDTO requestDTO) {

		MarketStatus marketStatus = new MarketStatus();

		marketStatus.setMarketStatusId(marketStatusDTO.getMarketStatusId());

		marketStatus.setExchangeId(marketStatusDTO.getExchangeId());

		marketStatus.setStatus(marketStatusDTO.getStatus());

		marketStatus.setChangedAt(marketStatusDTO.getChangedAt());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		marketStatus = marketStatusDao.save(marketStatus);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MarketStatus> getAllMarketStatuss(Pageable pageable) {
		return marketStatusDao.findAll(pageable);
	}

	public Page<MarketStatus> getAllMarketStatuss(Specification<MarketStatus> spec, Pageable pageable) {
		return marketStatusDao.findAll(spec, pageable);
	}

	public ResponseEntity<MarketStatusPageDTO> getMarketStatuss(MarketStatusSearchDTO marketStatusSearchDTO) {
	
			Integer marketStatusId = marketStatusSearchDTO.getMarketStatusId(); 
 			Integer exchangeId = marketStatusSearchDTO.getExchangeId(); 
 			String status = marketStatusSearchDTO.getStatus(); 
 			Date changedAt = marketStatusSearchDTO.getChangedAt();
 			String sortBy = marketStatusSearchDTO.getSortBy();
			String sortOrder = marketStatusSearchDTO.getSortOrder();
			String searchQuery = marketStatusSearchDTO.getSearchQuery();
			Integer page = marketStatusSearchDTO.getPage();
			Integer size = marketStatusSearchDTO.getSize();

	        Specification<MarketStatus> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, marketStatusId, "marketStatusId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, exchangeId, "exchangeId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			
			spec = ControllerUtils.andIfNecessary(spec, changedAt, "changedAt");
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("status")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<MarketStatus> marketStatuss = this.getAllMarketStatuss(spec, pageable);
		
		//System.out.println(String.valueOf(marketStatuss.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(marketStatuss.getTotalPages()));
		
		List<MarketStatus> marketStatussList = marketStatuss.getContent();
		
		MarketStatusConvertCriteriaDTO convertCriteria = new MarketStatusConvertCriteriaDTO();
		List<MarketStatusDTO> marketStatusDTOs = this.convertMarketStatussToMarketStatusDTOs(marketStatussList,convertCriteria);
		
		MarketStatusPageDTO marketStatusPageDTO = new MarketStatusPageDTO();
		marketStatusPageDTO.setMarketStatuss(marketStatusDTOs);
		marketStatusPageDTO.setTotalElements(marketStatuss.getTotalElements());
		return ResponseEntity.ok(marketStatusPageDTO);
	}

	public List<MarketStatusDTO> convertMarketStatussToMarketStatusDTOs(List<MarketStatus> marketStatuss, MarketStatusConvertCriteriaDTO convertCriteria) {
		
		List<MarketStatusDTO> marketStatusDTOs = new ArrayList<MarketStatusDTO>();
		
		for (MarketStatus marketStatus : marketStatuss) {
			marketStatusDTOs.add(convertMarketStatusToMarketStatusDTO(marketStatus,convertCriteria));
		}
		
		return marketStatusDTOs;

	}
	
	public MarketStatusDTO convertMarketStatusToMarketStatusDTO(MarketStatus marketStatus, MarketStatusConvertCriteriaDTO convertCriteria) {
		
		MarketStatusDTO marketStatusDTO = new MarketStatusDTO();

		marketStatusDTO.setMarketStatusId(marketStatus.getMarketStatusId());

		marketStatusDTO.setExchangeId(marketStatus.getExchangeId());

		marketStatusDTO.setStatus(marketStatus.getStatus());

		marketStatusDTO.setChangedAt(marketStatus.getChangedAt());
		
		return marketStatusDTO;
	}

	public ResultDTO updateMarketStatus(MarketStatusDTO marketStatusDTO, RequestDTO requestDTO) {
		
		MarketStatus marketStatus = marketStatusDao.getById(marketStatusDTO.getMarketStatusId());
		
		marketStatus.setMarketStatusId(ControllerUtils.setValue(marketStatus.getMarketStatusId(), marketStatusDTO.getMarketStatusId()));
		
		marketStatus.setExchangeId(ControllerUtils.setValue(marketStatus.getExchangeId(), marketStatusDTO.getExchangeId()));
		
		marketStatus.setStatus(ControllerUtils.setValue(marketStatus.getStatus(), marketStatusDTO.getStatus()));
		
		marketStatus.setChangedAt(ControllerUtils.setValue(marketStatus.getChangedAt(), marketStatusDTO.getChangedAt()));

        marketStatus = marketStatusDao.save(marketStatus);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MarketStatusDTO getMarketStatusDTOById(Integer marketStatusId) {
	
		MarketStatus marketStatus = marketStatusDao.getById(marketStatusId);
		
		MarketStatusConvertCriteriaDTO convertCriteria = new MarketStatusConvertCriteriaDTO();
		return(this.convertMarketStatusToMarketStatusDTO(marketStatus,convertCriteria));
	}

}
