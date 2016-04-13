/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.location;

import com.mss.msp.util.HibernateServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Kyle Bissell
 */
public class LocationServiceImpl implements LocationService {

    private static Logger log = Logger.getLogger(LocationServiceImpl.class);

    /**
     ***********************************************************
     *
     * @getStatesByCountry() to get the  states of country 
     * 
     *
     ***********************************************************
     */
    public List<State> getStatesByCountry(int countryId) {
        System.out.println("********************LocationServiceImpl :: getStatesByCountry Method Start*********************");
       
        List<State> states = new ArrayList<State>();
        Session session = null;

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            Criteria criteria = session.createCriteria(com.mss.msp.location.State.class);
            criteria.add(Restrictions.eq("countryId", countryId));
            criteria.addOrder(Order.asc("name"));
            states = criteria.list();

        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get states By country Id with hibernate: " + e);
        } finally {
            closeSession(session);
        }

         System.out.println("********************LocationServiceImpl :: getStatesByCountry Method End*********************");
        return states;
    }
     /**
     ***********************************************************************
     *
     * @getStatesByCountry() to get the  states of country by country name
     * 
     *
     ***********************************************************************
     */
    public List<State> getStatesByCountry(String name) {
        System.out.println("********************LocationServiceImpl :: getStatesByCountry Method Start*********************");
        List<State> states = new ArrayList<State>();
        Session session = null;

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            Query query = session.createQuery(
                    "SELECT states FROM Country as c where c.name = :name");
            System.out.println("getStatesByCountry() hibernate query-->" + query);
            query.setString("name", name);
            states = query.list();
        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get states By country name with hibernate: " + e);
        } finally {
            closeSession(session);
        }

        System.out.println("********************LocationServiceImpl :: getStatesByCountry Method End*********************");
        return states;

    }
     /**
     ***********************************************************************
     *
     * @getStatesNameByCountry() to get the  states of country by country id
     * 
     *
     ***********************************************************************
     */
    public List<String> getStatesNameByCountry(int countryId) {
          System.out.println("********************LocationServiceImpl :: getStatesNameByCountry Method Start*********************");
        List<String> states = new ArrayList<String>();
        Session session = null;

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            Query query = session.createQuery("SELECT name FROM State WHERE countryId = :countryId");
            query.setInteger("countryId", countryId);
            System.out.println("getStatesNameByCountry() hibernate query-->" + query);
            states = query.list();

        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get states names  By country Id with hibernate: " + e);
        } finally {
            closeSession(session);
        }

         System.out.println("********************LocationServiceImpl :: getStatesNameByCountry Method End*********************");
        return states;
    }
     /**
     ***********************************************************************
     *
     * @getStatesNameByCountry() to get the  states of country by country id
     * 
     *
     ***********************************************************************
     */
    public List<String> getStatesNameByCountry(String name) {
         System.out.println("********************LocationServiceImpl :: getStatesNameByCountry Method Start*********************");
        List<String> states = null;
        Session session = null;
        try {
            session = HibernateServiceLocator.getInstance().getSession();
            List<State> s = session.createQuery("select states From Country as c where c.name= :name").setString("name", name).list();
            
             System.out.println("getStatesNameByCountry() hibernate quesry--> select states From Country as c where c.name= :name");
            if (s != null) {
                if (states == null) {
                    states = new ArrayList<String>();
                }
                for (int i = 0; i < s.size(); i++) {
                    states.add(s.get(i).getName());
                }
            }


        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get states names as strings with hibernate: " + e);
        } finally {
            closeSession(session);
        }
        System.out.println("********************LocationServiceImpl :: getStatesNameByCountry Method End*********************");
        return states;
    }

     /**
     ***********************************************************************
     *
     * @lookupStateById() to get the  state  by state id
     * 
     *
     ***********************************************************************
     */
    public State lookupStateById(int id) {
        System.out.println("********************LocationServiceImpl :: lookupStateById Method Start*********************");
        Session session = null;
        State returnState = null;
        try {
            session = HibernateServiceLocator.getInstance().getSession();
            State s = (State) session.createQuery("from State where id=:sid").setParameter("sid", id).list().get(0);
            if (s != null) {
                returnState = s;
            }


        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get states names as strings with hibernate: " + e);
        } finally {
            closeSession(session);
        }
        System.out.println("********************LocationServiceImpl :: lookupStateById Method End*********************");
        return returnState;
    }

     /**
     ***************************************************
     *
     * @lookupStateById() to get the  countries  
     * 
     *
     ***************************************************
     */
    public List<Country> getCountries() {
        System.out.println("********************LocationServiceImpl :: getCountries Method Start*********************");
        List<Country> countires = new ArrayList<Country>();
        Session session = null;

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            Criteria criteria = session.createCriteria(com.mss.msp.location.Country.class);
            criteria.addOrder(Order.asc("name"));
            countires = criteria.list();

        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get countries with hibernate: " + e);
        } finally {
            closeSession(session);
        }

         System.out.println("********************LocationServiceImpl :: getCountries Method End*********************");
        return countires;

    }
    /**
     ***************************************************
     *
     * @getCountriesNames() to get the  countries  
     * 
     *
     ***************************************************
     */
    public List<String> getCountriesNames() {
        System.out.println("********************LocationServiceImpl :: getCountriesNames Method Start*********************");
        List<String> countries = null;
        Session session = null;

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            countries = session.createQuery("select name from Country").list();
            System.out.println("getCountriesNames() hibernate query--> select name from Country");
        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get Countires names as strings with hibernate: " + e);
        } finally {
            closeSession(session);
        }
          System.out.println("********************LocationServiceImpl :: getCountriesNames Method End*********************");
        return countries;
    }

     /**
     ***************************************************
     *
     * @getCountryNames() to get the  countries 
     * 
     *
     ***************************************************
     */
    public Map getCountryNames() {
        Map countries = new LinkedHashMap();
        Session session = null;
         System.out.println("********************LocationServiceImpl :: getCountryNames Method Start*********************");
        try {
            session = HibernateServiceLocator.getInstance().getSession();
         
           String hqlQuery = "select cv.id,cv.name from Country cv ORDER BY cv.name ASC";
           System.out.println("getCountryNames() hibernate query--> "+hqlQuery);
            Query query = session.createQuery(hqlQuery);
            List list = query.list();
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();
                countries.put(o[0], o[1]);

            }

        } catch (ServiceLocatorException e) {
         
            
        } finally {
            closeSession(session);
        }
         System.out.println("********************LocationServiceImpl :: getCountryNames Method Start*********************");
       
        return countries;
    }
 /**
     ***************************************************
     *
     * @lookupCountryById() to get the  country by id 
     * 
     *
     ***************************************************
     */
    public Country lookupCountryById(int id) {
         System.out.println("********************LocationServiceImpl :: lookupCountryById Method Start*********************");
        Country country = new Country();
        Session session = null;

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            Criteria criteria = session.createCriteria(com.mss.msp.location.Country.class);
            criteria.add(Restrictions.eq("id", id));
            country = (Country) criteria.uniqueResult();

        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get country by id with hibernate: " + e);
        } finally {
            closeSession(session);
        }

        System.out.println("********************LocationServiceImpl :: lookupCountryById Method End*********************");
        return country;
    }
/**
     **********************************************************
     *
     * @lookupCountryIdByName() to get the  country id by name 
     * 
     *
     **********************************************************
     */
    public Integer lookupCountryIdByName(String countryName) {
        System.out.println("********************LocationServiceImpl :: lookupCountryIdByName Method Start*********************");
        Country country = null;
        Session session = null;
        try {
            session = HibernateServiceLocator.getInstance().getSession();
            country = (Country) session.createCriteria(com.mss.msp.location.Country.class).
                    add(Restrictions.ilike("name", countryName, MatchMode.ANYWHERE)).uniqueResult();
        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get Country Id by Name as strings with hibernate: " + e);
        } finally {
            closeSession(session);
        }
         System.out.println("********************LocationServiceImpl :: lookupCountryIdByName Method End*********************");
        return country.getId();
    }
/**
     **********************************************************
     *
     * @lookupStateIdByName() to get the  state id by name 
     * 
     *
     **********************************************************
     */
    public Integer lookupStateIdByName(String stateName) {

         System.out.println("********************LocationServiceImpl :: lookupStateIdByName Method Start*********************");
        Session session = null;
        State state = null;
        try {
            session = HibernateServiceLocator.getInstance().getSession();
            state = (State) session.createCriteria(com.mss.msp.location.State.class)
                    .add(Restrictions.ilike("name", stateName, MatchMode.ANYWHERE)).uniqueResult();
        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get State Id by Name as strings with hibernate: " + e);
        } finally {
            closeSession(session);
        }
        System.out.println("********************LocationServiceImpl :: lookupStateIdByName Method End*********************");
        return state.getId();
    }

    /**
     ************************************************
     *
     * @lookupStateAbvByName() to get the state 
     * 
     *
     ************************************************
     */
    public String lookupStateAbvByName(String stateName) {
         System.out.println("********************LocationServiceImpl :: lookupStateAbvByName Method Start*********************");
        Session session = null;
        State state = new State();;
        try {
            session = HibernateServiceLocator.getInstance().getSession();
            state = (State) session.createCriteria(com.mss.msp.location.State.class)
                    .add(Restrictions.ilike("name", stateName, MatchMode.ANYWHERE)).uniqueResult();

        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get State Id by Name as strings with hibernate: " + e);
        } finally {
            closeSession(session);
        }
        String abv = "";
        if (state != null) {
            abv = state.getAbbreviation();
        }
        System.out.println("********************LocationServiceImpl :: lookupStateAbvByName Method End*********************");
        return abv;
    }

    /**
     *********************************************************
     *
     * @getStatesStringOfCountry() to get the state of country 
     * 
     *
     *********************************************************
     */
    public String getStatesStringOfCountry(HttpServletRequest httpServletRequest, int id) {
          System.out.println("********************LocationServiceImpl :: getStatesStringOfCountry Method Start*********************");
        Map states = new LinkedHashMap();
        String resultString = "";
        Session session = null;
       

        try {
            session = HibernateServiceLocator.getInstance().getSession();
           
            String hqlQuery = "select id,name from State s WHERE countryId=:countryid ORDER BY s.name ASC";
              System.out.println("getStatesStringOfCountry() hibernate query--> "+hqlQuery);
            Query query = session.createQuery(hqlQuery);
          

            query.setInteger("countryid", id);
            List list = query.list();
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();

                resultString += o[0] + "#" + o[1] + "^";
              

            }
           

        } catch (ServiceLocatorException e) {
          
        } finally {
            closeSession(session);
        }
        
        System.out.println("********************LocationServiceImpl :: getStatesStringOfCountry Method Start*********************");
        return resultString;
    }

    /**
     * *****************************************************************************
     * Date : April 29 2015
     *
     * Author : jagan chukkala<jchukkala@miraclesoft.com>
     *
     * getStatesOfCountry() method can be used to get the states by passing
     * country id And returns resultString
     * *****************************************************************************
     */
    public Map getStatesMapOfCountry(HttpServletRequest httpServletRequest, int id) {
        Map states = new LinkedHashMap();
        String resultString = "";
        Session session = null;
         System.out.println("********************LocationServiceImpl :: getStatesMapOfCountry Method Start*********************");
        try {
            session = HibernateServiceLocator.getInstance().getSession();
          
            String hqlQuery = "select id,name from State s WHERE countryId=:countryid ORDER BY s.name ASC";
             System.out.println("getStatesMapOfCountry() hibernate query--> "+hqlQuery);
            Query query = session.createQuery(hqlQuery);
            query.setInteger("countryid", id);
            List list = query.list();
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();

                states.put(o[0], o[1]);

            }

        } catch (ServiceLocatorException e) {
            System.out.println(e);
        } finally {
            closeSession(session);

        }
     
        System.out.println("********************LocationServiceImpl :: getStatesMapOfCountry Method End*********************");
        return states;
    }

    /**
     * *****************************************************************************
     * Date : April 28 2015
     *
     * Author : jagan chukkala<jchukkala@miraclesoft.com>
     *
     * getCountriesNames() method can be used to get the countries And returns
     * countries Map
     * *****************************************************************************
     */
    public Map getCountriesNamesMap() {
         System.out.println("********************LocationServiceImpl :: getCountriesNamesMap Method Start*********************");
        Map countries = new LinkedHashMap();
        Session session = null;
        try {
            session = HibernateServiceLocator.getInstance().getSession();
          
            String hqlQuery = "select cv.id,cv.name from Country cv";
             System.out.println("getCountriesNamesMap() hibernate quesry--> "+hqlQuery);
          

            Query query = session.createQuery(hqlQuery);
         

            List list = query.list();
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();
                countries.put(o[0], o[1]);
            }
        

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
         System.out.println("********************LocationServiceImpl :: getCountriesNamesMap Method End*********************");
        return countries;
    }

     /**
     *********************************************************************
     *
     * @lookupCountryCurrency() to get the country currency by country id
     * 
     *
     *********************************************************************
     */
    public String lookupCountryCurrency(int countryId) {
        System.out.println("********************LocationServiceImpl :: lookupCountryCurrency Method Start*********************");
        String cur = "";
        Session session = null;
        try {
            session = HibernateServiceLocator.getInstance().getSession();
            cur = (String) session.createQuery("select currency from Country where id = :id")
                    .setInteger("id", countryId).uniqueResult();
              System.out.println("lookupCountryCurrency() hibernate query-->select currency from Country where id = :id ");
        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get Country Currency by Name as string with hibernate: " + e);
        } finally {
            session.close();
        }
         System.out.println("********************LocationServiceImpl :: lookupCountryCurrency Method End*********************");
        return cur;
    }

    public Map<Integer, String> getStatesMapByCountryName(String countryName) {
        return null;
    }
     /**
     ***************************************************************
     *
     * @getStatesMapByCountryId() to get the states by country id
     * 
     *
     ***************************************************************
     */
    public Map<Integer, String> getStatesMapByCountryId(int countryId) {
        System.out.println("********************LocationServiceImpl :: getStatesMapByCountryId Method Start*********************");
        Map<Integer, String> states = new LinkedHashMap<Integer, String>();
        Session session = null;
        try {
            session = HibernateServiceLocator.getInstance().getSession();
            Query query = session.createQuery(
                    "SELECT id, name FROM State as s join Country as c where c.name = :name and s.countryId = c.id");
              System.out.println("getStatesMapByCountryId() hibernate query-->"+query);
            query.setInteger("id", countryId);
            List<Object[]> results = query.list();
            for (Object[] result : results) {
                states.put((Integer) result[0], (String) result[1]);
            }

        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get states By country name with hibernate: " + e);
        } finally {
            closeSession(session);
        }
        System.out.println("********************LocationServiceImpl :: getStatesMapByCountryId Method End*********************");
        return states;
    }

    private void closeSession(Session session) {
       
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
