/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

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

    public void addCourseToDB(Course course) {
        //to be implemented
    }

    public List<Course> getAllCourses() {        
        return allCourses;
    }
    
    public void printAllCourses() {
        for (Course course : allCourses) {
            System.out.println(course.getName());
        }
    }

    
}
