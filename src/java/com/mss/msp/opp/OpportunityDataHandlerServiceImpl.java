/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.opp;

import com.mss.msp.util.ApplicationConstants;
import com.mss.msp.util.HibernateServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Anton Franklin
 */
public class OpportunityDataHandlerServiceImpl implements OpportunityDataHandlerService {

    public List getOpportunitiesByAccID(Integer accountID) throws ServiceLocatorException {
        List<OpportunityVTO> opportunitiesList = new ArrayList<OpportunityVTO>();

        Session session = HibernateServiceLocator.getInstance().getSession();

        Criteria query = session.createCriteria(com.mss.msp.opp.OpportunityVTO.class);

        /*if (childAccountsList.size() > 0) {
         for (int i = 0; i < childAccountsList.size(); i++) {
         Criterion restriction1 = Restrictions.eq("accountID", accID);
         Criterion restriction2 = Restrictions.eq("accountID", Integer.parseInt(childAccountsList.get(i).getRelatedOrgID().toString()));
         query.add(Restrictions.or(restriction1, restriction2));
         System.out.println("The query created is " + query.toString());
         }
         } else {
         query.add(Restrictions.eq("accountID", accountID));
         }*/
        query.add(Restrictions.eq("accountID", accountID));
        opportunitiesList = query.list();

        try {
            // Closing hibernate session
            session.flush();
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
        }

        return opportunitiesList;
    }

    public void addOpportunity(OpportunityVTO opportunity) throws ServiceLocatorException {
        Session session = HibernateServiceLocator.getInstance().getSession();
        //Creating a transaction for the session object.
        Transaction tran = session.beginTransaction();

        session.save(opportunity);
        tran.commit();
        if (tran.wasCommitted()) {
            System.out.println("Opportunity Successfully");
        }
        session.close();
    }

    public OpportunityVTO getOpportunityByOpportunityID(OpportunityDataHandlerAction opportunityDataHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        OpportunityVTO opportunity = new OpportunityVTO();

        Session session = HibernateServiceLocator.getInstance().getSession();

        Criteria query = session.createCriteria(com.mss.msp.opp.OpportunityVTO.class);

        query.add(Restrictions.like("accountID", httpServletRequest.getSession(false).getAttribute(ApplicationConstants.ORG_ID)));

        query.add(Restrictions.like("opportunityID", httpServletRequest.getSession(false).getAttribute("opportunityID")));

        opportunity = (OpportunityVTO) query.uniqueResult();


        try {
            // Closing hibernate session
            session.flush();
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
        }

        return opportunity;
    }

    public void updateOpportunity(OpportunityVTO opportunity, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        //Setting the opportunityID in the session to be passed onto getProjectDetails method
        //httpServletRequest.getSession(false).setAttribute("opportunityID", opportunity.getOpportunityID());

        Session session = HibernateServiceLocator.getInstance().getSession();

        //Creating a transaction for the session object.
        Transaction tran = session.beginTransaction();

        session.update(opportunity);
        tran.commit();

        if (tran.wasCommitted()) {
            System.out.println("Opportunity Updated Successfully");
        }
        session.close();
    }

    public List getOpportunitySearchDetails(OpportunityDataHandlerAction opportunityDataHandlerAction, HttpServletRequest httpServletRequest) throws ServiceLocatorException {
        List<OpportunityVTO> opportunityList = new ArrayList<OpportunityVTO>();
        String sql;

        Session session = HibernateServiceLocator.getInstance().getSession();

        Criteria query = session.createCriteria(com.mss.msp.opp.OpportunityVTO.class);

        query.add(Restrictions.eq("accountID", opportunityDataHandlerAction.getAccountID()));


        if (opportunityDataHandlerAction.getOpportunityID() != null && !opportunityDataHandlerAction.getOpportunityID().equals("")) {
            System.out.println("Opportunity ID is" + opportunityDataHandlerAction.getOpportunityID());
            query.add(Restrictions.eq("opportunityID", opportunityDataHandlerAction.getOpportunityID()));
        }
        if (!opportunityDataHandlerAction.getOpportunityName().equals("")) {
            System.out.println("Opportunity Name is " + opportunityDataHandlerAction.getOpportunityName());
            query.add(Restrictions.ilike("opportunityName", opportunityDataHandlerAction.getOpportunityName(), MatchMode.ANYWHERE));
        }

        opportunityList = query.list();

        try {
            // Closing hibernate session
            session.flush();
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
        }

        return opportunityList;
    }
}
