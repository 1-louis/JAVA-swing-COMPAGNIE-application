/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compagnieaerienneswing.dao;

import com.compagnieaerienneswing.principal.Vol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author damie
 */
public class VolDAO {
    // READ
    public ArrayList<Vol> select(String sql){
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Vol> result = new ArrayList<Vol>();
                
        try{
            MySQL mySql = new MySQL();
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = mySql.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                int id = rs.getInt("idvol");
                int places = rs.getInt("place");
                String intitule = rs.getString("intitule");
                int aeroportDepart = rs.getInt("aeroport_depart");
                int aeroportArrivee = rs.getInt("aeroport_arrive");
                String dateDepart = rs.getString("date_depart");
                String dateArrivee = rs.getString("date_arrive");
                int idCompagnie = rs.getInt("idcompagnie");
                
                Vol vol = new Vol(id, places, intitule, aeroportDepart, aeroportArrivee, dateDepart, dateArrivee, idCompagnie);
                
                result.add(vol);
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
    
    public ArrayList<Vol> selectJoinEscale(String sql){
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Vol> result = new ArrayList<Vol>();
                
        try{
            MySQL mySql = new MySQL();
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = mySql.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                int id = rs.getInt("idvol");
                int places = rs.getInt("place");
                String intitule = rs.getString("intitule");
                int aeroportDepart = rs.getInt("aeroport_depart");
                int aeroportArrivee = rs.getInt("aeroport_arrive");
                String dateDepart = rs.getString("date_depart");
                String dateArrivee = rs.getString("date_arrive");
                int idCompagnie = rs.getInt("idcompagnie");
                int aeroportEscale = rs.getInt("aeroport_idaeroport");
                String dateDepartEscale = rs.getString("date_depart_escale");
                String dateArriveeEscale = rs.getString("date_arrive_escale");
                
                Vol vol = new Vol(id, places, intitule, aeroportDepart, aeroportArrivee, dateDepart, dateArrivee, idCompagnie, aeroportEscale, dateDepartEscale, dateArriveeEscale);
                
                result.add(vol);
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
    
    // CREATE
    public int insert(String sql) throws SQLException{
	Connection conn = null;
        int insertedId = 0;

        try{
            MySQL mySql = new MySQL();
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = mySql.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }

            conn.close();
        }
        catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
         } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
         } finally {
            try{
               if(conn!=null)
                  conn.close();
            } catch(SQLException se) {
               se.printStackTrace();
            }//end finally try
         }//end try
        
        return insertedId;
    }
}
