package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class PEPOperations extends Pep {
	private final String url = "jdbc:mysql://localhost:3306/tbpsns";
    private final String username = "root";
    private final String password = "";
    Scanner scanner = new Scanner(System.in);
    
	public PEPOperations(String receiptNo, String applicationType, String identityNo, String nationality,
			String emailAddress, String homeAddress, String department, String designation, String officeAddress,
			String officeContactNo, Date driverLicenseExpiryDate, Date safetyPermitStartDate,
			Date safetyPermitEndDate) {
		super(receiptNo, applicationType, identityNo, nationality, emailAddress, homeAddress, department, designation,
				officeAddress, officeContactNo, driverLicenseExpiryDate, safetyPermitStartDate, safetyPermitEndDate);
		// TODO Auto-generated constructor stub
	}

    public void registerPep(Scanner scanner) {
        System.out.println("Register PEP");
        try(Connection conn = DriverManager.getConnection(url, username, password);
                Statement stmt = conn.createStatement();
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO pep (NRICPassportNo, Name, CompanyName, VehicleNo, ContactNo) VALUES (?,?,?,?,?)")) {
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
    }
	
	public void readPep(Scanner scanner) {
		System.out.println("Read all PEP records");
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM pep";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                int rowCount = 0;
                while (rs.next()) {
                    String nric = rs.getString("IdentityNo");
                    String name = rs.getString("Name");
                    String companyName = rs.getString("CompanyName");
                    String vehicleNo = rs.getString("VehicleNo");
                    String contactNo = rs.getString("ContactNo");
                    System.out.println("NRIC: " + nric + "\nName: " + name + "\nCompany: " + companyName
                            + "\nVehicle No: " + vehicleNo + "\nContact No: " + contactNo + "\n");
                    ++rowCount;
                }
                System.out.println("Total number of records " + rowCount);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

    public void searchPep(Scanner scanner) {
    	System.out.println("Search PEP by name");
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {
            System.out.println("Enter keyword to search:");
            String keyword = scanner.nextLine();

            String sql = "SELECT * FROM pep WHERE Name LIKE ? ";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "%" + keyword + "%");
                ResultSet rs = pstmt.executeQuery();
                int rowCount = 0;

                while (rs.next()) {
                    String nric = rs.getString("NRICPassportNo");
                    String name = rs.getString("Name");
                    String companyName = rs.getString("CompanyName");
                    String vehicleNo = rs.getString("VehicleNo");
                    String contactNo = rs.getString("ContactNo");
                    System.out.println("NRIC: " + nric + "\nName: " + name + "\nCompany: " + companyName
                            + "\nVehicle No: " + vehicleNo + "\nContact No: " + contactNo + "\n");
                    ++rowCount;
                }
                System.out.println("Total number of records " + rowCount);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePep(Scanner scanner) {
    	System.out.println("Update PEP details");
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            //USER INPUT
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

            String sql = "UPDATE pep SET NRICPassportNo=?, Name=?, CompanyName=?, VehicleNo=?, ContactNo=? WHERE NRICPassportNo=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nric);
                pstmt.setString(2, name);
                pstmt.setString(3, companyName);
                pstmt.setString(4, vehicleNo);
                pstmt.setString(5, contactNo);
                pstmt.setString(6, nric);
                int countUpdated = pstmt.executeUpdate();

                System.out.println("Record updated successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePep(Scanner scanner) {
    	System.out.println("Delete PEP records");
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Enter ID number:");
            int id = Integer.parseInt(scanner.nextLine());

            String sql = "DELETE FROM pep WHERE id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int rowsDeleted = pstmt.executeUpdate();
                System.out.println(rowsDeleted + " record(s) deleted");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  //polymorphism
    public void callGetGroupVisitors() {
    	System.out.println("Enter Group Visitor ID: ");
        String idn = scanner.toString();
        String result = getGroupVisitors(idn);
        System.out.println(result);
    }
    
  //File
    public void exportToTextFile() {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM pep")) {
            String filename = "exportpep.txt";
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                while (rs.next()) {
                    String rnu = rs.getString("rnu");
                    String identityNo = rs.getString("IdentityNo");
                    String name = rs.getString("Name");
                    String shortName = rs.getString("ShortName");
                    String contactNo = rs.getString("ContactNo");
                    String eMailAddress = rs.getString("EMailAddress");
                    String homeAddress = rs.getString("HomeAddress");
                    String expiryDate = rs.getString("ExpiryDate");
                    String locationtoVisit = rs.getString("LocationtoVisit");
                    String purposeofVisit = rs.getString("PurposeofVisit");
                    writer.println("RNU: " + rnu + "\nidentityNo: " + identityNo + "\nName: " + name + "\nShortName: " + shortName
                            + "\nContactNo: " + contactNo + "\nHomeAddress: " + homeAddress + "\nExpiryDate: "
                            + expiryDate + "\nExpiry Date: " + expiryDate + "\nLocation to visit: " + locationtoVisit
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
    
}
