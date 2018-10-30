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

    public void addPackageToDB(OptionalPackage optionalPackagep) {
        //to be implemented
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
