/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.accajax;

import com.mss.msp.acc.Account;
import com.mss.msp.util.ServiceLocatorException;

/**
 *
 * @author NagireddySeerapu
 */
public interface AccountAjaxHandlerService {

    public Account ajaxAccountUpdate(Account account);

    public boolean checkForAccountName(String name, int id) throws ServiceLocatorException;

    public boolean checkForAccountURL(String url, int id) throws ServiceLocatorException;

    public String getContactDetails(int searchOrgId, String typeOfAccount) throws ServiceLocatorException;

    public String getLocationDetails(int searchOrgId, AccountAjaxHandler accountAjaxHandler) throws ServiceLocatorException;

    public String editLocationDetails(AccountAjaxHandler accountAjaxHandler) throws ServiceLocatorException;

    public String addOrUpdateLocationDetails(AccountAjaxHandler aThis, int orgUserId) throws ServiceLocatorException;

    public String saveUserContacts(int orgUserId, int userSessionId) throws ServiceLocatorException;

    public int getUserCount(int orgUserId) throws ServiceLocatorException;

    public String getContactSearchResults(AccountAjaxHandler accountAjaxHandler, int orgUserId) throws ServiceLocatorException;

    public String getDefaultVendorTiers(AccountAjaxHandler accountAjaxHandler) throws ServiceLocatorException;

    public String addVendorTierToCustmer(AccountAjaxHandler accountAjaxHandler) throws ServiceLocatorException;

    public String editVendorTierDetails(AccountAjaxHandler accountAjaxHandler) throws ServiceLocatorException;

    public String searchVendorTierDetails(AccountAjaxHandler accountAjaxHandler) throws ServiceLocatorException;

    //  public String getReportingPersonDetails(AccountAjaxHandler accountAjaxHandler, int designationId) throws ServiceLocatorException;
    public String getVendorDetails(AccountAjaxHandler accountAjaxHandler) throws ServiceLocatorException;

    public String saveVendorTierDetails(AccountAjaxHandler accountAjaxHandler) throws ServiceLocatorException;

    public String getVendorTierOverlayDetails(AccountAjaxHandler accountAjaxHandler) throws ServiceLocatorException;

    public String getCsrDetailsTable(AccountAjaxHandler aThis, int orgUserId) throws ServiceLocatorException;

    public int doAddAccountToCsr(AccountAjaxHandler aThis, int orgUserId) throws ServiceLocatorException;

    public int csrStatusChange(AccountAjaxHandler aThis, int orgUserId) throws ServiceLocatorException;

    public int accountTransferOrCopy(AccountAjaxHandler accountAjaxHandler) throws ServiceLocatorException;

    public String getAttachmentDetails(int searchOrgId, String typeOfAccount) throws ServiceLocatorException;

    public String getVendorFormEditDetails(int searchOrgId, int acc_attachment_id) throws ServiceLocatorException;

    public String getAttachmentsSearchDetails(AccountAjaxHandler accountAjaxHandler, int searchOrgId) throws ServiceLocatorException;
    
    public int getLocationCount(int orgUserId,String locationName) throws ServiceLocatorException;
}
