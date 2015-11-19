/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.vendor.vendorajax;

import com.mss.msp.util.ServiceLocatorException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author NagireddySeerapu
 */
public interface VendorAjaxHandlerService {

    public String getVendorStates( VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException;

    public String getVendorSearchDetails( VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException;

    public int updateVendorDetails(VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException;

    public String getVendorContactDetails(int orgId) throws ServiceLocatorException;

    public String saveVendorContacts(int userId, int userSessionId) throws ServiceLocatorException;

    public String getVendorContactSearchResults(VendorAjaxHandler vendorAjaxHandler, int orgId) throws ServiceLocatorException;

    public String getVendorsListByTireType(VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException;

    public int SaveVendorsAssociationDetals( VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException;

    public String getVendorAssociationDetails( VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException;

    public String searchVendorAssociationDetails( VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException;
    // added by Aklakh

    public String editVendorAssociation( int vendorId,int orgId) throws ServiceLocatorException;

    public String getVendorNames(int tireId) throws ServiceLocatorException;

    public int updateVendorAssociationDetails(VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException;

    public String getVendorDashboardList(int year, int month, int sessionOrgId) throws ServiceLocatorException;
    
     public String getVendorReqDashBoardGrid(VendorAjaxHandler vendorAjaxHandler) throws ServiceLocatorException;
}
