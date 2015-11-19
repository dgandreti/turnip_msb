/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.recruitment;

import com.mss.msp.requirement.RequirementAction;
import com.mss.msp.util.ServiceLocatorException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author NagireddySeerapu
 */
public interface RecruitmentService {

    public List getMyDefaultConsListDetails(RecruitmentAction recruitmentAction) throws ServiceLocatorException;

    public List getConsListDetails(RecruitmentAction recruitmentAction) throws ServiceLocatorException;

    public String getRequirementDetails(RecruitmentAction recruitmentAction) throws ServiceLocatorException;

    public List getAllRequirementList(HttpServletRequest httpServletRequest, RecruitmentAction aThis);

    public int doAddConsultantDetails(RecruitmentAction recruitmentAjaxHandlerAction, int OId) throws ServiceLocatorException;

    public ConsultantVTO getupdateConsultantDetails(RecruitmentAction recruitmentAction,Map map) throws ServiceLocatorException;

    public ConsultantVTO doupdateConsultantDetails(RecruitmentAction recruitmentAction, int userSessionId, int orgid) throws ServiceLocatorException;

    public String getConsultantListDetails(HttpServletRequest httpServletRequest, RecruitmentAction vendorAjaxHandler) throws ServiceLocatorException;

    public String searchConsultantListDetails(HttpServletRequest httpServletRequest, RecruitmentAction vendorAjaxHandler) throws ServiceLocatorException;

    public int addConsultAttachmentDetails(RecruitmentAction recruitmentaction);

    public String getActivityDetails(int consultantId) throws ServiceLocatorException;

    public int doAddConsultantActivityDetails(RecruitmentAction recruitmentAction) throws ServiceLocatorException;

    //public List getConsultantActivityDetails(RecruitmentAction recruitmentAction);
    public int doEditConsultantActivityDetails(RecruitmentAction recruitmentAction);

    public String getConsultantActivityDetailsbyActivityId(RecruitmentAction recruitmentAction);

    public List getLoginUserRequirementList(HttpServletRequest httpServletRequest, RecruitmentAction aThis);

    public List getCurrentStatus(RecruitmentAction recruitmentAction);

    public List getConsultantStatus(RecruitmentAction recruitmentAction);

   public int techReviewForward(RecruitmentAction recruitmentAction,String accToken,String validKey) throws ServiceLocatorException;

    public List getTechReviewDetails(RecruitmentAction aThis);

    public String getSearchTechReviewList(RecruitmentAction aThis) throws ServiceLocatorException;

    public String getConsultDetailsOnOverlay(RecruitmentAction aThis) throws ServiceLocatorException;

    public int saveTechReviewResults(RecruitmentAction aThis) throws ServiceLocatorException;

    public int doDeleteConsultantAttachment(RecruitmentAction recruitmentAction) throws ServiceLocatorException;

    public int updateConsultAttachmentDetails(RecruitmentAction recruitmentaction);

    public List getTechReviewSearchDetails(RecruitmentAction recruitmentAction);

    public int userMigration(RecruitmentAction recruitmentAction) throws ServiceLocatorException;

    public int doAddVendorFormDetails(RecruitmentAction recruitmentAjaxHandlerAction, int OId, String typeOfAccount) throws ServiceLocatorException;

    public int doUpdateVendorFormDetails(RecruitmentAction recruitmentAjaxHandlerAction, int attachment_id) throws ServiceLocatorException;
    
    public List getVendorRequirementsDashboard(HttpServletRequest httpServletRequest, RecruitmentAction recruitmentAction) throws ServiceLocatorException;
}
