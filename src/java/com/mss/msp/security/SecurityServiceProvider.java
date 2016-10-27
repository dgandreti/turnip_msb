/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.security;

/**
 * *****************************************************************************
 *
 * Project : ServicesBay V1
 *
 * Package :
 *
 * Date : Feb 16, 2015, 7:53 PM
 *
 * Author : Services Bay Team
 *
 * File : SecurityServiceProvider.java
 *
 * Copyright 2015 Miracle Software Systems, Inc. All rights reserved. MIRACLE
 * SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.ServiceLocatorException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 *
 * @author miracle
 */
public class SecurityServiceProvider {

    private static SecurityServiceProvider _instance;

    /**
     * Creates a new instance of DefaultDataProvider
     */
    public SecurityServiceProvider() {
    }
    /**
     * @return An instance of the DataServiceLocator class
     * @throws ServiceLocatorException
     */
    /*public static SecurityServiceProvider getInstance() throws ServiceLocatorException {
     try {
     if(_instance==null) {
     _instance = new SecurityServiceProvider();
     }
     } catch (Exception ex) {
     throw new ServiceLocatorException(ex);
     }
     return _instance;
     }*/
    private static Logger log = Logger.getLogger(SecurityServiceProvider.class);
    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUM = "0123456789";
    private static final String SPL_CHARS = "!@#$%^&*_=+-/";

    /**
     * *****************************************************************************
     *
     * Author : 
     *
     * Method : generateRandamSecurityKey() is for generating Random Security Key
     *
     * *****************************************************************************
     */
    public static String generateRandamSecurityKey(int minLen, int maxLen, int noOfCAPSAlpha,
            int noOfDigits, int noOfSplChars) {
        log.info("************Entered into SecurityServiceProvider :: generateRandamSecurityKey*********");
        if (minLen > maxLen) {
            throw new IllegalArgumentException("Min. Length > Max. Length!");
        }
        if ((noOfCAPSAlpha + noOfDigits + noOfSplChars) > minLen) {
            throw new IllegalArgumentException("Min. Length should be atleast sum of (CAPS, DIGITS, SPL CHARS) Length!");
        }
        Random rnd = new Random();
        int len = rnd.nextInt(maxLen - minLen + 1) + minLen;
        char[] pswd = new char[len];
        int index = 0;
        for (int i = 0; i < noOfCAPSAlpha; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
        }
        for (int i = 0; i < noOfDigits; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
        }
        for (int i = 0; i < noOfSplChars; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
        }
        for (int i = 0; i < len; i++) {
            if (pswd[i] == 0) {
                pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
            }
        }
        log.info("************End of SecurityServiceProvider :: generateRandamSecurityKey*********");
        return pswd.toString();
    }

    private static int getNextIndex(Random rnd, int len, char[] pswd) {
        int index = rnd.nextInt(len);
        while (pswd[index = rnd.nextInt(len)] != 0);
        return index;
    }

    /**
     * *****************************************************************************
     *
     * Author : 
     *
     * Method : getEncrypt() is for getting encrypted password
     *
     * *****************************************************************************
     */
    public static String getEncrypt(String plainText, String salt) {
        System.out.println(salt+"==----------------------------------------------------------------------------"
                + "--------------------------"+plainText);
        log.info("************Entered into SecurityServiceProvider :: getEncrypt*********");
        //String salt = "Random";
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update((plainText + salt).getBytes());
            System.out.println(messageDigest.toString()+"==----------------------------beftor that ------------------------------------------------"
                + "--------------------------"+messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        String encryptedPassword = (new BigInteger(messageDigest.digest())).toString(16);
        System.out.println("==----------------------------beftor that ------------------------------------------------"
                + "--------------------------"+encryptedPassword);
        //System.out.println("Encrypted Password: " + encryptedPassword);
        log.info("************End of SecurityServiceProvider :: getEncrypt*********");
        return encryptedPassword;

    }

    /**
     * *****************************************************************************
     *
     * Author : 
     *
     * Method : doRedirect() is for getting home action for user
     *
     * *****************************************************************************
     */
    public static String doRedirect(int orgId, String typeofuser, int primaryrole) throws ServiceLocatorException, SQLException {
        log.info("************Entered into SecurityServiceProvider :: doRedirect*********");
        String actionName = "";
        String actionStatus = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = ConnectionProvider.getInstance().getConnection();
        //  String queryString = "SELECT action_name FROM home_redirect_action where org_id=" + orgId + " and type_of_user='" + typeofuser + "' and primaryrole=" + primaryrole + " and status='Active'";
        String queryString = "SELECT action_name, status FROM home_redirect_action where org_id=" + orgId + " and type_of_user='" + typeofuser + "' and primaryrole=" + primaryrole;
        System.out.println("-----------------------------------------------------"+queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                actionName = resultSet.getString("action_name");
                actionStatus = resultSet.getString("status");
            }
            if (actionStatus.equals("In-Active")) {
                return "../general/logout.action";
            }
            log.info("************ SecurityServiceProvider :: doRedirect***Query******"+queryString);
            log.info("************ SecurityServiceProvider :: doRedirect*****Action Name****"+actionName);
            log.info("************End of SecurityServiceProvider :: doRedirect*********");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                // resultSet Object Checking if it's null then close and set null
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }

                if (statement != null) {
                    statement.close();
                    statement = null;
                }

                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }

        return actionName;

    }

    public boolean DoAuthorizationCheck(int orgid, List roleMap, String access_name, int ismanager, int isLead, int accesstoBoth) throws ServiceLocatorException, SQLException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        boolean check = false;
        connection = ConnectionProvider.getInstance().getConnection();
        String queryString = "select role_id from site_access_rules where org_id=" + orgid + " and access_flag=1 and access_name like '" + access_name + "'";
        // System.out.println("queryString-->" + queryString);
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);

            for (int i = 0; i < roleMap.size(); i++) {
                if (resultSet.next()) {
                    if (roleMap.get(i).equals(resultSet.getString("role_id"))) {
                        check = true;
                        break;
                    }

                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                throw new ServiceLocatorException(ex);
            }
        }
        return check;
    }
    /*    public static void main(String[] args) {
     int noOfCAPSAlpha = 4;
     int noOfDigits = 4;
     int noOfSplChars = 4;
     int minLen = 30;
     int maxLen = 50;
 
       
     String pswd = generateRandamSecurityKey(minLen, maxLen,
     noOfCAPSAlpha, noOfDigits, noOfSplChars);
     System.out.println("Len = " + pswd.length() + ", " + new String(pswd));
        
     }*/
}
