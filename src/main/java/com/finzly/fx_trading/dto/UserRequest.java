package com.finzly.fx_trading.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	
	@NotNull(message = "Name shouldn't be null")
	@NotEmpty(message= "Name can't be empty")
	@Pattern(regexp = "^[A-Za-z][A-Za-z0-9_ ]{2,24}$",message = "invalid username")
	private String name;
	@NotNull(message = "currencyPair is mandatory")
	@NotEmpty(message= "currencyPair can't be empty")
	@Pattern(regexp="USDINR",message = "Currently USDINR allowed")
	private String currencyPair;
	@NotNull(message = "amount is mandatory")
	@Min(1)
	private double amount;
}
