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
@ManagedBean(name = "courses")
@ApplicationScoped
public class CourseUtils {
    private final static Logger LOGGER = Logger.getLogger(LecturerUtils.class.getName());
    private List<Course> allCourses;   

    @PostConstruct
    public void init() {
        allCourses = new ArrayList<>();       
    }

    public void addCourse(Course course) {
        System.out.println("adding course");       
        allCourses.add(course);
        printAllCourses();
    }

    public static void addCourseToDB(Course course) {
        System.out.println("adding course"); 
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = DatabaseUtils.getConnection();
            stmt = con.prepareStatement("insert into \"Courses\"(\"name\", \"year\", \"semester\", \"max_students\") values(?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getYear());
            stmt.setInt(3, course.getSemester());
            stmt.setInt(4, course.getMaxStudents());
            
            int affectedRows = stmt.executeUpdate();
            long id = -1;

            if (affectedRows == 0) {
                    System.out.println("Addin course failed, no rows affected.");
            } else {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                            id = generatedKeys.getLong(1);
                            System.out.println("Generated Course with id = " + id);
                    } else {
                            System.out.println("Adding course failed, no ID obtained.");
                    }
                }
            }
            
            stmt.close();
        } catch(Exception ex){
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }

    public List<Course> getAllCourses() {        
        return allCourses;
    }
    
    public static List<Course> getAllCoursesFromDB() {     
        Connection con;
        PreparedStatement stmt;
        ResultSet rs;
        List<Course> res = new ArrayList<>();
        try {
            con = DatabaseUtils.getConnection();
            stmt = con.prepareStatement("select * from \"Courses\"");
            stmt.execute();
            rs = stmt.getResultSet();

            while (rs != null && rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setMaxStudents(rs.getInt("max_students"));
                course.setYear(rs.getInt("year"));
                course.setSemester(rs.getInt("semester"));
                res.add(course);
            }
            stmt.close();
        } catch(Exception e) {
            
        }
        return res;
    }
    
    public void printAllCourses() {
        for (Course course : allCourses) {
            System.out.println(course.getName());
        }
    }
}
