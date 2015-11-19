/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.onlineexam;

import com.mss.msp.util.ServiceLocatorException;
import java.util.Map;

/**
 *
 * @author miracle
 */
public interface OnlineExamService {
   public void getTokenInfo(OnlineExamAction onlineExamAction) throws ServiceLocatorException;
   public void getValidationInfo(OnlineExamAction onlineExamAction) throws ServiceLocatorException;
  
   public Map getQuestions(Map skillsMap,int totalQuestions,String level,String examType,int orgId)  throws ServiceLocatorException;
   public int getOnlineExamKey(OnlineExamAction onlineExamAction) throws ServiceLocatorException;
   public int insertAnswer(int examQuestionId,int ans1,int ans2,int ans3,int ans4,int ans5,int ans6,int consultantId,int examKey,int skiiId,int reqid,String status,int examId) throws ServiceLocatorException;
  public int updateConsultantStatusTechReview(int contechReviewId,String status,int examId,int totalQuestions) throws ServiceLocatorException;
   public int updateExamStatus(int conTechReviewId,String examStatus) throws ServiceLocatorException;
   public String getExamResult(int conTechReviewId,OnlineExamAction onlineExamAction) throws ServiceLocatorException;
}
