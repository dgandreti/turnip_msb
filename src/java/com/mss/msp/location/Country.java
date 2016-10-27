/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.location;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Kyle Bissell
 */
public class Country {
  private String name;
  private String currency;
  private Set<State> states;
  private Integer id;

  public Country() {
    this.states=null;
  }

  public Country(String name, String currency) {
    this.name = name;
    this.currency = currency;
    this.states=null;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Set<State> getStates() {
    return states;
  }

  public void setStates(org.hibernate.collection.PersistentSet states) {
      this.states = states;
  }

  public void addState(State state)
  {
    this.states.add(state);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
      System.out.println("Country-->setId(int)"+id);
    this.id = id;
  }

   public void setId(String id)
   {
       System.out.println("Country-->setId(String)"+id);
     if(id != null && ! id.equals("") && Integer.parseInt(id) >= -1){
       this.id = Integer.parseInt(id);
     }
   }


}
