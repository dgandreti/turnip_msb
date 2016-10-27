/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.vendorReviews;

import com.mss.msp.util.ServiceLocatorException;
import java.util.List;

/**
 *
 * @author miracle
 */
public interface VendorReviewsService {
    
    public List vendorRatingAndReveiw(VendorReviewsAction vendorReviewsAction) throws ServiceLocatorException;
    
    public int addVendorRatingAndReveiw(VendorReviewsAction vendorReviewsAction) throws ServiceLocatorException;
    
}
