/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usr.timesheets;

import com.mss.msp.util.ServiceLocatorException;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author miracle
 */
public interface UsrTimesheetService {
    public List getTimesheetListDetails( UsrTimeSheetAction usrTimeSheetAction) throws ServiceLocatorException;
    public List getTimesheetList( UsrTimeSheetAction usrTimeSheetAction) throws ServiceLocatorException;
    
    public List getTeamTimeSheetListDefault( UsrTimeSheetAction userTimeSheetAction) throws ServiceLocatorException;        
    public List getAllTimeSheetList( UsrTimeSheetAction usrTimeSheetAction) throws ServiceLocatorException;
    public int deleteTimeSheet( UsrTimeSheetAction usrTimeSheetAction) throws ServiceLocatorException;
    public int AddTimesheet( UsrTimeSheetAction userTimeSheetAction) throws ServiceLocatorException;
    public List getTeamTimeSheetList( UsrTimeSheetAction userTimeSheetAction) throws ServiceLocatorException;
    //public List getTeamTimesheetListDetails(HttpServletRequest httpServletRequest, UsrTimeSheetAction userTimeSheetAction) throws ServiceLocatorException;
    
    public TimesheetVTO getUserTimeSheet(UsrTimeSheetAction userTimeSheetAction) throws ServiceLocatorException;
    public int editTimeSheet(UsrTimeSheetAction userTimeSheetAction) throws ServiceLocatorException;
    public int approveRejectTimeSheet(UsrTimeSheetAction userTimeSheetAction) throws ServiceLocatorException;
    public List getweekStartAndEndDays(Calendar cal);
    public String checkTimeSheetExists(List list,int empID);
    public TimesheetVTO getWeekDaysBean(List list);
     public List getAllTimeSheets( UsrTimeSheetAction userTimeSheetAction) throws ServiceLocatorException;                
   
     public List getAllTimeSheetsSearch( UsrTimeSheetAction userTimeSheetAction) throws ServiceLocatorException;        
}
