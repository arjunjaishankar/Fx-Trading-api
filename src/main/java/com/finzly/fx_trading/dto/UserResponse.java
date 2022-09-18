package com.finzly.fx_trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

	private long tradingNo;
	private String name;
	private String transferAmount;
	private String currencyPair;
	private int spotDays;
	private float rate;
}
