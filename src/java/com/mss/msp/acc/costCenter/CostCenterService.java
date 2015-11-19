/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.costCenter;

import com.mss.msp.util.ServiceLocatorException;
import java.util.List;

/**
 *
 * @author miracle
 */
public interface CostCenterService {

    public List costCenterSearch(CostCenterAction costCenterAction) throws ServiceLocatorException;

    public List costCenterInfoSearch(CostCenterAction aThis) throws ServiceLocatorException;

    public List costCenterDashBoardDetails(CostCenterAction costCenterAction) throws ServiceLocatorException;
}
