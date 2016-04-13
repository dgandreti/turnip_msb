/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.detailsAjax;

import com.mss.msp.acc.details.AccountDetails;
import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.HibernateServiceLocator;
import com.mss.msp.util.ServiceLocator;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Greg
 */
public class AccountDetailsAjaxHandlerServiceImpl implements AccountDetailsAjaxHandlerService {

    Connection connection = null;
    CallableStatement callableStatement = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String queryString = "";

    public AccountDetails ajaxAccountUpdate(AccountDetails account, int relOrgId, int userSessionId) {
        System.out.println("********************AccountDetailsAjaxHandlerServiceImpl :: ajaxAccountUpdate Method Start*********************");
        Connection connection = null;
        CallableStatement callableStatement = null;
        Session session = null;
        Transaction tx = null;
        Integer country = Integer.parseInt(account.getCountry());
        Integer industry = Integer.parseInt(account.getIndustry());
        if ("".equals(account.getState())) {
            account.setState(null);
        } else {
            Integer state = Integer.parseInt(account.getState());
            if (state < 0) {
                account.setState(null);
            }
        }
        if (country < 0) {
            account.setCountry(null);
        }
        if (industry < 0) {
            account.setIndustry(null);
        }
        boolean isExceute = false;
        String resultString = "";
        int updatedRows = 0;

        try {
            connection = ConnectionProvider.getInstance().getConnection();
            callableStatement = connection.prepareCall("{CALL updateAccount(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            System.out.println("ajaxAccountUpdate :: procedure name : updateAccount ");
            callableStatement.setInt(1, account.getId());
            callableStatement.setString(2, account.getAddress1());
            callableStatement.setString(3, account.getAddress2());
            callableStatement.setString(4, account.getState());
            callableStatement.setString(5, account.getCountry());
            callableStatement.setString(6, account.getCity());
            callableStatement.setString(7, account.getZip());
            callableStatement.setInt(8, userSessionId);
            callableStatement.setString(9, account.getStatus());
            callableStatement.setString(10, account.getPhone());
            callableStatement.setString(11, account.getFax());
            callableStatement.setString(12, account.getIndustry());
            callableStatement.setString(13, account.getRegion());
            callableStatement.setString(14, account.getTaxId());
            callableStatement.setString(15, account.getTerritory());
            callableStatement.setString(16, account.getStockSymbol());
            callableStatement.setString(17, account.getNoemp());
            callableStatement.setString(18, account.getDescription());
            String rev = account.getRevenue();
            if (rev != null && !"".equals(rev)) {
                callableStatement.setString(19, rev);
            } else {
                callableStatement.setString(19, null);
            }
            callableStatement.setString(20, account.getEmailExt());
            callableStatement.setInt(21, account.getAccountType());
            callableStatement.setString(22, account.getBankName());
            callableStatement.setString(23, account.getBankAddress());
            callableStatement.setString(24, account.getBankCity());
            callableStatement.setString(25, account.getBankZip());
            callableStatement.setString(26, com.mss.msp.util.DataUtility.encrypted(account.getBankRoutingNumber()));
            callableStatement.setString(27, com.mss.msp.util.DataUtility.encrypted(account.getBankAccountNumber()));
            callableStatement.setString(28, account.getBeneficiaryName());
            callableStatement.setString(29, account.getSkillValues());
            callableStatement.registerOutParameter(30, Types.INTEGER);
            isExceute = callableStatement.execute();
            updatedRows = callableStatement.getInt(30);
            if (updatedRows > 0) {
                resultString = "Updated";
            }
            System.out.println("ajaxAccountUpdate::is updatedRows " + updatedRows);
        } catch (Exception sqe) {
            sqe.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                    callableStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            System.out.println("********************AccountDetailsAjaxHandlerServiceImpl :: ajaxAccountUpdate Method End*********************");
            return account;
        }
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : checkForAccountName() method is used to
     *
     * *****************************************************************************
     */
    public boolean checkForAccountName(String name, int id) throws ServiceLocatorException {
        boolean exists = false;
        Session session = HibernateServiceLocator.getInstance().getSession();
        String q = "select distinct a.id, a.name from Account a where a.name=:accName";
        List<Object[]> likeAccounts = session.createQuery(q).setParameter("accName", name).list();
        if (likeAccounts.size() > 0) {
            for (Object[] a : likeAccounts) {
                //System.out.println("INSIDE AJAX CALL" +a);
                if (Integer.parseInt(a[0].toString()) == id) {
                    exists = false;
                } else {
                    exists = true;
                }
            }
        } else {
            exists = false;
        }
        System.out.println("********************AccountDetailsAjaxHandlerServiceImpl :: checkForAccountName Method start*********************");
        try {
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
        System.out.println("********************AccountDetailsAjaxHandlerServiceImpl :: checkForAccountName Method End*********************");
        return exists;
    }

    /**
     * *****************************************************************************
     * Date :
     *
     * Author :
     *
     * ForUse : checkForAccountURL() method is used to
     *
     * *****************************************************************************
     */
    public boolean checkForAccountURL(String url, int id) throws ServiceLocatorException {
        boolean exists = false;
        Session session = HibernateServiceLocator.getInstance().getSession();
        String q = "select distinct a.id, a.url from Account a where a.url=:accurl";
        List<Object[]> likeAccounts = session.createQuery(q).setParameter("accurl", url).list();
        if (likeAccounts.size() > 0) {
            exists = true;
            System.out.println("url Exists");
        }
        System.out.println("********************AccountDetailsAjaxHandlerServiceImpl :: checkForAccountURL Method Start*********************");
        try {
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
        System.out.println("********************AccountDetailsAjaxHandlerServiceImpl :: checkForAccountURL Method End*********************");
        return exists;
    }
}
