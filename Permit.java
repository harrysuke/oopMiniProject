//Permit.java
package org.example;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

public interface Permit {
	int getRnu();
	java.lang.String getIdentityType();
	java.lang.String getName();
	java.lang.String getCompanyName();
	java.lang.String getVehicleNo();
	java.lang.String getContactNo();
	java.lang.String getApplicantCategory();
	java.lang.String getDateOfVisit();
	java.lang.String getExpiryDate();
	int getDurationOfVisit();
	java.lang.String getLocationToVisit();
	java.lang.String getPurposeOfVisit();
	BigDecimal getAmount();
	BigDecimal getTotalAmount();
	int getProcessingStatus();
	Date getSafetyPermitExpiry();
	java.lang.String getRemark();
	java.lang.String getPermitType();
	
	void getConnectionString();
	void registerVep();
	java.lang.String searchVep();
	
	//polymorphism
	String getGroupVisitors(String idn) throws SQLException;
}
