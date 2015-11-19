/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.location;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * Gets The countries and states from the database
 *
 * @author Kyle Bissell
 */
public interface LocationService {

//  public List<State> getStates();
//  public List<String> getStatesNames();
    /**
     * Gets a list of states for the country
     *
     * @param countryId The country id
     * @return returns a List of State that contains all the states in the given
     * country id
     */
    public List<State> getStatesByCountry(int countryId);

    /**
     * Gets a list of states for the country
     *
     * @param name The name of the country
     * @return a list of states that contains all states in given country
     */
    public List<State> getStatesByCountry(String name);

    /**
     * Gets the states for a given country
     *
     * @param countryId
     * @return returns a List of Strings that contains the name of all states in
     * the given country id
     */
    public List<String> getStatesNameByCountry(int countryId);

    /**
     * gets states for a given country
     *
     * @param name the name of the country
     * @return a List of Strings that contains the names of all states in the
     * given country
     */
    public List<String> getStatesNameByCountry(String name);

//  public List<State> lookupStateByName(String name);
// public List<State> lookupStateByAbbreviation(String abbreviation);
    public State lookupStateById(int id);

    /**
     * Get all of the Countries in the database
     *
     * @return A List of all Counties in the database
     */
    public List<Country> getCountries();

    /**
     * Get all of the countries in the database
     *
     * @return A list of All countries name in the database
     */
    public List<String> getCountriesNames();

    /**
     * get the Country from the database
     *
     * @param id The country id to lookup
     * @return The Country object of the given id
     */
    public Country lookupCountryById(int id);
//  public List<Country> lookupCountryByName(String name);
//  public List<Country> lookupCountryByState(State state);
//  public List<Country> lookupCountryByStateId(int stateId);
//  public List<Country> lookupCountryByStateName();

    public Integer lookupCountryIdByName(String countryName);

    public Integer lookupStateIdByName(String stateName);

    public String lookupStateAbvByName(String stateName);

    public String getStatesStringOfCountry(HttpServletRequest httpServletRequest, int id);

    public Map getStatesMapOfCountry(HttpServletRequest httpServletRequest, int id);

    public Map getCountriesNamesMap();
     public Map getCountryNames();
     public String lookupCountryCurrency(int countryId);
}
