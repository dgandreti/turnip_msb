/*******************************************************************************
 *
 * Project : Miracle ServicesBay
 *
 * Package :
 *
 * Date    : April 07 2015
 *
 * Author  : Nagireddy Seerapu<nseerapu@miraclesoft.com>
 *       
 *
 * File    : HibernateServiceLocator.java
 *
 * Copyright 2015 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */

package com.mss.msp.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author <nseerapu@miraclesoft.com>
 *
 * This Class.... ENTER THE DESCRIPTION HERE
 */
public class HibernateServiceLocator {
    
    // Instance for this class
    private static HibernateServiceLocator _instance;
    
    // java.sql.Connection object
    private Connection connection;
    
    // Hibernate Session Varible
    private Session session;
    
    // Hibernate SessionFactory Varible
    private SessionFactory sessionFactory;
    
    // ThreadLocal variable with early Instantiation
    private ThreadLocal sessionThreadLocal = new ThreadLocal();
    
    /** Creates a new instance of HibernateServiceLocator */
    private HibernateServiceLocator() throws ServiceLocatorException{
        
    }
    
    /**
     * @return An instance of the HibernateServiceLocator class
     * @throws ServiceLocatorException
     */
    public static HibernateServiceLocator getInstance() throws ServiceLocatorException {
        try {
            if(_instance==null) {
                _instance = new HibernateServiceLocator();
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return _instance;
    }
    
    
    
    /*
     * This method is used to store the session in thread
     * @return Hibernate Session instance
     */
    
    public Session getSession() throws ServiceLocatorException {
        try {
            if (CacheManager.getCache().containsKey(ApplicationConstants.HIBERNATE_SESSION_FACTORY_KEY)) {
                sessionFactory = (SessionFactory) CacheManager.getCache().get(ApplicationConstants.HIBERNATE_SESSION_FACTORY_KEY);
            } else {
                sessionFactory = new Configuration().configure().buildSessionFactory();
                CacheManager.getCache().put(ApplicationConstants.HIBERNATE_SESSION_FACTORY_KEY, sessionFactory);
            }
            session = sessionFactory.openSession();
        } catch (HibernateException ex) {
            throw new ServiceLocatorException(ErrorMessages.CANNOT_GET_SESSIONFACTORY + ex.getMessage(),ex);
        }
        return session;
    }
    
}
