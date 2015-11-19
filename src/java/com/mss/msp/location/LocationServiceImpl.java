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

    public List<State> getStatesByCountry(int countryId) {
        System.out.println("looking for country id : " + countryId);
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


        return states;
    }

    public List<State> getStatesByCountry(String name) {
        List<State> states = new ArrayList<State>();
        Session session = null;

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            Query query = session.createQuery(
                    "SELECT states FROM Country as c where c.name = :name");
            query.setString("name", name);
            states = query.list();
        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get states By country name with hibernate: " + e);
        } finally {
            closeSession(session);
        }


        return states;

    }

    public List<String> getStatesNameByCountry(int countryId) {
        List<String> states = new ArrayList<String>();
        Session session = null;

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            Query query = session.createQuery("SELECT name FROM State WHERE countryId = :countryId");
            query.setInteger("countryId", countryId);

            states = query.list();

        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get states names  By country Id with hibernate: " + e);
        } finally {
            closeSession(session);
        }


        return states;
    }

    public List<String> getStatesNameByCountry(String name) {
        List<String> states = null;
        Session session = null;
        try {
            session = HibernateServiceLocator.getInstance().getSession();
            List<State> s = session.createQuery("select states From Country as c where c.name= :name").setString("name", name).list();
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

        return states;
    }

    public State lookupStateById(int id) {
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
        return returnState;
    }

    public List<Country> getCountries() {
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


        return countires;

    }

    public List<String> getCountriesNames() {
        List<String> countries = null;
        Session session = null;

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            countries = session.createQuery("select name from Country").list();

        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get Countires names as strings with hibernate: " + e);
        } finally {
            closeSession(session);
        }

        return countries;
    }

    public Map getCountryNames() {
        Map countries = new LinkedHashMap();
        Session session = null;

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            //countries = session.createQuery("select id,name from CountryVto");

           String hqlQuery = "select cv.id,cv.name from Country cv ORDER BY cv.name ASC";

            Query query = session.createQuery(hqlQuery);
            List list = query.list();
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();
                countries.put(o[0], o[1]);
//System.out.println(o[0]);
//System.out.println(o[1]);
//System.out.println("success");
            }

        } catch (ServiceLocatorException e) {
            System.out.println("Exception --" + e);
            // log.log (Level.ERROR, "Trying to get Countires names as strings with hibernate: " + e);
        } finally {
            closeSession(session);
        }
        System.out.println("List of countries" + countries);
        return countries;
    }

    public Country lookupCountryById(int id) {
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


        return country;
    }

    public Integer lookupCountryIdByName(String countryName) {
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

        return country.getId();
    }

    public Integer lookupStateIdByName(String stateName) {

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

        return state.getId();
    }

    public String lookupStateAbvByName(String stateName) {
        System.out.println("stateName in Serice: " + stateName);
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
        return abv;
    }

    public String getStatesStringOfCountry(HttpServletRequest httpServletRequest, int id) {
        Map states = new LinkedHashMap();
        String resultString = "";
        Session session = null;
        System.out.println("in location service impl .............1");

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            //countries = session.createQuery("select id,name from CountryVto");
            System.out.println("in location service impl .............2");
            String hqlQuery = "select id,name from State s WHERE countryId=:countryid ORDER BY s.name ASC";

            Query query = session.createQuery(hqlQuery);
            System.out.println("in location service impl .............3");

            query.setInteger("countryid", id);
            List list = query.list();
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();

                resultString += o[0] + "#" + o[1] + "^";
                //states.put(o[0],o[1]);

            }
            System.out.println("in location service impl .............4");

        } catch (ServiceLocatorException e) {
            System.out.println(e);
        } finally {
            closeSession(session);
        }
        //System.out.println("List of States are"+states);
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

        try {
            session = HibernateServiceLocator.getInstance().getSession();
            //countries = session.createQuery("select id,name from CountryVto");

            String hqlQuery = "select id,name from State s WHERE countryId=:countryid ORDER BY s.name ASC";

            Query query = session.createQuery(hqlQuery);
            query.setInteger("countryid", id);
            List list = query.list();
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();

                //resultString += o[0] + "#" + o[1] + "^";
                states.put(o[0], o[1]);

            }

        } catch (ServiceLocatorException e) {
            System.out.println(e);
        } finally {
            closeSession(session);

        }
        //System.out.println("List of States are"+states);
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
        Map countries = new LinkedHashMap();
        Session session = null;
        try {
            session = HibernateServiceLocator.getInstance().getSession();
            System.out.println("im countries names map.........1");
            //countries = session.createQuery("select id,name from CountryVto");
            String hqlQuery = "select cv.id,cv.name from Country cv";
            System.out.println("im countries names map.........2");

            Query query = session.createQuery(hqlQuery);
            System.out.println("im countries names map.........3");

            List list = query.list();
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Object[] o = (Object[]) iterator.next();
                countries.put(o[0], o[1]);
            }
            System.out.println("im countries names map.........4");

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } finally {
            closeSession(session);
        }

        return countries;
    }

    public String lookupCountryCurrency(int countryId) {
        String cur = "";
        Session session = null;
        try {
            session = HibernateServiceLocator.getInstance().getSession();
            cur = (String) session.createQuery("select currency from Country where id = :id")
                    .setInteger("id", countryId).uniqueResult();
        } catch (ServiceLocatorException e) {
            log.log(Level.ERROR, "Trying to get Country Currency by Name as string with hibernate: " + e);
        } finally {
            session.close();
        }

        return cur;
    }

    public Map<Integer, String> getStatesMapByCountryName(String countryName) {
        return null;
    }

    public Map<Integer, String> getStatesMapByCountryId(int countryId) {
        Map<Integer, String> states = new LinkedHashMap<Integer, String>();
        Session session = null;
        try {
            session = HibernateServiceLocator.getInstance().getSession();
            Query query = session.createQuery(
                    "SELECT id, name FROM State as s join Country as c where c.name = :name and s.countryId = c.id");
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

        return states;
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
