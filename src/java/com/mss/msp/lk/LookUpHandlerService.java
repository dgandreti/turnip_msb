/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.lk;

import com.mss.msp.util.ServiceLocatorException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Greg
 */
public interface LookUpHandlerService {
    /**
     *
     * @param tableName
     * @param columnName
     * @param id the id to find a name
     * @return name the name given to the id
     */
    public String getName(String tableName, String columnName,Integer id) throws ServiceLocatorException;
    /**
     *
     * @param tableName
     * @param columnName
     * @param name the name to find an id
     * @return id the id for a given name
     */
    public Integer getId(String tableName, String columnName,String name) throws ServiceLocatorException;
    /**
     *
     * @param tableName  The name of the table.
     * @param columnName The name of the column
     * @return list The list of strings representing the column chosen
     * @throws ServiceLocatorException
     */
    public List<Object> getColumn(String tableName, String columnName) throws ServiceLocatorException;

    /**
     *
     * @return A List of All Industry Type names
     */
    public List<String> getIndustryTypeNames();

    public Map<Integer, String> getIndustryTypesMap();

    /**
     *
     * @return A List of All Account Type names
     */
    public List<String> getAccountTypeNames();

    public Map<Integer, String> getAccountTypesMap();

    /**
     *
     * @return A List of Vendor Types
     */
    public List<String> getVendorTypes();

    public Map<Integer, String> getVendorTypesMap();
}
