/*
 * AuthProperties.java
 *
 * Created on December 15, 2007, 7:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.mss.msp.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author miracle
 */
public class SecurityProperties {
    
    private static final String BUNDLE_NAME = "com/mss/msp/config/security";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
    
    public static String getProperty(String property) {
        try {
            return RESOURCE_BUNDLE.getString(property);
        } catch (MissingResourceException e) {
            return '!' + property + '!';
        }
    }
}
