/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usr.leaves;

import com.mss.msp.util.ServiceLocatorException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author NagireddySeerapu
 */
public interface UserLeavesService {
    //added by kiran

    public List getDefaultMyEmpLeavesListDetails(HttpServletRequest httpServletRequest, UserLeavesAction userLeavesAction) throws ServiceLocatorException;

    public List getEmpLeavesListDetails(HttpServletRequest httpServletRequest, UserLeavesAction userLeavesAction) throws ServiceLocatorException;

    public int UpdatedEmpLeaves(HttpServletRequest httpServletRequest, UserLeavesAction userLeavesAction, int userid) throws ServiceLocatorException;

    public EmpLeaves getEmployeeLeaves(HttpServletRequest httpServletRequest, int userid, int leave_id) throws ServiceLocatorException;

    public List getTeamLeavesListDetails(HttpServletRequest httpServletRequest, UserLeavesAction userLeavesAction) throws ServiceLocatorException;

    public List getTeamMemberLeavesDetails(HttpServletRequest httpServletRequest, UserLeavesAction userLeavesAction) throws ServiceLocatorException;

    public List getLeavesDashboardSearchList(HttpServletRequest httpServletRequest, UserLeavesAction userLeavesAction) throws ServiceLocatorException;
}
