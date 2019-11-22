/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.protectsoft.apieeweb.startup;

/**
 *
 * @author piperidis.a
 */
public class Storage<T> {

    private T type;
    private int cap;
    
    public Storage(){
        cap = 10;
    }
    
    public Storage(T t){
        this();
        this.type = t;
    }
    
    public int getCap() {
        System.out.println("getCap"+this);
        return cap;
    }
    
    public void setCap(int c) {
        System.out.println("setCap");
        this.cap = c;
    }
    
    public void increase() {
        this.cap++;
    }
    
    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }
}
