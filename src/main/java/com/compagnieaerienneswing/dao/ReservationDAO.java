/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compagnieaerienneswing.dao;

import com.compagnieaerienneswing.principal.Reservation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author damie
 */
public class ReservationDAO {
    // READ
    public ArrayList<Reservation> select(String sql){
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Reservation> result = new ArrayList<Reservation>();
                
        try{
            MySQL mySql = new MySQL();
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = mySql.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                int id = rs.getInt("idvol");
                int idClient = rs.getInt("client_idpersonne");
                String confirmation = rs.getString("confirmation");
                int idPersonne = rs.getInt("personne_idpersonne");
                
                Reservation resa = new Reservation(id, idClient, confirmation, idPersonne);               
                result.add(resa);
            }
            
            rs.close();            
            stmt.close();
            conn.close();
        }
        catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
         } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
         } finally {
            //finally block used to close resources
            try{
               if(stmt!=null)
                  stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do

            try{
               if(conn!=null)
                  conn.close();
            } catch(SQLException se) {
               se.printStackTrace();
            }//end finally try
         }//end try
        
        return result;
    }
}
