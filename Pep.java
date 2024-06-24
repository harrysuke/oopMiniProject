package org.example;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Pep extends Vep {
	private final String url = "jdbc:mysql://localhost:3306/tbpsns";
	private final String username = "root";
	private final String password = "";
	Scanner scanner = new Scanner(System.in);
	private int id;
	private final String receiptNo;
	private final String applicationType;
	private final String identityNo;
	private final String nationality;
	private final String emailAddress;
	private final String homeAddress;
	private final String department;
	private final String designation;
	private final String officeAddress;
	private final String officeContactNo;
	private final Date driverLicenseExpiryDate;
	private final Date safetyPermitStartDate;
	private final Date safetyPermitEndDate;

	public Pep(String receiptNo, String applicationType, String identityNo, String nationality, String emailAddress,
			String homeAddress, String department, String designation, String officeAddress, String officeContactNo,
			Date driverLicenseExpiryDate, Date safetyPermitStartDate, Date safetyPermitEndDate) {
		super(receiptNo, applicationType, identityNo, nationality, emailAddress, homeAddress, department, designation,
				officeAddress, officeContactNo, driverLicenseExpiryDate, safetyPermitStartDate, safetyPermitEndDate);
		this.receiptNo = receiptNo;
		this.applicationType = applicationType;
		this.identityNo = identityNo;
		this.nationality = nationality;
		this.emailAddress = emailAddress;
		this.homeAddress = homeAddress;
		this.department = department;
		this.designation = designation;
		this.officeAddress = officeAddress;
		this.officeContactNo = officeContactNo;
		this.driverLicenseExpiryDate = driverLicenseExpiryDate;
		this.safetyPermitStartDate = safetyPermitStartDate;
		this.safetyPermitEndDate = safetyPermitEndDate;
	}

	@Override
	public String getGroupVisitors(String idn) {
	    List<String> groupVisitors = new ArrayList<>();
	    try (Connection conn = DriverManager.getConnection(url, username, password);
	         Statement stmt = conn.createStatement()) {
	        String sql = "SELECT * FROM  viewveppep WHERE idno LIKE ?";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, "%" + idn + "%");
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                String idno = rs.getString("idno");
	                String name = rs.getString("Name");
	                String companyName = rs.getString("CompanyName");
	                String permitType = rs.getString("PermitType");
	                String amount = rs.getString("amount");
	                groupVisitors.add("idno: " + idno + "\nName: " + name + "\nCompany: " + companyName + "\nPermitType: " + permitType + "\nAmount: " + amount);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return String.join("\n", groupVisitors);
	}

	@Override
	public int getRnu() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getIdentityType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCompanyName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVehicleNo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContactNo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getApplicantCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDateOfVisit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getExpiryDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDurationOfVisit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLocationToVisit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPurposeOfVisit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getTotalAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getProcessingStatus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Date getSafetyPermitExpiry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemark() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPermitType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchVep() {
		// TODO Auto-generated method stub
		return null;
	}
}