/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usr.task;

import com.mss.msp.util.ServiceLocatorException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public interface TaskHandlerService {

    public List getEmployeeTasksDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException;

    public List getLoggedInEmpTasksDetails( TaskHandlerAction taskHandlerAction) throws ServiceLocatorException;

    public List getLoggedInTeamTasksDetails( TaskHandlerAction taskHandlerAction) throws ServiceLocatorException;

    public int addTaskDetals(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException;

    public List getTeamTasksDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException;

    public TasksVTO getEditTaskDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException;

    // public int updateTaskDetails(TaskHandlerAction taskHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    public int addAttachmentDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException;

    public int updateTaskDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException;

    public String getNotesDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException;

    public String getNotesDetailsOverlay(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException;

    public int UpdateNotesDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException;

    public int DoInsertNotesDetails(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException;

    public String getNotesDetailsBySearch(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException;
    
    public String getCommentsOnOverlay(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException;
}
