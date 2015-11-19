/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.general;

/*
 * @(#)HomeAction.java 1.0 Nov 01, 2007
 *
 * Copyright 2006 Miracle Software Systems(INDIA) Pvt Ltd. All rights reserved.
 *
 */




import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.ServiceLocator;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * The <code>HomeAction</code>  class is used for getting roles from
 * <i>Home.jsp</i> page.
 *
 * @author RajaReddy.Andra <a href="mailto:randra@miraclesoft.com">randra@miraclesoft.com</a>
 *
 * @version 1.0 Nov 01, 2007
 *
 * @see com.mss.mirage.util.ApplicationConstants
 * @see com.mss.mirage.util.ApplicationDataProvider
 * @see com.mss.mirage.util.ServiceLocator
 *
 */
public class HomeAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    
    /*@param roleTypeId is used to store roleid*/
    private int roleId;
    
    /*@param httpServletRequest is used to store request of type HttpServletRequest*/
    private HttpServletRequest httpServletRequest;
    
    /*@param httpServletResponse is used to store response of type httpServletResponse*/
    private HttpServletResponse httpServletResponse;
    
    /*@param currentLeftMenu is used to store left menus names */
    private Map currentLeftMenu;
    
    /*@param id is used to store id */
    private int id;
    
    /*@param description is used to store roles of employee*/
    private String description;
    
    /*@param defaultAction is used to store actions of leftmenu*/
    private String defaultAction;
    
    /*@param roleName is used to store roleName*/
    private String roleName;
    
    /*@param leftMenu is used to get leftMenu of type is collection*/
    private Collection leftMenu;
    
    /*@param resultType is used to store type of the result*/
    private String resultType;
    
    private String userId;
    private int usableTeamHours = 0;
    private int usedTeamHours = 0;
    private int naturalHolidayHours = 0;
    private int teamLeaveHours = 0;
    
    /** Creates a new instance of HomeAction */
    public HomeAction() {
    }
    
    /**execute() method return type String  it returns resultType
     *user select an perticuler role in home page and click on continue button he will get leftmenu
     *depending on the role.
     */
    public String execute() throws Exception {
        try{
            resultType = LOGIN;
            if(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID) != null){
                
                String defaultAction = null;
                if(getRoleId() == 0) {
                    setRoleId(Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.PRIMARYROLE).toString()));
                }
                String userId = httpServletRequest.getSession(false).getAttribute(ApplicationConstants.USER_ID).toString();
                httpServletRequest.getSession(false).setAttribute(ApplicationConstants.PRIMARYROLE,String.valueOf(getRoleId()));
                int orgId = Integer.parseInt(httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID).toString());
                                            //System.out.println("allowUser14-->"+allowUser);
                String def_action = ServiceLocator.getGeneralService().getPrimaryAction(orgId,getRoleId());
                if(def_action.equals("")){
                    setDefaultAction(ServiceLocator.getGeneralService().getPrimaryAction(0,getRoleId()));
                }else{
                    setDefaultAction(def_action);
                }
			System.err.println("ome redirectaction-->"+def_action);		
				
                
                resultType = SUCCESS;
            }//Closing Session checking
        } catch(Exception ex){
            //List errorMsgList = ExceptionToListUtility.errorMessages(ex);
            ex.printStackTrace();
            httpServletRequest.getSession(false).setAttribute("errorMessage",ex.toString());
            resultType =  ERROR;
        }
        
        return resultType;
    }
    
   
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
    /*getCurrentLeftMenu() used to get current left menu*/
    public Map getCurrentLeftMenu() {
        return currentLeftMenu;
    }
    
    /*setCurrentLeftMenu(Map currentLeftMenu) used to set current left menu*/
    public void setCurrentLeftMenu(Map currentLeftMenu) {
        this.currentLeftMenu = currentLeftMenu;
    }
    
    /*this is abstract method*/
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }
    
    /*getId() used to get id*/
    public int getId() {
        return id;
    }
    
    /*setId(int id) used to set id*/
    public void setId(int id) {
        this.id = id;
    }
    
    /*getDescription() used to get description of the role*/
    public String getDescription() {
        return description;
    }
    
    /*setDescription(String description) used to set description of the role*/
    public void setDescription(String description) {
        this.description = description;
    }
    
    /*getDefaultAction() used to get default action*/
    public String getDefaultAction() {
        return defaultAction;
    }
    
    /*setDefaultAction(String defaultAction) used to set default action*/
    public void setDefaultAction(String defaultAction) {
        this.defaultAction = defaultAction;
    }
    
    /*getLeftMenu() used to get left menu*/
    public Collection getLeftMenu() {
        return leftMenu;
    }
    
    /*setLeftMenu(Collection leftMenu) used to set left menu*/
    public void setLeftMenu(Collection leftMenu) {
        this.leftMenu = leftMenu;
    }
    
    public int getUsableTeamHours() {
        return usableTeamHours;
    }
    
    public void setUsableTeamHours(int usableTeamHours) {
        this.usableTeamHours = usableTeamHours;
    }
    
    public int getUsedTeamHours() {
        return usedTeamHours;
    }
    
    public void setUsedTeamHours(int usedTeamHours) {
        this.usedTeamHours = usedTeamHours;
    }
    
    public int getNaturalHolidayHours() {
        return naturalHolidayHours;
    }
    
    public void setNaturalHolidayHours(int naturalHolidayHours) {
        this.naturalHolidayHours = naturalHolidayHours;
    }
    
    public int getTeamLeaveHours() {
        return teamLeaveHours;
    }
    
    public void setTeamLeaveHours(int teamLeaveHours) {
        this.teamLeaveHours = teamLeaveHours;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
}
