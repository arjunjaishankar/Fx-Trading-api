package com.finzly.fx_trading.service;

import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;

import com.finzly.fx_trading.entity.UserData;

public class CurrencyConverter {

	public static String currencyConverter(UserData obj) {

		  double transferAmount = (obj.getAmount() * obj.getTradingData().getRate());
		  
		  Format formatter = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		  String amount = formatter.format(transferAmount);
		  
		  return amount;
	}
}
