package com.finzly.fx_trading.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fx_trading_data", catalog = "fx_trading")
@Data
@AllArgsConstructor()
@NoArgsConstructor
@Builder
public class UserData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tradingNo;
	private String name;
	private double amount;
	@Embedded
	private TradingDetails tradingData;

}
