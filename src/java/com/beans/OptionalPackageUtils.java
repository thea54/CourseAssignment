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

    public static void addPackageToDB(OptionalPackage optionalPackage) {
        System.out.println("adding package"); 
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = DatabaseUtils.getConnection();
            stmt = con.prepareStatement("insert into \"Packages\"(\"name\", \"year\", \"semester\") values(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, optionalPackage.getName());
            stmt.setInt(2, optionalPackage.getYear());
            stmt.setInt(3, optionalPackage.getSemester());
            
            int affectedRows = stmt.executeUpdate();
            long id = -1;

            if (affectedRows == 0) {
                    System.out.println("Addin package failed, no rows affected.");
            } else {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                            id = generatedKeys.getLong(1);
                            System.out.println("Generated Package with id = " + id);
                    } else {
                            System.out.println("Adding package failed, no ID obtained.");
                    }
                }
            }
            
            for(String s: optionalPackage.getCoursesStr()) {
                Course course = new Course();
                course.setName(s);
                course.setYear(optionalPackage.getYear());
                course.setSemester(optionalPackage.getSemester());
                course.setMaxStudents(30);
                CourseUtils.addCourseToDB(course);
            }
            
            stmt.close();
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
}
