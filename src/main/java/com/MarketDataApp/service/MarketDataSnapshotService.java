package com.MarketDataApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.MarketDataApp.domain.MarketDataSnapshot;
import com.MarketDataApp.dto.MarketDataSnapshotDTO;
import com.MarketDataApp.dto.MarketDataSnapshotSearchDTO;
import com.MarketDataApp.dto.MarketDataSnapshotPageDTO;
import com.MarketDataApp.dto.MarketDataSnapshotConvertCriteriaDTO;
import com.MarketDataApp.service.GenericService;
import com.MarketDataApp.dto.common.RequestDTO;
import com.MarketDataApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MarketDataSnapshotService extends GenericService<MarketDataSnapshot, Integer> {

	List<MarketDataSnapshot> findAll();

	ResultDTO addMarketDataSnapshot(MarketDataSnapshotDTO marketDataSnapshotDTO, RequestDTO requestDTO);

	ResultDTO updateMarketDataSnapshot(MarketDataSnapshotDTO marketDataSnapshotDTO, RequestDTO requestDTO);

    Page<MarketDataSnapshot> getAllMarketDataSnapshots(Pageable pageable);

    Page<MarketDataSnapshot> getAllMarketDataSnapshots(Specification<MarketDataSnapshot> spec, Pageable pageable);

	ResponseEntity<MarketDataSnapshotPageDTO> getMarketDataSnapshots(MarketDataSnapshotSearchDTO marketDataSnapshotSearchDTO);
	
	List<MarketDataSnapshotDTO> convertMarketDataSnapshotsToMarketDataSnapshotDTOs(List<MarketDataSnapshot> marketDataSnapshots, MarketDataSnapshotConvertCriteriaDTO convertCriteria);

	MarketDataSnapshotDTO getMarketDataSnapshotDTOById(Integer marketDataSnapshotId);



	
}
