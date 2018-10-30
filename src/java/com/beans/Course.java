/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Asus
 */
@ManagedBean(name="course")
@RequestScoped
public class Course {
    private Integer id;
    private String name;
    private String year;
    private String semester;
    private Lecturer lecturer;
    private String lecturerStr;
    private Integer maxStudents;
    private Package packageObj;
    private String packageStr;
    
    public String getName() {
        return name;
    }
    
    public String getYear() {
        return year;
    }
    
    public String getSemester() {
        return semester;
    }
    
    public Lecturer getLecturer() {
        return lecturer;
    }
    
    public Integer getMaxStudents() {
        return maxStudents;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setYear(String year) {
        this.year = year;
    }
    
    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
    
    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }
 
    public Integer getId() {
        return id;
    }
   
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Package getPackageObj() {
        return packageObj;
    }
   
    public void setPackageObj(Package packageObj) {
        this.packageObj = packageObj;
    }

    /**
     * @return the lecturerStr
     */
    public String getLecturerStr() {
        return lecturerStr;
    }

    /**
     * @param lecturerStr the lecturerStr to set
     */
    public void setLecturerStr(String lecturerStr) {
        this.lecturerStr = lecturerStr;
    }

    /**
     * @return the packageStr
     */
    public String getPackageStr() {
        return packageStr;
    }

    /**
     * @param packageStr the packageStr to set
     */
    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    
}
