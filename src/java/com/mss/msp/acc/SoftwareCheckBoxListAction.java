
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc;

/**
 *
 * @author trual_000
 */
import com.mss.msp.util.ConnectionProvider;
import static com.opensymphony.xwork2.Action.ERROR;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

public class SoftwareCheckBoxListAction extends ActionSupport implements Preparable{

        private List<String> softwares;

	private String yourSoftware;

        //in the jsp this value is capitalized... i have no idea why logic was not had
        //list of active softwares
        private List<String> defaultSoftware;

	//List of accounts associated with logged in account
        private List<String> accountIdList;


        //setup variables for first check list
        private List<String> software0;
        private String yourSoftware0;

        //Set up variables for second check list
        private List<String> software1;
        private String yourSoftware1;

        //set up variables for third check list
        private List<String> software2;
        private String yourSoftware2;

        private int accountId;

	public SoftwareCheckBoxListAction(){


	}


        public List<String> getDefaultSofwares(){
    try {
      prepare();
    } catch (Exception ex) {
      Logger.getLogger(SoftwareCheckBoxListAction.class.getName()).log(Level.SEVERE, null, ex);
    }
		return defaultSoftware;
	}



	public String execute() {
            System.out.println("execute menu action");
            System.out.println("Software: " + yourSoftware);
            /// I need to save the yoursoftware string to the database
		return SUCCESS;
	}

	public String display() {
            System.out.println("execute display action");
		return NONE;
	}



     /**
     * @param softwares the softwares to set
     */
    public void setSoftwares(List<String> softwares) {
        this.softwares = softwares;
    }

      /**
     * @return the softwares
     */
    public List<String> getSoftwares() {
        return softwares;
    }


    /**
     * @return the yourSoftware
     */
    public String getYourSoftware() {
        return yourSoftware;
    }

      /**
     * @param yourSoftware the yourSoftware to set
     */
    public void setYourSoftware(String yourSoftware) {
        this.yourSoftware = yourSoftware;
    }

    public void prepare() throws Exception {

         /* Account Id is static for now because we don't have a confirmed way
        of retrieving this information*/

        /* Need to query the available softwares */
	softwares = new ArrayList<String>();

        //initial connection stuff
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String info;

        try{
            System.out.println("In try");
            connection = ConnectionProvider.getInstance().getConnection();
             System.out.println("Connection established"+connection);
            statement = connection.createStatement();

            //retrieve account ids from database
            String SQL_QUERY= "SELECT related_org_id FROM servicebay.org_rel where org_id = '" +
                    accountId + "';";
            System.out.println("SQL_QUERY-->"+SQL_QUERY);
            resultSet = statement.executeQuery(SQL_QUERY);
            // store the results in a list
            accountIdList = new ArrayList<String>();

            while (resultSet.next()){

                accountIdList.add(Integer.toString(resultSet.getInt(1)));
            }

            ///  should have a list of the related accounts

                 SQL_QUERY = "SELECT lk_acc_software.software, acc_softwares.status \n" +
                    "From servicebay.acc_softwares\n" +
                    "Left Join servicebay.lk_acc_software\n" +
                    "on acc_softwares.software_id = lk_acc_software.id \n" +
                    "where acc_softwares.acc_id = '" + accountId + "'";
                 //if there are more accounts asociated with main account
                 if (accountIdList.size() > 1){
                     for (int i = 1; i < accountIdList.size(); i++){

                        SQL_QUERY += " OR acc_softwares.acc_id = '" + accountIdList.get(i) + "'";
                     }
                 }


                System.out.println("SQL_QUERY-->"+SQL_QUERY);
                resultSet = statement.executeQuery(SQL_QUERY);
                ResultSetMetaData metadata = resultSet.getMetaData();
                int numberOfColumns = metadata.getColumnCount();

                System.out.println(numberOfColumns);
                //List<String> defaultSoftwareList = new ArrayList<String>();
                defaultSoftware = new ArrayList<String>();
                System.out.println("1002");
                 while(resultSet.next()) {


                     String currentSoftware = resultSet.getString(1);
                   //check if currentSoftware is already in software list
                     if (inList(currentSoftware, softwares)) {
                         //is already in the list
                         if (resultSet.getString(2).equals("active")) {
                             //then is active and add to defualt string
                             defaultSoftware.add(currentSoftware);
                         }
                         continue;
                     }


                    // System.out.println(resultSet.getString(1));
                     softwares.add(currentSoftware);
                     // need to check status if status is a
                     //     then add it to the default list
                    if (resultSet.getString(2).equals("active")){
                        //then is active and add to defualt string
                        defaultSoftware.add(currentSoftware);
                    }


                 }
                 //software in the default list which is not in the software list
                 //does not affect the program



                 software0 = new ArrayList<String>();
                 software1 = new ArrayList<String>();
                 software2 = new ArrayList<String>();
                 // here i can divide software in to three list and use these
                 //three list to make three seperate checkbox list;
                 for (int i = 0; i < softwares.size(); i++){
                     if ((i %3 )== 0){
                         software2.add(softwares.get(i));
                     }
                     else if ((i % 2)== 0){
                         software1.add(softwares.get(i));
                     }
                     else {
                         software0.add(softwares.get(i));
                     }
                 }




        }
                 catch(Exception ex){


            System.out.println("Exception-->"+ex.getMessage());

        }finally{
            try{
                System.out.println("Closure");
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!=null){
                    statement.close();
                    statement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection=null;
                }
            }catch(SQLException sqle){
                throw new Exception(sqle);
            }
        }



	}

    //this method is responsible for saving the the software to the database
    //without refreshing the page
    public String saveToDB() throws Exception{
        System.out.println("in save to DB");
        System.out.println("Account Id: "+this.accountId);
        System.out.println("yourSoftware: " + yourSoftware);
        System.out.println("yourSoftware0: " + yourSoftware0);
        System.out.println("your Software1: "+ yourSoftware1);
        System.out.println("your Software2: "+ yourSoftware2);

        // combine yourSoftwares012 into a single yoursoftware
        yourSoftware = yourSoftware0 + ", " + yourSoftware1 + ", " + yourSoftware2;

        /* Account Id is static for now because we don't have a confirmed way
        of retrieving this information*/

        // need to open db connection
         //initial connection stuff
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        //other connect stuff

         try{
            System.out.println("In try of save to db");
            connection = ConnectionProvider.getInstance().getConnection();
             System.out.println("Connection established"+connection);
            statement = connection.createStatement();

            // this first query is going to set all status to inactive
            String SQL_QUERY = "UPDATE `servicebay`.`acc_softwares` SET `Status`='in-active' WHERE `acc_id`='"
                    + accountId + "'";
                 if (accountIdList.size() > 1){
                     for (int i = 1; i < accountIdList.size(); i++){

                        SQL_QUERY += " OR acc_softwares.acc_id = '" + accountIdList.get(i) + "'";
                     }
                 }


            System.out.println("SQL_QUERY-->"+SQL_QUERY);
            statement.executeUpdate(SQL_QUERY);

            // next query will set all yourSoftware statuses to active


            /// check to make sure list is not empty
            //therefore items are checked
            if (yourSoftware.length() > 1){
            //convert yoursoftware csv to arraylist
                List<String> yourSoftwareList = Arrays.asList(yourSoftware.split("\\s*,\\s*"));
                /*************  oooooooooopssss neeed the software ID not the software name *****/
                //set up next query
                SQL_QUERY = "UPDATE `servicebay`.`acc_softwares` SET `Status`='active' WHERE " +
                        "software_id IN (SELECT id FROM servicebay.lk_acc_software " +
                        "where (software='" + yourSoftwareList.get(0) + "'";
                //this for loop inserts in all software
                for ( int i = 1; i< yourSoftwareList.size(); i++) {
                        System.out.println("'"+yourSoftwareList.get(i)+"'");
                        SQL_QUERY += " OR software='" + yourSoftwareList.get(i) + "'";
                }
                SQL_QUERY += ")) AND ( `acc_id`='" + accountId + "'";
                 if (accountIdList.size() > 1){
                     for (int i = 1; i < accountIdList.size(); i++){

                        SQL_QUERY += " OR acc_softwares.acc_id = '" + accountIdList.get(i) + "'";
                     }
                 }
                 SQL_QUERY += ")";

                System.out.println(SQL_QUERY);
                statement.executeUpdate(SQL_QUERY);
            }

        }
            catch(Exception ex){


            System.out.println("Exception-->"+ex.getMessage());

        }finally{
            try{
                System.out.println("Closure in save to db");
                if(resultSet!=null){
                    resultSet.close();
                    resultSet = null;
                }
                if(statement!=null){
                    statement.close();
                    statement = null;
                }
                if(connection!=null){
                    connection.close();
                    connection=null;
                }
            }catch(SQLException sqle){
                throw new Exception(sqle);
            }
        }
        // need to change all status to in-active

        // set yoursoftware statuses to active

        return SUCCESS;
    }

    //function returns true if string is in list
    public boolean inList(String search, List<String> myList) {
        for (String str : myList) {
            if (str.trim().contains(search)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the software1
     */
    public List<String> getSoftware1() {
        return software1;
    }

    /**
     * @param software1 the software1 to set
     */
    public void setSoftware1(List<String> software1) {
        this.software1 = software1;
    }

    /**
     * @return the yourSoftware1
     */
    public String getYourSoftware1() {
        return yourSoftware1;
    }

    /**
     * @param yourSoftware1 the yourSoftware1 to set
     */
    public void setYourSoftware1(String yourSoftware1) {
        this.yourSoftware1 = yourSoftware1;
    }

    /**
     * @return the software2
     */
    public List<String> getSoftware2() {
        return software2;
    }

    /**
     * @param software2 the software2 to set
     */
    public void setSoftware2(List<String> software2) {
        this.software2 = software2;
    }

    /**
     * @return the yourSoftware2
     */
    public String getYourSoftware2() {
        return yourSoftware2;
    }

    /**
     * @param yourSoftware2 the yourSoftware2 to set
     */
    public void setYourSoftware2(String yourSoftware2) {
        this.yourSoftware2 = yourSoftware2;
    }

    /**
     * @return the software0
     */
    public List<String> getSoftware0() {
        return software0;
    }

    /**
     * @param software0 the software0 to set
     */
    public void setSoftware0(List<String> software0) {
        this.software0 = software0;
    }

    /**
     * @return the yourSoftware0
     */
    public String getYourSoftware0() {
        return yourSoftware0;
    }

    /**
     * @param yourSoftware0 the yourSoftware0 to set
     */
    public void setYourSoftware0(String yourSoftware0) {
        this.yourSoftware0 = yourSoftware0;
    }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }


}
