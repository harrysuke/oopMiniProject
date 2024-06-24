package org.example;

import java.sql.*;

public class VepTest{
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/tbpsns";
        String username = "root";
        String password = "";
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();

            String strSelect = "SELECT * FROM vep";
            ResultSet rset = stmt.executeQuery(strSelect);

            System.out.println("The record selected are:");
            int rowCount =0;

            while (rset.next()) {
                String rnu = rset.getString("rnu");
                String nric = rset.getString("NRICPassportNo");
                String name = rset.getString("Name");
                String company = rset.getString("CompanyName");
                String vehicleNo = rset.getString("VehicleNo");
                String contactNo = rset.getString("ContactNo");
                String dateOfVisit = rset.getString("DateofVisit");
                String expiryDate = rset.getString("ExpiryDate");
                String locationtoVisit = rset.getString("LocationtoVisit");
                String purposeofVisit = rset.getString("PurposeofVisit");
                System.out.println("RNU: " + rnu + "\nNRIC: " + nric + "\nName: " + name + "\nCompany: " + company
                        + "\nVehicle No: " + vehicleNo + "\nContact No: " + contactNo + "\nDate of visit: "
                        + dateOfVisit + "\nExpiry Date: " + expiryDate + "\nLocation to visit: " + locationtoVisit
                        + "\nPurpose of visit: " + purposeofVisit + "\n");
                ++rowCount;
            }
            System.out.println("Total number of records " + rowCount);
        }catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}