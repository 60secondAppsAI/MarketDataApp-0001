package com.MarketDataApp.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;


import com.MarketDataApp.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.MarketDataApp.domain.MarketDataSnapshot;
import com.MarketDataApp.dto.MarketDataSnapshotDTO;
import com.MarketDataApp.dto.MarketDataSnapshotSearchDTO;
import com.MarketDataApp.dto.MarketDataSnapshotPageDTO;
import com.MarketDataApp.service.MarketDataSnapshotService;
import com.MarketDataApp.dto.common.RequestDTO;
import com.MarketDataApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/marketDataSnapshot")
@RestController
public class MarketDataSnapshotController {

	private final static Logger logger = LoggerFactory.getLogger(MarketDataSnapshotController.class);

	@Autowired
	MarketDataSnapshotService marketDataSnapshotService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<MarketDataSnapshot> getAll() {

		List<MarketDataSnapshot> marketDataSnapshots = marketDataSnapshotService.findAll();
		
		return marketDataSnapshots;	
	}

	@GetMapping(value = "/{marketDataSnapshotId}")
	@ResponseBody
	public MarketDataSnapshotDTO getMarketDataSnapshot(@PathVariable Integer marketDataSnapshotId) {
		
		return (marketDataSnapshotService.getMarketDataSnapshotDTOById(marketDataSnapshotId));
	}

 	@RequestMapping(value = "/addMarketDataSnapshot", method = RequestMethod.POST)
	public ResponseEntity<?> addMarketDataSnapshot(@RequestBody MarketDataSnapshotDTO marketDataSnapshotDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = marketDataSnapshotService.addMarketDataSnapshot(marketDataSnapshotDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/marketDataSnapshots")
	public ResponseEntity<MarketDataSnapshotPageDTO> getMarketDataSnapshots(MarketDataSnapshotSearchDTO marketDataSnapshotSearchDTO) {
 
		return marketDataSnapshotService.getMarketDataSnapshots(marketDataSnapshotSearchDTO);
	}	

	@RequestMapping(value = "/updateMarketDataSnapshot", method = RequestMethod.POST)
	public ResponseEntity<?> updateMarketDataSnapshot(@RequestBody MarketDataSnapshotDTO marketDataSnapshotDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = marketDataSnapshotService.updateMarketDataSnapshot(marketDataSnapshotDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
