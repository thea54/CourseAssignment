/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author Asus
 */
@ManagedBean(name = "optPackages")
@ApplicationScoped
public class OptionalPackageUtils {

    private final static Logger LOGGER = Logger.getLogger(LecturerUtils.class.getName());
    private List<OptionalPackage> allPackages;   

    @PostConstruct
    public void init() {
        allPackages = new ArrayList<>();       
    }

    public void addPackage(OptionalPackage optionalPackage) {
        System.out.println("adding package");       
        allPackages.add(optionalPackage);
        printAllPackages();
    }

    public void addPackageToDB(OptionalPackage optionalPackage) {
        System.out.println("adding package"); 
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = getConnection();
            stmt = con.prepareStatement("insert into \"packages\"(\"id\", \"name\", \"year\", \"semester\") values(1, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, optionalPackage.getName());
            stmt.setString(2, optionalPackage.getYear());
            stmt.setString(3, optionalPackage.getSemester());
            
            int affectedRows = stmt.executeUpdate();
            long id = -1;

            if (affectedRows == 0) {
                    System.out.println("Addin package failed, no rows affected.");
            } else {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                    id = generatedKeys.getLong(1);
                            } else {
                                    System.out.println("Adding package failed, no ID obtained.");
                            }
                    }
            }
            
            stmt.close();
            con.close();
        } catch(Exception ex){
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }

    public List<OptionalPackage> getAllPackages() {        
        return allPackages;
    }
    
    public void printAllPackages() {
        for (OptionalPackage optionalPackage : allPackages) {
            System.out.println(optionalPackage.getName());
        }
    }
    
    private Connection getConnection() throws SQLException {
        Connection con = null;
        String url = "jdbc:postgresql://localhost/postgres";
        String user = "postgres";
        String password = "postgres";

        try {
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
                System.out.println(ex.getMessage());
        }
        if(con == null) {
                throw new SQLException("Driver now found");
        }

        return con;
    }
   
    
}
