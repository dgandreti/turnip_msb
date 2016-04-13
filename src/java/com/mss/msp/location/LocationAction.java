/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.location;

import com.mss.msp.util.ServiceLocator;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;

/**
 *
 * @author Kyle Bissell
 */
public class LocationAction extends ActionSupport{
  private String countryName="";
  private int countryId;
  private String stockSymbolString="";
  private List<State> states;

  
  /**
     ***********************************************************
     *
     * @getStatesForCountry() to get the  states for country
     * 
     *
     ***********************************************************
     */
  public String getStatesForCountry()
  {
       System.out.println("********************LocationAction :: getStatesForCountry Action Start*********************");
    states = new ArrayList<State>();
    String resultType = SUCCESS;
    states = ServiceLocator.getLocationService().getStatesByCountry(countryId);
       System.out.println("********************LocationAction :: getStatesForCountry Action Start*********************");  
    return resultType;
  }

  /**
     ***********************************************************
     *
     * @getStockSymbol() to get the  stock symbol
     * 
     *
     ***********************************************************
     */
 public String getStockSymbol(){
     System.out.println("********************LocationAction :: getStockSymbol Action Start*********************");
    String resultType=SUCCESS;
    stockSymbolString=ServiceLocator.getLocationService().lookupCountryCurrency(countryId);
     System.out.println("********************LocationAction :: getStockSymbol Action Start*********************");
    return resultType;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }


  public String getStockSymbolString() {
    return stockSymbolString;
  }

  public void setStockSymbolString(String stockSymbolString) {
    this.stockSymbolString = stockSymbolString;
  }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }



}
