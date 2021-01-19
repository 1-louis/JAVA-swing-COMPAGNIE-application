/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compagnieaerienneswing.dao;

import com.compagnieaerienneswing.principal.Compte;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author damie
 */
public class ClientDAO {  

    // READ
    public ArrayList<Compte> select(String sql){
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Compte> result = new ArrayList<Compte>();
                
        try{
            MySQL mySql = new MySQL();
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = mySql.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                int id = rs.getInt("idpersonne");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String dateNaiss = String.valueOf(rs.getDate("dateNaiss"));
                String adresse = rs.getString("adresse");
                String ville = rs.getString("ville");
                String cp = rs.getString("zipcode");
                int idPays = rs.getInt("idPays");
                String mail = rs.getString("email");
                String password = rs.getString("password");
                
                Compte compte = new Compte(id, nom, prenom, dateNaiss, adresse, ville, cp, idPays, mail, password);
                result.add(compte);
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
