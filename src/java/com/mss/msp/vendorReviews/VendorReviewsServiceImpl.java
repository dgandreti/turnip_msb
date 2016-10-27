/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.vendorReviews;

import com.mss.msp.util.ConnectionProvider;
import com.mss.msp.util.ServiceLocatorException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author miracle
 */
public class VendorReviewsServiceImpl implements VendorReviewsService {

    String queryString = "";

    public List vendorRatingAndReveiw(VendorReviewsAction vendorReviewsAction) throws ServiceLocatorException {
        ArrayList vendorReviewsList = new ArrayList();
        Connection connection = null;
        String queryString = "";
        Statement statement = null;
        ResultSet resultSet = null;
        String resultString = "";
        StringTokenizer str;
        try {
            queryString = "SELECT vr.id,vr.review_name,vr.review_rating,vr.review_comments,vr.created_by_orgId,ac.acc_logo"
                    + " FROM ven_reviews vr LEFT OUTER JOIN accounts ac ON(created_by_orgId=account_id)"
                    + "  WHERE vr.vendor_orgId = " + vendorReviewsAction.getVendorOrgId()
                    + " ORDER BY vr.created_date DESC LIMIT 100";
//            created_by_orgId = "+vendorReviewsAction.getSessionOrgId()
            System.out.println("-----------querystinr---------------" + queryString);
            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            while (resultSet.next()) {
                VendorReviewsVTO vendorReviewsVTO = new VendorReviewsVTO();
                vendorReviewsVTO.setReviewId(resultSet.getInt("id"));
                vendorReviewsVTO.setReviewName(resultSet.getString("review_name"));
                vendorReviewsVTO.setReviewComments(resultSet.getString("review_comments"));
                vendorReviewsVTO.setReviewRating(resultSet.getInt("review_rating"));
                vendorReviewsVTO.setCustomerOrgId(resultSet.getInt("created_by_orgId"));
                vendorReviewsVTO.setLogo(resultSet.getString("acc_logo"));
                String result = com.mss.msp.util.DataSourceDataProvider.getInstance().getStarCalculations(vendorReviewsAction.getVendorOrgId());
                str = new StringTokenizer(result, "^");
                while (str.hasMoreElements()) {
                    vendorReviewsVTO.setCount5(Integer.parseInt(str.nextElement().toString()));
                    vendorReviewsVTO.setCount4(Integer.parseInt(str.nextElement().toString()));
                    vendorReviewsVTO.setCount3(Integer.parseInt(str.nextElement().toString()));
                    vendorReviewsVTO.setCount2(Integer.parseInt(str.nextElement().toString()));
                    vendorReviewsVTO.setCount1(Integer.parseInt(str.nextElement().toString()));
                    vendorReviewsVTO.setTotal(Integer.parseInt(str.nextElement().toString()));
                    vendorReviewsVTO.setAvg(Integer.parseInt(str.nextElement().toString()));
                }


                vendorReviewsList.add(vendorReviewsVTO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return vendorReviewsList;
    }

    public int addVendorRatingAndReveiw(VendorReviewsAction vendorReviewsAction) throws ServiceLocatorException {
        Connection connection = null;
        String queryString = "";
        PreparedStatement preparedStatement = null;
        int resultString = 0;
        try {
            System.out.println("------------------" + com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
            queryString = "INSERT INTO ven_reviews(review_name,review_comments,review_rating,vendor_orgId,created_by_orgId,"
                    + "created_by,created_date) VALUES(?,?,?,?,?,?,?);";
            connection = ConnectionProvider.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1,vendorReviewsAction.getReviewName());
            preparedStatement.setString(2, vendorReviewsAction.getReviewComments());
            preparedStatement.setInt(3,vendorReviewsAction.getReviewRating());
            preparedStatement.setInt(4, vendorReviewsAction.getVendorOrgId());
            preparedStatement.setInt(5, vendorReviewsAction.getSessionOrgId());
            preparedStatement.setInt(6, vendorReviewsAction.getSessionUserId());
            preparedStatement.setTimestamp(7, com.mss.msp.util.DateUtility.getInstance().getCurrentMySqlDateTime());
            resultString = preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return resultString;
    }
}
