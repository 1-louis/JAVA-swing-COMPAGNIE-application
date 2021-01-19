/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compagnieaerienneswing.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author damie
 */
public class MySQL {
    
    public Connection getConnection() throws SQLException{
        String DB_URL = "jdbc:mysql://localhost:3306/airbabouche?serverTimezone=UTC";
       //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airbabouche?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");

        
        String USER = "root";
        String PASS = "";
        
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
    
    
    // CREATE
    public String insert(String sql){
	Connection conn = null;
        Statement stmt = null;
                
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            stmt.close();
            conn.close();
        }
        catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return se.getMessage();
         } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return e.getMessage();
         } finally {
            //finally block used to close resources
            try{
               if(stmt!=null)
                  stmt.close();
            } catch(SQLException se2) {
                return se2.getMessage();
            } // nothing we can do

            try{
               if(conn!=null)
                  conn.close();
            } catch(SQLException se) {
               se.printStackTrace();
               return se.getMessage();
            }//end finally try
         }//end try
        
        return "OK";
    }
    
    // UPDATE
    public String update(String sql){
        Connection conn = null;
        Statement stmt = null;
                
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            stmt.close();
            conn.close();
        }
        catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return se.getMessage();
         } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return e.getMessage();
         } finally {
            //finally block used to close resources
            try{
               if(stmt!=null)
                  stmt.close();
            } catch(SQLException se2) {
                return se2.getMessage();
            } // nothing we can do

            try{
               if(conn!=null)
                  conn.close();
            } catch(SQLException se) {
               se.printStackTrace();
               return se.getMessage();
            }//end finally try
         }//end try
        
        return "OK";
    }
    
    // DELETE
    public String delete(String sql){
        Connection conn = null;
        Statement stmt = null;
                
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            stmt.close();
            conn.close();
        }
        catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            return se.getMessage();
         } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            return e.getMessage();
         } finally {
            //finally block used to close resources
            try{
               if(stmt!=null)
                  stmt.close();
            } catch(SQLException se2) {
                return se2.getMessage();
            } // nothing we can do

            try{
               if(conn!=null)
                  conn.close();
            } catch(SQLException se) {
               se.printStackTrace();
               return se.getMessage();
            }//end finally try
         }//end try
        
        return "OK";
    }
}
