/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.accauth;

import com.mss.msp.util.ServiceLocatorException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author miracle
 */
public interface AccAuthServices {

    public List getAccAuthrization(AccAuthAction accAuthAction) throws ServiceLocatorException;

    public List searchAccAuthorization(AccAuthAction accAuthAction) throws ServiceLocatorException;

    public List searchActionResources(AccAuthAction accAuthAction) throws ServiceLocatorException;
}
