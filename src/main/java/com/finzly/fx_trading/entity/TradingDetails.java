package com.finzly.fx_trading.entity;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor(staticName = "builder")
@NoArgsConstructor
public class TradingDetails {
	
	private String currencyPair;
	private int spotDays;
	private float rate;

}
