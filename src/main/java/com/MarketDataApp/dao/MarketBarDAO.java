package com.MarketDataApp.dao;

import java.util.List;
import java.util.Date;

import com.MarketDataApp.dao.GenericDAO;
import com.MarketDataApp.domain.MarketBar;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface MarketBarDAO extends GenericDAO<MarketBar, Integer> {
  
	List<MarketBar> findAll();
	


}

