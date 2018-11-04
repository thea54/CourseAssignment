/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class DatabaseUtils 
{ 
    private static Connection con = null; 
  
    public static Connection getConnection() { 
        if (con == null) {
            String url = "jdbc:postgresql://localhost/fii_master_db";
            String user = "postgres";
            String password = "postgres";

            try {
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection(url, user, password);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return con; 
    } 
} 