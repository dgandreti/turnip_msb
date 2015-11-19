/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.opp;

import com.mss.msp.util.ServiceLocatorException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Anton Franklin
 */
public interface OpportunityDataHandlerService {

    public List getOpportunitiesByAccID(Integer accountID) throws ServiceLocatorException;

    public void addOpportunity(OpportunityVTO opprotunity) throws ServiceLocatorException;

    public OpportunityVTO getOpportunityByOpportunityID(OpportunityDataHandlerAction opportunityDataHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public void updateOpportunity(OpportunityVTO opportunity, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public List getOpportunitySearchDetails(OpportunityDataHandlerAction opportunityDataHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
}
