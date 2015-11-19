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

  public String getStatesForCountry()
  {
    states = new ArrayList<State>();
    String resultType = SUCCESS;
    states = ServiceLocator.getLocationService().getStatesByCountry(countryId);
    return resultType;
  }

 public String getStockSymbol(){
    String resultType=SUCCESS;
    stockSymbolString=ServiceLocator.getLocationService().lookupCountryCurrency(countryId);
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
