/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compagnieaerienneswing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author damie
 */
public class PersonneDAO {
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
