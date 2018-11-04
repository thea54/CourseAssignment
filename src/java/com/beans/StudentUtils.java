/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author Asus
 */
@ManagedBean(name = "students")
@ApplicationScoped
public class StudentUtils {

    public void addStudentToDB(Student student) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = DatabaseUtils.getConnection();
            stmt = con.prepareStatement("insert into \"Students\"(\"name\", \"mail\") values(?, ?);", Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, student.getName());      
            stmt.setString(2, student.getMail());     
            
            int affectedRows = stmt.executeUpdate();
            long id = -1;

            if (affectedRows == 0) {
                System.out.println("Addin student failed, no rows affected.");
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
            stmt.close();

            for(String c: student.getSelectedCourses()) {
                String[] splits = c.split(" ");
                stmt = con.prepareStatement("insert into \"StudentsCourses\"(\"course_id\", \"student_id\") values(?, ?);", Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, Integer.valueOf(splits[0]));      
                stmt.setInt(2, (int) id);
                stmt.executeUpdate();
                stmt.close();
                System.out.println("Student allocated");
            }
        } catch(Exception ex){
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }
    
}
