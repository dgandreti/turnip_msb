/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc;

import com.mss.msp.location.Country;
import com.mss.msp.location.State;
import com.mss.msp.util.ServiceLocatorException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kyle Bissell
 */
public class CsrAccount {

    private Integer id;
    private String name;
    private String url;
    private String status;
    private String lastAccessDate;
    private String lastAccessById;
    private String lastAccessDateString; //Formated lastAccessDate
    /**
     * basic info *
     */
    private String region;
    private String territory;
    private String stockSymbol;
    private String description;
    private String industry;
    private Integer industryId;
    private String tax_id;
    private Long revenue;
    private Double budget;
    private String phone;
    private String fax;
    private String noemp; //number of employees
    /**
     * address *
     */
    private String zip;
    private String city;
    private String address1;
    private String address2;
    private State state;
    private Country country;
    /**
     * org relations *
     */
    private String type;
    private Integer typeId;
    private String org_typeOfRelation;
    private String vendorType;
    private Integer vendorTypeId;
    //Primary IDS for joined tables
    private Integer acc_basic_info_id;
    private Integer acc_address_id;
    private Integer org_rel_id;
    private String lastAccessBy;
    // created by Aklakh
    private String projectName;
    private String projectEmpName;
    private int projectID;
    private int userID;
    private int designation;
    private String teamMemberStatus;
    private String reportsto1;
    private String reportsto2;
    private Integer csrId;
    private String csrorgStatus;
    private Integer isPrimary;

    public Integer getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Integer isPrimary) {
        this.isPrimary = isPrimary;
    }
    public String getCsrorgStatus() {
        return csrorgStatus;
    }

    public void setCsrorgStatus(String csrorgStatus) {
        this.csrorgStatus = csrorgStatus;
    }

    public Integer getCsrId() {
        return csrId;
    }

    public void setCsrId(Integer csrId) {
        this.csrId = csrId;
    }

    public CsrAccount() {
        this.country = new Country();
        this.state = new State();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getTax_id() {
        return tax_id;
    }

    public void setTax_id(String tax_id) {
        this.tax_id = tax_id;
    }

    public Long getRevenue() {
        return this.revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    public Double getBudget() {
        return this.budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(String lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getNoemp() {
        return noemp;
    }

    public void setNoemp(String noemp) {
        this.noemp = noemp;
    }

    public String getOrg_typeOfRelation() {
        return org_typeOfRelation;
    }

    public void setOrg_typeOfRelation(String org_typeOfRelation) {
        this.org_typeOfRelation = org_typeOfRelation;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public Integer getAcc_basic_info_id() {
        return acc_basic_info_id;
    }

    public void setAcc_basic_info_id(Integer acc_basic_info_id) {
        this.acc_basic_info_id = acc_basic_info_id;
    }

    public Integer getAcc_address_id() {
        return acc_address_id;
    }

    public void setAcc_address_id(Integer acc_address_id) {
        this.acc_address_id = acc_address_id;
    }

    public Integer getOrg_rel_id() {
        return org_rel_id;
    }

    public void setOrg_rel_id(Integer org_rel_id) {
        this.org_rel_id = org_rel_id;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", name=" + name + ", region=" + region + ", country="
                + country + ", stockSymbol=" + stockSymbol + ", description=" + description
                + ", zip=" + zip + ", url=" + url + ", state=" + state + ", status=" + status
                + ", address1=" + address1 + ", address2=" + address2 + ", industry=" + industry
                + ", tax_id=" + tax_id + ", revenue=" + revenue + ", budget=" + budget
                + " phone=" + phone + ", fax=" + fax + ", lastAccessDate=" + lastAccessDate
                + ", accountType=" + typeId + '}';
    }

    public String getLastAccessBy() {
        return lastAccessBy;
    }

    public void setLastAccessBy(String lastAccessBy) {
        this.lastAccessBy = lastAccessBy;
    }

    public String getLastAccessById() {
        return lastAccessById;
    }

    public void setLastAccessById(String lastAccessById) {
        this.lastAccessById = lastAccessById;
    }

    public String getLastAccessDateString() {
        this.lastAccessDateString = "";
        try {
            String currentFormat = "yyyy-MM-dd";
            String newFormat = "MM-dd-yyyy";
            this.lastAccessDateString = com.mss.msp.util.DateUtility.getInstance()
                    .formatDate(this.lastAccessDate.substring(0, 10), currentFormat, newFormat);
        } catch (ParseException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }

//    System.out.println( "aD: " + lastAccessDateString);

        return this.lastAccessDateString;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getVendorType() {
        return vendorType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

    public Integer getVendorTypeId() {
        return vendorTypeId;
    }

    public void setVendorTypeId(Integer vendorTypeId) {
        this.vendorTypeId = vendorTypeId;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public void setState(String state) {
        if (state != null && !state.equals("") && Integer.parseInt(state) > 0) {
            if (this.state == null) {
                this.state = new State();
            }
            this.state.setId(Integer.parseInt(state));
        }
    }

    public void setCountry(String country) {
        if (country != null && !country.equals("")) {
            if (this.country == null) {
                this.country = new Country();
            }
            this.country.setId(Integer.parseInt(country));
        }
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectEmpName() {
        return projectEmpName;
    }

    public void setProjectEmpName(String projectEmpName) {
        this.projectEmpName = projectEmpName;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getDesignation() {
        return designation;
    }

    public void setDesignation(int designation) {
        this.designation = designation;
    }

    public String getTeamMemberStatus() {
        return teamMemberStatus;
    }

    public void setTeamMemberStatus(String teamMemberStatus) {
        this.teamMemberStatus = teamMemberStatus;
    }

    public String getReportsto1() {
        return reportsto1;
    }

    public void setReportsto1(String reportsto1) {
        this.reportsto1 = reportsto1;
    }

    public String getReportsto2() {
        return reportsto2;
    }

    public void setReportsto2(String reportsto2) {
        this.reportsto2 = reportsto2;
    }
}
