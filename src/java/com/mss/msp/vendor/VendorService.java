/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.vendor;

import com.mss.msp.util.ServiceLocatorException;
import com.mss.msp.vendor.vendorajax.VendorAjaxHandler;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author NagireddySeerapu
 */
public interface VendorService {

    public List getVendorDetails( VendorAction vendorAction) throws ServiceLocatorException;

    public VendorListVTO getVendorDetailsById(VendorAction vendorAction) throws ServiceLocatorException;

    public int updateVendorSalesTeam(VendorAction vendorAction, String[] sales,int primaryAccount) throws ServiceLocatorException;
    
    public List<VendorDashboardList> showVendorDashboard(int orgId) throws ServiceLocatorException;
}
