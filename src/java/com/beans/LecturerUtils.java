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
@ManagedBean(name = "lecturers")
@ApplicationScoped
public class LecturerUtils {
    private final static Logger LOGGER = Logger.getLogger(LecturerUtils.class.getName());
    private List<Lecturer> allLecturers;
    

    @PostConstruct
    public void init() {
        allLecturers = new ArrayList<>();       
    }

    public void addLecturer(Lecturer lecturer) {
        System.out.println("adding lecturer");       
        allLecturers.add(lecturer);
        printAllLecturers();
    }

    public void addLecturerToDB(Lecturer lecturer) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = DatabaseUtils.getConnection();
            stmt = con.prepareStatement("insert into \"Lecturers\"(\"name\") values(?);", Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, lecturer.getName());      
            
            int affectedRows = stmt.executeUpdate();
            long id = -1;

            if (affectedRows == 0) {
                    System.out.println("Addin lecturer failed, no rows affected.");
            } else {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                            id = generatedKeys.getLong(1);
                            System.out.println("Generated lecturer with id = " + id);
                    } else {
                            System.out.println("Adding lecturer failed, no ID obtained.");
                    }
                }
            }            
       
            Course course = new Course();
            course.setName(lecturer.getCourseName());               
            CourseUtils.addCourseToDB(course); 
            
            stmt.close();
          
        } catch(Exception ex){
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }

    public List<Lecturer> getAllLecturers() {        
        return allLecturers;
    }
    
    public void printAllLecturers() {
        for (Lecturer lecturer : allLecturers) {
            System.out.println(lecturer.getName());
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
