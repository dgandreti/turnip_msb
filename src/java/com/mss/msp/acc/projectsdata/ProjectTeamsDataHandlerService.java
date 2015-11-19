/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.projectsdata;

import com.mss.msp.util.ServiceLocatorException;
import java.util.List;

/**
 *
 * @author Senay
 */
public interface ProjectTeamsDataHandlerService {

    public List getProjectsTeam(Integer projectID) throws ServiceLocatorException;

    public String getTeamMemberDetails(ProjectTeamsDataHandlerAction projectTeamsDataHandlerAction) throws ServiceLocatorException;

    public String showResourceDetails(ProjectTeamsDataHandlerAction projectTeamsDataHandlerAction) throws ServiceLocatorException;

    public int EmpReleasefromProject(ProjectTeamsDataHandlerAction projectTeamsDataHandlerAction) throws ServiceLocatorException;

    public String getProjectDashboardList(ProjectTeamsDataHandlerAction projectTeamsDataHandlerAction) throws ServiceLocatorException;

    public String getProjectsForYear(ProjectTeamsDataHandlerAction projectTeamsDataHandlerAction) throws ServiceLocatorException;
}
