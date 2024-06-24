package org.example;

public class Purpose {
	private String purposeCode;
	private String purposeDesc;
	public Purpose(String purposeCode, String purposeDesc) {
		super();
		this.purposeCode = purposeCode;
		this.purposeDesc = purposeDesc;
	}
	public String getPurposeCode() {
		return purposeCode;
	}
	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}
	public String getPurposeDesc() {
		return purposeDesc;
	}
	public void setPurposeDesc(String purposeDesc) {
		this.purposeDesc = purposeDesc;
	}
	
	
}
