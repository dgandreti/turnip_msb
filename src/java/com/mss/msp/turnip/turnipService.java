/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.turnip;

import com.mss.msp.util.ServiceLocatorException;

/**
 *
 * @author miracle
 */
public interface turnipService {

    public String getDefaultVendorResourceDetails(turnipAction turnipAction) throws ServiceLocatorException;

    public String getDefaultProjectResourceDetails(turnipAction turnipAction) throws ServiceLocatorException;

    public String getDefaultClientResourceDetails(turnipAction turnipAction) throws ServiceLocatorException;
    
    public String getClientRequirementDetails(turnipAction turnipAction) throws ServiceLocatorException;

    public String getDefaultRegionResourcesDetails(turnipAction turnipAction) throws ServiceLocatorException;
}
