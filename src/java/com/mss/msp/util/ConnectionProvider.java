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
 * File    : ConnectionProvider.java
 *
 * Copyright 2015 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.msp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.mss.msp.util.ServiceLocatorException;
import com.mss.msp.util.Properties;
/**
 *
 * @author Rajasekhar (Phno 9290842877)
 */
public class ConnectionProvider {
    
    private static ConnectionProvider _instance;
    
    private  DataSource dataSource;
    private  Connection connection;
    
    
    
    private ConnectionProvider(){
        
    }
    
    public static ConnectionProvider getInstance(){
        
        if(_instance == null){
            _instance = new ConnectionProvider();
        }
        return _instance;
    }
    
    /**
     *
     * @return returns Connection from IntialContext
     */
    public Connection getConnection() throws ServiceLocatorException{
        try{
            
            dataSource = DataServiceLocator.getInstance().getDataSource(Properties.getProperty("New.DataSource.Name"));
            connection = dataSource.getConnection();
        }catch(ServiceLocatorException se) {
            throw new ServiceLocatorException("Exception in Connection Provider");
        }catch(SQLException sqlEx) {
            throw new ServiceLocatorException(sqlEx);
        }
        return connection;
    }
    
//    public Connection getMysqlConnection() throws ServiceLocatorException, SQLException{
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//            connection = DriverManager.getConnection("jdbc:mysql://172.17.4.167:3306/mirageteam","mirage","mirage");
//        return connection;
//    }
    
    
    
}
