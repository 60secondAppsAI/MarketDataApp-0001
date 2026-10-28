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
import com.MarketDataApp.dao.MarketDataSnapshotDAO;
import com.MarketDataApp.domain.MarketDataSnapshot;
import com.MarketDataApp.dto.MarketDataSnapshotDTO;
import com.MarketDataApp.dto.MarketDataSnapshotSearchDTO;
import com.MarketDataApp.dto.MarketDataSnapshotPageDTO;
import com.MarketDataApp.dto.MarketDataSnapshotConvertCriteriaDTO;
import com.MarketDataApp.dto.common.RequestDTO;
import com.MarketDataApp.dto.common.ResultDTO;
import com.MarketDataApp.service.MarketDataSnapshotService;
import com.MarketDataApp.util.ControllerUtils;


@Service
public class MarketDataSnapshotServiceImpl extends GenericServiceImpl<MarketDataSnapshot, Integer> implements MarketDataSnapshotService {

    private final static Logger logger = LoggerFactory.getLogger(MarketDataSnapshotServiceImpl.class);

	@Autowired
	MarketDataSnapshotDAO marketDataSnapshotDao;

	

	@Override
	public GenericDAO<MarketDataSnapshot, Integer> getDAO() {
		return (GenericDAO<MarketDataSnapshot, Integer>) marketDataSnapshotDao;
	}
	
	public List<MarketDataSnapshot> findAll () {
		List<MarketDataSnapshot> marketDataSnapshots = marketDataSnapshotDao.findAll();
		
		return marketDataSnapshots;	
		
	}

	public ResultDTO addMarketDataSnapshot(MarketDataSnapshotDTO marketDataSnapshotDTO, RequestDTO requestDTO) {

		MarketDataSnapshot marketDataSnapshot = new MarketDataSnapshot();

		marketDataSnapshot.setMarketDataSnapshotId(marketDataSnapshotDTO.getMarketDataSnapshotId());

		marketDataSnapshot.setInstrumentId(marketDataSnapshotDTO.getInstrumentId());

		marketDataSnapshot.setExchangeId(marketDataSnapshotDTO.getExchangeId());

		marketDataSnapshot.setBidPrice(marketDataSnapshotDTO.getBidPrice());

		marketDataSnapshot.setAskPrice(marketDataSnapshotDTO.getAskPrice());

		marketDataSnapshot.setLastPrice(marketDataSnapshotDTO.getLastPrice());

		marketDataSnapshot.setCapturedAt(marketDataSnapshotDTO.getCapturedAt());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		marketDataSnapshot = marketDataSnapshotDao.save(marketDataSnapshot);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MarketDataSnapshot> getAllMarketDataSnapshots(Pageable pageable) {
		return marketDataSnapshotDao.findAll(pageable);
	}

	public Page<MarketDataSnapshot> getAllMarketDataSnapshots(Specification<MarketDataSnapshot> spec, Pageable pageable) {
		return marketDataSnapshotDao.findAll(spec, pageable);
	}

	public ResponseEntity<MarketDataSnapshotPageDTO> getMarketDataSnapshots(MarketDataSnapshotSearchDTO marketDataSnapshotSearchDTO) {
	
			Integer marketDataSnapshotId = marketDataSnapshotSearchDTO.getMarketDataSnapshotId(); 
 			Integer instrumentId = marketDataSnapshotSearchDTO.getInstrumentId(); 
 			Integer exchangeId = marketDataSnapshotSearchDTO.getExchangeId(); 
    			Date capturedAt = marketDataSnapshotSearchDTO.getCapturedAt();
 			String sortBy = marketDataSnapshotSearchDTO.getSortBy();
			String sortOrder = marketDataSnapshotSearchDTO.getSortOrder();
			String searchQuery = marketDataSnapshotSearchDTO.getSearchQuery();
			Integer page = marketDataSnapshotSearchDTO.getPage();
			Integer size = marketDataSnapshotSearchDTO.getSize();

	        Specification<MarketDataSnapshot> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, marketDataSnapshotId, "marketDataSnapshotId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, instrumentId, "instrumentId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, exchangeId, "exchangeId"); 
			
			
			
			
			spec = ControllerUtils.andIfNecessary(spec, capturedAt, "capturedAt");
			

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

		Page<MarketDataSnapshot> marketDataSnapshots = this.getAllMarketDataSnapshots(spec, pageable);
		
		//System.out.println(String.valueOf(marketDataSnapshots.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(marketDataSnapshots.getTotalPages()));
		
		List<MarketDataSnapshot> marketDataSnapshotsList = marketDataSnapshots.getContent();
		
		MarketDataSnapshotConvertCriteriaDTO convertCriteria = new MarketDataSnapshotConvertCriteriaDTO();
		List<MarketDataSnapshotDTO> marketDataSnapshotDTOs = this.convertMarketDataSnapshotsToMarketDataSnapshotDTOs(marketDataSnapshotsList,convertCriteria);
		
		MarketDataSnapshotPageDTO marketDataSnapshotPageDTO = new MarketDataSnapshotPageDTO();
		marketDataSnapshotPageDTO.setMarketDataSnapshots(marketDataSnapshotDTOs);
		marketDataSnapshotPageDTO.setTotalElements(marketDataSnapshots.getTotalElements());
		return ResponseEntity.ok(marketDataSnapshotPageDTO);
	}

	public List<MarketDataSnapshotDTO> convertMarketDataSnapshotsToMarketDataSnapshotDTOs(List<MarketDataSnapshot> marketDataSnapshots, MarketDataSnapshotConvertCriteriaDTO convertCriteria) {
		
		List<MarketDataSnapshotDTO> marketDataSnapshotDTOs = new ArrayList<MarketDataSnapshotDTO>();
		
		for (MarketDataSnapshot marketDataSnapshot : marketDataSnapshots) {
			marketDataSnapshotDTOs.add(convertMarketDataSnapshotToMarketDataSnapshotDTO(marketDataSnapshot,convertCriteria));
		}
		
		return marketDataSnapshotDTOs;

	}
	
	public MarketDataSnapshotDTO convertMarketDataSnapshotToMarketDataSnapshotDTO(MarketDataSnapshot marketDataSnapshot, MarketDataSnapshotConvertCriteriaDTO convertCriteria) {
		
		MarketDataSnapshotDTO marketDataSnapshotDTO = new MarketDataSnapshotDTO();

		marketDataSnapshotDTO.setMarketDataSnapshotId(marketDataSnapshot.getMarketDataSnapshotId());

		marketDataSnapshotDTO.setInstrumentId(marketDataSnapshot.getInstrumentId());

		marketDataSnapshotDTO.setExchangeId(marketDataSnapshot.getExchangeId());

		marketDataSnapshotDTO.setBidPrice(marketDataSnapshot.getBidPrice());

		marketDataSnapshotDTO.setAskPrice(marketDataSnapshot.getAskPrice());

		marketDataSnapshotDTO.setLastPrice(marketDataSnapshot.getLastPrice());

		marketDataSnapshotDTO.setCapturedAt(marketDataSnapshot.getCapturedAt());
		
		return marketDataSnapshotDTO;
	}

	public ResultDTO updateMarketDataSnapshot(MarketDataSnapshotDTO marketDataSnapshotDTO, RequestDTO requestDTO) {
		
		MarketDataSnapshot marketDataSnapshot = marketDataSnapshotDao.getById(marketDataSnapshotDTO.getMarketDataSnapshotId());
		
		marketDataSnapshot.setMarketDataSnapshotId(ControllerUtils.setValue(marketDataSnapshot.getMarketDataSnapshotId(), marketDataSnapshotDTO.getMarketDataSnapshotId()));
		
		marketDataSnapshot.setInstrumentId(ControllerUtils.setValue(marketDataSnapshot.getInstrumentId(), marketDataSnapshotDTO.getInstrumentId()));
		
		marketDataSnapshot.setExchangeId(ControllerUtils.setValue(marketDataSnapshot.getExchangeId(), marketDataSnapshotDTO.getExchangeId()));
		
		marketDataSnapshot.setBidPrice(ControllerUtils.setValue(marketDataSnapshot.getBidPrice(), marketDataSnapshotDTO.getBidPrice()));
		
		marketDataSnapshot.setAskPrice(ControllerUtils.setValue(marketDataSnapshot.getAskPrice(), marketDataSnapshotDTO.getAskPrice()));
		
		marketDataSnapshot.setLastPrice(ControllerUtils.setValue(marketDataSnapshot.getLastPrice(), marketDataSnapshotDTO.getLastPrice()));
		
		marketDataSnapshot.setCapturedAt(ControllerUtils.setValue(marketDataSnapshot.getCapturedAt(), marketDataSnapshotDTO.getCapturedAt()));

        marketDataSnapshot = marketDataSnapshotDao.save(marketDataSnapshot);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MarketDataSnapshotDTO getMarketDataSnapshotDTOById(Integer marketDataSnapshotId) {
	
		MarketDataSnapshot marketDataSnapshot = marketDataSnapshotDao.getById(marketDataSnapshotId);
		
		MarketDataSnapshotConvertCriteriaDTO convertCriteria = new MarketDataSnapshotConvertCriteriaDTO();
		return(this.convertMarketDataSnapshotToMarketDataSnapshotDTO(marketDataSnapshot,convertCriteria));
	}

}
