/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usr.leaves;
import java.util.Map;
/**
 *
 * @author 
 */
public class EmpLeaves {
     private String leaveEditFrmDate;
    private String leaveEditEndDate;
    private String reportsTo;
    private int leave_id;
    private String leaveType;
    private String alertMessage;
    private String status;
    private int user;
    private String empName;

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }
    public void setLeaveId(int leave_id){
        this.leave_id = leave_id;
    }
    public int getLeaveId()
    {
        return leave_id;
    }
         
    public void setLeaveEditFrmDate(String leaveEditFrmDate){
        this.leaveEditFrmDate = leaveEditFrmDate;
    }
    public String getLeaveEditFrmDate(){
        return leaveEditFrmDate;
    }
    public void setLeaveEditEndDate(String leaveEditEndDate){
        this.leaveEditEndDate = leaveEditEndDate;
    }
    public String getLeaveEditEndDate(){
        return leaveEditEndDate;
    }
    public void setReportsTo(String reportsTo){
        this.reportsTo = reportsTo;
    }
    public String getReportsTo(){
        return reportsTo;
    }
    public void setLeaveType(String leaveType){
        this.leaveType=leaveType;
    }
    public String getLeaveType(){
        return leaveType;
    }
    public void setAlertMessage(String alertMessage){
        this.alertMessage = alertMessage;
    }
    public String getAlertMessage(){
        return alertMessage;
    }
}
