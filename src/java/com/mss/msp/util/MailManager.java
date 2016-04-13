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
import com.mss.msp.requirement.RequirementAction;
import com.mss.msp.requirement.RequirementVTO;
import com.mss.msp.sagAjax.InvoiceAjaxAction;
import com.mss.msp.usr.task.TaskHandlerAction;
import com.mss.msp.usr.timesheets.UsrTimeSheetAction;
import com.mss.msp.usrajax.UserAjaxHandlerAction;
import com.mss.msp.util.DataSourceDataProvider;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
//new
import java.util.Set;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

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
       
        String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
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
        props.put("mail.smtp.starttls.enable","true");
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
            htmlText.append("<font color='red', size='2' face='Arial' style='font-weight:600;'>*Note: &nbsp;Please do not reply to this e-mail. It was generated by our System.</font>");
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
            String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
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
            htmlText.append("<font color='red', size='2' face='Arial' style='font-weight:600;'>*Note: &nbsp;Please do not reply to this e-mail. It was generated by our System.</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</body>");
            htmlText.append("</html>");

            String bodyContent = htmlText.toString();

            System.out.println(bodyContent);

            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,cc_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, from);
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
        
        props.put("mail.smtp.starttls.enable","true");
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
            htmlText.append("<font color='red', size='2' face='Arial' style='font-weight:600;'>*Note: &nbsp;Please do not reply to this e-mail. It was generated by our System.</font>");
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
            String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
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
            htmlText.append("<font color='red', size='2' face='Arial' style='font-weight:600;'>*Note: &nbsp;Please do not reply to this e-mail. It was generated by our System.</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</body>");
            htmlText.append("</html>");

            String bodyContent = htmlText.toString();

            System.out.println(bodyContent);

            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,cc_address,bcc_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, from);
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
    public int requirementReleaseMailGenerator(RequirementVTO requirementVTO, String MailIds, int userId, int orgId, String accName) throws ServiceLocatorException, SQLException {
        int addEmailResult = 0;
        try {
            String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
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
            htmlText.append("<font color='red', size='2' face='Arial' style='font-weight:600;'>*Note: &nbsp;Please do not reply to this e-mail. It was generated by our System.</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</body>");
            htmlText.append("</html>");

            String bodyContent = htmlText.toString();

            System.out.println(bodyContent);

            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, from);
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
    public int techReviewTechieEmailGenerator(RecruitmentAction recruitmentAction, String emapMailId) throws ServiceLocatorException, SQLException {
        int addEmailResult = 0;
        try {
            String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
            connection = ConnectionProvider.getInstance().getConnection();


            StringBuilder htmlText = new StringBuilder();

            htmlText.append("<html>");
            htmlText.append("<body>");
            htmlText.append("<table align='center'>");
            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<td><font color='white' size='4' face='Arial'><p>Tech Review Details</p></font></td>");
            htmlText.append("</tr>");
            htmlText.append("<tr><td>");
            htmlText.append("<table style='background:#FFFFCC;width:100%;' color='#151B54' size='4px' height='20px' >");
            htmlText.append("<tr><td><font color='#3399FF' size='2' face='Arial' style='font-weight:600;'><br>Hello ," + recruitmentAction.getForwardedToName() + "</font><br></td></tr>");
            htmlText.append("<tr><td colspan='2'>A Tech Review has been Forwarded by:<b><font color='#6666FF'>" + recruitmentAction.getForwardedByName() + "</font></b></td></tr>");
            htmlText.append(" <tr><td>");
            htmlText.append("<table border='0' align='left'>  ");
            htmlText.append(" <tr><td><font>Details :</font></td></tr>");
            htmlText.append("<tr><td>Consultant Name :</td><td align='left' ><font color='red' >" + recruitmentAction.getConsult_name() + "</font></td></tr> ");
            htmlText.append(" <tr><td>Job Id:</td><td align='left' ><font color='red' > JD-" + recruitmentAction.getRequirementId() + "</font></td></tr> ");
            if (recruitmentAction.getConsult_jobTitle() != null) {
                htmlText.append(" <tr><td>Job Title:</td><td align='left' ><font color='red' >" + recruitmentAction.getConsult_jobTitle() + "</font></td></tr>");
            }

            htmlText.append(" <tr><td>Position:</td><td align='left' ><font color='red' >" + recruitmentAction.getReqName() + "</font></td></tr> ");
            if (!"Online".equalsIgnoreCase(recruitmentAction.getInterviewType()) && !"Psychometric".equalsIgnoreCase(recruitmentAction.getInterviewType())) {
                //System.out.println("review type in if condition"+recruitmentAction.getInterviewType());    
                htmlText.append(" <tr><td valign='top' ><font >Review Date :</font></td><td align='left'><font color='red' >" + recruitmentAction.getInterviewDate() + "</font></td></tr>");


            }


            htmlText.append(" <br> <tr><td>Review Type:</td><td align='left' ><font color='red' >" + recruitmentAction.getInterviewType() + "</font></td></tr> ");
            if (!"".equals(recruitmentAction.getContechNote())) {
                htmlText.append(" <br> <tr><td>Comments:</td><td align='left' ><font color='red' >" + recruitmentAction.getContechNote() + "</font></td></tr> ");
            }






            if (recruitmentAction.getInterviewType().equalsIgnoreCase("Face to Face")) {
                htmlText.append(" <tr><td valign='top' ><font >Location :</font></td><td align='left'><font color='red' >" + recruitmentAction.getInterviewLocation() + "</font></td></tr>");
            }
            htmlText.append(" <tr><td></td></tr></table></td></tr>");
            htmlText.append(" <tr><td colspan='2'><br>Regards,</td></tr>");
            htmlText.append("<tr><td colspan='2' >Services Bay  Support Team,</td></tr> ");

            htmlText.append("<tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>*Note: &nbsp;Please do not reply to this e-mail. It was generated by our System.</font></td></tr> ");
            htmlText.append("</table></td></tr></table></body></html> ");

            String bodyContent = htmlText.toString();

            System.out.println(bodyContent);

            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, from);
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
            String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
            connection = ConnectionProvider.getInstance().getConnection();


            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html>");
            htmlText.append("<body>");
            htmlText.append("<table align='center'>");
            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<td><font color='white' size='4' face='Arial'><p>Tech Review Details</p></font></td>");
            htmlText.append("</tr>");
            htmlText.append("<tr><td>");
            htmlText.append("<table style='background:#FFFFCC;width:100%;' color='#151B54' size='4px' height='20px' >");
            htmlText.append("<tr><td><font color='#3399FF' size='2' face='Arial' style='font-weight:600;'><br>Hello " + recruitmentAction.getConsult_name() + ",</font><br></td></tr>");
            htmlText.append("<tr><td colspan='2'>Your Tech Review has been Scheduled On:<b><font color='#6666FF'>" + recruitmentAction.getInterviewDate() + "(" + recruitmentAction.getTimeZone() + ")" + "</font></b></td></tr>");
            //htmlText.append("<tr><td colspan='2'>Your Tech Review has been Scheduled On:<b><font color='#6666FF'>" + recruitmentAction.getReviewDate() + "</font></b></td></tr>");
            htmlText.append(" <tr><td>");
            htmlText.append("<table border='0' align='left'>  ");
            htmlText.append(" <tr><td><font>Details :</font></td></tr>");
            htmlText.append(" <tr><td>Job Id:</td><td align='left' ><font color='red' > JD-" + recruitmentAction.getRequirementId() + "</font></td></tr> ");
            htmlText.append(" <tr><td>Position:</td><td align='left' ><font color='red' >" + recruitmentAction.getReqName() + "</font></td></tr> ");
            //htmlText.append(" <tr><td valign='top' ><font >Review Date :</font></td><td align='left'><font color='red' >" + recruitmentAction.getInterviewDate() + "</font></td></tr>");
            htmlText.append(" <tr><td>Reviewer:</td><td align='left' ><font color='red' >" + recruitmentAction.getForwardedToName() + "</font></td></tr>");
            htmlText.append(" <br> <tr><td>Review Mode:</td><td align='left' ><font color='red' >" + recruitmentAction.getInterviewType() + "</font></td></tr> ");
            if (!"".equals(recruitmentAction.getContechNote())) {
                htmlText.append(" <br> <tr><td>Comments:</td><td align='left' ><font color='red' >" + recruitmentAction.getContechNote() + "</font></td></tr> ");
            }
            if (recruitmentAction.getInterviewType().equalsIgnoreCase("Face to Face")) {
                htmlText.append(" <tr><td valign='top' ><font >Location :</font></td><td align='left'><font color='red' >" + recruitmentAction.getInterviewLocation() + "</font></td></tr>");
            }
             htmlText.append(" <tr><td></td></tr></table></td></tr>");
            htmlText.append(" <tr><td colspan='2'><br>All The Best,</td></tr>");
            htmlText.append(" <br><tr><td colspan='2'><br>Regards,</td></tr>");
            htmlText.append("<tr><td colspan='2' >Services Bay  Support Team,</td></tr> ");

            htmlText.append("<tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>*Note: &nbsp;Please do not reply to this e-mail. It was generated by our System.</font></td></tr> ");
            htmlText.append("</table></td></tr></table></body></html> ");

            String bodyContent = htmlText.toString();

            System.out.println(bodyContent);

            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, from);
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

    public int doAddTechReviewEmailLogger(RecruitmentAction recruitmentAction, String key, String emailId, String accToken, String ReqName) throws ServiceLocatorException, SQLException {
        int addEmailResult = 0;
        try {
            connection = ConnectionProvider.getInstance().getConnection();
            String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
             String logo = com.mss.msp.util.Properties.getProperty("SERVICESBAYLOGO").toString();
            StringBuilder htmlText = new StringBuilder();
            String onlineExam = com.mss.msp.util.Properties.getProperty("ONLINEEXAMURL");

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
            htmlText.append("<a href='#'><img  src="+logo+" alt='miraclesoft_logo' border='0' style='width: 250px;height: 60px;' /></a>");
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
            htmlText.append("<p style='text-align:justify;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 6px;font-size:17px> Hello :" + recruitmentAction.getConsult_name() + " </p>");
            htmlText.append("<p style='text-align:justify;font-size:17px;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 6px;'>Click the following link to Write On-line Exam <a href='" + onlineExam + "onlineExam/Ques/onlineexam.action?token=" + accToken + "'>Click Here</a></p>");
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
            htmlText.append("<p style='font-size: 27px;color:#115B8F;font-family:Lato,Calibri,Arial,sans-serif;margin: 0px;padding: 0px;'><b>" + ReqName + ".</b></p>");
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
            prepareStatement.setString(1, from);
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

    public static void sendInvitationToConsultant(RecruitmentAction recruitmentAction,String consultMailId, String emapMailId) throws Exception {

        System.out.println("In sendInvitationToConsultant()");
        String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
        int mailFlag = Integer.parseInt(com.mss.msp.util.Properties.getProperty("Mail.Flag"));
        try {
            if (mailFlag == 1) {
                MimetypesFileTypeMap mimetypes = (MimetypesFileTypeMap) MimetypesFileTypeMap.getDefaultFileTypeMap();
                mimetypes.addMimeTypes("text/calendar ics ICS");

                //register the handling of text/calendar mime type
                MailcapCommandMap mailcap = (MailcapCommandMap) MailcapCommandMap.getDefaultCommandMap();
                mailcap.addMailcap("text/calendar;; x-java-content-handler=com.sun.mail.handlers.text_plain");


                Properties props = new Properties();

                props.setProperty("mail.transport.protocol", "smtp");
                props.setProperty("mail.host", SMTP_HOST);
                
                props.put("mail.smtp.starttls.enable","true");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", SMTP_PORT);
                Authenticator auth = new SMTPAuthenticator();
                //Session mailSession = Session.getDefaultInstance(props, null);
                Session mailSession = Session.getDefaultInstance(props, auth);

                MimeMessage message = new MimeMessage(mailSession);
                message.setFrom(new InternetAddress(from));
                message.setSubject("Tech Review Alert");
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(consultMailId));

// Create an alternative Multipart
                Multipart multipart = new MimeMultipart("alternative");

//part 1, html text
                BodyPart messageBodyPart;
                messageBodyPart = buildHtmlTextPart(recruitmentAction, emapMailId);
                multipart.addBodyPart(messageBodyPart);

// Add part two, the calendar
                BodyPart calendarPart = buildCalendarPart(recruitmentAction, emapMailId, from);
                multipart.addBodyPart(calendarPart);

//Put the multipart in message 
                message.setContent(multipart);

                // send the message
                Transport transport = mailSession.getTransport("smtp");
                transport.connect();
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            }

        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

    private static BodyPart buildHtmlTextPart(RecruitmentAction recruitmentAction, String emapMailId) throws MessagingException {

        MimeBodyPart descriptionPart = new MimeBodyPart();

        //Note: even if the content is spcified as being text/html, outlook won't read correctly tables at all
        // and only some properties from div:s. Thus, try to avoid too fancy content
        StringBuilder htmlText = new StringBuilder();
        htmlText.append("<html>");
        htmlText.append("<body>");
        htmlText.append("<table align='center'>");
        htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
        htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
        htmlText.append("<td><font color='white' size='4' face='Arial'><p>Tech Review Details</p></font></td>");
        htmlText.append("</tr>");
        htmlText.append("<tr><td>");
        htmlText.append("<table style='background:#FFFFCC;width:100%;' color='#151B54' size='4px' height='20px' >");
        htmlText.append("<tr><td><font color='#3399FF' size='2' face='Arial' style='font-weight:600;'><br><font color='black'>Hello &nbsp; </font>" + recruitmentAction.getConsult_name() + ",</font><br></td></tr>");
        htmlText.append("<tr><td colspan='2'>Your Tech Review has been Scheduled On:<b><font color='#6666FF'>" + recruitmentAction.getInterviewDate() + "(" + recruitmentAction.getTimeZone() + ")" + "</font></b></td></tr>");
        htmlText.append(" <tr><td>");
        htmlText.append("<table border='0' align='left'>  ");
        htmlText.append(" <tr><td><font>Details :</font></td></tr>");
        htmlText.append(" <tr><td>Job Id:</td><td align='left' ><font color='red' > JD-" + recruitmentAction.getRequirementId() + "</font></td></tr> ");
        htmlText.append(" <tr><td>Position:</td><td align='left' ><font color='red' >" + recruitmentAction.getReqName() + "</font></td></tr> ");
        //htmlText.append(" <tr><td valign='top' ><font >Review Date :</font></td><td align='left'><font color='red' >" + recruitmentAction.getInterviewDate() + "</font></td></tr>");
        htmlText.append(" <tr><td>Reviewer:</td><td align='left' ><font color='red' >" + recruitmentAction.getForwardedToName() + "</font></td></tr>");
        htmlText.append(" <br> <tr><td>Review Mode:</td><td align='left' ><font color='red' >" + recruitmentAction.getInterviewType() + "</font></td></tr> ");
        if (!"".equals(recruitmentAction.getContechNote())) {
            htmlText.append(" <br> <tr><td>Comments:</td><td align='left' ><font color='red' >" + recruitmentAction.getContechNote() + "</font></td></tr> ");
        }
        if (recruitmentAction.getInterviewType().equalsIgnoreCase("Face to Face")) {
            htmlText.append(" <tr><td valign='top' ><font >Location :</font></td><td align='left'><font color='red' >" + recruitmentAction.getInterviewLocation() + "</font></td></tr>");
        }
        htmlText.append(" <tr><td></td></tr></table></td></tr>");

        htmlText.append(" <tr><td colspan='2'><br>All The Best,</td></tr>");
        htmlText.append(" <br><tr><td colspan='2'><br>Regards,</td></tr>");
        htmlText.append("<tr><td colspan='2' >Services Bay  Support Team,</td></tr> ");

        htmlText.append("<tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>*Note: &nbsp;Please do not reply to this e-mail. It was generated by our System.</font></td></tr> ");
        htmlText.append("</table></td></tr></table></body></html> ");

        String bodyContent = htmlText.toString();


        System.out.println(bodyContent);
        // String content = "Tech Review invitation";
        descriptionPart.setContent(bodyContent, "text/html; charset=utf-8");

        return descriptionPart;
    }

    private static BodyPart buildCalendarPart(RecruitmentAction recruitmentAction, String emapMailId, String from) throws Exception {

        BodyPart calendarPart = new MimeBodyPart();

        SimpleDateFormat iCalendarDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm'00'");
        SimpleDateFormat sdfInput = new SimpleDateFormat("MM-dd-yyyy HH:mm");

        java.util.Date date = null;
        System.out.println("getInterviewDate()-->" + recruitmentAction.getInterviewDate());
        date = sdfInput.parse(recruitmentAction.getInterviewDate());
        System.out.println("iCalendarDateFormat.format(date);" + iCalendarDateFormat.format(date));

        Calendar cal = Calendar.getInstance();
        System.out.println("recruitmentAction.getInterviewDate()" + recruitmentAction.getInterviewDate());

        // SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.EN_US);
        // cal.setTime(iCalendarDateFormat.parse(recruitmentAction.getInterviewDate()));// all done
        Date start = cal.getTime();
        //   System.out.println("iCalendarDateFormat.format(start)"+iCalendarDateFormat.format(start));
        //check the icalendar spec in order to build a more complicated meeting request

       String calendarContent =
                "BEGIN:VCALENDAR\n"
                + "METHOD:REQUEST\n"
                + "PRODID: BCP - Meeting\n"
                + "VERSION:2.0\n"
                + "BEGIN:VEVENT\n"
                + "DTSTAMP:" + iCalendarDateFormat.format(date) + "\n"
                + "DTSTART:" + iCalendarDateFormat.format(date) + "\n"
                //  + "DTEND:" + iCalendarDateFormat.format(start) + "\n"
                + "SUMMARY:Tech Review\n"
                + "UID:324\n"
              //  + "ATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;RSVP=TRUE:MAILTO:organizer@yahoo.com\n"
                + "ORGANIZER:MAILTO:"+ from +"\n"
                + "LOCATION:"+ recruitmentAction.getInterviewLocation()+"\n"
                + "DESCRIPTION:learn some stuff\n"
                + "SEQUENCE:0\n"
                + "PRIORITY:5\n"
                + "CLASS:PUBLIC\n"
                + "STATUS:CONFIRMED\n"
                + "TRANSP:OPAQUE\n"
                + "BEGIN:VALARM\n"
                + "ACTION:DISPLAY\n"
                + "DESCRIPTION:REMINDER\n"
                + "TRIGGER;RELATED=START:-PT00H15M00S\n"
                + "END:VALARM\n"
                + "END:VEVENT\n"
                + "END:VCALENDAR";



        calendarPart.addHeader("Content-Class", "urn:content-classes:calendarmessage");
        calendarPart.setContent(calendarContent, "text/calendar;method=CANCEL");

        return calendarPart;
    }

    /**
     * *************************************
     *
     * @techReviewMailToVendor() it generates email when user forward review
     *
     *
     * @Author:Jagan Chukkala<jchukkala@miraclesoft.com>
     *
     * @Created Date:12/15/2015
     *
     *
     **************************************
     *
     */
    public int techReviewMailToVendor(RecruitmentAction recruitmentAction, String venEmail) throws ServiceLocatorException, SQLException {
        int addEmailResult = 0;
        try {
            String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
            connection = ConnectionProvider.getInstance().getConnection();


            StringBuilder htmlText = new StringBuilder();
            htmlText.append("<html>");
            htmlText.append("<body>");
            htmlText.append("<table align='center'>");
            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<td><font color='white' size='4' face='Arial'><p>Tech Review Details</p></font></td>");
            htmlText.append("</tr>");
            htmlText.append("<tr><td>");
            htmlText.append("<table style='background:#FFFFCC;width:100%;' color='#151B54' size='4px' height='20px' >");
            htmlText.append("<tr><td><font color='#3399FF' size='2' face='Arial' style='font-weight:600;'><br>Hello ," + recruitmentAction.getVenName() + "</font><br></td></tr>");
            htmlText.append("<tr><td colspan='2'>Tech Review has been Forwarded by:<b><font color='#6666FF'>" + recruitmentAction.getAccountName() + "</font></b></td></tr>");
            htmlText.append(" <tr><td>");
            htmlText.append("<table border='0' align='left'>  ");
            htmlText.append(" <tr><td><font>Details :</font></td></tr>");
            htmlText.append("<tr><td>Consultant Name :</td><td align='left' ><font color='red' >" + recruitmentAction.getConsult_name() + "</font></td></tr> ");
            htmlText.append(" <tr><td>Job Id:</td><td align='left' ><font color='red' > JD-" + recruitmentAction.getRequirementId() + "</font></td></tr> ");
            if (recruitmentAction.getConsult_jobTitle() != null) {
                htmlText.append(" <tr><td>Job Title:</td><td align='left' ><font color='red' >" + recruitmentAction.getConsult_jobTitle() + "</font></td></tr>");
            }
            htmlText.append(" <tr><td>Position:</td><td align='left' ><font color='red' >" + recruitmentAction.getReqName() + "</font></td></tr> ");
            if (!"Online".equalsIgnoreCase(recruitmentAction.getInterviewType()) && !"Psychometric".equalsIgnoreCase(recruitmentAction.getInterviewType())) {
                //System.out.println("review type in if condition"+recruitmentAction.getInterviewType());    
                htmlText.append(" <tr><td valign='top' ><font >Review Date :</font></td><td align='left'><font color='red' >" + recruitmentAction.getInterviewDate() + "(" + recruitmentAction.getTimeZone() + ")" + "</font></td></tr>");


                if (recruitmentAction.getReviewTime() != null) {
                    htmlText.append(" <tr><td> Review Time   :</td>");
                    htmlText.append(" <td align='left'> <font color='red' >" + recruitmentAction.getTechReviewTime() + "</font></td></tr>");
                }

            }

            htmlText.append(" <br> <tr><td>Review Type:</td><td align='left' ><font color='red' >" + recruitmentAction.getInterviewType() + "</font></td></tr> ");


            if (recruitmentAction.getInterviewType().equalsIgnoreCase("Face to Face")) {
                htmlText.append(" <tr><td valign='top' ><font >Location :</font></td><td align='left'><font color='red' >" + recruitmentAction.getInterviewLocation() + "</font></td></tr>");
            }
            if (!"".equals(recruitmentAction.getContechNote())) {
                htmlText.append(" <br> <tr><td>Comments:</td><td align='left' ><font color='red' >" + recruitmentAction.getContechNote() + "</font></td></tr> ");
            }
            htmlText.append(" <tr><td></td></tr></table></td></tr>");
            htmlText.append(" <tr><td colspan='2'><br>Regards,</td></tr>");
            htmlText.append("<tr><td colspan='2' >Services Bay  Support Team,</td></tr> ");

            htmlText.append("<tr><td><font color='red', size='2' face='Arial' style='font-weight:600;'>*Note: &nbsp;Please do not reply to this e-mail. It was generated by our System.</font></td></tr> ");
            htmlText.append("</table></td></tr></table></body></html> ");

            String bodyContent = htmlText.toString();

            System.out.println(bodyContent);

            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, from);
            prepareStatement.setString(2, recruitmentAction.getVenEmail());
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
     * @timesheetApproveEmail() it generates email to user when reporting person approve or reject the timesheet
     *
     *
     * @Author:Jagan Chukkala<jchukkala@miraclesoft.com>
     *
     * @Created Date:12/31/2015
     *
     *
     **************************************
     *
     */
    public int timesheetApproveEmail(UsrTimeSheetAction userTimeSheetAction,String empname) throws ServiceLocatorException, SQLException {
        int addEmailResult = 0;
        String status="";
        try {
            String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
             String logo = com.mss.msp.util.Properties.getProperty("SERVICESBAYLOGO").toString();
              String timesheetsImage = com.mss.msp.util.Properties.getProperty("TIMESHEETIMAGE").toString();
              String dateImage = com.mss.msp.util.Properties.getProperty("DATEIMAGE").toString();
            connection = ConnectionProvider.getInstance().getConnection();
            if (userTimeSheetAction.getTempVar() == 1){
                status="Approved";
                }
            else
            {
                status="Rejected";
            }
            //String emailId=DataSourceDataProvider.getInstance().getEmailId(userTimeSheetAction.getUsr_id());
            StringBuilder htmlText = new StringBuilder();

            htmlText.append("<html>");
            htmlText.append("<head><title></title><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'> </head>");
            htmlText.append("<body>");
            htmlText.append("<table width='100%' cellpadding='0' cellspacing='0' border='0' bgcolor='#F6F9FB' style='width:100%; mso-cellspacing: 0px; mso-padding-alt:  0px 0px 0px 0px' >");
            htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#Fff' width='100%' style='width:100%;'>");
            htmlText.append("<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' style='width:600px; mso-cellspacing: 0px; mso-padding-alt:0px 0px 0px 0px;color: #fff'>");
            htmlText.append(" <tr style='height:40px'><td align='center' width='600' style='width:600px;background-color:#33BCF2'><a href=''><img src="+logo+" alt='loin' width='200' height='23'></a></td></tr>");
            htmlText.append("<tr>");
            htmlText.append("<td style='background-repeat: no-repeat;height: 140px;background-size: cover;background-position: right;background-color: #fff;background-image: url("+timesheetsImage+");'>");
            htmlText.append("<h1 style=' color: seashell; color: #fff; font-family: Roboto Slab,serif; font-size: 36px;font-weight: 300;line-height: 1.6em;text-transform: uppercase;padding-left: 356px'>Timesheets</h1><br/>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("<tr><td><table><tr><td></td></tr></table></td></tr>");
            htmlText.append("<tr>");
            htmlText.append("<td style='background-repeat: no-repeat;height: 140px;background-size: 100px 66px;background-position: right;background-color: #fff;background-image: url("+dateImage+");'>");
            htmlText.append("<table width='600' cellspacing='0' cellpadding='0' border='0' align='center' class='fluid'>");
            htmlText.append("<tr><td> </td> </tr></table></tr>");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<table>");
            htmlText.append("<tr style='height: 250px'>");
            htmlText.append("<td>");
            htmlText.append("<table style='font-style: italic; letter-spacing: 0.5px;color: #3f3f3f;border-top: 2px solid #438fc3;background-color: #f8f8f8;'>");
            htmlText.append("<tr>");
            htmlText.append("<td width='40'></td>");
            htmlText.append("<td>");
            //htmlText.append("<p>Hi, member</p>");
            htmlText.append("<font color='blue' size='2' style='font-family:Roboto Slab,serif'><p>Hello "+empname+"</p><p>Your Time Sheet has been "+status+".</p><p><u><b>Time Sheet Details:</b></u><br>Week Start Date: "+userTimeSheetAction.getTimeSheetStartDate()+"<br>Week End Date: "+userTimeSheetAction.getTimeSheetEndDate()+"<br>Thank you.</p></font><font color='red', size='2' face='Arial'>*Note: &nbsp;Please do not reply to this e-mail.  It was generated by our System.</font><br clear='both'>");
            //htmlText.append("<p> This is sent to your nominated email address .you may also view your account details here</p><br>");
            //htmlText.append("<a style='text-decoration: none;text-align: center; color: #5e5e5e; font-size: 20px; font-weight: 100; margin-bottom: 20px;' href='#'>click here</a>");
            htmlText.append("</td>");
            htmlText.append("<td width='40'></td>");
            htmlText.append("</tr></table></td></tr>");
            htmlText.append("<tr> <td><table><tr><td width='600' height='46' align='center'>");
            htmlText.append("<table cellspacing='0' cellpadding='20' border='0' bgcolor='#3f3f3' align='center' style='border-spacing:0;table-layout:auto;margin:0 auto'>");
            htmlText.append("<tbody> <tr> <td align='center' style='border-collapse:collapse'>");
            htmlText.append("<table cellspacing='0' cellpadding='0' border='0' bgcolor='#3f3f3f' align='center' style='border-spacing:0;table-layout:auto;margin:0 auto'>");
            htmlText.append("<tbody> <tr><td width='700' style='border-collapse:collapse'>");
            htmlText.append("<table width='520' cellspacing='0' cellpadding='0' border='0' bgcolor='#3f3f3f' align='center' style='border-collapse:collapse;border-spacing:0;table-layout:auto;margin:0 auto'>");
            htmlText.append("<tbody>");
            htmlText.append("<tr>");
            htmlText.append("<td align='left' style='color:#e8e8e8;font-size:20px;font-family:Calibri,sans-serif,'Open Sans';font-weight:500;line-height:22px;border-collapse:collapse'>About Us </td>");
            htmlText.append("</tr>");
            htmlText.append("<tr><td height='20' style='font-size:20px;line-height:20px;border-collapse:collapse'>&nbsp; </td>");
            htmlText.append("</tr><tr>");
            htmlText.append("<td align='left' style='color:#848484;font-size:16px;font-family:Calibri,sans-serif,'Open Sans';line-height:20px;border-collapse:collapse'>");
            htmlText.append("<b>ServicesBay</b> application you simplify customer relations and recruitment.");
            htmlText.append("</td></tr>");
            htmlText.append("<tr><td height=\"20\" style=\"font-size:20px;line-height:20px;border-collapse:collapse\">&nbsp;</td>");
            htmlText.append("</tr><tr>");
            htmlText.append("<td align='left' style='border-collapse:collapse'>");
            htmlText.append("<table cellspacing='0' cellpadding='0' border='0' align='left' style='border-spacing:0;table-layout:auto;margin:0 auto'>");
            htmlText.append("<tbody>");
            htmlText.append("<tr><td style='border-collapse:collapse'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
            htmlText.append("<td style='border-collapse:collapse'>");
            htmlText.append("<a class=\"btn btn-social btn-google-plus\" href=\"https://plus.google.com/+Team_MSS/\" target=\"_blank\" itemprop=\"sameAs\">");
            htmlText.append("<img width='13' height='13' border='0' class='CToWUd' style='display:block;width:13px;min-height:13px;overflow:hidden!important' src='googleplus.png' alt='google'></a>");
            htmlText.append("</td><td style='border-collapse:collapse'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>");
            htmlText.append("<td style='border-collapse:collapse'>");
            htmlText.append("<a class=\"btn btn-social btn-facebook \" href=\"https://www.facebook.com/miracle45625\" target=\"_blank\" itemprop=\"sameAs\">");
            htmlText.append("<img width='13' height='10' border='0' class='CToWUd' style='display:block;width:13px;min-height:10px;overflow:hidden!important' src='facebook.png' alt='facebook'></a>");
            htmlText.append("</td><td style='border-collapse:collapse'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
            htmlText.append("<td style='border-collapse:collapse'>");
            htmlText.append(" <a class=\"btn btn-social btn-twitter\" href=\"https://twitter.com/Team_MSS\" target=\"_blank\" itemprop=\"sameAs\">");
            htmlText.append("<img width='12' height='12' border='0' class='CToWUd' style='display:block;width:12px;min-height:12px;overflow:hidden!important' src='twitter.png' alt='linkden'></a>");
            htmlText.append("</td><td style='border-collapse:collapse'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
            htmlText.append("<td style='border-collapse:collapse'>");
            htmlText.append("<a class=\"btn btn-social btn-linkedin\" href=\"https://www.linkedin.com/company/miracle-software-systems-inc\" target=\"_blank\" itemprop=\"sameAs\">");
            htmlText.append("<img width='12' height='12' border='0' class='CToWUd' style='display:block;width:12px;min-height:12px;overflow:hidden!important' src='linkedIn.png' alt='pinterest'></a>");
            htmlText.append("</td></tr></tbody></table></td></tr></tbody></table>");
           
            htmlText.append("</body></html>");
            String bodyContent = htmlText.toString();
            //System.out.println(bodyContent);
            System.out.println("bodyContent length---->"+bodyContent.length());
            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, from);
            prepareStatement.setString(2, DataSourceDataProvider.getInstance().getEmailId(userTimeSheetAction.getUsr_id()));
            prepareStatement.setString(3, "TimeSheets");

            prepareStatement.setString(4, bodyContent);
            prepareStatement.setString(5, "pending");
            prepareStatement.setInt(6, userTimeSheetAction.getUserSessionId());
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
     * @timesheetAddEmail() it generates email to reporting person when user submit the timesheets
     *
     *
     * @Author:Jagan Chukkala<jchukkala@miraclesoft.com>
     *
     * @Created Date:12/31/2015
     *
     *
     **************************************
     *
     */
    public int timesheetAddEmail(UsrTimeSheetAction userTimeSheetAction,String empname,String reportiingPersonsEmail) throws ServiceLocatorException, SQLException {
        int addEmailResult = 0;
        String status="";
        try {
            String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
            connection = ConnectionProvider.getInstance().getConnection();
             String logo = com.mss.msp.util.Properties.getProperty("SERVICESBAYLOGO").toString();
              String timesheetsImage = com.mss.msp.util.Properties.getProperty("TIMESHEETIMAGE").toString();
              String dateImage = com.mss.msp.util.Properties.getProperty("DATEIMAGE").toString();
            //String emailId=DataSourceDataProvider.getInstance().getEmailId(userTimeSheetAction.getUsr_id());
            StringBuilder htmlText = new StringBuilder();

            htmlText.append("<html>");
            htmlText.append("<head><title></title><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'> </head>");
            htmlText.append("<body>");
            htmlText.append("<table width='100%' cellpadding='0' cellspacing='0' border='0' bgcolor='#F6F9FB' style='width:100%; mso-cellspacing: 0px; mso-padding-alt:  0px 0px 0px 0px' >");
            htmlText.append("<tr>");
            htmlText.append("<td bgcolor='#Fff' width='100%' style='width:100%;'>");
            htmlText.append("<table width='600' cellpadding='0' cellspacing='0' border='0' align='center' style='width:600px; mso-cellspacing: 0px; mso-padding-alt:0px 0px 0px 0px;color: #fff'>");
            htmlText.append(" <tr style='height:40px'><td align='center' width='600' style='width:600px;background-color:#33BCF2'><a href=''><img src="+logo+" alt='loin' width='200' height='23'></a></td></tr>");
            htmlText.append("<tr>");
            htmlText.append("<td style='background-repeat: no-repeat;height: 140px;background-size: cover;background-position: right;background-color: #fff;background-image: url("+timesheetsImage+");'>");
            htmlText.append("<h1 style=' color: seashell; color: #fff; font-family: Roboto Slab,serif; font-size: 36px;font-weight: 300;line-height: 1.6em;text-transform: uppercase;padding-left: 356px'>Timesheets</h1><br/>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("<tr><td><table><tr><td></td></tr></table></td></tr>");
            htmlText.append("<tr>");
            htmlText.append("<td style='background-repeat: no-repeat;height: 140px;background-size: 100px 66px;background-position: right;background-color: #fff;background-image: url("+dateImage+")'>");
            htmlText.append("<table width='600' cellspacing='0' cellpadding='0' border='0' align='center' class='fluid'>");
            htmlText.append("<tr><td> </td> </tr></table></tr>");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<table>");
            htmlText.append("<tr style='height: 250px'>");
            htmlText.append("<td>");
            htmlText.append("<table style='font-style: italic; letter-spacing: 0.5px;color: #3f3f3f;border-top: 2px solid #438fc3;background-color: #f8f8f8;'>");
            htmlText.append("<tr>");
            htmlText.append("<td width='40'></td>");
            htmlText.append("<td>");
            htmlText.append("<p style='color: blue' >Hi,</p>");
            htmlText.append("<font color='blue' size='2' style='font-family:Roboto Slab,serif'><p>"+empname+" Time Sheet has been Submitted.</p><p><u><b>Time Sheet Details:</b></u><br>Week Start Date: "+userTimeSheetAction.getTimeSheetStartDate()+"<br>Week End Date: "+userTimeSheetAction.getTimeSheetEndDate()+"<br>Thank you.</p></font><font color='red', size='2' face='Arial'>*Note: &nbsp;Please do not reply to this e-mail.  It was generated by our System.</font><br clear='both'>");
            //htmlText.append("<p> This is sent to your nominated email address .you may also view your account details here</p><br>");
            //htmlText.append("<a style='text-decoration: none;text-align: center; color: #5e5e5e; font-size: 20px; font-weight: 100; margin-bottom: 20px;' href='#'>click here</a>");
            htmlText.append("</td>");
            htmlText.append("<td width='40'></td>");
            htmlText.append("</tr></table></td></tr>");
            htmlText.append("<tr> <td><table><tr><td width='600' height='46' align='center'>");
            htmlText.append("<table cellspacing='0' cellpadding='20' border='0' bgcolor='#3f3f3' align='center' style='border-spacing:0;table-layout:auto;margin:0 auto'>");
            htmlText.append("<tbody> <tr> <td align='center' style='border-collapse:collapse'>");
            htmlText.append("<table cellspacing='0' cellpadding='0' border='0' bgcolor='#3f3f3f' align='center' style='border-spacing:0;table-layout:auto;margin:0 auto'>");
            htmlText.append("<tbody> <tr><td width='700' style='border-collapse:collapse'>");
            htmlText.append("<table width='520' cellspacing='0' cellpadding='0' border='0' bgcolor='#3f3f3f' align='center' style='border-collapse:collapse;border-spacing:0;table-layout:auto;margin:0 auto'>");
            htmlText.append("<tbody>");
            htmlText.append("<tr>");
            htmlText.append("<td align='left' style='color:#e8e8e8;font-size:20px;font-family:Calibri,sans-serif,'Open Sans';font-weight:500;line-height:22px;border-collapse:collapse'>About Us </td>");
            htmlText.append("</tr>");
            htmlText.append("<tr><td height='20' style='font-size:20px;line-height:20px;border-collapse:collapse'>&nbsp; </td>");
            htmlText.append("</tr><tr>");
            htmlText.append("<td align='left' style='color:#848484;font-size:16px;font-family:Calibri,sans-serif,'Open Sans';line-height:20px;border-collapse:collapse'>");
            htmlText.append("<b>ServicesBay</b> application you simplify customer relations and recruitment.");
            htmlText.append("</td></tr>");
            htmlText.append("<tr><td height=\"20\" style=\"font-size:20px;line-height:20px;border-collapse:collapse\">&nbsp;</td>");
            htmlText.append("</tr><tr>");
            htmlText.append("<td align='left' style='border-collapse:collapse'>");
            htmlText.append("<table cellspacing='0' cellpadding='0' border='0' align='left' style='border-spacing:0;table-layout:auto;margin:0 auto'>");
            htmlText.append("<tbody>");
            htmlText.append("<tr><td style='border-collapse:collapse'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
            htmlText.append("<td style='border-collapse:collapse'>");
            htmlText.append("<a class=\"btn btn-social btn-google-plus\" href=\"https://plus.google.com/+Team_MSS/\" target=\"_blank\" itemprop=\"sameAs\">");
            htmlText.append("<img width='13' height='13' border='0' class='CToWUd' style='display:block;width:13px;min-height:13px;overflow:hidden!important' src='googleplus.png' alt='google'></a>");
            htmlText.append("</td><td style='border-collapse:collapse'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>");
            htmlText.append("<td style='border-collapse:collapse'>");
            htmlText.append("<a class=\"btn btn-social btn-facebook \" href=\"https://www.facebook.com/miracle45625\" target=\"_blank\" itemprop=\"sameAs\">");
            htmlText.append("<img width='13' height='10' border='0' class='CToWUd' style='display:block;width:13px;min-height:10px;overflow:hidden!important' src='facebook.png' alt='facebook'></a>");
            htmlText.append("</td><td style='border-collapse:collapse'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
            htmlText.append("<td style='border-collapse:collapse'>");
            htmlText.append(" <a class=\"btn btn-social btn-twitter\" href=\"https://twitter.com/Team_MSS\" target=\"_blank\" itemprop=\"sameAs\">");
            htmlText.append("<img width='12' height='12' border='0' class='CToWUd' style='display:block;width:12px;min-height:12px;overflow:hidden!important' src='twitter.png' alt='linkden'></a>");
            htmlText.append("</td><td style='border-collapse:collapse'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
            htmlText.append("<td style='border-collapse:collapse'>");
            htmlText.append("<a href='http://emailonacid.us1.list-manage.com/track/click?u=b6418bd90c647b0ab5f294717&amp;id=3068995fd5&amp;e=604b7c5692' style='display:block;width:12px;min-height:12px;color:#3498db;border:0px!important;text-decoration:none' target='_blank'><a class=\"btn btn-social btn-linkedin\" href=\"https://www.linkedin.com/company/miracle-software-systems-inc\" target=\"_blank\" itemprop=\"sameAs\">");
            htmlText.append("<img width='12' height='12' border='0' class='CToWUd' style='display:block;width:12px;min-height:12px;overflow:hidden!important' src='linkedIn.png' alt='pinterest'></a>");
            htmlText.append("</td></tr></tbody></table></td></tr></tbody></table>");
           
            htmlText.append("</body></html>");
            String bodyContent = htmlText.toString();
            //System.out.println(bodyContent);
            System.out.println("bodyContent length---->"+bodyContent.length());
            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, from);
            prepareStatement.setString(2, StringUtils.chop(reportiingPersonsEmail));
            prepareStatement.setString(3, "TimeSheets");

            prepareStatement.setString(4, bodyContent);
            prepareStatement.setString(5, "pending");
            prepareStatement.setInt(6, userTimeSheetAction.getUserSessionId());
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
     * @sendInvitation() to send mail to recruitment member with PO attachment
     *
     * @Author:jagan chukkala<jchukkala@miraclesoft.com>
     *
     * @Created Date:08/Jan/2016
     *
     *
     **************************************
     *
     */
    public static void sendPOStatement(UserAjaxHandlerAction userAjaxHandlerAction, String attach, String file,String fileName,String customerName) throws Exception {

        System.out.println("In sendPOStatement()");
        String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
        String fileAttachment = attach;
        int mailFlag = Integer.parseInt(com.mss.msp.util.Properties.getProperty("Mail.Flag"));
//        System.out.println("userid,--->"+userId);
//        System.out.println("username,--->"+userName);
//        System.out.println("startdate--->"+startDate);
//        System.out.println("enddate--->"+endDate);
//        System.out.println("logo--->"+accLogo);
//        System.out.println("accname--->"+accName);
        try {
            if (mailFlag == 1) {
//                MimetypesFileTypeMap mimetypes = (MimetypesFileTypeMap) MimetypesFileTypeMap.getDefaultFileTypeMap();
//                mimetypes.addMimeTypes("text/calendar ics ICS");
//
//                //register the handling of text/calendar mime type
//                MailcapCommandMap mailcap = (MailcapCommandMap) MailcapCommandMap.getDefaultCommandMap();
//                mailcap.addMailcap("text/calendar;; x-java-content-handler=com.sun.mail.handlers.text_plain");


                Properties props = new Properties();

                props.setProperty("mail.transport.protocol", "smtp");
                props.setProperty("mail.host", SMTP_HOST);

                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", SMTP_PORT);
                Authenticator auth = new SMTPAuthenticator();
                //Session mailSession = Session.getDefaultInstance(props, null);
                Session mailSession = Session.getDefaultInstance(props, auth);

                MimeMessage message = new MimeMessage(mailSession);
                message.setFrom(new InternetAddress(from));
                message.setSubject("ServicesBay Notification : Purchase Order");

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(userAjaxHandlerAction.getEmailId()));
                MimeBodyPart messageBodyPart =
                        new MimeBodyPart();
                Multipart multipart = new MimeMultipart("related");
                StringBuilder htmlText = new StringBuilder();
                htmlText.append("<table width='650' align='center' cellspacing='0' cellpadding='0' border='0' style='width: 487.5pt;' class='MsoNormalTable'>");
                htmlText.append("<tbody style=''><tr style=''><td valign='top' style='padding: 0in;'><p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>");
                htmlText.append("</span></p></td></tr>");
               
                
                htmlText.append("<tr style='background-color:lightgray'><td style='text-align: center;'> <img width='300' height='94' border='0' dfsrc='http://devint.1ct.es/343/images/mailer-header.png' id='_x0000_i1025' style='' src='cid:image' > </td></tr>");
                
                
                htmlText.append("<tr id='SaluationSection' style=''><td valign='top' style='padding: 0in;'>");
                htmlText.append("<table width='100%' cellspacing='0' cellpadding='0' border='0' style='width: 100%;' class='MsoNormalTable'>");
                htmlText.append("<tbody style=''><tr style=''><td valign='top' style='padding: 0in;'><div style=''><table width='100%' cellspacing='0' cellpadding='0' border='0' style='width: 100%;' class='MsoNormalTable'>");
                htmlText.append("<tbody style=''><tr style=''><td valign='top' style='padding: 0in;'><div align='center' style=''>");
                htmlText.append("<table width='650' cellspacing='0' cellpadding='0' border='0' style='width: 487.5pt;' class='MsoNormalTable'><tbody style=''>");
                htmlText.append("<tr style=''><td valign='top' style='padding: 0in;'><table width='100%' cellspacing='0' cellpadding='0' border='0' style='width: 100%;' class='MsoNormalTable'>");
                htmlText.append("<tbody style=''><tr style=''><td valign='top' style='padding: 0in;'><div style=''><table width='100%' cellspacing='0' cellpadding='0' border='0' style='width: 100%;' class='MsoNormalTable'>");
                htmlText.append("<tbody style=''><tr style=''><td valign='top' style='padding: 11.25pt 15pt; background: rgb(240, 252, 255) none repeat scroll 0% 0%;'><h2 style=''>");
                htmlText.append("Dear<span style='font-family: &quot;segoe ui&quot;,sans-serif;'> "+userAjaxHandlerAction.getAccountName()+",</span></h2></td></tr><tr id='RequestDetails' style=''><td valign='top' style='padding: 0in;'>");

                htmlText.append("<p style='margin-left: 11.25pt;'><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(79, 79, 79);'>Please find attached approved Purchase Order for <b> "+userAjaxHandlerAction.getUserName()+"</b> (Candidate ID - "+userAjaxHandlerAction.getUserId()+") </span>");
                htmlText.append("</p></td></tr><tr id='RequestInformation' style=''><td valign='top' style='padding: 11.25pt 15pt;'>");
                htmlText.append("<p style='margin-left: 1.5pt;'><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(79, 79, 79);'>Following are the details of the Purchase Order for your reference: </span></p>");
                htmlText.append("<table width='100%' cellspacing='0' cellpadding='0' border='1' style='width: 100%; border-collapse: collapse; border: medium none;' class='MsoNormalTable'>");
                htmlText.append("<tbody style=''><tr style=''><td valign='top' style='border: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>PO Number </span>");
                htmlText.append("</p></td><td valign='top' style='border-style: solid solid solid none; border-top: 1pt solid rgb(120, 204, 245); border-right: 1pt solid rgb(120, 204, 245); border-bottom: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>"+userAjaxHandlerAction.getSowId()+"</span></p>");
                htmlText.append("</td></tr><tr style=''><td valign='top' style='border-style: none solid solid; border-right: 1pt solid rgb(120, 204, 245); border-bottom: 1pt solid rgb(120, 204, 245); border-left: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>Subcontractor</span></p>");
                htmlText.append("</td><td valign='top' style='border-style: none solid solid none; border-bottom: 1pt solid rgb(120, 204, 245); border-right: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>"+userAjaxHandlerAction.getUserName()+" (Candidate ID - "+userAjaxHandlerAction.getUserId()+")</span></p>");
                htmlText.append("</td></tr><tr style=''><td valign='top' style='border-style: none solid solid; border-right: 1pt solid rgb(120, 204, 245); border-bottom: 1pt solid rgb(120, 204, 245); border-left: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>PO Start Date</span></p>");
                htmlText.append("</td><td valign='top' style='border-style: none solid solid none; border-bottom: 1pt solid rgb(120, 204, 245); border-right: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>");
                htmlText.append("<span id='OBJ_PREFIX_DWT132_com_zimbra_date' role='link' class='Object'>"+userAjaxHandlerAction.getPoStartDate()+"</span></span></p></td></tr>");
                htmlText.append("<tr style=''><td valign='top' style='border-style: none solid solid; border-right: 1pt solid rgb(120, 204, 245); border-bottom: 1pt solid rgb(120, 204, 245); border-left: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>PO End Date </span></p>");
                htmlText.append("</td><td valign='top' style='border-style: none solid solid none; border-bottom: 1pt solid rgb(120, 204, 245); border-right: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'><p class='MsoNormal' style=''>");
                htmlText.append("<span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'><span id='OBJ_PREFIX_DWT133_com_zimbra_date' role='link' class='Object'>"+userAjaxHandlerAction.getPoEndDate()+"</span>");
                htmlText.append("</span></p></td></tr><tr style=''><td valign='top' style='border-style: none solid solid; border-right: 1pt solid rgb(120, 204, 245); border-bottom: 1pt solid rgb(120, 204, 245); border-left: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>Recruiter Mail ID</span>");
                htmlText.append("</p></td><td valign='top' style='border-style: none solid solid none; border-bottom: 1pt solid rgb(120, 204, 245); border-right: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>"+userAjaxHandlerAction.getEmailId()+"");
                htmlText.append("</span></p></td></tr></tbody></table></td></tr><tr style=''><td valign='top' style='padding: 3.75pt 397.5pt 3.75pt 0in;'>");

                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(79, 79, 79);'>&nbsp;&nbsp;&nbsp;&nbsp;Rate Details:</span></p>");
                htmlText.append("</td></tr><tr style=''>");
                htmlText.append("<td valign='top' style='padding: 0in;' colspan='2'><div align='center' style=''>");
                htmlText.append("<table width='100%' cellspacing='0' cellpadding='0' border='1' style='width: 94%; border-collapse: collapse; border: medium none;' class='MsoNormalTable'>");
                htmlText.append("<tbody style=''><tr style=''><td valign='top' style='border: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'><p class='MsoNormal' style=''>");

                htmlText.append("<span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>Base Rate</span></p>");
                htmlText.append("</td><td valign='top' style='border-style: solid solid solid none; border-top: 1pt solid rgb(120, 204, 245); border-right: 1pt solid rgb(120, 204, 245); border-bottom: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                htmlText.append("<p class='MsoNormal' style=''>");
                htmlText.append("<span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>Overtime Rate</span></p>");
//                htmlText.append("</td><td valign='top' style='border-style: solid solid solid none; border-top: 1pt solid rgb(120, 204, 245); border-right: 1pt solid rgb(120, 204, 245); border-bottom: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
//                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>T&amp;E Rate</span>");
                htmlText.append("</p></td><td valign='top' style='border-style: solid solid solid none; border-top: 1pt solid rgb(120, 204, 245); border-right: 1pt solid rgb(120, 204, 245); border-bottom: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>Overtime Limit</span></p>");
                htmlText.append("</td></tr><tr style=''><td valign='top' style='border-style: none solid solid; border-right: 1pt solid rgb(120, 204, 245); border-bottom: 1pt solid rgb(120, 204, 245); border-left: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>"+userAjaxHandlerAction.getBaseRate()+"</span></p>");
                htmlText.append("</td><td valign='top' style='border-style: none solid solid none; border-bottom: 1pt solid rgb(120, 204, 245); border-right: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                //htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>"+userAjaxHandlerAction.getBaseRate()+"</span></p>");
                //htmlText.append("</td><td valign='top' style='border-style: none solid solid none; border-bottom: 1pt solid rgb(120, 204, 245); border-right: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>"+userAjaxHandlerAction.getOverTimeRate()+"</span></p>");
                htmlText.append("</td><td valign='top' style='border-style: none solid solid none; border-bottom: 1pt solid rgb(120, 204, 245); border-right: 1pt solid rgb(120, 204, 245); padding: 3.75pt;'>");
                htmlText.append("<p class='MsoNormal' style=''><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>"+userAjaxHandlerAction.getOverTimeLimit()+"</span></p>");
                htmlText.append("</td></tr></tbody></table></div></td></tr>");
                htmlText.append("<tr style='height: 11.25pt;'><td valign='top' style='padding: 0in; height: 11.25pt;'></td><td style='padding: 0in; height: 11.25pt;'></td>");
                htmlText.append("</tr><tr id='RequestOtherDetails' style=''><td valign='top' style='padding: 0in;'>");
                //htmlText.append("<p style='margin-left: 15pt;'><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(79, 79, 79);'>Please Note: T&amp;E would be processed through ASPEN system upon invoice being raised and uploaded in ASPEN. </span></p>");
                htmlText.append("</td><td style='padding: 0in;'></td></tr><tr style='height: 11.25pt;'><td valign='top' style='padding: 0in; height: 11.25pt;'>");
                htmlText.append("</td><td style='padding: 0in; height: 11.25pt;'></td></tr><tr id='RequestOtherDetails1' style=''><td valign='top' style='padding: 0in;'><p style='margin-left: 15pt;'>");
                htmlText.append("<span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;sans-serif; color: rgb(79, 79, 79);>Incase of any discrepancy in PO, please write to the recruiter you are co-ordinating with, within the next 7 days. Please CC <span id='OBJ_PREFIX_DWT135_ZmEmailObjectHandler' role='link' class='Object'>");

                htmlText.append("</span> </span></p></td><td style='padding: 0in;'></td></tr><tr style='height: 11.25pt;'>");
                htmlText.append("<td valign='top' style='padding: 0in; height: 11.25pt;'></td><td style='padding: 0in; height: 11.25pt;'></td></tr>");
                htmlText.append("<tr id='ApprovalMethods' style=''><td valign='top' style='padding: 0in;'></td><td style='padding: 0in;'></td></tr>");
                htmlText.append("<tr id='twoWayReplyButtons' style=''><td valign='top' style='padding: 0in;'></td><td style='padding: 0in;'></td></tr>");
                htmlText.append("<tr id='valedictionSection' style=''><td valign='top' style='padding: 0in;'>");
                htmlText.append("<p style='margin-left: 15pt;'><span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(79, 79, 79);'>Regards, <br style=''>ServicesBay Team </span></p>");

                htmlText.append("</td><td style='padding: 0in;'></td></tr></tbody>");
                htmlText.append("</table></div></td></tr></tbody>");
                htmlText.append("</table></td></tr></tbody></table><br></div></td></tr></tbody></table></div></td></tr></tbody></table></td></tr>");
                htmlText.append("<tr id='AutoGeneratedMailOneWay' style=''><td valign='top' style='padding: 11.25pt 15pt; background: rgb(247, 247, 247) none repeat scroll 0% 0%;'><p style=''>");
                htmlText.append("<span style='font-size: 9pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(62, 76, 81);'>** This is an Auto Generated Mail. Please Do not reply to this mail**</span>");
                htmlText.append("</p></td></tr><tr id='footerImage' style=''>");
                htmlText.append("<tr id='footerImage' width='100%' style='background-color:lightgray'>");
                htmlText.append("<td><table><tr><td style='border-right:1px solid #3c3c3c'><img src='cid:image' style='width:300px;height:80px' /></td><td ><b style='text-align:center' >"+customerName+"</b></td></tr></table></td>");
 
                
                
//                htmlText.append("<td valign='top' style='padding: 0in;'><p class='MsoNormal' style=''>");
//                htmlText.append("<span style='font-size: 10.5pt; font-family: &quot;segoe ui&quot;,sans-serif; color: rgb(41, 56, 62);'>");
//                htmlText.append("<img width='300' height='75' border='0'  style='' src=\"cid:image\" />"+userAjaxHandlerAction.getAccountName()+"</span>");
//                htmlText.append("</p></td>"
//                        + ""
                        htmlText.append("</tr></tbody></table>");

                
                
                String bodyContent = htmlText.toString();
               // System.out.println(bodyContent);
//                StringBuffer body
//            = new StringBuffer("<html>This message contains two inline images.<br>");
                //String htmlText = "<html><H1 style='color: red'>Hello</H1><img src=\"cid:image\"></img></html>";
               messageBodyPart.setContent(bodyContent, "text/html; charset=utf-8");
               messageBodyPart.setDisposition(MimeBodyPart.INLINE);
              
//                messageBodyPart.setContent(bodyContent
//, "text/html");
                // inline images
//        Map<String, String> inlineImages = new HashMap<String, String>();
//        inlineImages.put("image1", attach);
                //inlineImages.put("image2", "E:/Test/cube.jpg");

                //messageBodyPart.setText(htmlText);
// Create an alternative Multipart

                multipart.addBodyPart(messageBodyPart);
//part 1, html text
//                BodyPart messageBodyPart;
//                messageBodyPart = buildHtmlTextPart(recruitmentAction, emapMailId);
//                multipart.addBodyPart(messageBodyPart);

// Add part two, the calendar
//                BodyPart calendarPart = buildCalendarPart(recruitmentAction, emapMailId, from);
//                multipart.addBodyPart(calendarPart);

//Put the multipart in message 
                MimeBodyPart messageBodyPart1 =
                        new MimeBodyPart();
                //messageBodyPart = new MimeBodyPart();
                DataSource source =
                        new FileDataSource(fileAttachment);
//                messageBodyPart.setDataHandler(
//                        new DataHandler(source));
//                messageBodyPart.setFileName(fileAttachment);
//                multipart.addBodyPart(messageBodyPart);

                messageBodyPart1.setDataHandler(new DataHandler(source));
               messageBodyPart1.setHeader("Content-Type", "image/jpeg");
                
                messageBodyPart1.setHeader("Content-ID", "<image>");
               
                multipart.addBodyPart(messageBodyPart1);
                MimeBodyPart messageBodyPart2 =
                        new MimeBodyPart();
                DataSource sources =
                        new FileDataSource(file);
                messageBodyPart2.setDataHandler(new DataHandler(sources));
                messageBodyPart2.setFileName(fileName);
                multipart.addBodyPart(messageBodyPart2);
                // Put parts in message

                message.setContent(multipart);

                // send the message
                Transport transport = mailSession.getTransport("smtp");
                transport.connect();
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            }

        } catch (NoSuchProviderException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    public int requirementStatusChangeMailGenerator(RequirementAction requirementAction, String MailIds, int userId) throws ServiceLocatorException, SQLException {
        int addEmailResult = 0;
        try {
            String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
            connection = ConnectionProvider.getInstance().getConnection();


            StringBuilder htmlText = new StringBuilder();

            htmlText.append("<html>");
            htmlText.append("<body>");
            htmlText.append("<table align='center'>");

            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<td>");
            htmlText.append("<font color='white' size='4' face='Arial'>");
            htmlText.append("<p>Requirement Status Change Notification</p>");
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
            htmlText.append(" Requirement status has been Changed.From Now You can add Consultant<b><font color='#6666FF'>" );
            htmlText.append("</font></b></td></tr>");

            htmlText.append("<tr><td> <table border='0' align='left'> <tr><td ><font> ");
            htmlText.append(" Requirement Details :</font></td>");
            htmlText.append("</tr>");

            htmlText.append("<br> <tr><td>");
            htmlText.append("Name :</td><td align='left' >");
            htmlText.append("<font color='red' >" + requirementAction.getRequirementName() + "</font></td></tr> ");

            htmlText.append(" <tr><td>  ");
            htmlText.append("No.Of Resources :</td><td align='left' >");
            htmlText.append("<font color='red' >" + requirementAction.getRequirementNoofResources() + "</font></td></tr> ");

            htmlText.append("<tr><td>");
            htmlText.append("Start Date :</td><td align='left' >");
            htmlText.append("<font color='red' >" + requirementAction.getRequirementFrom() + "</font></td></tr> ");

//            htmlText.append("<tr><td valign='top' ><font > ");
//            htmlText.append("End Date :</font></td><td align='left'> ");
//            htmlText.append(" <font color='red' >" + requirementVTO.getReqEndDate() + "</font></td></tr> ");

            htmlText.append(" <tr><td> ");
            htmlText.append("Description   :</font></td><td align='left' >");
            htmlText.append(" <font color='red' >" + requirementAction.getRequirementJobdesc() + "</font></td></tr> ");
            
            

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
            htmlText.append("<font color='red', size='2' face='Arial' style='font-weight:600;'>*Note: &nbsp;Please do not reply to this e-mail. It was generated by our System.</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</body>");
            htmlText.append("</html>");

            String bodyContent = htmlText.toString();

            System.out.println(bodyContent);

            String sqlQuery = "INSERT INTO email_logger(from_adress,to_address,subjectcontent,bodycontent,email_status,created_by) VALUES(?,?,?,?,?,?)";
            prepareStatement = connection.prepareStatement(sqlQuery);
            prepareStatement.setString(1, from);
            prepareStatement.setString(2, MailIds);
            prepareStatement.setString(3, "Requirement Status "
                    + "Change Notification");

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
     * *****************************************************************************
     * Date : 03/26/2016
     *
     * Author : Manikanta<meeralla@miraclesoft.com>
     *
     * ForUse : sendPendingInvMail() method is used to send Pending Invoice Mail
     * 
     *
     * *****************************************************************************
     */
    public int sendPendingInvMail(InvoiceAjaxAction invoiceAjaxAction,String venInvCreatedMailId) throws ServiceLocatorException, SQLException {
        int status=0;
        System.out.println("********************MailManager :: sendPendingInvMail Method Start*********************");
        try {
            String from = com.mss.msp.util.Properties.getProperty("MSB.from").toString();
            
            StringBuilder htmlText = new StringBuilder();

            htmlText.append("<html>");
            htmlText.append("<body>");
            htmlText.append("<table align='center'>");

            htmlText.append("<tr style='background:#33CCFF;height:40px;width:100%;'>");
            htmlText.append("<td>");
            htmlText.append("<font color='white' size='4' face='Arial'>");
            htmlText.append("<p>Notification on Pending payments</p>");
            htmlText.append("</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");

            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<table style='background:#FFFFCC;width:100%;' color='#151B54' size='4px' height='20px' >");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<font color='#3399FF' size='2' face='Arial' style='font-weight:600;'>");
            htmlText.append("<br>Hello Vendor,  ");
            htmlText.append("</font><br></td></tr>");

          
            htmlText.append(" <tr>  ");
            htmlText.append("<td align='left' >");
            htmlText.append("<font color='' >" + invoiceAjaxAction.getInvEmailBodyContent() + "</font></td></tr> ");

          

            htmlText.append(" <tr><td> ");
            htmlText.append("</td></tr>  </table> </td></tr> <tr><td colspan='2' >");
            htmlText.append("<br>Regards,");
            htmlText.append("</td></tr><tr><td colspan='2' >");
            htmlText.append("Services Bay ");
            htmlText.append(" Support Team,</td></tr><tr>");
            htmlText.append("<td colspan='2' > ");
            htmlText.append("<tr>");
            htmlText.append("<td>");
            htmlText.append("<font color='red', size='2' face='Arial' style='font-weight:600;'>*Note:&nbsp;Please do not reply to this e-mail. It was generated by our System.</font>");
            htmlText.append("</td>");
            htmlText.append("</tr>");
            htmlText.append("</table>");
            htmlText.append("</body>");
            htmlText.append("</html>");

            htmlText.toString();
            status = doaddemailLog( from,venInvCreatedMailId,"","",invoiceAjaxAction.getInvEmailSubject(),htmlText.toString(),invoiceAjaxAction.getUsrSessionId());
            
         
         
        } catch (Exception E) {
            E.printStackTrace();
        }
        System.out.println("********************MailManager :: sendPendingInvMail Method End*********************");
        return status;
    }
}
