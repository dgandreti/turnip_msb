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
 * File    : DataServiceLocator.java
 *
 * Copyright 2015 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.msp.util;


import com.mchange.v2.c3p0.PooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.naming.Context;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * This is a Data Service Locator object used to abstract all JNDI usage and to hide the
 * complexities of initial context creation, DataSource lookup. Multiple clients can reuse
 * the Service Locator object to reduce code complexity, provide a single point of control,
 * and improve performance by providing a caching facility.
 * <p>
 * This class reduces the client complexity that results from the client's dependency on and
 * need to perform lookup and creation processes, which are resource-intensive. To eliminate these
 * problems, this pattern provides a mechanism to abstract all dependencies and network details into
 * the Service Locator.
 *
 * <p>
 * Usage: This is a Singleton class, usage is as follows:<br>
 * Use the getInstance method to create an instance of the class.
 *
 * <code>ServiceLocator serviceLocator = ServiceLocator.getInstance();</code>
 *
 * @author MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 * @version 1.0
 *
 */

public class DataServiceLocator {
    
    private Context context;
    
    private static DataServiceLocator _instance;
    
    private DataServiceLocator() throws ServiceLocatorException {
        try {
            context = new InitialContext();
        }catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
    }
    
    /**
     * @return An instance of the DataServiceLocator class
     * @throws ServiceLocatorException
     */
    public static DataServiceLocator getInstance() throws ServiceLocatorException {
        try {
            if(_instance==null) {
                _instance = new DataServiceLocator();
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return _instance;
    }
    
    /**
     * @param dataSourceName - Name of the DataSource, needs to be looked up
     * @return Looked DataSource from the JNDI registry.
     * @throws ServiceLocatorException when there exists a problem Looking up the Data Source.
     */
    public DataSource getDataSource(String dataSourceName)
    throws ServiceLocatorException {
        DataSource dataSource = null;
        
        try {
            if (CacheManager.getCache().containsKey(dataSourceName)) {
                dataSource = (DataSource) CacheManager.getCache().get(dataSourceName);
               
            } else {
                dataSource = (DataSource) context.lookup("java:comp/env/"+dataSourceName);
                CacheManager.getCache().put(dataSourceName, dataSource);
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ErrorMessages.CANNOT_GET_DATASOURCE + ex.getMessage(),ex);
        }
        
//        try{
//            // make sure it's a c3p0 PooledDataSource
//            if ( dataSource instanceof PooledDataSource) {
//                PooledDataSource pds = (PooledDataSource) dataSource;
//                System.err.println("num_connections: " + pds.getNumConnectionsDefaultUser());
//                System.err.println("num_busy_connections: " + pds.getNumBusyConnectionsDefaultUser());
//                System.err.println("num_idle_connections: " + pds.getNumIdleConnectionsDefaultUser());
//                System.err.println("Thread Pool Sise: "+pds.getThreadPoolSize());
//                System.err.println("Active Threads: "+pds.getThreadPoolNumActiveThreads());
//                System.err.println("Idle Threads: "+pds.getThreadPoolNumIdleThreads());
//                System.err.println("Number Of Connections All Users: "+pds.getNumConnectionsAllUsers());
//                System.err.println("num_user_pools: "+pds.getNumUserPools());
//                System.err.println();
//            } else System.err.println("Not a c3p0 PooledDataSource!");
//        }catch(SQLException sql){
//            throw new ServiceLocatorException(ErrorMessages.CANNOT_GET_DATASOURCE + sql.getMessage(),sql);
//        }
        
        return dataSource;
    }
    
    
    
}
