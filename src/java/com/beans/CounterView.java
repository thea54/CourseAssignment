/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author iilie
 */
@ManagedBean
@ViewScoped
public class CounterView implements Serializable {
     
    private int number = 0;
    
    public int getNumber() {
        return number;
    }
 
    public int increment() {
        number++;
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}