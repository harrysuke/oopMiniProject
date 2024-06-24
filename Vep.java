package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.StreamSupport;

public abstract class Vep implements Permit {
	private final String url = "jdbc:mysql://localhost:3306/tbpsns";
    private final String username = "root";
    private final String password = "";
    Scanner scanner = new Scanner(System.in);
	private int id;
	private String nricPassportNo;
	
	public Vep() {
        // Initialize scanner
    }
	
	public Vep(String receiptNo, String applicationType, String identityNo, String nationality, String emailAddress,
			String homeAddress, String department, String designation, String officeAddress, String officeContactNo,
			Date driverLicenseExpiryDate, Date safetyPermitStartDate, Date safetyPermitEndDate) {
		// TODO Auto-generated constructor stub
	}
	
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
                System.out.println("RNU: " + rnu + "\nNRIC: " + nric + "\nName: " + name + "\nCompany: " + company
                        + "\nVehicle No: " + vehicleNo + "\nContact No: " + contactNo + "\nDate of visit: "
                        + dateOfVisit + "\nExpiry Date: " + expiryDate + "\nLocation to visit: " + locationtoVisit
                        + "\nPurpose of visit: " + purposeofVisit + "\n");
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

            String sql = "INSERT INTO vep (NRICPassportNo, Name, CompanyName, VehicleNo, ContactNo) VALUES (?,?,?,?,?)";
            try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nric);
                pstmt.setString(2, name);
                pstmt.setString(3, companyName);
                pstmt.setString(4, vehicleNo);
                pstmt.setString(5, contactNo);
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

	public void getConnectionString() {
		
	}
	
	public void registerVep() {
		
	}
	
	public String searchVep(String nricPassportNo) {
		return nricPassportNo;
		
	}
	
	//polymorphism
	public abstract String getGroupVisitors();
}
