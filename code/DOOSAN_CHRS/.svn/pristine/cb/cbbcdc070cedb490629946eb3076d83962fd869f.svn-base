package com.ait.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//import javax.naming.*;

/**
 * <p>
 * Title: ServiceLocator
 * </p>
 * 
 * <p>
 * Description: ServiceLocator is a global class, that can be used to retrieve a
 * java sql connection whenever it is needed. Usually,this class will be used in
 * DAO classes, and as the following form: private ServiceLocator services;
 * services = ServiceLocator.getInstance(); Connection con =
 * services.getConnection()
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * 
 * <p>
 * Company: AIT
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class ServiceLocator {

    private static ServiceLocator instance;

    private DataSource ds;

    public static ServiceLocator getInstance() throws ServiceLocatorException {
        if (instance == null)
            instance = new ServiceLocator();
        return instance;
    }

    private ServiceLocator() throws ServiceLocatorException {
        try {
            ds = getDataSource();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds a data source by looking up the initial context
     * 
     * @return javax.sql.DataSource
     * @throws ServiceLocatorException
     */
    private DataSource getDataSource() throws ServiceLocatorException {
        if (ds == null) {
            try {
                Context ctx = new InitialContext();
                Context env = (Context) ctx.lookup("java:/comp/env");
                ds = (DataSource) env.lookup("jdbc/EM2");
            } catch (NamingException e) {
                throw new ServiceLocatorException(
                        "Unable to locate data source; " + e.getMessage(), e);
            }
        }
        return ds;
    }

    /**
     * this connection is got from the given data source.
     * 
     * @return java.sql.Connection
     * @throws ServiceLocatorException
     */
    public Connection getConnection() throws ServiceLocatorException {
        //Logger.getLogger(getClass()).debug("############# Getting Data Connection From JNDI #############");
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new ServiceLocatorException("Unable to open connection to data source; " + e.getMessage(), e);
        }
    }

}
