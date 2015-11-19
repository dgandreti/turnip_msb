/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.detailsAjax;

import com.mss.msp.acc.details.AccountDetails;
import com.mss.msp.util.ServiceLocatorException;

/**
 *
 * @author Greg
 */
public interface AccountDetailsAjaxHandlerService {
    public AccountDetails ajaxAccountUpdate(AccountDetails accountDetails,int i,int userSessionId);
    public boolean checkForAccountName(String name,int id)  throws ServiceLocatorException ;
    public boolean checkForAccountURL(String url,int id)  throws ServiceLocatorException ;
}
