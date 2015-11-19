/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.sagAjax;

import com.mss.msp.util.ServiceLocatorException;

/**
 *
 * @author miracle
 */
public interface InvoiceAjaxService {

    public String generateInvoice(InvoiceAjaxAction aThis)throws ServiceLocatorException;
    
    public String getRecreatedList(InvoiceAjaxAction aThis)throws ServiceLocatorException;
    
     public String getTotalHoursTooltip(InvoiceAjaxAction aThis) throws ServiceLocatorException;
}
