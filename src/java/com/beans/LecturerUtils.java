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
        //to be implemented
    }

    public List<Lecturer> getAllLecturers() {        
        return allLecturers;
    }
    
    public void printAllLecturers() {
        for (Lecturer lecturer : allLecturers) {
            System.out.println(lecturer.getName());
        }
    }

}
