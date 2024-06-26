//Pep.java
package org.example;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Pep extends Vep {
	private final String url = "jdbc:mysql://localhost:3306/tbpsns";
	private final String username = "root";
	private final String password = "";
	Scanner scanner = new Scanner(System.in);
	public int id;
	public static String receiptNo;
	public static String applicationType;
	public static String identityNo;
	public static String nationality;
	public static String emailAddress;
	public static String homeAddress;
	public static String department;
	public static String designation;
	public static String officeAddress;
	public static String officeContactNo;
	public static Date driverLicenseExpiryDate;
	public static Date safetyPermitStartDate;
	public static Date safetyPermitEndDate;
	public String shortName;

	public Pep(int id, int rnu, String nricPassportNo, String name, String companyName, String vehicleNo, String contactNo, String dateOfVisit, String expiryDate, String locationtoVisit, String purposeofVisit, String receiptNo, String applicationType, String identityNo, String nationality, String emailAddress, String homeAddress, String department, String designation, String officeAddress, String officeContactNo, Date driverLicenseExpiryDate, Date safetyPermitStartDate, Date safetyPermitEndDate) {
		super(id, rnu, nricPassportNo, name, companyName, vehicleNo, contactNo, dateOfVisit, expiryDate, locationtoVisit, purposeofVisit);
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

	public Pep(String receiptNo, String applicationType, String identityNo, String nationality, String emailAddress, String homeAddress, String department, String designation, String officeAddress, String officeContactNo, Date driverLicenseExpiryDate, Date safetyPermitStartDate, Date safetyPermitEndDate, String receiptNo1, String applicationType1, String identityNo1, String nationality1, String emailAddress1, String homeAddress1, String department1, String designation1, String officeAddress1, String officeContactNo1, Date driverLicenseExpiryDate1, Date safetyPermitStartDate1, Date safetyPermitEndDate1) {
		super(receiptNo, applicationType, identityNo, nationality, emailAddress, homeAddress, department, designation, officeAddress, officeContactNo, driverLicenseExpiryDate, safetyPermitStartDate, safetyPermitEndDate);
		this.receiptNo = receiptNo1;
		this.applicationType = applicationType1;
		this.identityNo = identityNo1;
		this.nationality = nationality1;
		this.emailAddress = emailAddress1;
		this.homeAddress = homeAddress1;
		this.department = department1;
		this.designation = designation1;
		this.officeAddress = officeAddress1;
		this.officeContactNo = officeContactNo1;
		this.driverLicenseExpiryDate = driverLicenseExpiryDate1;
		this.safetyPermitStartDate = safetyPermitStartDate1;
		this.safetyPermitEndDate = safetyPermitEndDate1;
	}



	public Pep(String receiptNo, String applicationType, String identityNo, String nationality, String emailAddress, String homeAddress, String department, String designation, String officeAddress, String officeContactNo, Date driverLicenseExpiryDate, Date safetyPermitStartDate, Date safetyPermitEndDate) {

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

	public Pep(String receiptNo, String applicationType, Object identityNo, String nationality, String emailAddress, String homeAddress, String department, String designation, String officeAddress, String officeContactNo, Date driverLicenseExpiryDate, Date safetyPermitStartDate, Date safetyPermitEndDate) {

	}

	public Pep() {

	}

	public <T> Pep(MysqlxDatatypes.Scalar.String receiptNo, MysqlxDatatypes.Scalar.String applicationType, Optional<T> identityNo, MysqlxDatatypes.Scalar.String nationality, MysqlxDatatypes.Scalar.String emailAddress, MysqlxDatatypes.Scalar.String homeAddress, MysqlxDatatypes.Scalar.String department, MysqlxDatatypes.Scalar.String designation, MysqlxDatatypes.Scalar.String officeAddress, MysqlxDatatypes.Scalar.String officeContactNo, Date driverLicenseExpiryDate, Date safetyPermitStartDate, Date safetyPermitEndDate) {
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

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getOfficeContactNo() {
		return officeContactNo;
	}

	public void setOfficeContactNo(String officeContactNo) {
		this.officeContactNo = officeContactNo;
	}

	public Date getDriverLicenseExpiryDate() {
		return driverLicenseExpiryDate;
	}

	public void setDriverLicenseExpiryDate(Date driverLicenseExpiryDate) {
		this.driverLicenseExpiryDate = driverLicenseExpiryDate;
	}

	public Date getSafetyPermitStartDate() {
		return safetyPermitStartDate;
	}

	public void setSafetyPermitStartDate(Date safetyPermitStartDate) {
		this.safetyPermitStartDate = safetyPermitStartDate;
	}

	public Date getSafetyPermitEndDate() {
		return safetyPermitEndDate;
	}

	public void setSafetyPermitEndDate(Date safetyPermitEndDate) {
		this.safetyPermitEndDate = safetyPermitEndDate;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public MysqlxDatatypes.Scalar.String getGroupVisitors(MysqlxDatatypes.Scalar.String idn) throws SQLException {
		return null;
	}
}