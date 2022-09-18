package com.finzly.fx_trading.controller;

import java.util.List;

import javax.validation.Valid;

import com.finzly.fx_trading.dto.UserRequest;
import com.finzly.fx_trading.dto.UserResponse;
import com.finzly.fx_trading.entity.UserData;
import com.finzly.fx_trading.exception.DataNotFoundException;
import com.finzly.fx_trading.service.BookTrade;
import com.finzly.fx_trading.service.DisplayTradeHistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TradingController {

	@Autowired
	private DisplayTradeHistory showTradeHistory;
	
	@Autowired
	private BookTrade bookTrade;
	
	@PostMapping("/book-trade")
	private ResponseEntity<String> bookTrade(@RequestBody @Valid UserRequest userRequest) {
		return new ResponseEntity<>(bookTrade.bookTrade(userRequest),HttpStatus.CREATED);
	}
	
	@GetMapping("/trade-history")
	private ResponseEntity<List<UserResponse>> tradeHistory() throws DataNotFoundException{
		return new ResponseEntity<>(showTradeHistory.tradeHistory(),HttpStatus.OK);
	}
	
	@GetMapping("/exit")
	private ResponseEntity<String> exit(){
		return new ResponseEntity<>("Bye - have a good day",HttpStatus.OK);
	}
	
	//sortDir = asc,desc
	//sortField  = tradingNo,name
	@GetMapping("/trade-history/{pageNumber}/{sortField}/{sortDir}")
	private ResponseEntity<List<UserResponse>> tradeHistoryWithPaginationSorting(@PathVariable ("pageNumber") int pageNumber,
														@PathVariable ("sortField") String sortField,
														@PathVariable ("sortDir") String sortDir) throws DataNotFoundException{
		int pageSize = 5;
		Page<UserData> page = showTradeHistory.findPage(pageNumber,pageSize, sortField, sortDir);
		List<UserResponse> userResponseList = showTradeHistory
											.getCurrentPageContent(page.getContent());
		return new ResponseEntity<>(userResponseList,HttpStatus.OK);
	}
}
