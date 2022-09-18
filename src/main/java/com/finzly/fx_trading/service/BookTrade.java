package com.finzly.fx_trading.service;

import com.finzly.fx_trading.dao.TradingRepository;
import com.finzly.fx_trading.dto.UserRequest;
import com.finzly.fx_trading.entity.TradingDetails;
import com.finzly.fx_trading.entity.UserData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookTrade {

	@Autowired
	private TradingRepository repo;

	private UserData userData;
	
	private UserRequest userRequest;

	private String transferAmount;
	

	public String bookTrade(UserRequest userRequest) {
		this.userRequest = userRequest;
		TradingDetails tradingData = initializeTradingData();
		userData = UserData.builder()
				.name(userRequest.getName())
				.amount(userRequest.getAmount())
				.tradingData(tradingData).build();
		repo.save(userData);
		transferAmount = CurrencyConverter.currencyConverter(userData);
		return bookingConfirmation();
	}

	private TradingDetails initializeTradingData() {
		TradingDetails obj = TradingDetails.builder(userRequest.getCurrencyPair().toUpperCase(), 2, 66.00f);
		return obj;
	}

//	public String getRate() {
//		return "you are transfering " + transferAmount + " to " + userData.getName();
//	}

	public String bookingConfirmation() {
		
		return "Trade for "+
		userData.getTradingData().getCurrencyPair()
		+" has been booked with rate "+userData.getTradingData().getRate()
		+" , The amount of "+ transferAmount +" will  be transferred in "
		+userData.getTradingData().getSpotDays()
		+" working days to "+userData.getName();
	}

}
