/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usr.timesheets;

import java.io.Serializable;

/**
 *
 * @author miracle
 */
public class TimesheetVTO implements Serializable {

    private int project1key;
    private int project2key;
    private int project3key;
    private int project4key;
    private int project5key;
    private int timesheetid;
    private int usr_id;
    private double totalhrs;
    private String reportsto1;
    private String reportsto1status;
    private int reprtsto2;
    private String reportsto2status;
    private String dateStart;
    private String dateEnd;
    private String statusChangedDate;
    private String submittedDate;
    private String approvedDate;
    private String comments;
    private String notes;
    private String ResourceType;
    private String empName;
    private String timeSheetStartDate;
    private String timeSheetEndDate;
    private String timeSheetApprovedDate;
    private String timeSheetSubmittedDate;
    private String weeklyDates1;
    private String weeklyDates2;
    private String weeklyDates3;
    private String weeklyDates4;
    private String weeklyDates5;
    private String weeklyDates6;
    private String weeklyDates7;
    private Double projectNameSun1;
    private Double projectNameMon1;
    private Double projectNameTue1;
    private Double projectNameWed1;
    private Double projectNameThu1;
    private Double projectNameFri1;
    private Double projectNameSat1;
    private Double projectNameSun2;
    private Double projectNameMon2;
    private Double projectNameTue2;
    private Double projectNameWed2;
    private Double projectNameThu2;
    private Double projectNameFri2;
    private Double projectNameSat2;
    private Double projectNameSun3;
    private Double projectNameMon3;
    private Double projectNameTue3;
    private Double projectNameWed3;
    private Double projectNameThu3;
    private Double projectNameFri3;
    private Double projectNameSat3;
    private Double projectNameSun4;
    private Double projectNameMon4;
    private Double projectNameTue4;
    private Double projectNameWed4;
    private Double projectNameThu4;
    private Double projectNameFri4;
    private Double projectNameSat4;
    private Double projectNameSun5;
    private Double projectNameMon5;
    private Double projectNameTue5;
    private Double projectNameWed5;
    private Double projectNameThu5;
    private Double projectNameFri5;
    private Double projectNameSat5;
    private Double internalSun;
    private Double internalMon;
    private Double internalTue;
    private Double internalWed;
    private Double internalThu;
    private Double internalFri;
    private Double internalSat;
    private Double vacationSun;
    private Double vacationMon;
    private Double vacationTue;
    private Double vacationWed;
    private Double vacationThu;
    private Double vacationFri;
    private Double vacationSat;
    private Double holidaySun;
    private Double holidayMon;
    private Double holidayTue;
    private Double holidayWed;
    private Double holidayThu;
    private Double holidayFri;
    private Double holidaySat;
    private Double comptimeSun;
    private Double comptimeMon;
    private Double comptimeTue;
    private Double comptimeWed;
    private Double comptimeThu;
    private Double comptimeFri;
    private Double comptimeSat;
    private String timeSheetNotes;
    private String timeSheetStatus;
    private double totalBillHrs;
    private String customerName;
    private String vendorName;

    public int getTimesheetid() {
        return timesheetid;
    }

    public void setTimesheetid(int timesheetid) {
        this.timesheetid = timesheetid;
    }

    public int getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public double getTotalhrs() {
        return totalhrs;
    }

    public void setTotalhrs(double totalhrs) {
        this.totalhrs = totalhrs;
    }

    public String getReportsto1() {
        return reportsto1;
    }

    public void setReportsto1(String reportsto1) {
        this.reportsto1 = reportsto1;
    }

    public String getReportsto1status() {
        return reportsto1status;
    }

    public void setReportsto1status(String reportsto1status) {
        this.reportsto1status = reportsto1status;
    }

    public int getReprtsto2() {
        return reprtsto2;
    }

    public void setReprtsto2(int reprtsto2) {
        this.reprtsto2 = reprtsto2;
    }

    public String getReportsto2status() {
        return reportsto2status;
    }

    public void setReportsto2status(String reportsto2status) {
        this.reportsto2status = reportsto2status;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getStatusChangedDate() {
        return statusChangedDate;
    }

    public void setStatusChangedDate(String statusChangedDate) {
        this.statusChangedDate = statusChangedDate;
    }

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getResourceType() {
        return ResourceType;
    }

    public void setResourceType(String ResourceType) {
        this.ResourceType = ResourceType;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getTimeSheetStartDate() {
        return timeSheetStartDate;
    }

    public void setTimeSheetStartDate(String timeSheetStartDate) {
        this.timeSheetStartDate = timeSheetStartDate;
    }

    public String getTimeSheetEndDate() {
        return timeSheetEndDate;
    }

    public void setTimeSheetEndDate(String timeSheetEndDate) {
        this.timeSheetEndDate = timeSheetEndDate;
    }

    public String getTimeSheetApprovedDate() {
        return timeSheetApprovedDate;
    }

    public void setTimeSheetApprovedDate(String timeSheetApprovedDate) {
        this.timeSheetApprovedDate = timeSheetApprovedDate;
    }

    public String getTimeSheetSubmittedDate() {
        return timeSheetSubmittedDate;
    }

    public void setTimeSheetSubmittedDate(String timeSheetSubmittedDate) {
        this.timeSheetSubmittedDate = timeSheetSubmittedDate;
    }

    public String getWeeklyDates1() {
        return weeklyDates1;
    }

    public void setWeeklyDates1(String weeklyDates1) {
        this.weeklyDates1 = weeklyDates1;
    }

    public String getWeeklyDates2() {
        return weeklyDates2;
    }

    public void setWeeklyDates2(String weeklyDates2) {
        this.weeklyDates2 = weeklyDates2;
    }

    public String getWeeklyDates3() {
        return weeklyDates3;
    }

    public void setWeeklyDates3(String weeklyDates3) {
        this.weeklyDates3 = weeklyDates3;
    }

    public String getWeeklyDates4() {
        return weeklyDates4;
    }

    public void setWeeklyDates4(String weeklyDates4) {
        this.weeklyDates4 = weeklyDates4;
    }

    public String getWeeklyDates5() {
        return weeklyDates5;
    }

    public void setWeeklyDates5(String weeklyDates5) {
        this.weeklyDates5 = weeklyDates5;
    }

    public String getWeeklyDates6() {
        return weeklyDates6;
    }

    public void setWeeklyDates6(String weeklyDates6) {
        this.weeklyDates6 = weeklyDates6;
    }

    public String getWeeklyDates7() {
        return weeklyDates7;
    }

    public void setWeeklyDates7(String weeklyDates7) {
        this.weeklyDates7 = weeklyDates7;
    }

    public Double getProjectNameSun1() {
        return projectNameSun1;
    }

    public void setProjectNameSun1(Double projectNameSun1) {
        this.projectNameSun1 = projectNameSun1;
    }

    public Double getProjectNameMon1() {
        return projectNameMon1;
    }

    public void setProjectNameMon1(Double projectNameMon1) {
        this.projectNameMon1 = projectNameMon1;
    }

    public Double getProjectNameTue1() {
        return projectNameTue1;
    }

    public void setProjectNameTue1(Double projectNameTue1) {
        this.projectNameTue1 = projectNameTue1;
    }

    public Double getProjectNameWed1() {
        return projectNameWed1;
    }

    public void setProjectNameWed1(Double projectNameWed1) {
        this.projectNameWed1 = projectNameWed1;
    }

    public Double getProjectNameThu1() {
        return projectNameThu1;
    }

    public void setProjectNameThu1(Double projectNameThu1) {
        this.projectNameThu1 = projectNameThu1;
    }

    public Double getProjectNameFri1() {
        return projectNameFri1;
    }

    public void setProjectNameFri1(Double projectNameFri1) {
        this.projectNameFri1 = projectNameFri1;
    }

    public Double getProjectNameSat1() {
        return projectNameSat1;
    }

    public void setProjectNameSat1(Double projectNameSat1) {
        this.projectNameSat1 = projectNameSat1;
    }

    public Double getProjectNameSun2() {
        return projectNameSun2;
    }

    public void setProjectNameSun2(Double projectNameSun2) {
        this.projectNameSun2 = projectNameSun2;
    }

    public Double getProjectNameMon2() {
        return projectNameMon2;
    }

    public void setProjectNameMon2(Double projectNameMon2) {
        this.projectNameMon2 = projectNameMon2;
    }

    public Double getProjectNameTue2() {
        return projectNameTue2;
    }

    public void setProjectNameTue2(Double projectNameTue2) {
        this.projectNameTue2 = projectNameTue2;
    }

    public Double getProjectNameWed2() {
        return projectNameWed2;
    }

    public void setProjectNameWed2(Double projectNameWed2) {
        this.projectNameWed2 = projectNameWed2;
    }

    public Double getProjectNameThu2() {
        return projectNameThu2;
    }

    public void setProjectNameThu2(Double projectNameThu2) {
        this.projectNameThu2 = projectNameThu2;
    }

    public Double getProjectNameFri2() {
        return projectNameFri2;
    }

    public void setProjectNameFri2(Double projectNameFri2) {
        this.projectNameFri2 = projectNameFri2;
    }

    public Double getProjectNameSat2() {
        return projectNameSat2;
    }

    public void setProjectNameSat2(Double projectNameSat2) {
        this.projectNameSat2 = projectNameSat2;
    }

    public Double getProjectNameSun3() {
        return projectNameSun3;
    }

    public void setProjectNameSun3(Double projectNameSun3) {
        this.projectNameSun3 = projectNameSun3;
    }

    public Double getProjectNameMon3() {
        return projectNameMon3;
    }

    public void setProjectNameMon3(Double projectNameMon3) {
        this.projectNameMon3 = projectNameMon3;
    }

    public Double getProjectNameTue3() {
        return projectNameTue3;
    }

    public void setProjectNameTue3(Double projectNameTue3) {
        this.projectNameTue3 = projectNameTue3;
    }

    public Double getProjectNameWed3() {
        return projectNameWed3;
    }

    public void setProjectNameWed3(Double projectNameWed3) {
        this.projectNameWed3 = projectNameWed3;
    }

    public Double getProjectNameThu3() {
        return projectNameThu3;
    }

    public void setProjectNameThu3(Double projectNameThu3) {
        this.projectNameThu3 = projectNameThu3;
    }

    public Double getProjectNameFri3() {
        return projectNameFri3;
    }

    public void setProjectNameFri3(Double projectNameFri3) {
        this.projectNameFri3 = projectNameFri3;
    }

    public Double getProjectNameSat3() {
        return projectNameSat3;
    }

    public void setProjectNameSat3(Double projectNameSat3) {
        this.projectNameSat3 = projectNameSat3;
    }

    public Double getProjectNameSun4() {
        return projectNameSun4;
    }

    public void setProjectNameSun4(Double projectNameSun4) {
        this.projectNameSun4 = projectNameSun4;
    }

    public Double getProjectNameMon4() {
        return projectNameMon4;
    }

    public void setProjectNameMon4(Double projectNameMon4) {
        this.projectNameMon4 = projectNameMon4;
    }

    public Double getProjectNameTue4() {
        return projectNameTue4;
    }

    public void setProjectNameTue4(Double projectNameTue4) {
        this.projectNameTue4 = projectNameTue4;
    }

    public Double getProjectNameWed4() {
        return projectNameWed4;
    }

    public void setProjectNameWed4(Double projectNameWed4) {
        this.projectNameWed4 = projectNameWed4;
    }

    public Double getProjectNameThu4() {
        return projectNameThu4;
    }

    public void setProjectNameThu4(Double projectNameThu4) {
        this.projectNameThu4 = projectNameThu4;
    }

    public Double getProjectNameFri4() {
        return projectNameFri4;
    }

    public void setProjectNameFri4(Double projectNameFri4) {
        this.projectNameFri4 = projectNameFri4;
    }

    public Double getProjectNameSat4() {
        return projectNameSat4;
    }

    public void setProjectNameSat4(Double projectNameSat4) {
        this.projectNameSat4 = projectNameSat4;
    }

    public Double getProjectNameSun5() {
        return projectNameSun5;
    }

    public void setProjectNameSun5(Double projectNameSun5) {
        this.projectNameSun5 = projectNameSun5;
    }

    public Double getProjectNameMon5() {
        return projectNameMon5;
    }

    public void setProjectNameMon5(Double projectNameMon5) {
        this.projectNameMon5 = projectNameMon5;
    }

    public Double getProjectNameTue5() {
        return projectNameTue5;
    }

    public void setProjectNameTue5(Double projectNameTue5) {
        this.projectNameTue5 = projectNameTue5;
    }

    public Double getProjectNameWed5() {
        return projectNameWed5;
    }

    public void setProjectNameWed5(Double projectNameWed5) {
        this.projectNameWed5 = projectNameWed5;
    }

    public Double getProjectNameThu5() {
        return projectNameThu5;
    }

    public void setProjectNameThu5(Double projectNameThu5) {
        this.projectNameThu5 = projectNameThu5;
    }

    public Double getProjectNameFri5() {
        return projectNameFri5;
    }

    public void setProjectNameFri5(Double projectNameFri5) {
        this.projectNameFri5 = projectNameFri5;
    }

    public Double getProjectNameSat5() {
        return projectNameSat5;
    }

    public void setProjectNameSat5(Double projectNameSat5) {
        this.projectNameSat5 = projectNameSat5;
    }

    public Double getInternalSun() {
        return internalSun;
    }

    public void setInternalSun(Double internalSun) {
        this.internalSun = internalSun;
    }

    public Double getInternalMon() {
        return internalMon;
    }

    public void setInternalMon(Double internalMon) {
        this.internalMon = internalMon;
    }

    public Double getInternalTue() {
        return internalTue;
    }

    public void setInternalTue(Double internalTue) {
        this.internalTue = internalTue;
    }

    public Double getInternalWed() {
        return internalWed;
    }

    public void setInternalWed(Double internalWed) {
        this.internalWed = internalWed;
    }

    public Double getInternalThu() {
        return internalThu;
    }

    public void setInternalThu(Double internalThu) {
        this.internalThu = internalThu;
    }

    public Double getInternalFri() {
        return internalFri;
    }

    public void setInternalFri(Double internalFri) {
        this.internalFri = internalFri;
    }

    public Double getInternalSat() {
        return internalSat;
    }

    public void setInternalSat(Double internalSat) {
        this.internalSat = internalSat;
    }

    public Double getVacationSun() {
        return vacationSun;
    }

    public void setVacationSun(Double vacationSun) {
        this.vacationSun = vacationSun;
    }

    public Double getVacationMon() {
        return vacationMon;
    }

    public void setVacationMon(Double vacationMon) {
        this.vacationMon = vacationMon;
    }

    public Double getVacationTue() {
        return vacationTue;
    }

    public void setVacationTue(Double vacationTue) {
        this.vacationTue = vacationTue;
    }

    public Double getVacationWed() {
        return vacationWed;
    }

    public void setVacationWed(Double vacationWed) {
        this.vacationWed = vacationWed;
    }

    public Double getVacationThu() {
        return vacationThu;
    }

    public void setVacationThu(Double vacationThu) {
        this.vacationThu = vacationThu;
    }

    public Double getVacationFri() {
        return vacationFri;
    }

    public void setVacationFri(Double vacationFri) {
        this.vacationFri = vacationFri;
    }

    public Double getVacationSat() {
        return vacationSat;
    }

    public void setVacationSat(Double vacationSat) {
        this.vacationSat = vacationSat;
    }

    public Double getHolidaySun() {
        return holidaySun;
    }

    public void setHolidaySun(Double holidaySun) {
        this.holidaySun = holidaySun;
    }

    public Double getHolidayMon() {
        return holidayMon;
    }

    public void setHolidayMon(Double holidayMon) {
        this.holidayMon = holidayMon;
    }

    public Double getHolidayTue() {
        return holidayTue;
    }

    public void setHolidayTue(Double holidayTue) {
        this.holidayTue = holidayTue;
    }

    public Double getHolidayWed() {
        return holidayWed;
    }

    public void setHolidayWed(Double holidayWed) {
        this.holidayWed = holidayWed;
    }

    public Double getHolidayThu() {
        return holidayThu;
    }

    public void setHolidayThu(Double holidayThu) {
        this.holidayThu = holidayThu;
    }

    public Double getHolidayFri() {
        return holidayFri;
    }

    public void setHolidayFri(Double holidayFri) {
        this.holidayFri = holidayFri;
    }

    public Double getHolidaySat() {
        return holidaySat;
    }

    public void setHolidaySat(Double holidaySat) {
        this.holidaySat = holidaySat;
    }

    public Double getComptimeSun() {
        return comptimeSun;
    }

    public void setComptimeSun(Double comptimeSun) {
        this.comptimeSun = comptimeSun;
    }

    public Double getComptimeMon() {
        return comptimeMon;
    }

    public void setComptimeMon(Double comptimeMon) {
        this.comptimeMon = comptimeMon;
    }

    public Double getComptimeTue() {
        return comptimeTue;
    }

    public void setComptimeTue(Double comptimeTue) {
        this.comptimeTue = comptimeTue;
    }

    public Double getComptimeWed() {
        return comptimeWed;
    }

    public void setComptimeWed(Double comptimeWed) {
        this.comptimeWed = comptimeWed;
    }

    public Double getComptimeThu() {
        return comptimeThu;
    }

    public void setComptimeThu(Double comptimeThu) {
        this.comptimeThu = comptimeThu;
    }

    public Double getComptimeFri() {
        return comptimeFri;
    }

    public void setComptimeFri(Double comptimeFri) {
        this.comptimeFri = comptimeFri;
    }

    public Double getComptimeSat() {
        return comptimeSat;
    }

    public void setComptimeSat(Double comptimeSat) {
        this.comptimeSat = comptimeSat;
    }

    public String getTimeSheetNotes() {
        return timeSheetNotes;
    }

    public void setTimeSheetNotes(String timeSheetNotes) {
        this.timeSheetNotes = timeSheetNotes;
    }

    public String getTimeSheetStatus() {
        return timeSheetStatus;
    }

    public void setTimeSheetStatus(String timeSheetStatus) {
        this.timeSheetStatus = timeSheetStatus;
    }

    public Double getTotalBillHrs() {
        return totalBillHrs;
    }

    public void setTotalBillHrs(Double totalBillHrs) {
        this.totalBillHrs = totalBillHrs;
    }

    public int getProject1key() {
        return project1key;
    }

    public void setProject1key(int project1key) {
        this.project1key = project1key;
    }

    public int getProject2key() {
        return project2key;
    }

    public void setProject2key(int project2key) {
        this.project2key = project2key;
    }

    public int getProject3key() {
        return project3key;
    }

    public void setProject3key(int project3key) {
        this.project3key = project3key;
    }

    public int getProject4key() {
        return project4key;
    }

    public void setProject4key(int project4key) {
        this.project4key = project4key;
    }

    public int getProject5key() {
        return project5key;
    }

    public void setProject5key(int project5key) {
        this.project5key = project5key;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    
}
