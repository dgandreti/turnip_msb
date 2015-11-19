/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.details;

import com.mss.msp.acc.Account;
import com.mss.msp.util.ServiceLocatorException;

/**
 *
 * @author Greg
 */
public interface AccountDetailsHandlerService {

    /**
     * Get the account based on users log in.
     *
     * @return Account the account associated with the user from
     * ApplicationConstants.ORG_REL (account_id)
     */
    public AccountDetails viewAccountDetails(int id) throws ServiceLocatorException;

    public AccountContactVTO editAccountContacts(int contactId) throws ServiceLocatorException;

    public String updateAccountContactDetails(AccountDetailsAction accountDetailsAction) throws ServiceLocatorException;
}
