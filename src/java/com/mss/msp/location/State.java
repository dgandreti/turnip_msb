/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.location;

/**
 *
 * @author Kyle Bissell
 */
public class State {
  private String name;
  private String abbreviation;
  private Integer id;
  private Integer countryId;


  public State() {
    name ="";
    abbreviation="";
    id=null;
    countryId=null;
  }

  public State(String name, String abbreviation) {
    this.name = name;
    this.abbreviation = abbreviation;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCountryId() {
    return countryId;
  }

  public void setCountryId(Integer countryId) {
    this.countryId = countryId;
  }

     public void setId(String id)
   {
     if(id != null && ! id.equals("") && Integer.parseInt(id) >= 0){
       this.id = Integer.parseInt(id);
     }
   }

}
