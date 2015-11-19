package com.mss.msp.usrajax;

import com.mss.msp.requirement.RequirementAction;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * @author miracle
 */
public interface UserAjaxHandlerService {

    /**
     * Creates a new instance of AjaxHandlerService
     */
    public String getStates(String country) throws ServiceLocatorException;

    public int addForGotPasswordDetails(String emailId, String urlLink, String key) throws ServiceLocatorException;

    public int doUserRegister(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;

    // public String getEmployeeDetails(String query) throws ServiceLocatorException;
    public String getEmployeeDetails(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;

    public String getTechEmployeeDetails(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;
    /*methods for contact address updation by ramakrishna start*/

    public String getEmpAddressDetails(int empId) throws ServiceLocatorException;

    public int setEmpAddressDetails(int empId, String address, String address2, String city, String state, String zip, String country, String phone, String address_flag) throws ServiceLocatorException;

    public int setEmpCAddressDetails(int empId, String address, String address2, String city, String state, String zip, String country, String phone, String address_flag) throws ServiceLocatorException;
    /*methods for contact address updation by ramakrishna end*/

    public String roleSubmit(HttpServletRequest httpServletRequest, int roleId, int orgId) throws ServiceLocatorException;

    public String getTypesOfTask(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;
    /* ---End, Add task types by Aklakh---  */

    public String getRelatedToNames(UserAjaxHandlerAction userAjaxHandlerAction);
    //Added By Jagan

    public int getInsertedLeaveDetails(int userSessionId, UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;

    public String getAttachment(UserAjaxHandlerAction userAjaxHandlerAction);

    public int doDeactiveAttachment(UserAjaxHandlerAction userAjaxHandlerAction);
    //added by manikanta

    //public String getEmployeeNames(HttpServletRequest httpServletRequest, int dept_id) throws ServiceLocatorException;
    public String getStatesOfCountry(int countryId) throws ServiceLocatorException;

    public String getLeavesListDetails(UserAjaxHandlerAction aThis) throws ServiceLocatorException;

    public String getExternalEmployeeDetails(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;

    public String getExternalEmployee2Details(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;

    public String getVendorNames(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;

    public String getCsrNames(String csrName) throws ServiceLocatorException;

    public String csrTermination(int userId) throws ServiceLocatorException;

    public String changeCsrAccountStatus(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;

    public String getCsrAccount(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;

    public String getEmpCategories(UserAjaxHandlerAction userAjaxHandlerAction, int sessionOrgId) throws ServiceLocatorException;

    public String doUserGroupingMethod(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;

    public String empCategoryTermination(UserAjaxHandlerAction userAjaxHandlerAction, int sessionOrgId) throws ServiceLocatorException;

    public String getHomeRedirectSearchDetails(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;

    public String getAccountNames(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;

    public String storeAddOrEditHomeRedirectDetails(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;

    public String getHomeRedirectCommentsDetails(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;

    public String getCategoryList(UserAjaxHandlerAction aThis) throws ServiceLocatorException;

    public String getEmpCategoryNames(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;

    public boolean isUserGroupExist(int userId) throws ServiceLocatorException;

    public String checkFileName(String xlsfileFileName) throws ServiceLocatorException;
    
    public String getQuestion(int questionNo, HttpServletRequest httpServletRequest, int selectedAns, String navigation, int remainingQuestions, int onClickStatus, int subTopicId, int specficQuestionNo,UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;
    public String getEmpRecruitment(UserAjaxHandlerAction userAjaxHandlerAction) throws ServiceLocatorException;
}
