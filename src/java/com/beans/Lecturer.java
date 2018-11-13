/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Asus
 */
@ManagedBean(name="lecturer")
@RequestScoped
public class Lecturer implements Serializable{

    private Integer id;
    private String name;
    private Course course;
    private String courseName = "";

    String bookmarkURL;

    public String getBookmarkURL() {
            return bookmarkURL;
    }

    public void setBookmarkURL(String bookmarkURL) {
            this.bookmarkURL = bookmarkURL;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    
    public Course getCourse() {
        return course;
    }

    
    public void setCourse(Course course) {
        this.course = course;
    }

   
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
}
   
   
    
}
