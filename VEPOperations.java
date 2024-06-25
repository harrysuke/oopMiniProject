//VEPOperations.java
package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;

//A concrete class that extends the Vep class.
public class VEPOperations extends Vep {
    private final String url = "jdbc:mysql://localhost:3306/tbpsns";
    private final String username = "root";
    private final String password = "";
    Scanner scanner = new Scanner(System.in);

    public void read(){
        try(Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();
        //String sql = "SELECT rnu, NRICPassportNo, Name, CompanyName, VehicleNo, ContactNo, DateofVisit, ExpiryDate, LocationtoVisit, PurposeofVisit FROM vep";
        ResultSet rs = stmt.executeQuery("SELECT rnu, NRICPassportNo, Name, CompanyName, VehicleNo, ContactNo, DateofVisit, ExpiryDate, LocationtoVisit, PurposeofVisit FROM vep")
        ){
            int rowCount = 0;
            while (rs.next()){
                String rnu = rs.getString("rnu");
                String nric = rs.getString("NRICPassportNo");
                String name = rs.getString("Name");
                String company = rs.getString("CompanyName");
                String vehicleNo = rs.getString("VehicleNo");
                String contactNo = rs.getString("ContactNo");
                String dateOfVisit = rs.getString("DateofVisit");
                String expiryDate = rs.getString("ExpiryDate");
                String locationtoVisit = rs.getString("LocationtoVisit");
                String purposeofVisit = rs.getString("PurposeofVisit");
                //System.out.println("RNU: " + rnu + "\nNRIC: " + nric + "\nName: " + name + "\nCompany: " + company
                        //+ "\nVehicle No: " + vehicleNo + "\nContact No: " + contactNo + "\nDate of visit: "
                        //+ dateOfVisit + "\nExpiry Date: " + expiryDate + "\nLocation to visit: " + locationtoVisit
                        //+ "\nPurpose of visit: " + purposeofVisit + "\n");
                System.out.format("%-10s %-20s %-20s %-20s %-15s %-15s %-15s %-15s %-20s %-20s\n",
                        rnu, nric, name, company, vehicleNo, contactNo, dateOfVisit, expiryDate, locationtoVisit, purposeofVisit);
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

            String sql = "UPDATE vep SET NRICPassportNo=?, Name=?, CompanyName=?, VehicleNo=?, ContactNo=? WHERE id=?";
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, nric);
                pstmt.setString(2, name);
                pstmt.setString(3, companyName);
                pstmt.setString(4, vehicleNo);
                pstmt.setString(5, contactNo);
                pstmt.setInt(6, id);
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

            //Date currentDate = new Date();
            //String currentDateStr = df.format(currentDate);

            String sql = "INSERT INTO vep (rnu, NRICPassportNo, Name, CompanyName, VehicleNo, ContactNo, DateofVisit, ExpiryDate, LocationtoVisit, PurposeofVisit) VALUES (0,?,?,?,?,?,?,?,0,0)";
            try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nric);
                pstmt.setString(2, name);
                pstmt.setString(3, companyName);
                pstmt.setString(4, vehicleNo);
                pstmt.setString(5, contactNo);
                pstmt.setDate(6, new java.sql.Date(new Date().getTime()));
                pstmt.setDate(7, new java.sql.Date(new Date().getTime()));
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

            String sql = "DELETE FROM vep WHERE id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
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

            String sql = "SELECT * FROM vep WHERE Name LIKE ? ";
            try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "%"+keyword+"%");
                ResultSet rs = pstmt.executeQuery();
                int rowCount = 0;

                while (rs.next()){
                    String rnu = rs.getString("rnu");
                    String nric = rs.getString("NRICPassportNo");
                    String name = rs.getString("Name");
                    String company = rs.getString("CompanyName");
                    String vehicleNo = rs.getString("VehicleNo");
                    String contactNo = rs.getString("ContactNo");
                    String dateOfVisit = rs.getString("DateofVisit");
                    String expiryDate = rs.getString("ExpiryDate");
                    String locationtoVisit = rs.getString("LocationtoVisit");
                    String purposeofVisit = rs.getString("PurposeofVisit");
                    System.out.println("RNU: " + rnu + "\nNRIC: " + nric + "\nName: " + name + "\nCompany: " + company
                            + "\nVehicle No: " + vehicleNo + "\nContact No: " + contactNo + "\nDate of visit: "
                            + dateOfVisit + "\nExpiry Date: " + expiryDate + "\nLocation to visit: " + locationtoVisit
                            + "\nPurpose of visit: " + purposeofVisit + "\n");
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
    public void callGetGroupVisitors() throws SQLException {
    	System.out.println("Enter Group Visitor ID: ");
        String idn = scanner.nextLine();
        String result = getGroupVisitors(idn);
        System.out.println(result);
    }
    
    //File
    public void exportToTextFile() {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vep")) {
            String filename = "exportvep.txt";
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                while (rs.next()) {
                    String rnu = rs.getString("rnu");
                    String nric = rs.getString("NRICPassportNo");
                    String name = rs.getString("Name");
                    String company = rs.getString("CompanyName");
                    String vehicleNo = rs.getString("VehicleNo");
                    String contactNo = rs.getString("ContactNo");
                    String dateOfVisit = rs.getString("DateofVisit");
                    String expiryDate = rs.getString("ExpiryDate");
                    String locationtoVisit = rs.getString("LocationtoVisit");
                    String purposeofVisit = rs.getString("PurposeofVisit");
                    writer.println("RNU: " + rnu + "\nNRIC: " + nric + "\nName: " + name + "\nCompany: " + company
                            + "\nVehicle No: " + vehicleNo + "\nContact No: " + contactNo + "\nDate of visit: "
                            + dateOfVisit + "\nExpiry Date: " + expiryDate + "\nLocation to visit: " + locationtoVisit
                            + "\nPurpose of visit: " + purposeofVisit + "\n");
                }
                System.out.println("Records exported to " + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getRnu() {
        return 0;
    }

    @Override
    public String getIdentityType() {
        return "";
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getCompanyName() {
        return "";
    }

    @Override
    public String getVehicleNo() {
        return "";
    }

    @Override
    public String getContactNo() {
        return "";
    }

    @Override
    public String getApplicantCategory() {
        return "";
    }

    @Override
    public String getDateOfVisit() {
        return null;
    }

    @Override
    public String getExpiryDate() {
        return null;
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
}