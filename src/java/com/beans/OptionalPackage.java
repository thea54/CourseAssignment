/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Asus
 */
@ManagedBean(name = "optPackage")
@RequestScoped
public class OptionalPackage {

   private Integer id;
    private String name;
    private String year;
    private String semester;
    private List<Course> courses;
    private List<String> coursesStr;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<Course> getCourses() {
        return courses;
    }
  
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    
    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
}

    public List<String> getCoursesStr() {
        return coursesStr;
    }

    public void setCoursesStr(List<String> coursesStr) {
        this.coursesStr = coursesStr;
    }
    
}
