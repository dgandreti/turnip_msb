/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.requirement;

import com.mss.msp.util.ServiceLocatorException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public interface RequirementService {

    public int addRequirementDetails(RequirementAction requirementAction, int orgId) throws ServiceLocatorException;

    public RequirementVTO editrequirement(String requirementid,Map skillsMap) throws ServiceLocatorException;

    public int updateRequirement(int userid, RequirementAction requirementAction) throws ServiceLocatorException;

    public String getRequirementDetails(RequirementAction requirementAction) throws ServiceLocatorException;

    public String getReqDetailsBySearch(RequirementAction requirementAction) throws ServiceLocatorException;

    public String getSkillDetaisls(RequirementAction requirementAction) throws ServiceLocatorException;

    public String getPreferedSkillDetails(RequirementAction requirementAction) throws ServiceLocatorException;

    public String getSearchRequirementsList(HttpServletRequest httpServletRequest, RequirementAction aThis,Map skillsMap) throws ServiceLocatorException;

    public String getRecruiterOverlay(RequirementAction requirementAction) throws ServiceLocatorException;

    public String getSkillOverlay(RequirementAction requirementAction) throws ServiceLocatorException;

    public String getPreSkillOverlay(RequirementAction requirementAction) throws ServiceLocatorException;

    public String storeProofData(HttpServletRequest httpServletRequest, RequirementAction requirementAction) throws ServiceLocatorException;

    public int doReleaseRequirements(RequirementAction requirementAction) throws ServiceLocatorException;

    public int doUpdateStatusReport(RequirementAction aThis) throws ServiceLocatorException;

    
    public int getOrgIdCustomer(String requirementid) throws ServiceLocatorException;

    public String getRequirementDashBoardDetails(RequirementAction requirementAction) throws ServiceLocatorException;

    public String getRequirementDashBoardDetailsOnOverlay(RequirementAction requirementAction) throws ServiceLocatorException;

    public List getDefaultCustomerRequirementDashBoardDetails(RequirementAction requirementAction) throws ServiceLocatorException;

    public String getCustomerRequirementDashBoardDetails(RequirementAction requirementAction) throws ServiceLocatorException;

    public String getVendorRequirementsDashBoard(RequirementAction requirementAction) throws ServiceLocatorException;

    public String getVendorDashBoardDetailsOnOverlay(RequirementAction requirementAction) throws ServiceLocatorException;
}
