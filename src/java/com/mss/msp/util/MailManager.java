/*
 * SendMail.java
 *
 * Created on September 7, 2007, 1:12 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.mss.msp.util;

import com.mss.msp.recruitment.RecruitmentAction;
import com.mss.msp.requirement.RequirementVTO;
import com.mss.msp.usr.task.TaskHandlerAction;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Multipart;
import javax.mail.BodyPart;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
//import java.io.UnsupportedEncodingException;
import java.io.*;
//new
import java.util.Set;
import java.util.HashSet;
import com.mss.msp.util.DataSourceDataProvider;
import java.sql.PreparedStatement;
import java.util.*;
// New imports for authentication
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.servlet.http.HttpServletRequest;

public class MailManager {

    private String emailId;
    private Connection connection;
    private PreparedStatement prepareStatement;

    public MailManager() {
    }
    private static final String SMTP_AUTH_USER = com.mss.msp.util.Properties.getProperty("Mail.Auth").toString();
    private static final String SMTP_AUTH_PWD = com.mss.msp.util.Properties.getProperty("Mail.Auth.pwd").toString();
    private static final String SMTP_HOST = com.mss.msp.util.Properties.getProperty("Mail.Host").toString();
    private static final String SMTP_PORT = com.mss.msp.util.Properties.getProperty("Mail.Port").toString();

    public static void sendResetPwdLink(String forGotPwdURL, String emailId, String session7digit) {


        //System.out.println("In MailManager-->" + emailId + "-->session7digit-->" + session7digit);
        //String forGotPwdURL = com.mss.msp.util.Properties.getProperty("FORGOTPASSWORDPROCESS.URL").toString();
        /**
         * The from is used for storing the from address.
         */
        String from = "sb@miraclesoft.com";

        Properties props = new Properties();
        //emailId = "vkandregula@miraclesoft.com";
        /**
         * Here set smtp protocal to props
         */
        props.setProperty("mail.transport.protocol", "smtp");

        //**Here set the address of the host to props */
        props.setProperty("mail.host", SMTP_HOST);

        /**
         * Here set the authentication for the host *
         */
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);

        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getDefaultInstance(props, auth);
        //mailSession.setDebug(true);
        mailSession.setDebug(false);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Password Reset Link");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));


            MimeMultipart multipart = new MimeMultipart("related");

            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html>");
            htmlText.append("<body>");
            htmlText.append("<table align='center'>");
            htmlText.append("<tr style='background:#99FF33;height:40px;width:100%;'>");
            htmlText.append("<td>");
            htmlText.append("<font color='white' size='4' face='Arial'>");
            htmlText.append("<p>MSB Reset Password</p>");
            htmlText.append("</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<table style='background:#FFFFCC;width:100%;'>");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<font color='#3399FF' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<p>Hello,</p><br/>");
            htmlText.append("<p>We recieved a request to the password associated with this email address .</p>");
            htmlText.append("<p>If you made this request,please follow the instructions below.</p><br/>");
            htmlText.append(" <font color=\"red\">  <a href='" + forGotPwdURL + "?"
                    + "emailId=" + emailId + "&sessionId=" + session7digit + "'> "
                    + " Click here </a> </font>  to reset your password.");
            htmlText.append("<p>If you did not have your password reset, you can safely ignore this email.</p>");
            htmlText.append("<p>We assure you that your customer account is safe.</p>");
            htmlText.append("</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</body>");
            htmlText.append("</html>");
            htmlText.append("</body>");
            htmlText.append("</html>");

            messageBodyPart.setContent(htmlText.toString(), "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);
            // put everything together
            message.setContent(multipart);
            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Author : Prasad New Method for Authentication... Date : 04-March-2015
     */
    private static class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }

    /**
     * Date : APRIL 14, 2015, 3:35 PM IST
     *
     * Author : Praveen kumar <pkatru@miraclesoft.com >
     *
     *
     *
     * ForUse : Insert the record into email Logger
     *
     * ****************************************************************************
     */
    public int doaddemailLog(String fromAdd, String toAdd, String bcc, String cc, String Subject, String bodyContent, int createdBy) throws ServiceLocatorException, SQLException {
        int StatusEmail = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String sqlQuery = "insert into email_logger(from_adress,to_address,bcc_address,cc_address,subjectcontent,bodycontent,created_by,email_status) values(?,?,?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, fromAdd);
            prepareStatement.setString(2, toAdd);
            prepareStatement.setString(3, bcc);
            prepareStatement.setString(4, cc);
            prepareStatement.setString(5, Subject);
            prepareStatement.setString(6, bodyContent);
            prepareStatement.setInt(7, createdBy);
            prepareStatement.setString(8, "pending");
            /*System.out.println("1" + fromAdd);
             System.out.println("2" + toAdd);
             System.out.println("3" + bcc);
             System.out.println("4" + cc);
             System.out.println("5" + Subject);
             System.out.println("6" + bodyContent);
             */
            StatusEmail = prepareStatement.executeUpdate();
            //  System.out.println("here logger is inserted");
        } catch (Exception E) {
            E.printStackTrace();
        } finally {
            try {

                if (prepareStatement != null) {
                    prepareStatement.close();
                    prepareStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return StatusEmail;
    }
    /**
     * *************************************
     *
     * @generateTaskAddEmail() it generates email when a task is added 
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:04/29/2015
     *
     *
     **************************************
     *
     */
    public int generateTaskAddEmail(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException, SQLException {
        int addEmailResult = 0;
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            String priority = "";
            if (taskHandlerAction.getTaskPriority().equalsIgnoreCase("M")) {
                priority = "Medium";
            } else if (taskHandlerAction.getTaskPriority().equalsIgnoreCase("L")) {
                priority = "Low";
            } else if (taskHandlerAction.getTaskPriority().equalsIgnoreCase("H")) {
                priority = "High";
            } else {
                priority = "";
            }

            StringBuilder htmlText = new StringBuilder();

            htmlText.append("<html>");
            htmlText.append("<body>");
            htmlText.append("<table align='center'>");

            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<td>");
            htmlText.append("<font color='white' size='4' face='Arial'>");
            htmlText.append("<p> Task Management System in MSB.</p>");
            htmlText.append("</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");

            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<table style='background:#FFFFCC;width:100%;' color='#151B54' size='4px' height='20px' >");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<font color='#3399FF' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<br>  Hello Team,  ");
            htmlText.append("</font><br></td></tr>");

            htmlText.append("<tr><td colspan='2'>");
            htmlText.append("A task has been created by:<b><font color='#6666FF'>" + taskHandlerAction.getFullName());
            htmlText.append("</font></b></td></tr>");

            htmlText.append("<tr><td> <table border='0' align='left'><font> ");
            htmlText.append(" Task Details :</font></td>");
            htmlText.append("</tr>");

            htmlText.append("<br> <tr><td>");
            htmlText.append("TaskStatus :</td><td align='left' >");
            htmlText.append("<font color='red' >" + taskHandlerAction.getStatusName() + "</font></td></tr> ");

            htmlText.append(" <tr><td>  ");
            htmlText.append("Title :</td><td align='left' >");
            htmlText.append("<font color='red' >" + taskHandlerAction.getTaskName() + "</font></td></tr> ");

            htmlText.append("<tr><td>");
            htmlText.append("Priority  :</td><td align='left' >");
            htmlText.append("<font color='red' >" + priority + "</font></td></tr> ");

            htmlText.append("<tr><td valign='top' ><font > ");
            htmlText.append("Task ID   :</font></td><td align='left'> ");
            htmlText.append(" <font color='red' >" + taskHandlerAction.getLastTaskId() + "</font></td></tr> ");

            htmlText.append(" <tr><td> ");
            htmlText.append("Comments   :</font></td><td align='left' >");
            htmlText.append(" <font color='red' >" + taskHandlerAction.getTask_comments() + "</font></td></tr> ");

            htmlText.append(" <tr><td> ");
            htmlText.append("</td></tr>  </table> </td></tr> <tr><td colspan='2' >");
            htmlText.append("<br>Regards,");
            htmlText.append("</td></tr><tr><td colspan='2' >");
            htmlText.append("Services Bay ");
            htmlText.append(" Support Team,</td></tr><tr>");
            htmlText.append("<td colspan='2' ><font ");
            htmlText.append(" >Miracle Software Systems, Inc.</font>");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</body>");
            htmlText.append("</html>");

            String bodyContent = htmlText.toString();

            System.out.println(bodyContent);

            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,cc_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, "sb@miraclesoft.com");
            prepareStatement.setString(2, taskHandlerAction.getPrimaryMailId());
            prepareStatement.setString(3, taskHandlerAction.getSecondaryMailId());
            prepareStatement.setString(4, taskHandlerAction.getTaskName());
            prepareStatement.setString(5, bodyContent);
            prepareStatement.setString(6, "pending");
            prepareStatement.setInt(7, taskHandlerAction.getUserSessionId());

            addEmailResult = prepareStatement.executeUpdate();


            System.out.println("================>in mailmanager::::" + taskHandlerAction.getUserSessionId());


        } catch (Exception E) {
            E.printStackTrace();
        } finally {
            try {

                if (prepareStatement != null) {
                    prepareStatement.close();
                    prepareStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return addEmailResult;
    }

    public void sendLeaveEmail(String leaveLoginId, String supTo, String Bcc, String userLoginName, String reason, String status, String leaveRequiredFrom,
            String leaveRequiredTo, String leaveType, int userSessionId) throws ServiceLocatorException, SQLException {


        System.out.println("leaveLoginId--->" + leaveLoginId);
        System.out.println("supTo--->" + supTo);
        System.out.println("supTo--->" + userLoginName);

        String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
        String fstReportsto = leaveLoginId;
        String supReportsto = supTo;
        //String ccEmailId = "vsiram@miraclesoft.com";
        //String bccEmailId = "adoddi@miraclesoft.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", SMTP_HOST);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT);

        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getDefaultInstance(props, auth);
        //mailSession.setDebug(true);
        mailSession.setDebug(false);
        Transport transport;

        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(" Leave Request Mail");
            message.setFrom(new InternetAddress(from));

            //To Team lead
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(fstReportsto));
            //To Superior Reports To  
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(supReportsto));
            //To Manager
            //message.addRecipient(Message.RecipientType.CC,new InternetAddress(ccEmailId));
            //To the Applying Person
            //message.addRecipient(Message.RecipientType.CC,new InternetAddress(bccEmailId));
            //message.addRecipient(Message.RecipientType.BCC,new InternetAddress(cc));

            if ("C".equalsIgnoreCase(status)) {
                status = "Canceled";
            } else if ("O".equalsIgnoreCase(status)) {
                status = "Applied";
            } else if ("A".equalsIgnoreCase(status)) {
                status = "Approved";

            } else if ("R".equalsIgnoreCase(status)) {
                status = "Rejected";
            }

            if ("PA".equalsIgnoreCase(leaveType)) {
                leaveType = "Post Approval";
            } else if ("CT".equalsIgnoreCase(leaveType)) {
                leaveType = "Comptime";
            } else if ("VA".equalsIgnoreCase(leaveType)) {
                leaveType = "Vacation";
            } else if ("TO".equalsIgnoreCase(leaveType)) {
                leaveType = "Time Off";
            } else if ("CL".equalsIgnoreCase(leaveType)) {
                leaveType = "Cancel Leave";

            }


            MimeMultipart multipart = new MimeMultipart("related");

            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            StringBuilder htmlText = new StringBuilder();

            htmlText.append("<html>");
            htmlText.append("<body>");
            htmlText.append("<table align='center'>");
            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<td>");
            htmlText.append("<font color='white' size='4' face='Arial'>");
            htmlText.append("<p> Leave Tracking System in MSB.</p>");
            htmlText.append("</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<table style='background:#FFFFCC;width:100%;' color='#151B54' size='4px' height='20px' >");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<font color='#3399FF' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<br>  New Leave Request  ");
            htmlText.append("</font><br></td></tr><tr><td colspan='2'>");
            htmlText.append(" Hello " + com.mss.msp.util.DataSourceDataProvider.getInstance().getFirstnameandLastnameByEmail(leaveLoginId) + " ,");
            htmlText.append("</td></tr> <tr><td> <table border='0' align='left'> <tr><td ><font> ");
            htmlText.append(" LeaveStatus :</font></td>");
            htmlText.append("<td width='75%' align='left' ><font color='red'> ");
            htmlText.append("" + status + "</font></td></tr> <tr><td> ");
            htmlText.append("<font  ");
            htmlText.append("><br><font  ");
            htmlText.append("><b> Leave Details </b></font></td><td align='left' ");
            htmlText.append("></td></tr><br> <tr><td");
            htmlText.append(" > ");
            htmlText.append("LeaveType :</td><td align='left' ");
            htmlText.append("><font color='red' >" + leaveType + "</font></td></tr> <tr><td");
            htmlText.append(" >  ");
            htmlText.append("Start Date :</td><td align='left' ");
            htmlText.append("><font color='red' >" + leaveRequiredFrom + "</font></td></tr> <tr><td");
            htmlText.append(">");
            htmlText.append("End Date  :</td><td align='left' ");
            htmlText.append("><font color='red' >" + leaveRequiredTo + "</font></td></tr> <tr><td");
            htmlText.append(" valign='top' ><font  ");
            htmlText.append(">Reason   :</font></td><td align='left' ");
            htmlText.append(" ><font color='red' >" + reason + "</font></td></tr> <tr><td");
            htmlText.append(" > ");
            htmlText.append("</td></tr>  </table> </td></tr> <tr><td colspan='2' >");
            htmlText.append("<br> Regards,");
            htmlText.append("</td></tr><tr><td colspan='2' >");
            htmlText.append(" Services Bay ");
            htmlText.append(" Support Team,</td></tr><tr>");
            htmlText.append("<td colspan='2' ><font ");
            htmlText.append(" > Miracle Software Systems, Inc.</font>");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</body>");
            htmlText.append("</html>");
            String bodyContent = htmlText.toString();
            messageBodyPart.setContent(htmlText.toString(), "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);
            // put everything together
            message.setContent(multipart);
            doaddemailLog(from, fstReportsto, Bcc, supReportsto, "Leave Request Mail", bodyContent, userSessionId);
            //transport.connect();
            //transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            //transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            transport.close();



        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * *************************************
     *
     * @generateTaskCompleteEmail() it generates email when user changes status
     * to complete
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:04/29/2015
     *
     *
     **************************************
     *
     */
    public int generateTaskCompleteEmail(TaskHandlerAction taskHandlerAction) throws ServiceLocatorException, SQLException {
        int addEmailResult = 0;
        try {

            connection = ConnectionProvider.getInstance().getConnection();
            String priority = "";
            if (taskHandlerAction.getPriority().equalsIgnoreCase("M")) {
                priority = "Medium";
            } else if (taskHandlerAction.getPriority().equalsIgnoreCase("L")) {
                priority = "Low";
            } else if (taskHandlerAction.getPriority().equalsIgnoreCase("H")) {
                priority = "High";
            } else {
                priority = "";
            }

            StringBuilder htmlText = new StringBuilder();

            htmlText.append("<html>");
            htmlText.append("<body>");
            htmlText.append("<table align='center'>");

            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<td>");
            htmlText.append("<font color='white' size='4' face='Arial'>");
            htmlText.append("<p> Task Management System in MSB.</p>");
            htmlText.append("</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");

            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<table style='background:#FFFFCC;width:100%;' color='#151B54' size='4px' height='20px' >");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<font color='#3399FF' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<br>  Hello Team,  ");
            htmlText.append("</font><br></td></tr>");

            htmlText.append("<tr><td colspan='2'>");
            htmlText.append("A task has been Assigned by:<b><font color='#6666FF'>" + taskHandlerAction.getFullName());
            htmlText.append("</font></b></td></tr>");

            htmlText.append("<tr><td> <table border='0' align='left'> <tr><td ><font> ");
            htmlText.append(" Task Details :</font></td>");
            htmlText.append("</tr>");

            htmlText.append("<br> <tr><td>");
            htmlText.append("TaskStatus :</td><td align='left' >");
            htmlText.append("<font color='red' >" + taskHandlerAction.getStatusName() + "</font></td></tr> ");

            htmlText.append(" <tr><td>  ");
            htmlText.append("Title :</td><td align='left' >");
            htmlText.append("<font color='red' >" + taskHandlerAction.getTaskName() + "</font></td></tr> ");

            htmlText.append("<tr><td>");
            htmlText.append("Priority  :</td><td align='left' >");
            htmlText.append("<font color='red' >" + priority + "</font></td></tr> ");

            htmlText.append("<tr><td valign='top' ><font > ");
            htmlText.append("Task ID   :</font></td><td align='left'> ");
            htmlText.append(" <font color='red' >" + taskHandlerAction.getTaskid() + "</font></td></tr> ");

            htmlText.append(" <tr><td> ");
            htmlText.append("Comments   :</font></td><td align='left' >");
            htmlText.append(" <font color='red' >" + taskHandlerAction.getTask_comments() + "</font></td></tr> ");

            htmlText.append(" <tr><td> ");
            htmlText.append("</td></tr>  </table> </td></tr> <tr><td colspan='2' >");
            htmlText.append("<br>Regards,");
            htmlText.append("</td></tr><tr><td colspan='2' >");
            htmlText.append("Services Bay ");
            htmlText.append(" Support Team,</td></tr><tr>");
            htmlText.append("<td colspan='2' ><font ");
            htmlText.append(" >Miracle Software Systems, Inc.</font>");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</body>");
            htmlText.append("</html>");

            String bodyContent = htmlText.toString();

            System.out.println(bodyContent);

            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,cc_address,bcc_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, "sb@miraclesoft.com");
            prepareStatement.setString(2, taskHandlerAction.getTaskCreatedByEmail());
            prepareStatement.setString(3, taskHandlerAction.getPrimaryMailId());
            prepareStatement.setString(4, taskHandlerAction.getSecondaryMailId());
            prepareStatement.setString(5, taskHandlerAction.getTaskName());
            prepareStatement.setString(6, bodyContent);
            prepareStatement.setString(7, "pending");
            prepareStatement.setInt(8, taskHandlerAction.getUserSessionId());

            addEmailResult = prepareStatement.executeUpdate();





        } catch (Exception E) {
            E.printStackTrace();
        } finally {
            try {

                if (prepareStatement != null) {
                    prepareStatement.close();
                    prepareStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return addEmailResult;
    }
    /**
     * *************************************
     *
     * @requirementReleaseMailGenerator() it generates email when user changes
     * status to release
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:04/29/2015
     *
     *
     **************************************
     *
     */
    public int requirementReleaseMailGenerator(RequirementVTO requirementVTO,String MailIds, int userId, int orgId, String accName) throws ServiceLocatorException, SQLException {
        int addEmailResult = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();


            StringBuilder htmlText = new StringBuilder();

            htmlText.append("<html>");
            htmlText.append("<body>");
            htmlText.append("<table align='center'>");

            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<td>");
            htmlText.append("<font color='white' size='4' face='Arial'>");
            htmlText.append("<p>Requirement Release Notification</p>");
            htmlText.append("</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");

            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<table style='background:#FFFFCC;width:100%;' color='#151B54' size='4px' height='20px' >");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<font color='#3399FF' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<br>Hello,  ");
            htmlText.append("</font><br></td></tr>");

            htmlText.append("<tr><td colspan='2'>");
            htmlText.append("A Requirement has been Released by:<b><font color='#6666FF'>" + accName);
            htmlText.append("</font></b></td></tr>");

            htmlText.append("<tr><td> <table border='0' align='left'> <tr><td ><font> ");
            htmlText.append(" Requirement Details :</font></td>");
            htmlText.append("</tr>");

            htmlText.append("<br> <tr><td>");
            htmlText.append("Name :</td><td align='left' >");
            htmlText.append("<font color='red' >" + requirementVTO.getReqName() + "</font></td></tr> ");

            htmlText.append(" <tr><td>  ");
            htmlText.append("No.Of Resources :</td><td align='left' >");
            htmlText.append("<font color='red' >" + requirementVTO.getReqResources() + "</font></td></tr> ");

            htmlText.append("<tr><td>");
            htmlText.append("Start Date :</td><td align='left' >");
            htmlText.append("<font color='red' >" + requirementVTO.getReqStartDate() + "</font></td></tr> ");

//            htmlText.append("<tr><td valign='top' ><font > ");
//            htmlText.append("End Date :</font></td><td align='left'> ");
//            htmlText.append(" <font color='red' >" + requirementVTO.getReqEndDate() + "</font></td></tr> ");

            htmlText.append(" <tr><td> ");
            htmlText.append("Description   :</font></td><td align='left' >");
            htmlText.append(" <font color='red' >" + requirementVTO.getReqDesc() + "</font></td></tr> ");

            htmlText.append(" <tr><td> ");
            htmlText.append("</td></tr>  </table> </td></tr> <tr><td colspan='2' >");
            htmlText.append("<br>Regards,");
            htmlText.append("</td></tr><tr><td colspan='2' >");
            htmlText.append("Services Bay ");
            htmlText.append(" Support Team,</td></tr><tr>");
            htmlText.append("<td colspan='2' ><font ");
            htmlText.append(" >Miracle Software Systems, Inc.</font>");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</body>");
            htmlText.append("</html>");

            String bodyContent = htmlText.toString();

            System.out.println(bodyContent);

            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, "sb@miraclesoft.com");
            prepareStatement.setString(2, MailIds);
            prepareStatement.setString(3, "Requirement Release Notification");

            prepareStatement.setString(4, bodyContent);
            prepareStatement.setString(5, "pending");
            prepareStatement.setInt(6, userId);
            System.out.println("sqlQuery " + sqlQuery);
            addEmailResult = prepareStatement.executeUpdate();





        } catch (Exception E) {
            E.printStackTrace();
        } finally {
            try {

                if (prepareStatement != null) {
                    prepareStatement.close();
                    prepareStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return addEmailResult;
    }
    
    /**
     * *************************************
     *
     * @requirementReleaseMailGenerator() it generates email when user changes
     * status to release
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:04/29/2015
     *
     *
     **************************************
     *
     */
    public int techReviewTechieEmailGenerator(RecruitmentAction recruitmentAction,String emapMailId) throws ServiceLocatorException, SQLException {
        int addEmailResult = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();


            StringBuilder htmlText = new StringBuilder();

            htmlText.append("<html>");
            htmlText.append("<body>");
            htmlText.append("<table align='center'>");
            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<td><font color='white' size='4' face='Arial'><p>Tech Review Alert</p></font></td>");
            htmlText.append("</tr>");
            htmlText.append("<tr><td>");
            htmlText.append("<table style='background:#FFFFCC;width:100%;' color='#151B54' size='4px' height='20px' >");
            htmlText.append("<tr><td><font color='#3399FF' size='2' face='Arial' style='font-weight:600;'><br>Hello ,</font><br></td></tr>");
            htmlText.append("<tr><td colspan='2'>A Tech Review has been Forwarded by:<b><font color='#6666FF'>" + recruitmentAction.getForwardedByName() + "</font></b></td></tr>");
            htmlText.append(" <tr><td>");
            htmlText.append("<table border='0' align='left'>  ");
            htmlText.append(" <tr><td><font>Tech Review Details :</font></td></tr>");
            htmlText.append(" <br> <tr><td>Review Type:</td><td align='left' ><font color='red' >" + recruitmentAction.getInterviewType() + "</font></td></tr> ");
            htmlText.append("<tr><td>Consultant Name :</td><td align='left' ><font color='red' >" + recruitmentAction.getConsult_name() + "</font></td></tr> ");
            htmlText.append(" <tr><td>Job Title:</td><td align='left' ><font color='red' >" + recruitmentAction.getConsult_jobTitle() + "</font></td></tr>");
            htmlText.append("<tr><td>Skill Set:</td><td align='left' ><font color='red' >" + recruitmentAction.getConSkills() + "</font></td></tr> ");
            htmlText.append(" <tr><td valign='top' ><font >Review Date :</font></td><td align='left'><font color='red' >" + recruitmentAction.getReviewDate() + "</font></td></tr>");

            htmlText.append(" <tr><td> Review Time   :</td>");
            htmlText.append(" <td align='left'> <font color='red' >" + recruitmentAction.getReviewTime() + "</font></td></tr>");
            if (recruitmentAction.getInterviewType().equalsIgnoreCase("Face to Face")) {
                htmlText.append(" <tr><td valign='top' ><font >Location :</font></td><td align='left'><font color='red' >" + recruitmentAction.getInterviewLocation() + "</font></td></tr>");
            }
            htmlText.append(" <tr><td></td></tr></table></td></tr>");
            htmlText.append(" <tr><td colspan='2'><br>Regards,</td></tr>");
            htmlText.append("<tr><td colspan='2' >Services Bay  Support Team,</td></tr> ");
            htmlText.append(" <tr><td colspan='2'><font>Miracle Software Systems, Inc.</font></td></tr>");
            htmlText.append("<tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font></td></tr> ");
            htmlText.append("</table></td></tr></table></body></html> ");

            String bodyContent = htmlText.toString();

            System.out.println(bodyContent);

            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, "sb@miraclesoft.com");
            prepareStatement.setString(2, emapMailId);
            prepareStatement.setString(3, "Tech Review Alert");

            prepareStatement.setString(4, bodyContent);
            prepareStatement.setString(5, "pending");
            prepareStatement.setInt(6, recruitmentAction.getUserSessionId());
            System.out.println("sqlQuery " + sqlQuery);
            addEmailResult = prepareStatement.executeUpdate();





        } catch (Exception E) {
            E.printStackTrace();
        } finally {
            try {

                if (prepareStatement != null) {
                    prepareStatement.close();
                    prepareStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return addEmailResult;
    }

    /**
     * *************************************
     *
     * @requirementReleaseMailGenerator() it generates email when user changes
     * status to release
     *
     * @Author:Ramakrishna<lankireddy@miraclesoft.com>
     *
     * @Created Date:04/29/2015
     *
     *
     **************************************
     *
     */
    public int techReviewConsultantEmailGenerator(RecruitmentAction recruitmentAction) throws ServiceLocatorException, SQLException {
        int addEmailResult = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();


            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html>");
            htmlText.append("<body>");
            htmlText.append("<table align='center'>");
            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<td><font color='white' size='4' face='Arial'><p>Tech Review Alert</p></font></td>");
            htmlText.append("</tr>");
            htmlText.append("<tr><td>");
            htmlText.append("<table style='background:#FFFFCC;width:100%;' color='#151B54' size='4px' height='20px' >");
            htmlText.append("<tr><td><font color='#3399FF' size='2' face='Arial' style='font-weight:600;'><br>Hello " + recruitmentAction.getConsult_name() + ",</font><br></td></tr>");
            htmlText.append("<tr><td colspan='2'>Your Tech Review has been Scheduled On:<b><font color='#6666FF'>" + recruitmentAction.getReviewDate() + "</font></b></td></tr>");
            htmlText.append(" <tr><td>");
            htmlText.append("<table border='0' align='left'>  ");
            htmlText.append(" <tr><td><font>Tech Review Details :</font></td></tr>");
            htmlText.append(" <br> <tr><td>Review Type:</td><td align='left' ><font color='red' >" + recruitmentAction.getInterviewType() + "</font></td></tr> ");
            htmlText.append(" <tr><td>For Position:</td><td align='left' ><font color='red' >" + recruitmentAction.getReqName() + "</font></td></tr> ");
            htmlText.append(" <tr><td>Reviewer:</td><td align='left' ><font color='red' >" + recruitmentAction.getForwardedToName() + "</font></td></tr>");
            htmlText.append("<tr><td>Skill Set:</td><td align='left' ><font color='red' >" + recruitmentAction.getConSkills() + "</font></td></tr> ");
            htmlText.append(" <tr><td valign='top' ><font >Review Date :</font></td><td align='left'><font color='red' >" + recruitmentAction.getReviewDate() + "</font></td></tr>");
            htmlText.append(" <tr><td> Review Time   :</td>");
            htmlText.append(" <td align='left'> <font color='red' >" + recruitmentAction.getReviewTime() + "</font></td></tr>");
            htmlText.append(" <tr><td></td></tr></table></td></tr>");
            if (recruitmentAction.getInterviewType().equalsIgnoreCase("Face to Face")) {
                htmlText.append(" <tr><td valign='top' ><font >Location :</font></td><td align='left'><font color='red' >" + recruitmentAction.getInterviewLocation() + "</font></td></tr>");
            }
            htmlText.append(" <tr><td colspan='2'><br>All The Best,</td></tr>");
            htmlText.append(" <br><tr><td colspan='2'><br>Regards,</td></tr>");
            htmlText.append("<tr><td colspan='2' >Services Bay  Support Team,</td></tr> ");
            htmlText.append(" <tr><td colspan='2'><font>Miracle Software Systems, Inc.</font></td></tr>");
            htmlText.append("<tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:Please do not reply to this e-mail. It was generated by our System.</font></td></tr> ");
            htmlText.append("</table></td></tr></table></body></html> ");

            String bodyContent = htmlText.toString();

            System.out.println(bodyContent);

            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, "sb@miraclesoft.com");
            prepareStatement.setString(2, recruitmentAction.getConEmail());
            prepareStatement.setString(3, "Tech Review Alert");

            prepareStatement.setString(4, bodyContent);
            prepareStatement.setString(5, "pending");
            prepareStatement.setInt(6, recruitmentAction.getUserSessionId());
            System.out.println("sqlQuery " + sqlQuery);
            addEmailResult = prepareStatement.executeUpdate();





        } catch (Exception E) {
            E.printStackTrace();
        } finally {
            try {

                if (prepareStatement != null) {
                    prepareStatement.close();
                    prepareStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return addEmailResult;
    }
    
    public int doAddTechReviewEmailLogger(RecruitmentAction recruitmentAction,String key,String emailId,String accToken,String ReqName) throws ServiceLocatorException, SQLException {
        int addEmailResult = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();


            StringBuilder htmlText = new StringBuilder();
            String onlineExam =com.mss.msp.util.Properties.getProperty("ONLINEEXAMURL");

         htmlText.append("<html>");
         htmlText.append("<head>");
         htmlText.append("<title>ServicesBay Online Exam</title>");
         htmlText.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
         htmlText.append("</head>");
         htmlText.append("<body  style='font-family: Georgia, Times, serif'>");
         htmlText.append("<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center' >");
         htmlText.append("<tr>");
         htmlText.append("<td width='100%' valign='top' bgcolor='#ffffff' style=''>");
         htmlText.append("<table style='border-bottom: 1px solid #333; align:center; width:900px; cellspacing:0;cellpadding:0 ; border:0;margin:0 auto;'>");
         htmlText.append("<tr>");
         htmlText.append("<td style='padding:10px 2px;float: left;border-collapse:collapse;'>");
         htmlText.append("<a href='#'><img  src='logo.jpg' alt='miraclesoft_logo' border='0' style='width: 250px;height: 60px;' /></a>"); 
         htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append("</table>");
         htmlText.append("<table style=' align:center; width:900px; cellspacing:0;cellpadding:0 ; border:0;margin:0 auto'>");
         htmlText.append("<tr>");
         htmlText.append("<td style=' border-bottom: 1px solid black !important;width: 100px;'></td>");
         htmlText.append("</tr>");
         htmlText.append("</table>");
         htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
         htmlText.append(" <td style='padding:10px 20px;border-collapse:collapse;'>");
         htmlText.append("<table  style='border-bottom: 1px solid #333; width:900px; cellspacing:0;cellpadding:0 ; border:0;margin:0 auto;'>");
         htmlText.append("<tr>");
         htmlText.append("<td style=''>");
         htmlText.append("<table align=left  width='600px' style='border-right: 1px solid black;padding:5px;'>");
         htmlText.append("<tr>");
         htmlText.append("<td><b style='font-size: 22px;color:#115B8F;font-family: Lato,Roboto,sans-serif;margin: 0px;padding: 0px;'>Welcome to On-line Technical Assesment Test</b></td>");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
         htmlText.append("<td> <table><tr> <td style=' border-bottom: 1px solid black !important;width: 250px;'></td></tr></table></td>");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
         htmlText.append("<td>");
         htmlText.append("<p style='text-align:justify;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 6px;font-size:17px> Hello :"+ recruitmentAction.getConsult_name() +" </p>");
         htmlText.append("<p style='text-align:justify;font-size:17px;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 6px;'>Click the following link to Write On-line Exam <a href='"+onlineExam+"onlineExam/Ques/onlineexam.action?token="+accToken+"'>Click Here</a></p>");
         htmlText.append("<p style='text-align:justify;font-size:17px;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 6px;'>Use the code To Activate Exam: <b><font color='Green'>" + key + "</font></b></p><br>");
         htmlText.append("<div style='margin: 0px;padding: 6px;text-align:left;font-family:Lato,Calibri,Arial,sans-serif;font-size:17px'>");
         htmlText.append("<font color='red'>Note*:</font>");
         htmlText.append("<ul>");
         htmlText.append("<li>The Activation code provided will be Expired in 24 Hours.</li>");
         htmlText.append("<li>The Activation code is only for one time use.</li>");
         htmlText.append("</ul>");
         htmlText.append(" </div> </td> ");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
         htmlText.append("<td> <p style='text-align:justify;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 6px;font-size:17px'> Thanks, </p>");
         htmlText.append("<p style='text-align:justify;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 6px;font-size:17px'> ServicesBay Team.</p>");
         htmlText.append("<div style='margin: 0px;padding: 6px;text-align:left;font-family:Lato,Calibri,Arial,sans-serif;font-size:17px'>");
         htmlText.append("<ul>*DO NOT REPLY TO THIS E-MAIL*<br/>This is an automated e-mail message sent from our support system. Do not reply to this e-mail as we won't receive your reply!</ul>");
         htmlText.append("</div></div> </td>");
         htmlText.append("</tr>");
         htmlText.append("</table>");
         htmlText.append("<table  align='right' style='border-bottom: 1px solid #333; width:285px; cellspacing:0;cellpadding:0 ; border:0;'>");
         htmlText.append("<tr>");
         htmlText.append("<td>");
         htmlText.append("<b style='color: #F03D49;font-size: 32px;margin: 0px;padding: 0px;font-family:Lato,Calibri,Arial,sans-serif;'>Requirement Details</b>");
         htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
         htmlText.append("<td>");
         htmlText.append("<table>");
         htmlText.append("<tr>");
         htmlText.append("<td style=''></td>");
         htmlText.append("</tr>");
         htmlText.append("</table>");
         htmlText.append("</tr>");
         htmlText.append("<tr>");
         htmlText.append("<td style=' border-bottom: 1px solid black !important;width: 10px;'>");
         htmlText.append("<tr>");
         htmlText.append("<p style='font-size: 27px;color:#115B8F;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 0px;'><b>"+ReqName+".</b></p>"); 
         htmlText.append("<p style='font-family:Lato,Calibri,Arial,sans-serif;font-size: 18px;margin: 0px;padding: 0px;'>Requirement Details</p>");
         htmlText.append("<p style='font-family:Lato,Calibri,Arial,sans-serif;font-size: 18px;margin: 0px;padding: 0px;'>ServicesBay Team</p>");
         htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append("</table>");
         htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append("</table>");
         htmlText.append("</td>");
         htmlText.append("</tr>");
         htmlText.append(" </table>");
         htmlText.append("</body>");
         htmlText.append("</html>");
 

            String bodyContent = htmlText.toString();

            System.out.println(bodyContent);

            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, "sb@miraclesoft.com");
            prepareStatement.setString(2, emailId);
            prepareStatement.setString(3, "Tech Review Alert");

            prepareStatement.setString(4, bodyContent);
            prepareStatement.setString(5, "pending");
            prepareStatement.setInt(6, recruitmentAction.getUserSessionId());
            System.out.println("sqlQuery " + sqlQuery);
            addEmailResult = prepareStatement.executeUpdate();





        } catch (Exception E) {
            E.printStackTrace();
        } finally {
            try {

                if (prepareStatement != null) {
                    prepareStatement.close();
                    prepareStatement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return addEmailResult;
    }
}
