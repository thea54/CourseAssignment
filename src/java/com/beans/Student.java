package com.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Asus
 */
@ManagedBean(name = "student")
@RequestScoped
public class Student {
    private String name = "";
    private String mail = "";
    private List<Course> availableCourses;
    private List<String> availableCoursesStr;
    private String[] selectedCourses;

    @PostConstruct
    public void init() {
        availableCourses = CourseUtils.getAllCoursesFromDB();
        System.out.println("available courses:" + availableCourses.size());
        availableCoursesStr = new ArrayList<String>();
        availableCoursesStr.add("Thea");
        availableCoursesStr.add("Tavy");
        availableCoursesStr.add("Test");
        
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Course> getAvailableCourses() {
        return availableCourses;
    }

    public void setAvailableCourses(List<Course> availableCourses) {
        this.availableCourses = availableCourses;
    }
    
    public List<String> getAvailableCoursesStr() {
        return availableCoursesStr;
    }

    public void setAvailableCoursesStr(List<String> availableCoursesStr) {
        this.availableCoursesStr = availableCoursesStr;
    }

    public String[] getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(String[] selectedCourses) {
        this.selectedCourses = selectedCourses;
    }
}
