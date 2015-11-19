/*******************************************************************************
 *
 * Project : ServicesBay V1
 *
 * Package :
 *
 * Date    :  Feb 16, 2015, 7:53 PM
 *
 * Author  : Services Bay Team
 *
 * File    : CacheManager.java
 *
 * Copyright 2015 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.msp.util;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class CacheManager {
    
    /* Cache to store the datasource objects
     * for improving performance by avoided repetative
     * calls to the JNDI registry
     */  
    private static Map cache;
    
    
    /** Creates a new instance of ApplicationCacheManager */
    private CacheManager() {
        
    }
    
    /**
     * @return An instance of the Cache Map
     * @throws ServiceLocatorException
     */
    public static Map getCache() throws ServiceLocatorException {
        try {
            if(cache == null) {
                cache = Collections.synchronizedMap(new HashMap());
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return cache;
    }
}
