/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.lk;

import com.mss.msp.acc.Account;
import com.mss.msp.util.HibernateServiceLocator;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Greg Service to look up ids NOT SUPPORTED lk TABLES ==>
 * lk_task_status , lk_taskrelated_to
 */
public class LookUpHandlerServiceImpl implements LookUpHandlerService {

    public String getName(String tableName, String columnName, Integer id) throws ServiceLocatorException {

        Session session = null;
        String name = "";


        session = HibernateServiceLocator.getInstance().getSession();

        Query query = session.createSQLQuery("SELECT " + columnName + " FROM " + tableName + " WHERE id = " + id + " ;");
        List result = query.list();
        if (result.size() > 0) {
            name = result.get(0).toString();//may need to catch null error
        }
        try {
            // Closing hibernate session
            session.close();
            session = null;
        } catch (HibernateException he) {
            throw new ServiceLocatorException(he);
        } finally {
            if (session != null) {
                try {
                    session.close();
                    session = null;
                } catch (HibernateException he) {
                    he.printStackTrace();
                }
            }
            return name;
        }

    }

    public Integer getId(String tableName, String columnName, String name) throws ServiceLocatorException {
        Session session = null;
        Integer returnId = -100;


        session = HibernateServiceLocator.getInstance().getSession();

        Query query = session.createSQLQuery("SELECT id FROM " + tableName + " WHERE " + columnName + " = '" + name + "';");
        List result = query.list();
        if (result.size() > 0) {
            returnId = new Integer(result.get(0).toString());//may need to catch null error
        }
        try {
            // Closing hibernate session
            session.close();
            session = null;
        } catch (HibernateException he) {
            throw new ServiceLocatorException(he);
        } finally {
            if (session != null) {
                try {
                    session.close();
                    session = null;
                } catch (HibernateException he) {
                    he.printStackTrace();
                }
            }
            return returnId;
        }
    }

    public List<Object> getColumn(String tableName, String columnName) throws ServiceLocatorException {
        Session session = null;
        List<Object> column = null;


        session = HibernateServiceLocator.getInstance().getSession();

        Query query = session.createSQLQuery("SELECT " + columnName + " FROM " + tableName + ";");
        column = query.list();

        try {
            // Closing hibernate session
            session.close();
            session = null;
        } catch (HibernateException he) {
            throw new ServiceLocatorException(he);
        } finally {
            if (session != null) {
                try {
                    session.close();
                    session = null;
                } catch (HibernateException he) {
                    he.printStackTrace();
                }
            }
            return column;
        }
    }

    public List<String> getIndustryTypeNames() {
        List<String> names = new ArrayList<String>();
        try {
            List cols = getColumn("lk_acc_industry_type", "acc_industry_name");
            for (int i = 0; i < cols.size(); i++) {
                names.add(cols.get(i).toString());
            }
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(LookUpHandlerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return names;
    }

    public List<String> getAccountTypeNames() {
        List<String> names = new ArrayList<String>();
        try {
            List cols = getColumn("lk_acc_type", "acc_type_name");
            for (int i = 0; i < cols.size(); i++) {
                names.add(cols.get(i).toString());
            }
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(LookUpHandlerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return names;
    }

    public List<String> getVendorTypes() {
        List<String> names = new ArrayList<String>();
        try {
            List cols = getColumn("lk_vendor_type", "vendor_type");
            for (int i = 0; i < cols.size(); i++) {
                names.add(cols.get(i).toString());
            }
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(LookUpHandlerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return names;
    }

    private Map<Integer, String> getMap(String cols, String table) throws ServiceLocatorException {
        Map<Integer, String> map = new HashMap<Integer, String>();
        Session session = null;

        session = HibernateServiceLocator.getInstance().getSession();

        Query query = session.createSQLQuery("SELECT " + cols + " FROM " + table + " where status like :accountstatus");
        List<Object[]> results = query.setString("accountstatus", "Active").list();

        for (Object[] result : results) {
            map.put((Integer) result[0], (String) result[1]);
        }

        if (session != null) {
            try {
                session.close();
                session = null;
            } catch (HibernateException he) {
                he.printStackTrace();
            }
        }


        return map;
    }

    public Map getIndustryTypesMap() {

        Map countries = new LinkedHashMap();
        Object OthersKey = "";
        Session session = null;

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            String hqlQuery = "select id, acc_industry_name from lk_acc_industry_type where status='Active' order by acc_industry_name ASC";

            Query query = session.createSQLQuery(hqlQuery);
            List list = query.list();
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();
                if (o[1].equals("Others")) {
                    OthersKey = o[0];
                    System.out.println("n1---->" + OthersKey);
                    continue;
                }
                countries.put(o[0], o[1]);

            }
            countries.put(OthersKey, "Others");
        } catch (ServiceLocatorException e) {
            System.out.println("Exception --" + e);
            // log.log (Level.ERROR, "Trying to get Countires names as strings with hibernate: " + e);
        } finally {
            closeSession(session);
        }
        System.out.println("List of Industries" + countries);
        return countries;

    }

    public Map<Integer, String> getAccountTypesMap() {
        Map<Integer, String> accountTypes = new HashMap<Integer, String>();
        try {
            accountTypes = getMap("id, acc_type_name", "lk_acc_type");
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(LookUpHandlerServiceImpl.class.getName()).log(Level.SEVERE, "Failed Atempt to get Account Types Map", ex);
        }

        return accountTypes;
    }

    public Map<Integer, String> getVendorTypesMap() {
        Map<Integer, String> vendorTypes = new HashMap<Integer, String>();
        try {
            vendorTypes = getMap("id, vendor_type", "lk_vendor_type");
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(LookUpHandlerServiceImpl.class.getName()).log(Level.SEVERE, "Failed Atempt to get Vendor Types Map", ex);
        }

        return vendorTypes;
    }

    private void closeSession(Session session) {
        // Closing hibernate session
        if (session != null) {
            session.close();

            if (session.isOpen()) {
                try {
                    session.flush();
                    session.close();
                    session = null;
                } catch (HibernateException he) {
                    he.printStackTrace();
                }
            }
        }
    }
}
