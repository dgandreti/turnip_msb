/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.util;

import com.mss.msp.util.CacheManager;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * 
 *
 * @author NagireddySeerapu
 */
public class HibernateDataProvider {
    private static HibernateDataProvider _instance;
    
     public HibernateDataProvider(){
        
    }
    /**
     * @return An instance of the DataServiceLocator class
     * @throws ServiceLocatorException
     */
    public static HibernateDataProvider getInstance() throws ServiceLocatorException {
        try {
            if(_instance==null) {
                _instance = new HibernateDataProvider();
            }
        } catch (Exception ex) {
            throw new ServiceLocatorException(ex);
        }
        return _instance;
    }
    
    /**
     *@ returns map for IndustryType.
     *@ throws HibernateException.
     */
    public Map getRoles(String industryTypeKey) throws ServiceLocatorException{
        Map industryTypeMap = new TreeMap();//key-Description
        if (CacheManager.getCache().containsKey(industryTypeMap)) {
            
            industryTypeMap  = (Map) CacheManager.getCache().get(industryTypeKey);
        } else {
            //getting sessionFactory for the HibernateUtil class.
            Session session = HibernateServiceLocator.getInstance().getSession();
            
            //Creating a transaction for the session object.
            Transaction tran=session.beginTransaction();
            //Genarating a quary for retrieving the data from the database.
            
            String SQL_STG="select tp.id,tp.description from RolesData as tp WHERE tp.description!='Pre-Sales' AND tp.description!='Sourcing'";
            Query query=session.createQuery(SQL_STG);
            for(Iterator it=query.iterate();it.hasNext();){
                Object[] row = (Object[]) it.next();
                
                //Storing values into the TreeMap.
                industryTypeMap.put(row[0].toString(),row[1].toString());
                
            }// closing for loop.
            
            //Storing the rolesMap object in to the cache as singleton object.
            CacheManager.getCache().put(industryTypeKey, industryTypeMap);
            
            // Closing hibernate session
            try{
                // Closing hibernate session
                session.close();
                session = null;
            }catch(HibernateException he){
                throw new ServiceLocatorException(he);
            }finally{
                if(session!=null){
                    try{
                        session.close();
                        session = null;
                    }catch(HibernateException he){
                        he.printStackTrace();
                    }
                }
            }
            
            
        }// closing if condition.
        
        return industryTypeMap; // returning the object.
    }//closing the method.
    
    
}
