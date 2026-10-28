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

import com.MarketDataApp.domain.MarketStatus;
import com.MarketDataApp.dto.MarketStatusDTO;
import com.MarketDataApp.dto.MarketStatusSearchDTO;
import com.MarketDataApp.dto.MarketStatusPageDTO;
import com.MarketDataApp.service.MarketStatusService;
import com.MarketDataApp.dto.common.RequestDTO;
import com.MarketDataApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/marketStatus")
@RestController
public class MarketStatusController {

	private final static Logger logger = LoggerFactory.getLogger(MarketStatusController.class);

	@Autowired
	MarketStatusService marketStatusService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<MarketStatus> getAll() {

		List<MarketStatus> marketStatuss = marketStatusService.findAll();
		
		return marketStatuss;	
	}

	@GetMapping(value = "/{marketStatusId}")
	@ResponseBody
	public MarketStatusDTO getMarketStatus(@PathVariable Integer marketStatusId) {
		
		return (marketStatusService.getMarketStatusDTOById(marketStatusId));
	}

 	@RequestMapping(value = "/addMarketStatus", method = RequestMethod.POST)
	public ResponseEntity<?> addMarketStatus(@RequestBody MarketStatusDTO marketStatusDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = marketStatusService.addMarketStatus(marketStatusDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/marketStatuss")
	public ResponseEntity<MarketStatusPageDTO> getMarketStatuss(MarketStatusSearchDTO marketStatusSearchDTO) {
 
		return marketStatusService.getMarketStatuss(marketStatusSearchDTO);
	}	

	@RequestMapping(value = "/updateMarketStatus", method = RequestMethod.POST)
	public ResponseEntity<?> updateMarketStatus(@RequestBody MarketStatusDTO marketStatusDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = marketStatusService.updateMarketStatus(marketStatusDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
