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

import com.MarketDataApp.domain.MarketBar;
import com.MarketDataApp.dto.MarketBarDTO;
import com.MarketDataApp.dto.MarketBarSearchDTO;
import com.MarketDataApp.dto.MarketBarPageDTO;
import com.MarketDataApp.service.MarketBarService;
import com.MarketDataApp.dto.common.RequestDTO;
import com.MarketDataApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/marketBar")
@RestController
public class MarketBarController {

	private final static Logger logger = LoggerFactory.getLogger(MarketBarController.class);

	@Autowired
	MarketBarService marketBarService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<MarketBar> getAll() {

		List<MarketBar> marketBars = marketBarService.findAll();
		
		return marketBars;	
	}

	@GetMapping(value = "/{marketBarId}")
	@ResponseBody
	public MarketBarDTO getMarketBar(@PathVariable Integer marketBarId) {
		
		return (marketBarService.getMarketBarDTOById(marketBarId));
	}

 	@RequestMapping(value = "/addMarketBar", method = RequestMethod.POST)
	public ResponseEntity<?> addMarketBar(@RequestBody MarketBarDTO marketBarDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = marketBarService.addMarketBar(marketBarDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/marketBars")
	public ResponseEntity<MarketBarPageDTO> getMarketBars(MarketBarSearchDTO marketBarSearchDTO) {
 
		return marketBarService.getMarketBars(marketBarSearchDTO);
	}	

	@RequestMapping(value = "/updateMarketBar", method = RequestMethod.POST)
	public ResponseEntity<?> updateMarketBar(@RequestBody MarketBarDTO marketBarDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = marketBarService.updateMarketBar(marketBarDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
