//Vep.java
package org.example;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

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

    @Override
    public void read(){
        try(Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            //String sql = "SELECT rnu, NRICPassportNo, Name, CompanyName, VehicleNo, ContactNo, DateofVisit, ExpiryDate, LocationtoVisit, PurposeofVisit FROM vep";
            ResultSet rs = stmt.executeQuery("SELECT rnu, NRICPassportNo, Name, CompanyName, VehicleNo, ContactNo, DateofVisit, ExpiryDate, LocationtoVisit, PurposeofVisit FROM vep")
        ){
            int rowCount = 0;
            while (rs.next()){
                Vep vep = new Vep();
                vep.setRnu(Integer.parseInt(rs.getString("rnu")));
                vep.setNricPassportNo(rs.getString("NRICPassportNo"));
                vep.setName(rs.getString("Name"));
                vep.setCompanyName(rs.getString("CompanyName"));
                vep.setVehicleNo(rs.getString("VehicleNo"));
                vep.setContactNo(rs.getString("ContactNo"));
                vep.setDateOfVisit(rs.getString("DateofVisit"));
                vep.setExpiryDate(rs.getString("ExpiryDate"));
                vep.setLocationtoVisit(rs.getString("LocationtoVisit"));
                vep.setPurposeofVisit(rs.getString("PurposeofVisit"));
                System.out.println("RNU: " + vep.getRnu() + "\nNRIC: " + vep.getNricPassportNo() + "\nName: " + vep.getName() + "\nCompany: " + vep.getCompanyName()
                        + "\nVehicle No: " + vep.getVehicleNo() + "\nContact No: " + vep.getContactNo() + "\nDate of visit: "
                        + vep.getDateOfVisit() + "\nExpiry Date: " + vep.getExpiryDate() + "\nLocation to visit: " + vep.getLocationtoVisit()
                        + "\nPurpose of visit: " + vep.getPurposeofVisit() + "\n");
                ++rowCount;
            }
            System.out.println("Total number of records " + rowCount);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void update(){
        try(Connection conn = DriverManager.getConnection(url, username, password)){

            //USER INPUT
            System.out.println("Updating VEP record\n");

            System.out.println("Enter NRIC/Passport number:");
            String nric = scanner.nextLine();

            System.out.println("Enter Name:");
            String name = scanner.nextLine();

            System.out.println("Enter Company Name:");
            String companyName = scanner.nextLine();

            System.out.println("Enter vehicle number:");
            String vehicleNo = scanner.nextLine();

            System.out.println("Enter contact number:");
            String contactNo = scanner.nextLine();

            System.out.println("Enter the ID of the record to update:");
            int id = scanner.nextInt();

            Vep vep = new Vep();
            vep.setNricPassportNo(nric);
            vep.setName(name);
            vep.setCompanyName(companyName);
            vep.setVehicleNo(vehicleNo);
            vep.setContactNo(contactNo);
            vep.setId(id);

            String sql = "UPDATE vep SET NRICPassportNo=?, Name=?, CompanyName=?, VehicleNo=?, ContactNo=? WHERE id=?";
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, vep.getNricPassportNo());
                pstmt.setString(2, vep.getName());
                pstmt.setString(3, vep.getCompanyName());
                pstmt.setString(4, vep.getVehicleNo());
                pstmt.setString(5, vep.getContactNo());
                pstmt.setInt(6, vep.getId());
                int countUpdated = pstmt.executeUpdate();

                System.out.println("Record updated successfully");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void create(){
        try(Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement()){

            //USER INPUT
            System.out.println("Creating new VEP record\n");

            System.out.println("Enter NRIC/Passport number:");
            String nric = scanner.nextLine();

            System.out.println("Enter Name:");
            String name = scanner.nextLine();

            System.out.println("Enter Company Name:");
            String companyName = scanner.nextLine();

            System.out.println("Enter vehicle number:");
            String vehicleNo = scanner.nextLine();

            System.out.println("Enter contact number:");
            String contactNo = scanner.nextLine();

            Vep vep = new Vep();
            vep.setNricPassportNo(nric);
            vep.setName(name);
            vep.setCompanyName(companyName);
            vep.setVehicleNo(vehicleNo);
            vep.setContactNo(contactNo);

            String sql = "INSERT INTO vep (NRICPassportNo, Name, CompanyName, VehicleNo, ContactNo) VALUES (?,?,?,?,?)";
            try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, vep.getNricPassportNo());
                pstmt.setString(2, vep.getName());
                pstmt.setString(3, vep.getCompanyName());
                pstmt.setString(4, vep.getVehicleNo());
                pstmt.setString(5, vep.getContactNo());
                int rowsAffected = pstmt.executeUpdate();

                System.out.println(rowsAffected+" record added successfully");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(){
        try(Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement()){
            System.out.println("Enter ID to delete");
            int id = scanner.nextInt();

            Vep vep = new Vep();
            vep.setId(id);

            String sql = "DELETE FROM vep WHERE id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, vep.getId());
                int rowsDeleted = pstmt.executeUpdate();
                System.out.println(rowsDeleted+" record(s) deleted");
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void search(){
        try(Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement()){
            System.out.println("Enter keyword to search:");
            String keyword = scanner.nextLine();

            Vep vep = new Vep();
            vep.setName(keyword);

            String sql = "SELECT * FROM vep WHERE Name LIKE ? ";
            try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "%"+vep.getName()+"%");
                ResultSet rs = pstmt.executeQuery();
                int rowCount = 0;

                while (rs.next()){
                    Vep vepResult = new Vep();
                    vepResult.setRnu(Integer.parseInt(rs.getString("rnu")));
                    vepResult.setNricPassportNo(rs.getString("NRICPassportNo"));
                    vepResult.setName(rs.getString("Name"));
                    vepResult.setCompanyName(rs.getString("CompanyName"));
                    vepResult.setVehicleNo(rs.getString("VehicleNo"));
                    vepResult.setContactNo(rs.getString("ContactNo"));
                    vepResult.setDateOfVisit(rs.getString("DateofVisit"));
                    vepResult.setExpiryDate(rs.getString("ExpiryDate"));
                    vepResult.setLocationtoVisit(rs.getString("LocationtoVisit"));
                    vepResult.setPurposeofVisit(rs.getString("PurposeofVisit"));
                    System.out.println("RNU: " + vepResult.getRnu() + "\n");
                    System.out.println("NRIC: " + vepResult.getNricPassportNo() + "\n");
                    System.out.println("Name: " + vepResult.getName() + "\n");
                    System.out.println("Company: " + vepResult.getCompanyName() + "\n");
                    System.out.println("Vehicle No: " + vepResult.getVehicleNo() + "\n");
                    System.out.println("Contact No: " + vepResult.getContactNo() + "\n");
                    System.out.println("Date of visit: " + vepResult.getDateOfVisit() + "\n");
                    System.out.println("Expiry Date: " + vepResult.getExpiryDate() + "\n");
                    System.out.println("Location to visit: " + vepResult.getLocationtoVisit() + "\n");
                    System.out.println("Purpose of visit: " + vepResult.getPurposeofVisit() + "\n");
                    ++rowCount;
                }
                System.out.println("Total number of records "+rowCount);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    //polymorphism
    public String getGroupVisitors(String idno) throws SQLException{
            List<String> groupVisitors = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 Statement stmt = conn.createStatement()) {
                String sql = "SELECT idno, Name, CompanyName, PermitType, amount FROM viewveppep WHERE idno = ?"; // Select specific columns
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, idno);
                    try (ResultSet rs = stmt.executeQuery(sql)) {
                        if (!rs.next()){
                            return "No record found for ID: "+idno;
                        }
                        while (rs.next()) {
                            String idn = rs.getString("idno");
                            String name = rs.getString("Name");
                            String companyName = rs.getString("CompanyName");
                            String permitType = rs.getString("PermitType");
                            String amount = rs.getString("amount"); // Assuming amount is a String

                            groupVisitors.add(String.format("idno: %s\nName: %s\nCompany: %s\nPermit Type: %s\nAmount: %s\n", idn, name, companyName, permitType, amount));
                        }
                    }
                }
            }
            return String.join("", groupVisitors); // Join list elements for a single string
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
    public MysqlxDatatypes.Scalar.String getGroupVisitors(MysqlxDatatypes.Scalar.String idn) throws SQLException {
        return null;
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
