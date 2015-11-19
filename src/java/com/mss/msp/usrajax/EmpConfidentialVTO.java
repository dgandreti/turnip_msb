/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usrajax;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Manikanta Eeralla <meeralla@miraclesoft.com>
 * 
 * 
 */
public class EmpConfidentialVTO implements Serializable {

    private int userid;
   
    private String ssnPanNo;
    private String panName;
    private String empBankName;
    private String empBankAccNo;
    private String empBankAccName;
    private String empBankIfsc;
    private String empUan;
    private String empPfNo;
    private String empPassportNo;
    private Date empPassportExpDate;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

  

   

    public String getSsnPanNo() {
        return ssnPanNo;
    }

    public void setSsnPanNo(String ssnPanNo) {
        this.ssnPanNo = ssnPanNo;
    }

    public String getPanName() {
        return panName;
    }

    public void setPanName(String panName) {
        this.panName = panName;
    }

    public String getEmpBankName() {
        return empBankName;
    }

    public void setEmpBankName(String empBankName) {
        this.empBankName = empBankName;
    }

    public String getEmpBankAccNo() {
        return empBankAccNo;
    }

    public void setEmpBankAccNo(String empBankAccNo) {
        this.empBankAccNo = empBankAccNo;
    }

    public String getEmpBankAccName() {
        return empBankAccName;
    }

    public void setEmpBankAccName(String empBankAccName) {
        this.empBankAccName = empBankAccName;
    }

    public String getEmpBankIfsc() {
        return empBankIfsc;
    }

    public void setEmpBankIfsc(String empBankIfsc) {
        this.empBankIfsc = empBankIfsc;
    }

    public String getEmpUan() {
        return empUan;
    }

    public void setEmpUan(String empUan) {
        this.empUan = empUan;
    }

    public String getEmpPfNo() {
        return empPfNo;
    }

    public void setEmpPfNo(String empPfNo) {
        this.empPfNo = empPfNo;
    }

    public String getEmpPassportNo() {
        return empPassportNo;
    }

    public void setEmpPassportNo(String empPassportNo) {
        this.empPassportNo = empPassportNo;
    }

    public Date getEmpPassportExpDate() {
        return empPassportExpDate;
    }

    public void setEmpPassportExpDate(Date empPassportExpDate) {
        this.empPassportExpDate = empPassportExpDate;
    }

    
}
