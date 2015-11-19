/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.costCenterAjax;

import com.mss.msp.acc.costCenter.*;
import com.mss.msp.util.ServiceLocatorException;
import java.util.List;

/**
 *
 * @author miracle
 */
public interface CostCenterAjaxHandlerService {

    public String getCostCenterDashboardDetails(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException;

    public String getCostCentersDashboardForOrg(CostCenterAjaxHandlerAction costCenterAjaxHandlerAction) throws ServiceLocatorException;

    public String getProjectNamesInCostCenter(CostCenterAjaxHandlerAction aThis) throws ServiceLocatorException;

    public String costCenterInfoSearchList(CostCenterAjaxHandlerAction aThis) throws ServiceLocatorException;

    public String addCostCenter(CostCenterAjaxHandlerAction CostCenterAjaxHandlerAction) throws ServiceLocatorException;

    public String editCostCenter(CostCenterAjaxHandlerAction aThis) throws ServiceLocatorException;

    public String addCostCenterBudget(CostCenterAjaxHandlerAction aThis) throws ServiceLocatorException;

    public String getCostCenterBudgetDetails(CostCenterAjaxHandlerAction aThis) throws ServiceLocatorException;
}
