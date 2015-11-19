/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.sag;

import com.mss.msp.util.ServiceLocatorException;
import java.util.List;

/**
 *
 * @author miracle
 */
public interface InvoiceService {

    public List getInvoiceDetails(InvoiceAction invoiceAction, String typeUser) throws ServiceLocatorException;

    public boolean deleteInvoice(int invoiceId) throws ServiceLocatorException;

    public List doSearchInvoiceDetails(InvoiceAction aThis, String toString) throws ServiceLocatorException;

    public InvoiceVTO goInvoiceEditDetails(InvoiceAction aThis,String typeofuser) throws ServiceLocatorException;

    public boolean doEditInvoiceDetatils(InvoiceVTO invoiceVTOClass,String typeOfUser,InvoiceAction aThis)throws ServiceLocatorException;

    
}
