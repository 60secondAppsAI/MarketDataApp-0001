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

import com.MarketDataApp.domain.MarketQuote;
import com.MarketDataApp.dto.MarketQuoteDTO;
import com.MarketDataApp.dto.MarketQuoteSearchDTO;
import com.MarketDataApp.dto.MarketQuotePageDTO;
import com.MarketDataApp.service.MarketQuoteService;
import com.MarketDataApp.dto.common.RequestDTO;
import com.MarketDataApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/marketQuote")
@RestController
public class MarketQuoteController {

	private final static Logger logger = LoggerFactory.getLogger(MarketQuoteController.class);

	@Autowired
	MarketQuoteService marketQuoteService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<MarketQuote> getAll() {

		List<MarketQuote> marketQuotes = marketQuoteService.findAll();
		
		return marketQuotes;	
	}

	@GetMapping(value = "/{marketQuoteId}")
	@ResponseBody
	public MarketQuoteDTO getMarketQuote(@PathVariable Integer marketQuoteId) {
		
		return (marketQuoteService.getMarketQuoteDTOById(marketQuoteId));
	}

 	@RequestMapping(value = "/addMarketQuote", method = RequestMethod.POST)
	public ResponseEntity<?> addMarketQuote(@RequestBody MarketQuoteDTO marketQuoteDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = marketQuoteService.addMarketQuote(marketQuoteDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/marketQuotes")
	public ResponseEntity<MarketQuotePageDTO> getMarketQuotes(MarketQuoteSearchDTO marketQuoteSearchDTO) {
 
		return marketQuoteService.getMarketQuotes(marketQuoteSearchDTO);
	}	

	@RequestMapping(value = "/updateMarketQuote", method = RequestMethod.POST)
	public ResponseEntity<?> updateMarketQuote(@RequestBody MarketQuoteDTO marketQuoteDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = marketQuoteService.updateMarketQuote(marketQuoteDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
