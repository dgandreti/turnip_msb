/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.sag.sow;

import com.mss.msp.util.ServiceLocatorException;
import java.util.List;

/**
 *
 * @author miracle
 */
public interface SOWService {

    public List getSowDetails(SOWAction sowAction) throws ServiceLocatorException;

    public List getSOWSearchResults(SOWAction sowAction) throws ServiceLocatorException;

    public String getSOWEditDetails(SOWAction sowAction) throws ServiceLocatorException;

    public String doAddUpdateSOWDetails(SOWAction sowAction) throws ServiceLocatorException;

    public int doAddSOWAttachment(SOWAction sowAction) throws ServiceLocatorException;

    public List getSOWAttachments(SOWAction sowAction) throws ServiceLocatorException;

    public String doInsertSAGRecord(SOWAction sowAction) throws ServiceLocatorException;

    public int SOWSaveOrSubmit(SOWAction sowAction) throws ServiceLocatorException;

    public String sowRecreateEdit(SOWAction sowAction) throws ServiceLocatorException;

    public String poDownloadButton(SOWAction aThis) throws ServiceLocatorException;
}
