/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.accauthajax;

/**
 *
 * @author miracle
 */
import com.mss.msp.util.ServiceLocatorException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface AccAuthAjaxHandlerService {

    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
    public String insertOrUpdateAccAuth(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException;

    public String getRolesForAccType(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException;

    public String getAccountNames(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException;

    public String getActionResorucesSearchResults(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException;

    public String insertOrUpdateActionResources(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException;
    
    public String actionResourceTermination(AccAuthAjaxHandlerAction accAuthAjaxHandlerAction) throws ServiceLocatorException;
}
