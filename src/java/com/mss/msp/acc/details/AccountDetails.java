/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.acc.details;

import java.util.List;

/**
 *
 * @author Greg
 */
public class AccountDetails {

    //ALL NEEDED THINGS
    //THE ACCOUNTS TABLE
    private Integer id;
    private String name;
    private String url;
    private String status;
    //THE ACC_BASIC_INFO TABLE
    private String region;
    private String stockSymbol;
    private String description;
    private String industry;
    private String taxId;
    private Integer budget;
    private Integer revenue;
    private String phone;
    private String fax;
    private String noemp;
    private String territory;
    //THE ACC_ADDRESS TABLE
    private String country;
    private String address1;
    private String address2;
    private String state;
    private String zip;
    private String city;
    //THE ORG_REL TABLE
    private Integer accountType;
    //private Integer vendor;
    //LOOK UP NAMES
    private String lkState;
    private String lkCountry;
    private String lkIndustry;
    private String lkAccountType;
    private String lkVendor;
    private String emailExt;
    private String bankName;
    private String bankAddress;
    private String bankCity;
    private String bankZip;
    private String bankRoutingNumber;
    private String bankAccountNumber;
    private Integer isPrimary;
    private String beneficiaryName;
    private String skillValues;
    private List skillValueList;
    private String reqSkillSet;

    public AccountDetails() {
    }

    public AccountDetails(Integer id, String name, String url, String status, String region, String stockSymbol, String description, String industry, String taxId, Integer budget, Integer revenue, String phone, String fax, String noemp, String territory, String country, String address1, String address2, String state, String zip, String city, Integer accountType, String lkState, String lkCountry, String lkIndustry, String lkAccountType, String lkVendor, String emailExt, String bankAccountNumber, String bankName, String bankAddress, String bankCity, String bankZip, String bankRoutingNumber, Integer isPrimary, String beneficiaryName,String skillValues) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.status = status;
        this.region = region;
        this.stockSymbol = stockSymbol;
        this.description = description;
        this.industry = industry;
        this.taxId = taxId;
        this.budget = budget;
        this.revenue = revenue;
        this.phone = phone;
        this.fax = fax;
        this.noemp = noemp;
        this.territory = territory;
        this.country = country;
        this.address1 = address1;
        this.address2 = address2;
        this.state = state;
        this.zip = zip;
        this.city = city;
        this.accountType = accountType;
        //this.vendor=vendor;
        this.lkState = lkState;
        this.lkCountry = lkCountry;
        this.lkIndustry = lkIndustry;
        this.lkAccountType = lkAccountType;
        this.lkVendor = lkVendor;
        this.emailExt = emailExt;
        this.bankName = bankName;
        this.bankAddress = bankAddress;;
        this.bankCity = bankCity;
        this.bankZip = bankZip;
        this.bankRoutingNumber = bankRoutingNumber;
        this.bankAccountNumber = bankAccountNumber;
        this.isPrimary = isPrimary;
        this.beneficiaryName = beneficiaryName;
         this.skillValues = skillValues;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
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

    public String getNoemp() {
        return noemp;
    }

    public void setNoemp(String noemp) {
        this.noemp = noemp;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getLkState() {
        return lkState;
    }

    public void setLkState(String lkState) {
        this.lkState = lkState;
    }

    public String getLkCountry() {
        return lkCountry;
    }

    public void setLkCountry(String lkCountry) {
        this.lkCountry = lkCountry;
    }

    public String getLkIndustry() {
        return lkIndustry;
    }

    public void setLkIndustry(String lkIndustry) {
        this.lkIndustry = lkIndustry;
    }

    public String getLkAccountType() {
        return lkAccountType;
    }

    public void setLkAccountType(String lkAccountType) {
        this.lkAccountType = lkAccountType;
    }

    @Override
    public String toString() {
        return "AccountDetails{" + "id=" + id + ", name=" + name + ", url=" + url + ", status=" + status + ", region=" + region + ", stockSymbol=" + stockSymbol + ", description=" + description + ", industry=" + industry + ", taxId=" + taxId + ", budget=" + budget + ", revenue=" + revenue + ", phone=" + phone + ", fax=" + fax + ", noemp=" + noemp + ", territory=" + territory + ", country=" + country + ", address1=" + address1 + ", address2=" + address2 + ", state=" + state + ", zip=" + zip + ", accountType=" + accountType + ", lkState=" + lkState + ", lkCountry=" + lkCountry + ", lkIndustry=" + lkIndustry + ", lkAccountType=" + lkAccountType + '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLkVendor() {
        return lkVendor;
    }

    public void setLkVendor(String lkVendor) {
        this.lkVendor = lkVendor;
    }

//    public Integer getVendor() {
//        return vendor;
//    }
//
//    public void setVendor(Integer vendor) {
//        this.vendor = vendor;
//    }
    public String getEmailExt() {
        return emailExt;
    }

    public void setEmailExt(String emailExt) {
        this.emailExt = emailExt;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getBankZip() {
        return bankZip;
    }

    public void setBankZip(String bankZip) {
        this.bankZip = bankZip;
    }

    public String getBankRoutingNumber() {
        return bankRoutingNumber;
    }

    public void setBankRoutingNumber(String bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Integer getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Integer isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getSkillValues() {
        return skillValues;
    }

    public void setSkillValues(String skillValues) {
        this.skillValues = skillValues;
    }

    public List getSkillValueList() {
        return skillValueList;
    }

    public void setSkillValueList(List skillValueList) {
        this.skillValueList = skillValueList;
    }

    public String getReqSkillSet() {
        return reqSkillSet;
    }

    public void setReqSkillSet(String reqSkillSet) {
        this.reqSkillSet = reqSkillSet;
    }
    
}
