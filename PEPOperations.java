//PEPOperations.java
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
import java.util.Optional;
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
		super(receiptNo, applicationType, Optional.ofNullable(identityNo), nationality, emailAddress, homeAddress, department, designation,
				officeAddress, officeContactNo, driverLicenseExpiryDate, safetyPermitStartDate, safetyPermitEndDate);
		// TODO Auto-generated constructor stub
	}

    public void registerPep(Scanner scanner) {
        System.out.println("Register PEP");
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

            Pep pep = new Pep();
            pep.setNricPassportNo(nric);
            pep.setName(name);
            pep.setCompanyName(companyName);
            pep.setVehicleNo(vehicleNo);
            pep.setContactNo(contactNo);

            String sql = "INSERT INTO pep (IdentityNo, Name, CompanyName, VehicleNo, ContactNo) VALUES (?,?,?,?,?)";
            try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, pep.getNricPassportNo());
                pstmt.setString(2, pep.getName());
                pstmt.setString(3, pep.getCompanyName());
                pstmt.setString(4, pep.getVehicleNo());
                pstmt.setString(5, pep.getContactNo());
                int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected+" record added successfully");
            }catch (SQLException e){
                e.printStackTrace();
            }
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
                    Pep pep = new Pep();
                    pep.setNricPassportNo(rs.getString("IdentityNo"));
                    pep.setName(rs.getString("Name"));
                    pep.setCompanyName(rs.getString("CompanyName"));
                    pep.setVehicleNo(rs.getString("VehicleNo"));
                    pep.setContactNo(rs.getString("ContactNo"));
                    System.out.format("%-20s %-20s %-20s %-20s %-20s\n",
                            pep.getNricPassportNo(), pep.getName(), pep.getCompanyName(), pep.getVehicleNo(), pep.getContactNo());
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

            Pep pep = new Pep();
            pep.setName(keyword);

            String sql = "SELECT * FROM pep WHERE Name LIKE ? ";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "%" + pep.getName() + "%");
                ResultSet rs = pstmt.executeQuery();
                int rowCount = 0;

                while (rs.next()) {
                    Pep pepResult = new Pep();
                    pepResult.setNricPassportNo(rs.getString("IdentityNo"));
                    pepResult.setName(rs.getString("Name"));
                    pepResult.setCompanyName(rs.getString("CompanyName"));
                    pepResult.setVehicleNo(rs.getString("VehicleNo"));
                    pepResult.setContactNo(rs.getString("ContactNo"));
                    //System.out.println("NRIC: " + pepResult.getNricPassportNo() + "\nName: " + pepResult.getName() + "\nCompany: " + pepResult.getCompanyName()
                    //+ "\nVehicle No: " + pepResult.getVehicleNo() + "\nContact No: " + pepResult.getContactNo() + "\n");
                    //System.out.print("NRIC: " + pepResult.getNricPassportNo() + "\tName: " + pepResult.getName() + "\tCompany: " + pepResult.getCompanyName()
                    //+ "\tVehicle No: " + pepResult.getVehicleNo() + "\tContact No: " + pepResult.getContactNo() + "\n");
                    System.out.format("%-10s %-20s %-20s %-20s %-15s %-15s %-15s %-15s %-20s %-20s\n",
                            pepResult.getRnu(), pepResult.getNricPassportNo(), pepResult.getName(), pepResult.getCompanyName(), pepResult.getVehicleNo(), pepResult.getContactNo(), pepResult.getDateOfVisit(), pepResult.getExpiryDate(), pepResult.getLocationToVisit(), pepResult.getPurposeofVisit());
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

            System.out.println("Enter the ID of the record to update:");
            int id = scanner.nextInt();

            Pep pep = new Pep();
            pep.setNricPassportNo(nric);
            pep.setName(name);
            pep.setCompanyName(companyName);
            pep.setVehicleNo(vehicleNo);
            pep.setContactNo(contactNo);
            pep.setId(id);

            String sql = "UPDATE pep SET IdentityNo=?, Name=?, CompanyName=?, VehicleNo=?, ContactNo=? WHERE id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, pep.getNricPassportNo());
                pstmt.setString(2, pep.getName());
                pstmt.setString(3, pep.getCompanyName());
                pstmt.setString(4, pep.getVehicleNo());
                pstmt.setString(5, pep.getContactNo());
                pstmt.setInt(6, pep.getId());
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

            Pep pep = new Pep();
            pep.setId(id);

            String sql = "DELETE FROM pep WHERE id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, pep.getId());
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
                    Pep pep = new Pep();
                    pep.setRnu(Integer.parseInt(rs.getString("rnu")));
                    pep.setIdentityNo(rs.getString("IdentityNo"));
                    pep.setName(rs.getString("Name"));
                    pep.setShortName(rs.getString("ShortName"));
                    pep.setContactNo(rs.getString("ContactNo"));
                    pep.setEmailAddress(rs.getString("EMailAddress"));
                    pep.setHomeAddress(rs.getString("HomeAddress"));
                    pep.setExpiryDate(rs.getString("ExpiryDate"));
                    pep.setLocationtoVisit(rs.getString("LocationtoVisit"));
                    pep.setPurposeofVisit(rs.getString("PurposeofVisit"));
                    writer.println("RNU: " + pep.getRnu() + "\nidentityNo: " + pep.getIdentityNo() + "\nName: " + pep.getName() + "\nShortName: " + pep.getShortName()
                            + "\nContactNo: " + pep.getContactNo() + "\nEmailAddress: " + pep.getEmailAddress() + "\nHomeAddress: "
                            + pep.getHomeAddress() + "\nExpiry Date: " + pep.getExpiryDate() + "\nLocation to visit: " + pep.getLocationtoVisit()
                            + "\nPurpose of visit: " + pep.getPurposeofVisit() + "\n");
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
