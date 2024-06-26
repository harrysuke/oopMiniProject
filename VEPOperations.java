//VEPOperations.java
package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//A concrete class that extends the Vep class.
public class VEPOperations extends Vep {

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
                //System.out.println("RNU: " + rnu + "\nNRIC: " + nric + "\nName: " + name + "\nCompany: " + company
                //+ "\nVehicle No: " + vehicleNo + "\nContact No: " + contactNo + "\nDate of visit: "
                //+ dateOfVisit + "\nExpiry Date: " + expiryDate + "\nLocation to visit: " + locationtoVisit
                //+ "\nPurpose of visit: " + purposeofVisit + "\n");
                System.out.format("%-10s %-20s %-20s %-20s %-15s %-15s %-15s %-15s %-20s %-20s\n",
                        vep.getRnu(), vep.getNricPassportNo(), vep.getName(), vep.getCompanyName(), vep.getVehicleNo(), vep.getContactNo(), vep.getDateOfVisit(), vep.getExpiryDate(), vep.getLocationToVisit(), vep.getPurposeofVisit());
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

            String sql = "UPDATE vep SET NRICPassportNo=?, Name=?, CompanyName=?, VehicleNo=?, ContactNo=?, rnu=0 WHERE id=?";
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
                    
                    //System.out.println("RNU: " + vepResult.getRnu() + "\t");
                    //System.out.println("NRIC: " + vepResult.getNricPassportNo() + "\t");
                    //System.out.println("Name: " + vepResult.getName() + "\t");
                    //System.out.println("Company: " + vepResult.getCompanyName() + "\t");
                    //System.out.println("Vehicle No: " + vepResult.getVehicleNo() + "\t");
                    //System.out.println("Contact No: " + vepResult.getContactNo() + "\t");
                    //System.out.println("Date of visit: " + vepResult.getDateOfVisit() + "\t");
                    //System.out.println("Expiry Date: " + vepResult.getExpiryDate() + "\t");
                    //System.out.println("Location to visit: " + vepResult.getLocationtoVisit() + "\t");
                    //System.out.println("Purpose of visit: " + vepResult.getPurposeofVisit() + "\t");

                    System.out.format("%-10s %-20s %-20s %-20s %-15s %-15s %-15s %-15s %-20s %-20s\n",
                    		vepResult.getRnu(), vepResult.getNricPassportNo(), vepResult.getName(), vepResult.getCompanyName(), vepResult.getVehicleNo(), vepResult.getContactNo(), vepResult.getDateOfVisit(), vepResult.getExpiryDate(), vepResult.getLocationToVisit(), vepResult.getPurposeofVisit());
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

    //File
    public void exportToTextFile() {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vep")) {
            String filename = "exportvep.txt";
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                while (rs.next()) {
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
                    writer.println("RNU: " + vep.getRnu() + "\nNRIC: " + vep.getNricPassportNo() + "\nName: " + vep.getName() + "\nCompany: " + vep.getCompanyName()
                            + "\nVehicle No: " + vep.getVehicleNo() + "\nContact No: " + vep.getContactNo() + "\nDate of visit: "
                            + vep.getDateOfVisit() + "\nExpiry Date: " + vep.getExpiryDate() + "\nLocation to visit: " + vep.getLocationtoVisit()
                            + "\nPurpose of visit: " + vep.getPurposeofVisit() + "\n");
                }
                System.out.println("Records exported to " + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
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
}