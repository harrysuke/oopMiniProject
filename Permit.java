//Permit.java
package org.example;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

public interface Permit {
	int getRnu();
	String getIdentityType();
	String getName();
	String getCompanyName();
	String getVehicleNo();
	String getContactNo();
	String getApplicantCategory();
	String getDateOfVisit();
	String getExpiryDate();
	int getDurationOfVisit();
	String getLocationToVisit();
	String getPurposeOfVisit();
	BigDecimal getAmount();
	BigDecimal getTotalAmount();
	int getProcessingStatus();
	Date getSafetyPermitExpiry();
	String getRemark();
	String getPermitType();
	
	void getConnectionString();
	void registerVep();
	String searchVep();
	
	//polymorphism
	String getGroupVisitors(String idn) throws SQLException;
}
