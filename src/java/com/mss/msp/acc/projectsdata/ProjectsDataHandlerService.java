/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.projectsdata;

import com.mss.msp.util.ServiceLocatorException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Riza Erbas
 */
public interface ProjectsDataHandlerService {

    public List getProjectSearchDetails(ProjectsDataHandlerAction projectsDataHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public List getProjectsByAccID(Integer accountID, Integer projectCreatedBy, String roleValue) throws ServiceLocatorException;

    public void addProject(ProjectsVTO projects) throws ServiceLocatorException;

    public String checkProjectName(String projectName,String projectFlag,int projectId,int accountID) throws ServiceLocatorException;

    public ProjectsVTO getProjectsByProjectID(ProjectsDataHandlerAction projectsDataHandlerAction) throws ServiceLocatorException;

    public Integer getProjectResourceCount(ProjectsDataHandlerAction projectsDataHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public void updateProject(ProjectsVTO project, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public List getSubProjects(Integer subProjectID) throws ServiceLocatorException;

    public List getProjectsDashboard(ProjectsDataHandlerAction projectsDataHandlerAction) throws ServiceLocatorException;
}
