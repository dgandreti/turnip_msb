package com.mss.msp.general;

import com.mss.msp.util.ServiceLocatorException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface GeneralService {

    public String generateUserId(String mailId) throws ServiceLocatorException;

    ;
    
    public int doUpdateResetPassword(String password, String email) throws ServiceLocatorException;

    public String forGotPwdLinkStatus(String mailId, String ssid) throws ServiceLocatorException;

    public int doPasswordLinkStatusUpdate(String email) throws ServiceLocatorException;

    public String getPrimaryAction(int orgId, int roleId) throws ServiceLocatorException;

    public Map getCountriesNames();

    public Map getStatesMapOfCountry(HttpServletRequest httpServletRequest, int id);

    public String getStatesStringOfCountry(HttpServletRequest httpServletRequest, int id);

    public String getStatesOfCountry(HttpServletRequest httpServletRequest, int id);

    public List getDefaultRequirementDashBoardDetails(GeneralAction generalAction) throws ServiceLocatorException;
    
    public int verifyCurrentPassword(GeneralAction generalAction) throws ServiceLocatorException;
    
    public String UserRegistration(GeneralAction generalAction) throws ServiceLocatorException;
}
