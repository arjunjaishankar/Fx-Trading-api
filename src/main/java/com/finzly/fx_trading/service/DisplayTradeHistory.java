package com.finzly.fx_trading.service;

import java.util.ArrayList;
import java.util.List;

import com.finzly.fx_trading.dao.TradingRepository;
import com.finzly.fx_trading.dto.UserResponse;
import com.finzly.fx_trading.entity.UserData;
import com.finzly.fx_trading.exception.DataNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DisplayTradeHistory {

	@Autowired
	private TradingRepository repo;

	public List<UserResponse> tradeHistory() throws DataNotFoundException {

		List<UserData> userDataList = repo.findAll();
		if(userDataList.isEmpty())
			throw new DataNotFoundException("You haven't done any trading.");
		return getCurrentPageContent(userDataList);
	}

	public Page<UserData> findPage(int pageNo, int pageSize, String sortField, String sortDirection) throws DataNotFoundException {

		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<UserData> userDataPages =  repo.findAll(pageable);
		if(userDataPages.isEmpty())
			throw new DataNotFoundException("You haven't done any trading.");
			return userDataPages;
	}

	public List<UserResponse> getCurrentPageContent(List<UserData> userDataList) {
		List<UserResponse> userResponseList = new ArrayList<>();
		for (UserData userData : userDataList) {
			
			UserResponse userResponse = UserResponse.builder()
										.tradingNo(userData.getTradingNo())
										.name(userData.getName())
										.currencyPair(userData.getTradingData().getCurrencyPair())
										.spotDays(userData.getTradingData().getSpotDays())
										.rate(userData.getTradingData().getRate())
										.transferAmount(CurrencyConverter.currencyConverter(userData))
										.build();
			
			userResponseList.add(userResponse);
		}
		return userResponseList;
	}

}
