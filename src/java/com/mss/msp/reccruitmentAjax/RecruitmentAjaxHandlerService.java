/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.reccruitmentAjax;

import com.mss.msp.util.ServiceLocatorException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author NagireddySeerapu
 */
public interface RecruitmentAjaxHandlerService {
    //for add consultant created by Aklakh

    public int getAddedConsultantDetails(RecruitmentAjaxHandlerAction recruitmentAjaxHandlerAction, int OId) throws ServiceLocatorException;

    public String getAttachmentDetails( RecruitmentAjaxHandlerAction aThis) throws ServiceLocatorException;
    // add by Aklakh

  //  public String saveConsultantLoginDetails(int consulnt_id, int UserSessionId) throws ServiceLocatorException;

    public String getConsultantTechReviews(RecruitmentAjaxHandlerAction recruitmentAjaxHandlerAction) throws ServiceLocatorException;

    public String techReviewCommentsOverlay(RecruitmentAjaxHandlerAction recruitmentAjaxHandlerAction) throws ServiceLocatorException;

    public String getTechReviewResultOnOverlay(RecruitmentAjaxHandlerAction recruitmentAjaxHandlerAction) throws ServiceLocatorException;
//     public int getConsultantCount(int consultantId) throws ServiceLocatorException;
    
    public String questionsCount(RecruitmentAjaxHandlerAction recruitmentAjaxHandlerAction,int orgId) throws ServiceLocatorException;
   
    public String questionsCountCheck(RecruitmentAjaxHandlerAction recruitmentAjaxHandlerAction,int orgId) throws ServiceLocatorException;
    
    public String skillsQuestions(RecruitmentAjaxHandlerAction recruitmentAjaxHandlerAction,int orgId) throws ServiceLocatorException;
    
    public String saveOnlineExamStatus(RecruitmentAjaxHandlerAction recruitmentAjaxHandlerAction) throws ServiceLocatorException;
}
