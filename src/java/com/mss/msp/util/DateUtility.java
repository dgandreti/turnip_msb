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
 * File : DateUtility.java
 *
 * Copyright 2015 Miracle Software Systems, Inc. All rights reserved. MIRACLE
 * SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.msp.util;

//import com.sun.org.apache.bcel.internal.generic.DCMPG;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.lang.math.JVMRandom;
import org.apache.commons.lang.time.DateUtils;

/**
 *
 * @author miracle
 */
public class DateUtility {

    private static DateUtility _instance;
    private String smonth;
    private String sday;
    private String syear;
    private String convertedDate;
    private Timestamp timestamp;
    private Timestamp dateTimestampObj;
    private Date dateActual;

    /**
     * @return An instance of the DataServiceLocator class
     * @throws ServiceLocatorException
     */
    public static DateUtility getInstance() throws ServiceLocatorException {
        try {
            if (_instance == null) {
                _instance = new DateUtility();
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return _instance;
    }

    public String convertStringToMySQLDate(String dateString) {
        SimpleDateFormat sdfInput = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = sdfInput.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return sdfOutput.format(date);
    }

    public String convertStringToMySQLDate1(String dateString) {
        SimpleDateFormat sdfInput = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = sdfInput.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return sdfOutput.format(date);
    }

    /*
     * Return :: String in usTimeStampToTimeStampHyphen format
     *
     */
    public String usTimeStampToTimeStampHyphen(String usTimeStamp) {
        int indDash1 = usTimeStamp.indexOf("/", 0);
        int indDash2 = usTimeStamp.indexOf("/", indDash1 + 1);
        if (indDash1 != -1 || indDash2 != -1) {
            if ((indDash1 == 2 || indDash1 == 1) && (indDash2 == 5 || indDash2 == 4 || indDash2 == 3)) {
                smonth = usTimeStamp.trim().substring(0, indDash1);
                sday = usTimeStamp.trim().substring(indDash1 + 1, indDash2);
                syear = usTimeStamp.trim().substring(indDash2 + 1, indDash2 + 5);
                convertedDate = syear + "-" + smonth + "-" + sday + " " + usTimeStamp.substring(indDash2 + 5);
            }
        }


        return convertedDate;
    }

    /**
     *
     * @param oldDate
     * @return returns sqlDateFormat
     */
    public String usDateToSqldate(String oldDate) {


        /* date Format is mm/dd/yyyy */
        int ind1 = oldDate.indexOf("/", 0);
        int ind2 = oldDate.indexOf("/", ind1 + 1);

        if ((ind1 == 2 || ind1 == 1) && (ind2 == 5 || ind2 == 4 || ind2 == 3)) {
            smonth = oldDate.trim().substring(0, ind1);
            sday = oldDate.trim().substring(ind1 + 1, ind2);
            syear = oldDate.trim().substring(ind2 + 1, oldDate.length());
            convertedDate = syear + "/" + smonth + "/" + sday;
        } else if (ind1 == 4 && (ind2 == 7 || ind2 == 6)) {
            convertedDate = oldDate.trim();
        }

        return convertedDate;

    }

    /**
     *
     * @param oldDate sqlformat String
     * @return US Date Format
     */
    public String sqlDateToUSdate(String oldDate) {

        int ind1 = oldDate.indexOf("/", 0);
        int ind2 = oldDate.indexOf("/", ind1 + 1); // returns index of  "/" in given String
        if (ind1 == 4 && (ind2 == 7 || ind2 == 6)) {
            syear = oldDate.trim().substring(0, ind1);
            smonth = oldDate.trim().substring(ind1 + 1, ind2);
            sday = oldDate.trim().substring(ind2 + 1, oldDate.length());
            convertedDate = smonth + "/" + sday + "/" + syear; // concatnation of String Objects month,day,year
        } else if ((ind1 == 2 || ind1 == 1) && ind2 == 5) {
            convertedDate = oldDate.trim();  // returns same format of String
        }

        return convertedDate;
    }

    /**
     *
     * @param oldDate sqlformat String
     * @return US Date Format
     */
    public String sqlDate_ToUSdate(String oldDate) {

        int ind1 = oldDate.indexOf("-", 0);
        int ind2 = oldDate.indexOf("-", ind1 + 1); // returns index of  "/" in given String
        if (ind1 == 4 && (ind2 == 7 || ind2 == 6)) {
            syear = oldDate.trim().substring(0, ind1);
            smonth = oldDate.trim().substring(ind1 + 1, ind2);
            sday = oldDate.trim().substring(ind2 + 1, oldDate.length());
            convertedDate = smonth + "-" + sday + "-" + syear; // concatnation of String Objects month,day,year
        } else if ((ind1 == 2 || ind1 == 1) && ind2 == 5) {
            convertedDate = oldDate.trim();  // returns same format of String
        }

        return convertedDate;
    }

    /**
     *
     * @param usTimeStamp US TimeStamp in String Format
     * @return Sql format String
     */
    public String usTimeStampToSqlTimeStamp(String usTimeStamp) {

        /* date Format is mm/dd/yyyy HH:mm:ss */
        int indDash1 = usTimeStamp.indexOf("/", 0);
        int indDash2 = usTimeStamp.indexOf("/", indDash1 + 1);


        if (indDash1 == -1 || indDash2 == -1) {

            int _ind1 = usTimeStamp.indexOf("-", 0);
            int _ind2 = usTimeStamp.indexOf("-", _ind1 + 1); //returns index of " - " in given String Object

            if ((_ind1 == 2 || _ind1 == 1) && (_ind2 == 5 || _ind2 == 4 || _ind2 == 3)) {

                smonth = usTimeStamp.trim().substring(0, _ind1);
                sday = usTimeStamp.trim().substring(_ind1 + 1, _ind2);
                syear = usTimeStamp.trim().substring(_ind2 + 1, _ind2 + 5);
                convertedDate = syear + "-" + smonth + "-" + sday + " " + usTimeStamp.substring(_ind2 + 5); //return format is yyyy-MM-dd HH:mm:ss

            } else if (_ind1 == 4 && (_ind2 == 7 || _ind2 == 6)) {
                convertedDate = usTimeStamp.trim(); //returns same String format
            }

        } else {

            if ((indDash1 == 2 || indDash1 == 1) && (indDash2 == 5 || indDash2 == 4 || indDash2 == 3)) {
                smonth = usTimeStamp.trim().substring(0, indDash1);
                sday = usTimeStamp.trim().substring(indDash1 + 1, indDash2);
                syear = usTimeStamp.trim().substring(indDash2 + 1, indDash2 + 5);
                convertedDate = syear + "/" + smonth + "/" + sday + " " + usTimeStamp.substring(indDash2 + 5);  //return format is yyyy/MM/dd HH:mm:ss
            } else if (indDash1 == 4 && (indDash2 == 7 || indDash2 == 6)) {
                convertedDate = usTimeStamp.trim();
            }
        }

        return convertedDate;
    }

    /**
     *
     * @param sqlTimeStamp Sql format String
     * @return US timeStamp String
     */
    public String sqlTimeStampTousTimeStamp(String sqlTimeStamp) {

        int ind1 = sqlTimeStamp.indexOf("-", 0);
        int ind2 = sqlTimeStamp.indexOf("-", ind1 + 1);
        if (ind1 == 4 && (ind2 == 7 || ind2 == 6)) {
            syear = sqlTimeStamp.trim().substring(0, ind1);
            smonth = sqlTimeStamp.trim().substring(ind1 + 1, ind2);
            sday = sqlTimeStamp.trim().substring(ind2 + 1, ind2 + 3);
            convertedDate = smonth.trim() + "/" + sday.trim() + "/" + syear + "" + sqlTimeStamp.substring(ind2 + 3, ind2 + 12);

        } else if ((ind1 == 2 || ind1 == 1) && ind2 == 5) {
            convertedDate = sqlTimeStamp.trim();
        }
        return convertedDate;
    }

    /**
     * Written by Ranga Rao Panda
     */
    /**
     * The method can be used for converting the java.util.Date object to
     * String, format of yyyy/MM/dd.
     *
     * @param sourceDate A java.util.Date.
     *
     * @return String.
     */
    public String convertDateToMySql(java.util.Date sourceDate) {
        return new java.text.SimpleDateFormat("yyyy/MM/dd").format(sourceDate);
    }

    /**
     * The method can be used for converting the java.util.Date object to
     * String, format of MM/dd/yyyy. This can be used for displaying the date in
     * jsp pages.
     *
     * @param sourceDate A java.util.Date
     *
     * @return String.
     */
    public String convertDateToView(java.util.Date sourceDate) {
        return new java.text.SimpleDateFormat("MM/dd/yyyy").format(sourceDate);
    }

    /**
     * The method can be used for converting the String object to java.sql.Date,
     * format of yyyy-MM-dd.
     *
     * @param sourceDate A String object.
     *
     * @return sqlDate A java.sql.Date.
     */
    /**
     * The method can be used for converting the java.util.Date object to
     * String, format of MM-dd-yyyy. This can be used for displaying the date in
     * jsp pages.
     *
     * @param sourceDate A java.util.Date
     *
     * @return String.
     */
    public String convertDateToViewInDashformat(java.util.Date sourceDate) {
        return new java.text.SimpleDateFormat("MM-dd-yyyy").format(sourceDate);
    }

    public Date getMysqlDate(String sourceDate) {
        Date sqlDate = Date.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd").format(convertStringToMySql(sourceDate)));
        return sqlDate;
    }

    /**
     * The method can be used for converting the String object to
     * java.util.Date, format of MM/dd/yyyy.
     *
     * @param sourceDate A String object.
     *
     * @return targetDate A java.util.Date.
     *
     * @throws ParseException.
     */
    public java.util.Date convertStringToMySql(String sourceDate) {

        // hold the converted date object of String.
        java.util.Date targetDate = null;
        try {
            targetDate = new java.text.SimpleDateFormat("MM/dd/yyyy").parse(sourceDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return targetDate;
    }

    public String convertDateToMySql1(java.util.Date sourceDate) {
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(sourceDate);
    }
    //

    public Timestamp getCurrentMySqlDateTime() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return timestamp.valueOf(simpleDateFormat.format(calendar.getTime()).toString());
    }
    // new method for converting timestamp to string while creating issue

    public String getCurrentMySqlDateTime1() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return simpleDateFormat.format(calendar.getTime()).toString();
    }
    //new by aditya

    public String getCurrentMySqlDate() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return simpleDateFormat.format(calendar.getTime()).toString();
    }
    //new by aditya

    public String getCurrentMySqlEndDate() {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, 30);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(calendar.getTime()).toString();
    }

    public String getCurrentDate() {

        DateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss_a");
        java.util.Date currentDate = new java.util.Date();
        return dateFormat.format(currentDate);

    }

    public String getCurrentSQLDate() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date currentDate = new java.util.Date();
        return dateFormat.format(currentDate);

    }

    public String getCurrentSQLDate1() {

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date currentDate = new java.util.Date();
        return dateFormat.format(currentDate);

    }

    public Timestamp strToTimeStampObj(String dateAccessor) {
        try {

            int indDash1 = dateAccessor.indexOf("/", 0);
            int indDash2 = dateAccessor.indexOf("/", indDash1 + 1);


            if (indDash1 == -1 || indDash2 == -1) {

                int _ind1 = dateAccessor.indexOf("-", 0);
                int _ind2 = dateAccessor.indexOf("-", _ind1 + 1); //returns index of " - " in given String Object

                if (_ind1 == 4 && (_ind2 == 7 || _ind2 == 6)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date = sdf.parse(dateAccessor);
                    dateTimestampObj = new java.sql.Timestamp(date.getTime());
                }
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                java.util.Date date = sdf.parse(usTimeStampToSqlTimeStamp(dateAccessor));
                dateTimestampObj = new java.sql.Timestamp(date.getTime());
            }
        } catch (ParseException pe) {
            System.out.println("ParseException in" + pe);
        }
        return dateTimestampObj;
    }

    public Date strToTimeStampObj1(String dateAccessor) {
        java.util.Date date = null;
        try {

            int indDash1 = dateAccessor.indexOf("/", 0);
            int indDash2 = dateAccessor.indexOf("/", indDash1 + 1);


            if (indDash1 == -1 || indDash2 == -1) {

                int _ind1 = dateAccessor.indexOf("-", 0);
                int _ind2 = dateAccessor.indexOf("-", _ind1 + 1); //returns index of " - " in given String Object

                if (_ind1 == 4 && (_ind2 == 7 || _ind2 == 6)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    date = sdf.parse(dateAccessor);
                    dateActual = new java.sql.Date(date.getTime());
                }
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                date = sdf.parse(usTimeStampToSqlTimeStamp(dateAccessor));
                dateActual = new java.sql.Date(date.getTime());
            }
        } catch (ParseException pe) {
            System.out.println("ParseException in" + pe);
        }
        return dateActual;
    }

    public Time strToSqlTimeObj(String Time) {
        String tempStr = "";
        if (Time.length() == 8 || Time.length() == 7) {
            tempStr = Time.substring(0, 5).trim();
        } else {
            tempStr = Time;
        }
        tempStr = "0" + tempStr + ":" + "00";
        java.sql.Time timeObj = java.sql.Time.valueOf(tempStr);

        return timeObj;
    }

    public String getToDayDate() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date currentDate = new java.util.Date();
        return dateFormat.format(currentDate);


    }

    public String getToDayDateToView() {

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date currentDate = new java.util.Date();
        return dateFormat.format(currentDate);
    }

    public Date getMySqlDate1(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date uDate = sdf.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(uDate.getTime());

        return sqlDate;
    }

    public String convertDateToString(Date date) {
        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd-MMM-yyyy");
        return sdfOutput.format(date);
    }

    /**
     * Last date
     */
    public String LastMonthLastDate() throws ParseException {
        Calendar cal = Calendar.getInstance();
        int cur_year = cal.get(Calendar.YEAR);
        int cur_month = cal.get(Calendar.MONTH);
        int last_month = cur_month - 1;
        cal.set(cur_year, last_month, 1); //------>
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(cal.getTime());
    }

    public String FirstDateOfLastMonth() throws ParseException {
        Calendar cal = Calendar.getInstance();
        int cur_year = cal.get(Calendar.YEAR);
        int cur_month = cal.get(Calendar.MONTH);
        int last_month = cur_month - 1;
        cal.set(cur_year, last_month, 1);

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        String lastDate = sdf.format(cal.getTime());
        return lastDate;

    }
    //new

    public String convertToviewFormat(String dateString) {
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfOutput = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date date = null;
        try {
            date = sdfInput.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return sdfOutput.format(date);
    }

    /**
     * *
     * Date :08-28-2013 DESC: to get the 7 dayes before date Name :
     * PriviousSeventhDay
     */
    public String PriviousSeventhDay() {
        //public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        cal.add(Calendar.DATE, -7);

        return dateFormat.format(cal.getTime());
    }

    /* New Function For Current Month First and last date
     * Date : 10/20/2014
     * Author : Vikram Bammidi
     */
    public String CurrentMonthLastDate() throws ParseException {
        Calendar cal = Calendar.getInstance();
        int cur_year = cal.get(Calendar.YEAR);
        int cur_month = cal.get(Calendar.MONTH);
        cal.set(cur_year, cur_month, 1); //------>
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        return sdf.format(cal.getTime());
    }

    public String FirstDateOfCurrentMonth() throws ParseException {
        Calendar cal = Calendar.getInstance();
        int cur_year = cal.get(Calendar.YEAR);
        int cur_month = cal.get(Calendar.MONTH);

        cal.set(cur_year, cur_month, 1);

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        String lastDate = sdf.format(cal.getTime());
        return lastDate;

    }
    private static final SimpleDateFormat monthDayYearformatter = new SimpleDateFormat("MMMMM dd, yyyy");

    public static String TimeStampMonthDayYear(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            return monthDayYearformatter.format((java.util.Date) timestamp);
        }
    }

    public static String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {

        java.util.Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
        SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
        String parsedDate = formatter.format(initDate);
        return parsedDate;
    }

    public String convertStringToMySQLDateInDashWithTimeWithOutSeconds(String dateString) {
        SimpleDateFormat sdfInput = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date = null;
        try {
            date = sdfInput.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return sdfOutput.format(date);
    }

    public String convertStringToMySQLDateInDash(String dateString) {
        SimpleDateFormat sdfInput = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = sdfInput.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return sdfOutput.format(date);
    }

    public String convertToviewFormatInDash(String dateString) {
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfOutput = new SimpleDateFormat("MM-dd-yyyy");
        java.util.Date date = null;
        try {
            date = sdfInput.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return sdfOutput.format(date);
    }

    public String convertDateToViewInDashFormat(java.util.Date sourceDate) {
        return new java.text.SimpleDateFormat("MM-dd-yyyy").format(sourceDate);
    }

    public String convertToviewFormatInDashWithTime(String dateString) {
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdfOutput = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
        java.util.Date date = null;
        try {
            date = sdfInput.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return sdfOutput.format(date);
    }

    public String convertDateYMDtoMDY(String dateString) {
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfOutput = new SimpleDateFormat("MM-dd-yyyy");
        java.util.Date date = null;
        try {
            date = sdfInput.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return sdfOutput.format(date);
    }
}
