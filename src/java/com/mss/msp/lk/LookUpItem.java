/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.lk;

/**
 *
 * @author Greg
 */
public class LookUpItem {
    
    private String name;
    private Integer id;
    
    public LookUpItem(){
        
    }
    
    public LookUpItem(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
