/*******************************************************************************
 *
 * Project : ServicesBay V1
 *
 * Package :
 *
 * Date    :  Feb 16, 2015, 7:53 PM
 *
 * Author  : Services Bay Team
 *
 * File    : DefaultDataProvider.java
 *
 * Copyright 2015 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.msp.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author miracle
 */
public class DefaultDataProvider {
    private static DefaultDataProvider _instance;
    
    
    /** Creates a new instance of DefaultDataProvider */
    public DefaultDataProvider() {
    }
    
    /**
     * @return An instance of the DataServiceLocator class
     * @throws ServiceLocatorException
     */
    public static DefaultDataProvider getInstance() throws ServiceLocatorException {
        try {
            if(_instance==null) {
                _instance = new DefaultDataProvider();
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return _instance;
    }

    public static Map stateProvider() {

        Map state = new LinkedHashMap();

        state.put("AL", "Alabama");
        state.put("AK", "Alaska");
        state.put("AZ", "Arizona");
        state.put("AR", "Arkansas");
        state.put("CA", "California");
        state.put("CO", "Colorado");
        state.put("CT", "Connecticut");
        state.put("DE", "Delaware");
        state.put("DC", "District of Columbia");
        state.put("FL", "Florida");
        state.put("GA", "Georgia");
        state.put("HI", "Hawaii");
        state.put("ID", "Idaho");
        state.put("IL", "Illinois");
        state.put("IN", "Indiana");
        state.put("IA", "Iowa");
        state.put("KS", "Kansas");
        state.put("KY", "Kentucky");
        state.put("LA", "Louisiana");
        state.put("ME", "Maine");
        state.put("MD", "Maryland");
        state.put("MA", "Massachusetts");
        state.put("MI", "Michigan");
        state.put("MN", "Minnesota");
        state.put("MS", "Mississippi");
        state.put("MO", "Missouri");
        state.put("MT", "Montana");
        state.put("NE", "Nebraska");
        state.put("NV", "Nevada");
        state.put("NH", "New Hampshire");
        state.put("NJ", "New Jersey");
        state.put("NM", "New Mexico");
        state.put("NY", "New York");
        state.put("NC", "North Carolina");
        state.put("ND", "North Dakota");
        state.put("OH", "Ohio");
        state.put("OK", "Oklahoma");
        state.put("OR", "Oregon");
        state.put("PA", "Pennsylvania");
        state.put("RI", "Rhode Island");
        state.put("SC", "South Carolina");
        state.put("SD", "South Dakota");
        state.put("TN", "Tennessee");
        state.put("TX", "Texas");
        state.put("UT", "Utah");
        state.put("VT", "Vermont");
        state.put("VA", "Virginia");
        state.put("WA", "Washington");
        state.put("WV", "West Virginia");
        state.put("WI", "Wisconsin");
        state.put("WY", "Wyoming");

        return state;

    }
}