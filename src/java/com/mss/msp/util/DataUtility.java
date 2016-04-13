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
 * File : DataUtility.java
 *
 * Copyright 2015 Miracle Software Systems, Inc. All rights reserved. MIRACLE
 * SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.msp.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.lang.Math.round;
import static java.lang.Math.random;
import static java.lang.Math.pow;
import static java.lang.Math.abs;
import static java.lang.Math.min;
import static org.apache.commons.lang.StringUtils.leftPad;
import org.apache.commons.net.util.Base64;

public class DataUtility {

    public static DataUtility _instance;

    private DataUtility() {
    }

    public static DataUtility getInstance() throws ServiceLocatorException {
        if (_instance == null) {
            _instance = new DataUtility();
        }
        return _instance;
    }

    // This method is used for getting the Map object keys in the format of
    // String array
    public String[] readMapKeys(Map<String, String> mapObj)
            throws ServiceLocatorException {

        String keysArray[] = new String[mapObj.size()];

        Set<String> keysSet = mapObj.keySet();
        Iterator<String> keysItr = keysSet.iterator();

        int index = 0;
        while (keysItr.hasNext()) {
            keysArray[index] = (String) keysItr.next();
            index++;
        }

        return keysArray;
    }

    public String listToStringConverter(String usrcategoryList) throws ServiceLocatorException {
        StringBuffer sb = new StringBuffer();
        //System.err.println("usrcatlist-->" + usrcategoryList);
        usrcategoryList = usrcategoryList.replace("[", "");
        usrcategoryList = usrcategoryList.replace("]", "");


        if (usrcategoryList.length() <= 1) {
            sb.append(usrcategoryList);
        } else {
            String[] atrarray = usrcategoryList.split(",");
            //System.err.println("string array length-->" + atrarray.length);
            for (int i = 0; i <= atrarray.length; i++) {
                sb.append(atrarray[i]);
                sb.append(",");
            }
        }

        return sb.toString();
        //2,3,4
    }

    public List getTimsheetAccessingRolesList() {

        List roleLit = new ArrayList();

        String rolesvalues = Properties.getProperty("TIMESHEETREPORTSTOROLES");

        String[] stringlist = rolesvalues.split(",");

        //System.err.println("string length" + stringlist.length);

        for (int i = 0; i < stringlist.length; i++) {
            int rollist = Integer.parseInt(stringlist[i]);
            roleLit.add(rollist);
        }

        return roleLit;
    }

    /**
     * *************************************
     *
     * @formatName()
     *
     * @Author:Jagan Chukkalal<jchukkala@miraclesoft.com>
     *
     * @Created Date:08/05/2015
     *
     * For USe:To format the name entered by user Eg: ABCdef formatted to Abcdef
     * Eg: ABcd EfGh formatted to Abcd Efgh
     * *************************************
     */
    public String formatName(String name) {

        //String sr = name;
        //System.out.println(sr);
        String[] str;
        str = name.split(" ");
        int length = str.length;
        String[] str1 = new String[length];
        String strAppend = "";

        //System.out.println("length is--->" + length);
        for (int count = 0; count < str.length; count++) {
            str1[count] = str[count].substring(0, 1).toUpperCase() + str[count].substring(1).toLowerCase();
        }
        for (int count = 0; count < str1.length; count++) {
            if (count + 1 == str1.length) {
                strAppend = strAppend + str1[count];
            } else {
                strAppend = strAppend + str1[count] + " ";
            }
        }
        //System.out.println("String appended--->" + strAppend);
        return strAppend;

    }

    public String getMonthNameByNumber(int month) {
        String monthName = "";
        if (month == 1) {
            monthName = "Jan";
        }
        if (month == 2) {
            monthName = "Feb";
        }
        if (month == 3) {
            monthName = "Mar";
        }
        if (month == 4) {
            monthName = "Apr";
        }
        if (month == 5) {
            monthName = "May";
        }
        if (month == 6) {
            monthName = "Jun";
        }
        if (month == 7) {
            monthName = "Jul";
        }
        if (month == 8) {
            monthName = "Aug";
        }
        if (month == 9) {
            monthName = "Sep";
        }
        if (month == 10) {
            monthName = "Oct";
        }
        if (month == 11) {
            monthName = "Nov";
        }
        if (month == 12) {
            monthName = "Dec";
        }
        return monthName;
    }

    /**
     * *************************************
     *
     * @getRandomHexadecimal()
     *
     * @Author:Vinod Kumar Siram<vsiram@miraclesoft.com>
     *
     * @Created Date:09/29/2015
     *
     * For USe:To generate random number of requested size
     * *************************************
     * to Execute :  String pswd = getRandomHexadecimal(32);
     */
    public static String getRandomHexadecimal(int length) {
    StringBuffer sb = new StringBuffer();
    for (int i = length; i > 0; i -= 12) {
      int n = min(12, abs(i));
      sb.append(leftPad(Long.toString(round(random() * pow(36, n)), 36), n, '0'));
    }
    return sb.toString();
  }
     /**
     * *************************************
     *
     * @encrypted()
     *
     * @Author:Jagan Chukkala<jchukkala@miraclesoft.com>
     *
     * @Created Date:11/18/2015
     *
     * For USe:To encrypt the string entered by user 
     * 
     * *************************************
     */
    public static String encrypted(String texto) {
        return new String(Base64.encodeBase64(texto.getBytes()));
    }
    /**
     * *************************************
     *
     * @decrypted()
     *
     * @Author:Jagan Chukkala<jchukkala@miraclesoft.com>
     *
     * @Created Date:11/18/2015
     *
     * For USe:To Decrypt the string that is encrypted already  
     * 
     * *************************************
     */
    public static String decrypted(String texto) {
        return new String(Base64.decodeBase64(texto.getBytes()));
    }
}
