/**
 * *****************************************************************************
 *
 * Project : ServicesBay V1
 *
 * Package :
 *
 * Date : Feb 16, 2015, 7:53 PM
 *
 * Author : Services Bay Team
 *
 * File : ServiceLocator.java
 *
 * Copyright 2015 Miracle Software Systems, Inc. All rights reserved. MIRACLE
 * SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.msp.util;

import com.mss.msp.acc.AccountServiceImpl;
import com.mss.msp.acc.AccountService;
import com.mss.msp.acc.accajax.AccountAjaxHandlerService;
import com.mss.msp.acc.accajax.AccountAjaxHandlerServiceImpl;
import com.mss.msp.acc.costCenter.CostCenterService;
import com.mss.msp.acc.costCenter.CostCenterServiceImpl;
import com.mss.msp.acc.costCenterAjax.CostCenterAjaxHandlerService;
import com.mss.msp.acc.costCenterAjax.CostCenterAjaxHandlerServiceImpl;
import com.mss.msp.acc.details.AccountDetailsHandlerService;
import com.mss.msp.acc.details.AccountDetailsHandlerServiceImpl;
import com.mss.msp.acc.detailsAjax.AccountDetailsAjaxHandlerService;
import com.mss.msp.acc.detailsAjax.AccountDetailsAjaxHandlerServiceImpl;
import com.mss.msp.acc.projectsdata.ProjectTeamsDataHandlerService;
import com.mss.msp.acc.projectsdata.ProjectTeamsDataHandlerServiceImpl;
import com.mss.msp.acc.projectsdata.ProjectsDataHandlerService;
import com.mss.msp.acc.projectsdata.ProjectsDataHandlerServiceImpl;
import com.mss.msp.accauth.AccAuthServiceImpl;
import com.mss.msp.accauth.AccAuthServices;
import com.mss.msp.accauthajax.AccAuthAjaxHandlerService;
import com.mss.msp.accauthajax.AccAuthAjaxHandlerServiceImpl;
import com.mss.msp.budget.BudgetService;
import com.mss.msp.budget.BudgetServiceImpl;
import com.mss.msp.usrajax.UserAjaxHandlerService;
import com.mss.msp.usrajax.UserAjaxHandlerServiceImpl;
import com.mss.msp.general.GeneralServiceImpl;
import com.mss.msp.general.GeneralService;
import com.mss.msp.sag.InvoiceService;
import com.mss.msp.sag.InvoiceServiceImpl;
import com.mss.msp.sag.sow.SOWService;
import com.mss.msp.sag.sow.SOWServiceImpl;
import com.mss.msp.sagAjax.InvoiceAjaxService;
import com.mss.msp.sagAjax.InvoiceAjaxServiceImpl;
import com.mss.msp.lk.LookUpHandlerService;
import com.mss.msp.lk.LookUpHandlerServiceImpl;
import com.mss.msp.location.LocationService;
import com.mss.msp.location.LocationServiceImpl;
import com.mss.msp.onlineexam.OnlineExamService;
import com.mss.msp.onlineexam.OnlineExamServiceImpl;
import com.mss.msp.onlineexam.questions.ExamQuestionsService;
import com.mss.msp.onlineexam.questions.ExamQuestionsServiceImpl;
import com.mss.msp.reccruitmentAjax.RecruitmentAjaxHandlerService;
import com.mss.msp.reccruitmentAjax.RecruitmentAjaxHandlerServiceImpl;
import com.mss.msp.recruitment.RecruitmentService;
import com.mss.msp.recruitment.RecruitmentServiceImpl;
import com.mss.msp.requirement.RequirementService;
import com.mss.msp.requirement.RequirementServiceImpl;
import com.mss.msp.usr.task.TaskHandlerService;
import com.mss.msp.usr.task.TaskHandlerServiceImpl;
import com.mss.msp.usersdata.UsersdataHandlerservice;
import com.mss.msp.usersdata.UsersdataHandlerserviceImpl;
import com.mss.msp.usr.leaves.UserLeaveServiceImpl;
import com.mss.msp.usr.leaves.UserLeavesService;
import com.mss.msp.vendor.VendorService;
import com.mss.msp.vendor.VendorServiceImpl;
import com.mss.msp.vendor.vendorajax.VendorAjaxHandlerService;
import com.mss.msp.vendor.vendorajax.VendorAjaxHandlerServiceImpl;
import com.mss.msp.opp.OpportunityDataHandlerService;
import com.mss.msp.opp.OpportunityDataHandlerServiceImpl;
import com.mss.msp.usr.timesheets.UsrTimesheetService;
import com.mss.msp.usr.timesheets.UsrTimesheetServiceImpl;

public class ServiceLocator {

    public static GeneralService getGeneralService() {
        GeneralService generalService = new GeneralServiceImpl();
        return generalService;
    }

    public static UserAjaxHandlerService getUserAjaxHandlerService() {
        UserAjaxHandlerService service = new UserAjaxHandlerServiceImpl();
        return service;
    }

    public static ExamQuestionsService getExamQuestionsHandlerservice() {
        ExamQuestionsService service = new ExamQuestionsServiceImpl();
        return service;
    }

    public static TaskHandlerService getTaskHandlerService() {
        TaskHandlerService service = new TaskHandlerServiceImpl();
        return service;
    }

    public static UsersdataHandlerservice getUsersdataHandlerservicee() {
        UsersdataHandlerservice service = new UsersdataHandlerserviceImpl();
        return service;
    }

    public static AccountService getAccountService() {
        AccountService accService = new AccountServiceImpl();
        return accService;
    }

    public static AccountAjaxHandlerService getAccountAjaxHandlerService() {
        AccountAjaxHandlerService accAjaxService = new AccountAjaxHandlerServiceImpl();
        return accAjaxService;
    }

    public static RecruitmentService getRecruitmentService() {
        RecruitmentService recruitmentService = new RecruitmentServiceImpl();
        return recruitmentService;
    }

    public static RecruitmentAjaxHandlerService getRecruitmentAjaxHandlerService() {
        RecruitmentAjaxHandlerService recruitmentAjaxHandlerService = new RecruitmentAjaxHandlerServiceImpl();
        return recruitmentAjaxHandlerService;
    }

    public static UserLeavesService getUserLeavesService() {
        UserLeavesService userLeavesService = new UserLeaveServiceImpl();
        return userLeavesService;
    }

    public static LocationService getLocationService() {
        LocationService service = new LocationServiceImpl();
        return service;
    }

    public static VendorService getVendorService() {
        VendorService vendorService = new VendorServiceImpl();
        return vendorService;

    }

    public static VendorAjaxHandlerService getVendorAjaxHandlerService() {
        VendorAjaxHandlerService vendorAjaxHandlerService = new VendorAjaxHandlerServiceImpl();
        return vendorAjaxHandlerService;

    }

    public static RequirementService getRequirementService() {
        RequirementService requirementService = new RequirementServiceImpl();
        return requirementService;
    }

    public static LookUpHandlerService getLookUpHandlerService() {
        return new LookUpHandlerServiceImpl();
    }

    public static AccountDetailsAjaxHandlerService getAccountDetailsAjaxHandlerService() {
        return new AccountDetailsAjaxHandlerServiceImpl();
    }

    public static AccountDetailsHandlerService getAccountDetailsService() {
        return new AccountDetailsHandlerServiceImpl();
    }

    public static ProjectsDataHandlerService getProjectDataHandlerService() {
        return new ProjectsDataHandlerServiceImpl();
    }

    public static ProjectTeamsDataHandlerService getProjectTeamsDataHandlerService() {
        return new ProjectTeamsDataHandlerServiceImpl();
    }

    public static OpportunityDataHandlerService getOpportunityDataHandlerService() {
        return new OpportunityDataHandlerServiceImpl();
    }

    public static UsrTimesheetService getUserTimesheetService() {
        UsrTimesheetService usrTimesheetService = new UsrTimesheetServiceImpl();
        return usrTimesheetService;
    }

    public static BudgetService getBudgetService() {
        BudgetService budgetService = new BudgetServiceImpl();
        return budgetService;
    }

    public static AccAuthServices getAccAuthservice() {
        AccAuthServices accAuthServices = new AccAuthServiceImpl();
        return accAuthServices;
    }

    public static AccAuthAjaxHandlerService getAccAuthAjaxservice() {
        AccAuthAjaxHandlerService accAuthAjaxHandlerService = new AccAuthAjaxHandlerServiceImpl();
        return accAuthAjaxHandlerService;
    }

    public static InvoiceService getInvoiceService() {
        InvoiceService invoiceService = new InvoiceServiceImpl();
        return invoiceService;
    }

    public static InvoiceAjaxService getInvoiceAjaxService() {
        InvoiceAjaxService invoiceAjaxService = new InvoiceAjaxServiceImpl();
        return invoiceAjaxService;
    }

    public static SOWService getSOWService() {
        SOWService sowService = new SOWServiceImpl();
        return sowService;
    }

    public static CostCenterService getCostCenterService() {
        CostCenterService costCenterService = new CostCenterServiceImpl();
        return costCenterService;
    }

    public static CostCenterAjaxHandlerService getCostCenterAjaxHandlerService() {
        CostCenterAjaxHandlerService costCenterAjaxHandlerService = new CostCenterAjaxHandlerServiceImpl();
        return costCenterAjaxHandlerService;
    }
     public static OnlineExamService getOnlineExamService() {
        OnlineExamService onlineExamService = new OnlineExamServiceImpl();
        return onlineExamService;
    }   
    
}
