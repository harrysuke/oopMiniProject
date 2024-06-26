//Vep.java
package org.example;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

public class Vep extends VepAbstract {
    private int id;
    private int rnu;
    private String nricPassportNo;
    private String name;
    private String companyName;
    private String vehicleNo;
    private String contactNo;
    private String dateOfVisit;
    private String expiryDate;
    private String locationtoVisit;
    private String purposeofVisit;

    public Vep() {
        // Initialize scanner
    }

    @Override
    public void read() {
        VEPOperations vepOperations = new VEPOperations();
        vepOperations.read();
    }

    public Vep(int id, int rnu, String nricPassportNo, String name, String companyName, String vehicleNo, String contactNo, String dateOfVisit, String expiryDate, String locationtoVisit, String purposeofVisit) {
        this.id = id;
        this.rnu = rnu;
        this.nricPassportNo = nricPassportNo;
        this.name = name;
        this.companyName = companyName;
        this.vehicleNo = vehicleNo;
        this.contactNo = contactNo;
        this.dateOfVisit = dateOfVisit;
        this.expiryDate = expiryDate;
        this.locationtoVisit = locationtoVisit;
        this.purposeofVisit = purposeofVisit;
    }

    public Vep(String receiptNo, String applicationType, String identityNo, String nationality, String emailAddress, String homeAddress, String department, String designation, String officeAddress, String officeContactNo, Date driverLicenseExpiryDate, Date safetyPermitStartDate, Date safetyPermitEndDate) {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getRnu() {
        return rnu;
    }

    @Override
    public String getIdentityType() {
        return "";
    }

    public void setRnu(int rnu) {
        this.rnu = rnu;
    }

    public String getNricPassportNo() {
        return nricPassportNo;
    }

    public void setNricPassportNo(String nricPassportNo) {
        this.nricPassportNo = nricPassportNo;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    @Override
    public String getContactNo() {
        return contactNo;
    }

    @Override
    public String getApplicantCategory() {
        return "";
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(String dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    @Override
    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public int getDurationOfVisit() {
        return 0;
    }

    @Override
    public String getLocationToVisit() {
        return "";
    }

    @Override
    public String getPurposeOfVisit() {
        return "";
    }

    @Override
    public BigDecimal getAmount() {
        return null;
    }

    @Override
    public BigDecimal getTotalAmount() {
        return null;
    }

    @Override
    public int getProcessingStatus() {
        return 0;
    }

    @Override
    public Date getSafetyPermitExpiry() {
        return null;
    }

    @Override
    public String getRemark() {
        return "";
    }

    @Override
    public String getPermitType() {
        return "";
    }

    @Override
    public void getConnectionString() {

    }

    @Override
    public void registerVep() {

    }

    @Override
    public String searchVep() {
        return "";
    }

    @Override
    public String getGroupVisitors(String idn) throws SQLException {
        return "";
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getLocationtoVisit() {
        return locationtoVisit;
    }

    public void setLocationtoVisit(String locationtoVisit) {
        this.locationtoVisit = locationtoVisit;
    }

    public String getPurposeofVisit() {
        return purposeofVisit;
    }

    public void setPurposeofVisit(String purposeofVisit) {
        this.purposeofVisit = purposeofVisit;
    }
}
