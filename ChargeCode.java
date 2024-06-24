package org.example;

import java.math.BigDecimal;

public class ChargeCode {
	private String chargeCode;
	private String chargeCodeDesc;
	private BigDecimal amount;
	private final int groupNo;
	
	public ChargeCode(String chargeCode, String chargeCodeDesc, BigDecimal amount, int groupNo) {
		this.chargeCode = chargeCode;
		this.chargeCodeDesc = chargeCodeDesc;
		this.amount = amount;
		this.groupNo = groupNo;
	}
	
	public String getChargeCode() {
		return chargeCode;
	}
	
	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}
	
	public String getChargeCodeDesc() {
		return chargeCodeDesc;
	}
	
	public void setChargeCodeDesc() {
		this.chargeCodeDesc = chargeCodeDesc;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public int getGroupNo() {
		return groupNo;
	}
	
	public void setGroupNo(int groupNo) {
		
	}
}
