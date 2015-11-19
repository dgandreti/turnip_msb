/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.onlineexam;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.DataSourceDataProvider;
import com.mss.msp.util.DateUtility;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
/**
 *
 * @author miracle
 */
public class OnlineExamAction extends ActionSupport implements ServletRequestAware{
     private String resultType;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse; 
    private String token;
//    eid,reqid,consultantid,acctoken,validationkey,createdby,createddate,comments,totalquestions,qualifiedmarks,examstatus,severity
    private int consultantId;
    private String examStaus;
    private int totalQuestions;
    private int qualifiedMarks;
    private String resultMessage;
    private String createdDate;
    private boolean isExpired=true;
    private String isValid;
    private String validationKey;
    private String email;
    private String actualValidationToken;
    private String actualConsultantEmailId;
    private String consultantName;
    private String requirementTitle;
    private int requirementId;
    private String examSeverity;
    private String examType;
    private String durationTime;
    private String examTopics;
    private int orgId;
    private int conTechReviewId;
     private int examId;
     private String skillName1;
    private String skillName2;
    private String skillName3;
    private String skillName4;
    private String skillName5;
    private String skillName6;
    private String skillName7;
    private String skillName8;
    private String skillName9;
    private String skillName10;
    private int skillResult1;
    private int skillResult2;   
    private int skillResult3;
    private int skillResult4;
    private int skillResult5;
    private int skillResult6;
    private int skillResult7;
    private int skillResult8;
    private int skillResult9;
    private int skillResult10;
    //private String totalAnswers;
    private int totalResult;
    public String onlineExam() {
        resultType = LOGIN;
        try {
//           
                System.out.println("hear updation is completed....");
//              
               
                System.out.println("Token-->"+getToken());
                
                ServiceLocator.getOnlineExamService().getTokenInfo(this);
                setToken(getToken());
                System.out.println("consultId-->"+getConsultantId());
                System.out.println("createdDate-->"+getCreatedDate());
                System.out.println("examStaus-->"+getExamStaus());
                System.out.println("examStaus-->"+getExamTopics());
                setExamTopics(getExamTopics());
                if(getIsValid().equals("VALID")){
                    if(!isIsExpired()){
                       
                        if(getExamStaus().equals("Created") ){
                            
                            resultType = SUCCESS;
                        }else{
                        setResultMessage("<font color='red' size='2px'>Invalid Token</font>");
                        resultType = ERROR;
                        }
                        
                    }else {
                        setResultMessage("<font color='red' size='2px'>Token Expired</font>");
                        resultType = ERROR;
                    }
                    
                }else {
                    setResultMessage("<font color='red' size='2px'>Invalid token.</font>");
                    resultType = ERROR;
                }
               
        } catch (Exception ex) {
          
            setResultMessage("<font color='red' size='2px'>"+ex.getMessage()+"</font>");
            resultType = ERROR;
        }
        return resultType;
    }
    
    
     public String doValidateInfo() {
        resultType = LOGIN;
        try {
            
                
                ServiceLocator.getOnlineExamService().getValidationInfo(this);
               
              
                setValidationKey(getValidationKey());
                setExamTopics(getExamTopics());
                //System.out.println("examTopics--->in validation"+getExamTopics());
                if(getValidationKey().equals(getActualValidationToken())){
                    if(getEmail().equals(getActualConsultantEmailId())){
                        setRequirementTitle(DataSourceDataProvider.getInstance().getReqNameById(getRequirementId()));
                        if("Created".equals(getExamStaus()) ){
                            resultType = SUCCESS;
                        }else{
                          setResultMessage("<font color='red' size='2px'>Invalid Token</font>");
                    resultType = INPUT;  
                        }
                        
                    }else {
                        setResultMessage("<font color='red' size='2px'>Invalid email</font>");
                    resultType = INPUT;
                    }
                }else {
                    setResultMessage("<font color='red' size='2px'>Invalid Token</font>");
                    resultType = INPUT;
                }
                
                
                
                 //System.out.println("examStaus-->"+DateUtility.getInstance().convertStringTimeStamp(getCreatedDate()));
                
               // int compare = DateUtility.getInstance().getCurrentMySqlDateTime().compareTo(DateUtility.getInstance().convertStringTimeStamp(getCreatedDate()));
                 //   System.out.println("Compare-->"+compare)   ;
                

                
           
        } catch (Exception ex) {
         
            setResultMessage("<font color='red' size='2px'>"+ex.getMessage()+"</font>");
            resultType = ERROR;
        }
        return resultType;
    }
   public String doStartOnlineExam() {
       
        resultType = LOGIN;

        
        try {

            setRequirementId(getRequirementId());
            setConsultantId(getConsultantId());
            setExamSeverity(getExamSeverity());
            setTotalQuestions(getTotalQuestions());
            setExamType(getExamType());
            setDurationTime(getDurationTime());
            setValidationKey(getValidationKey());
            setExamTopics(getExamTopics());
            setOrgId(getOrgId());
            setConTechReviewId(getConTechReviewId());
            setExamId(getExamId());
            System.out.println("getTopics-->"+getExamTopics()+"reqId"+getRequirementId()+"conId"+getConsultantId()+"examSeverity"+getExamSeverity()+"examType"+getDurationTime());
            HttpSession session = getHttpServletRequest().getSession(true);
            session.setAttribute(ApplicationConstants.ONLINE_EXAM_CONSULTANTID,getConsultantId());
            session.setAttribute(ApplicationConstants.ONLINE_EXAM_REQUIREMENTID,getRequirementId());
            Map skillMap=DataSourceDataProvider.getInstance().getSkillsMap(getConTechReviewId());
            
            
            Map skillsQuestionsMap=DataSourceDataProvider.getInstance().getSkillsQuestionsMap(getValidationKey());
            session.setAttribute(ApplicationConstants.ONLINE_SKILLS_MAP,skillMap);
            session.setAttribute(ApplicationConstants.ONLINE_EXAM_CONSULTANT_NAME,DataSourceDataProvider.getInstance().getFnameandLnamebyUserid(getConsultantId()));
            //System.out.println(consultantId+"-->"+requirementId); 
            int examKey=ServiceLocator.getOnlineExamService().getOnlineExamKey(this);
            session.setAttribute(ApplicationConstants.ONLINE_EXAM_CURRENT_KEY,examKey);
            Map questionMap= ServiceLocator.getOnlineExamService().getQuestions(skillsQuestionsMap, getTotalQuestions(), getExamSeverity(),getExamType(),getOrgId());
            //System.out.println("question map length"+questionMap.size());
            session.setAttribute(ApplicationConstants.ONLINE_EXAM_QUESTION_MAP,questionMap);
            
            
            String ecertStatus=DataSourceDataProvider.getInstance().getExamStatus(getConTechReviewId());
           
            if(!"Created".equals(ecertStatus) ){
            setResultMessage("<font color='red' size='2px' >Invalid Token</font>");
            resultType = ERROR;
            }else{
                ServiceLocator.getOnlineExamService().updateExamStatus(getConTechReviewId(), "Started");
              
                resultType = SUCCESS;
            }
            


        } catch (Exception ex) {
           
            setResultMessage("<font color='red' size='2px' style='margin-left:65%'>"+ex.getMessage()+"</font>");
            resultType = ERROR;
        }
        return resultType;
    }
    public String doSubmitOnlineExam() {
       
        resultType = LOGIN;

        try {

         int    consultantId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ONLINE_EXAM_CONSULTANTID).toString());
        int  examKey = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ONLINE_EXAM_CURRENT_KEY).toString());
        int  reqId =Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ONLINE_EXAM_REQUIREMENTID).toString());
        setTotalQuestions(getTotalQuestions());
        setValidationKey(getValidationKey());
        setConTechReviewId(getConTechReviewId());
        setExamId(getExamId());
        //int skillId=Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ONLINE_EXAM_REQUIREMENTID).toString());
        Map questionmap=(Map)httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ONLINE_EXAM_QUESTION_MAP);   
        System.out.println("map Size is--->"+questionmap.size());
            Iterator iterator = questionmap.entrySet().iterator();
            int i=0,attemptedQuestionsResult=0;
            QuestionVTO questionVTO=null;
                while (iterator.hasNext()) {
                        Map.Entry mapEntry = (Map.Entry) iterator.next();
                       // System.out.println("The key is: " + mapEntry.getKey() + ",value is :" + mapEntry.getValue());
                        
                        questionVTO = (QuestionVTO)questionmap.get(mapEntry.getKey());
                        
                        int ExamQuestionId = questionVTO.getId();
                        System.out.println("examKey-->"+examKey);
                        System.out.println("ExamQuestionId-->"+ExamQuestionId);
                        attemptedQuestionsResult = DataSourceDataProvider.getInstance().isAttempted(ExamQuestionId,examKey);
                        System.out.println("attemptedQuestionsResult--->"+attemptedQuestionsResult);
                        if(attemptedQuestionsResult == 0){
                            ServiceLocator.getOnlineExamService().insertAnswer(ExamQuestionId,0,0,0,0,0,0,consultantId,examKey,questionVTO.getSkillId(),reqId,"U",getExamId());
                            i++;
                        }
                        ServiceLocator.getOnlineExamService().updateExamStatus(getConTechReviewId(),"Submitted");
                        ServiceLocator.getOnlineExamService().updateConsultantStatusTechReview(getConTechReviewId(), "Exam Completed",getExamId(),getTotalQuestions());
                        
                }
                ServiceLocator.getOnlineExamService().getExamResult(getConTechReviewId(),this);   
         if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ONLINE_EXAM_CONSULTANTID)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ONLINE_EXAM_CONSULTANTID);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ONLINE_EXAM_CONSULTANTID,null);
                    }
         if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ONLINE_EXAM_CURRENT_KEY)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ONLINE_EXAM_CURRENT_KEY);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ONLINE_EXAM_CURRENT_KEY,null);
                    }
         if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ONLINE_EXAM_REQUIREMENTID)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ONLINE_EXAM_REQUIREMENTID);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ONLINE_EXAM_REQUIREMENTID,null);
                    }
          if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ONLINE_EXAM_QUESTION_MAP)!=null){
                        httpServletRequest.getSession(false).removeAttribute(ApplicationConstants.ONLINE_EXAM_QUESTION_MAP);
                        httpServletRequest.getSession(false).setAttribute(ApplicationConstants.ONLINE_EXAM_QUESTION_MAP,null);
                    }
          if(httpServletRequest.getSession(false) != null){
                httpServletRequest.getSession(false).invalidate();
              }
           
                
                resultType = SUCCESS;
              


        } catch (Exception ex) {
           // httpServletRequest.getSession(false).setAttribute("errorMessage:", ex.toString());
            setResultMessage("<font color='red' size='2px'>"+ex.getMessage()+"</font>");
            resultType = ERROR;
        }
        return resultType;
    } 
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    public String getExamStaus() {
        return examStaus;
    }

    public void setExamStaus(String examStaus) {
        this.examStaus = examStaus;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getQualifiedMarks() {
        return qualifiedMarks;
    }

    public void setQualifiedMarks(int qualifiedMarks) {
        this.qualifiedMarks = qualifiedMarks;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    
    
    
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @return the isExpired
     */
    public boolean isIsExpired() {
        return isExpired;
    }

    /**
     * @param isExpired the isExpired to set
     */
    public void setIsExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    /**
     * @return the isValid
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * @param isValid the isValid to set
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getValidationKey() {
        return validationKey;
    }

    public void setValidationKey(String validationKey) {
        this.validationKey = validationKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActualValidationToken() {
        return actualValidationToken;
    }

    public void setActualValidationToken(String actualValidationToken) {
        this.actualValidationToken = actualValidationToken;
    }

    public String getActualConsultantEmailId() {
        return actualConsultantEmailId;
    }

    public void setActualConsultantEmailId(String actualConsultantEmailId) {
        this.actualConsultantEmailId = actualConsultantEmailId;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getRequirementTitle() {
        return requirementTitle;
    }

    public void setRequirementTitle(String requirementTitle) {
        this.requirementTitle = requirementTitle;
    }

    public int getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public String getExamSeverity() {
        return examSeverity;
    }

    public void setExamSeverity(String examSeverity) {
        this.examSeverity = examSeverity;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(String durationTime) {
        this.durationTime = durationTime;
    }

    public String getExamTopics() {
        return examTopics;
    }

    public void setExamTopics(String examTopics) {
        this.examTopics = examTopics;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getConTechReviewId() {
        return conTechReviewId;
    }

    public void setConTechReviewId(int conTechReviewId) {
        this.conTechReviewId = conTechReviewId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getSkillName1() {
        return skillName1;
    }

    public void setSkillName1(String skillName1) {
        this.skillName1 = skillName1;
    }

    public String getSkillName2() {
        return skillName2;
    }

    public void setSkillName2(String skillName2) {
        this.skillName2 = skillName2;
    }

    public String getSkillName3() {
        return skillName3;
    }

    public void setSkillName3(String skillName3) {
        this.skillName3 = skillName3;
    }

    public String getSkillName4() {
        return skillName4;
    }

    public void setSkillName4(String skillName4) {
        this.skillName4 = skillName4;
    }

    public String getSkillName5() {
        return skillName5;
    }

    public void setSkillName5(String skillName5) {
        this.skillName5 = skillName5;
    }

    public String getSkillName6() {
        return skillName6;
    }

    public void setSkillName6(String skillName6) {
        this.skillName6 = skillName6;
    }

    public String getSkillName7() {
        return skillName7;
    }

    public void setSkillName7(String skillName7) {
        this.skillName7 = skillName7;
    }

    public String getSkillName8() {
        return skillName8;
    }

    public void setSkillName8(String skillName8) {
        this.skillName8 = skillName8;
    }

    public String getSkillName9() {
        return skillName9;
    }

    public void setSkillName9(String skillName9) {
        this.skillName9 = skillName9;
    }

    public String getSkillName10() {
        return skillName10;
    }

    public void setSkillName10(String skillName10) {
        this.skillName10 = skillName10;
    }

    public int getSkillResult1() {
        return skillResult1;
    }

    public void setSkillResult1(int skillResult1) {
        this.skillResult1 = skillResult1;
    }

    public int getSkillResult2() {
        return skillResult2;
    }

    public void setSkillResult2(int skillResult2) {
        this.skillResult2 = skillResult2;
    }

    public int getSkillResult3() {
        return skillResult3;
    }

    public void setSkillResult3(int skillResult3) {
        this.skillResult3 = skillResult3;
    }

    public int getSkillResult4() {
        return skillResult4;
    }

    public void setSkillResult4(int skillResult4) {
        this.skillResult4 = skillResult4;
    }

    public int getSkillResult5() {
        return skillResult5;
    }

    public void setSkillResult5(int skillResult5) {
        this.skillResult5 = skillResult5;
    }

    public int getSkillResult6() {
        return skillResult6;
    }

    public void setSkillResult6(int skillResult6) {
        this.skillResult6 = skillResult6;
    }

    public int getSkillResult7() {
        return skillResult7;
    }

    public void setSkillResult7(int skillResult7) {
        this.skillResult7 = skillResult7;
    }

    public int getSkillResult8() {
        return skillResult8;
    }

    public void setSkillResult8(int skillResult8) {
        this.skillResult8 = skillResult8;
    }

    public int getSkillResult9() {
        return skillResult9;
    }

    public void setSkillResult9(int skillResult9) {
        this.skillResult9 = skillResult9;
    }

    public int getSkillResult10() {
        return skillResult10;
    }

    public void setSkillResult10(int skillResult10) {
        this.skillResult10 = skillResult10;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }
    
}
