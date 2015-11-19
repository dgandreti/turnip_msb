/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.budget;

import com.mss.msp.util.ServiceLocatorException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public interface BudgetService {

    public List getProjectBudgetDetails(BudgetAction budgetAction) throws ServiceLocatorException;

    public String getProjectBudgetSearchResults(BudgetAction budgetAction) throws ServiceLocatorException;

    public String saveProjectBudgetDetails( BudgetAction budgetAction) throws ServiceLocatorException;

    public String getProjectBudgetDetailsToViewOnOverlay( BudgetAction budgetAction) throws ServiceLocatorException;

    public String doBudgetRecordDelete( BudgetAction budgetAction) throws ServiceLocatorException;

    public String setDirectorResultForBudget( BudgetAction budgetAction) throws ServiceLocatorException;
    
    public String getCostCentertDetailsByProjectId(BudgetAction budgetAction) throws ServiceLocatorException;
}
