package com.finzly.fx_trading.entity;

public enum CurrencyPair {
	
	USDINR;
	@Override
	public String toString() {
		switch (this) {
		case USDINR:
			return "USDINR";
		default:
			return "unspecified";
		}
	}
}
