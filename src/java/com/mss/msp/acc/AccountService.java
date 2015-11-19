package com.mss.msp.acc;

import com.mss.msp.location.Country;
import com.mss.msp.location.State;
import com.mss.msp.util.ServiceLocatorException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author NagireddySeerapu
 */
public interface AccountService {

    /**
     * Returns all Accounts that have the given paramaters
     *
     * @param name The Name of the account, if null or empty String will be left
     * out of search
     * @param url The Url of the Accounts website, if null or empty String will
     * be left out of search
     * @param zip The Zip of the Accounts main office, if null or empty String
     * will be left out of search
     * @param status The Status of the Account, if null or empty String will be
     * left out of search
     * @param state The State of The Accounts main office, if null or empty
     * String will be left out of search
     * @return
     * @throws ServiceLocatorException
     */
    public List<Account> doAccountSearch(String name, String url, String zip, String status,
            State state, Country Country, Integer type, Integer industry, String lastAcccessDate, Integer orgId, Integer csrId) throws ServiceLocatorException;

    public List<Account> doAccountSearch(Account account, Integer orgId, Integer csrId);

    /**
     * Get the account based on users log in.
     *
     * @return Account the account associated with the user from
     * ApplicationConstants.ORG_REL (account_id)
     */
    public Account viewAccount(int id) throws ServiceLocatorException;

    /**
     * Gets All States that Accounts are in
     *
     * @return A list of States an Account is in
     * @throws ServiceLocatorException
     */
    public List<String> getAccountStatuses();

    public List<String> lookupAccountNamesByName(String accountName);

    public int updateAccSalesTeam(com.mss.msp.acc.details.AccountDetailsAction accountDetailsAction, String[] sales, int primaryAccount) throws ServiceLocatorException;

    public String addAccountContactDetails(AccountAction accountAction) throws ServiceLocatorException;

    /**
     *
     * @param account
     * @param userId
     * @param orgId
     * @return
     */
    public boolean addAccount(Account account, Integer userId, Integer orgId);

    public String checkAccount(Account account, Integer userId, Integer orgId);

    public void updateImageForProfile(AccountAction accountAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String addTeamMemberToProject(AccountAction aThis, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    public String getProjectName(int projectId, HttpServletRequest httpServletRequest) throws ServiceLocatorException;

    // public ProjectTeamDetailsVTO getProjectEmpDetails(AccountAction accountAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException;
    // Add by Aklakh
    public Account getSubProjectDetails(HttpServletRequest httpServletRequest, AccountAction accountAction) throws ServiceLocatorException;

    public int updateAssignTeam(AccountAction accountAction, String[] project) throws ServiceLocatorException;

    public Account getProjectTeamDetails(HttpServletRequest httpServletRequest, AccountAction accountAction) throws ServiceLocatorException;
}
