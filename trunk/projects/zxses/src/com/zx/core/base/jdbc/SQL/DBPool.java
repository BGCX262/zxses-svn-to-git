package com.zx.core.base.jdbc.SQL;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.zx.core.util.DateUtil;

public class DBPool {

    private Logger logger = Logger.getLogger(DBPool.class);
    private static int clients = 0;
    private static DBPool instance;
    private Vector drivers = new Vector();
    private Hashtable pools = new Hashtable();

    public DBPool() {
        init();
    }

    public static synchronized DBPool getInstance() {
        if (instance == null) {
            instance = new DBPool();
        }
        clients++;
        return instance;
    }

    private void init() {
        InputStream is = getClass().getResourceAsStream("/config/database/config.properties");
        Properties dbProps = new Properties();
        try {
            dbProps.load(is);
        } catch (Exception e) {
            logger.error("错误：Init Properties Error");
            logger.error(e);
            logger.info("错误：Init Properties Error\n"
                    + DateUtil.getInstance().dateTimeToStr(new Date()) + " "
                    + getClass().getName());
            e.printStackTrace();
            return;
        }
        loadDrivers(dbProps);
        createPools(dbProps);
    }

    public void freeConnection(String name, Connection con) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            pool.freeConnection(con);
        }
    }

    public Connection getConnection(String name) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            return pool.getConnection();
        }
        return null;
    }

    public Connection getConnection(String name, long time) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            return pool.getConnection(time);
        }
        return null;
    }

    public synchronized void release() {
        if (--clients != 0) {
            return;
        }
        Enumeration allPools = pools.elements();
        while (allPools.hasMoreElements()) {
            DBConnectionPool pool = (DBConnectionPool) allPools.nextElement();
            pool.release();
        }
        Enumeration allDrivers = drivers.elements();
        while (allDrivers.hasMoreElements()) {
            Driver driver = (Driver) allDrivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.info("信息：Deregister Driver "
                        + driver.getClass().getName());
                System.out.println("信息：Deregister Driver "
                        + driver.getClass().getName() + "\n"
                        + DateUtil.getInstance().dateTimeToStr(new Date())
                        + " " + getClass().getName());
            } catch (Exception e) {
                logger.error("错误：Cann't Deregister Driver "
                        + driver.getClass().getName());
                logger.error(e);
                System.out.println("错误：Cann't Deregister Driver "
                        + driver.getClass().getName() + "\n"
                        + DateUtil.getInstance().dateTimeToStr(new Date())
                        + " " + getClass().getName());
                e.printStackTrace();
            }
        }
    }

    private void loadDrivers(Properties props) {
        String driverClasses = props.getProperty("database.driver");
        StringTokenizer st = new StringTokenizer(driverClasses);
        while (st.hasMoreElements()) {
            String driverClassName = st.nextToken().trim();
            try {
                Driver driver = (Driver) Class.forName(driverClassName)
                        .newInstance();
                DriverManager.registerDriver(driver);
                drivers.addElement(driver);
            } catch (Exception e) {
                logger.error("错误：Register Driver Failure");
                logger.error(e);
                System.out.println("错误：Register Driver Failure\n"
                        + DateUtil.getInstance().dateTimeToStr(new Date())
                        + " " + getClass().getName());
                e.printStackTrace();
            }
        }
    }

    private void createPools(Properties props) {
        Enumeration propNames = props.propertyNames();
        while (propNames.hasMoreElements()) {
            String name = (String) propNames.nextElement();
            if (name.endsWith(".url")) {
                String poolName = name.substring(0, name.lastIndexOf("."));
                String url = props.getProperty(poolName + ".url");
                if (url == null) {
                    logger.error("错误：No URL specified for " + poolName);
                    System.out.println("错误：No URL specified for " + poolName
                            + "\n"
                            + DateUtil.getInstance().dateTimeToStr(new Date())
                            + " " + getClass().getName());
                    continue;
                }
                String user = props.getProperty(poolName + ".user");
                String password = props.getProperty(poolName + ".password");
                String maxconn = props.getProperty(poolName + ".maxconn", "0");
                int max;
                try {
                    max = Integer.valueOf(maxconn).intValue();
                } catch (Exception e) {
                    logger.error("错误：Invalid Max Connection of Database");
                    logger.error(e);
                    System.out
                            .println("错误：Invalid Max Connection of Database\n"
                                    + DateUtil.getInstance().dateTimeToStr(
                                            new Date()) + " "
                                    + getClass().getName());
                    e.printStackTrace();
                    max = 0;
                }
                DBConnectionPool pool = new DBConnectionPool(poolName, url,
                        user, password, max);
                pools.put(poolName, pool);
            }
        }
    }

    class DBConnectionPool {

        private int checkedOut;
	private Vector freeConnections = new Vector();
        private int maxConn;
        private String name;
        private String password;
        private String URL;
        private String user;

        public DBConnectionPool(String name, String URL, String user,
                String password, int maxConn) {
            this.name = name;
            this.URL = URL;
            this.user = user;
            this.password = password;
            this.maxConn = maxConn;
        }

        @SuppressWarnings("unchecked")
	public synchronized void freeConnection(Connection con) {
            freeConnections.addElement(con);
            checkedOut--;
            notifyAll();
        }

        public synchronized Connection getConnection() {
            Connection con = null;

            if (freeConnections.size() > 0) {
                con = (Connection) freeConnections.firstElement();
                freeConnections.removeElementAt(0);
                try {
                    if (con.isClosed()) {
                        con = getConnection();
                    }
                } catch (Exception e) {
                    logger
                            .error("错误：Cann't get Connection from Connection Pool of Database");
                    logger.error(e);
                    System.out
                            .println("错误：Cann't get Connection from Connection Pool of Database\n"
                                    + DateUtil.getInstance().dateTimeToStr(
                                            new Date())
                                    + " "
                                    + getClass().getName());
                    e.printStackTrace();
                    con = getConnection();
                }
            } else if (maxConn == 0 || checkedOut < maxConn) {
                con = newConnection();
            }
            if (con != null) {
                checkedOut++;
            }
            return con;
        }

        public synchronized Connection getConnection(long timeout) {
            long startTime = new java.util.Date().getTime();
            Connection con;
            while ((con = getConnection()) == null) {
                try {
                    wait(timeout);
                } catch (InterruptedException e) {
                    logger
                            .error("错误：Timeout to get Connection from Connection Pool of Database");
                    logger.error(e);
                    System.out
                            .println("错误：Timeout to get Connection from Connection Pool of Database\n"
                                    + DateUtil.getInstance().dateTimeToStr(
                                            new Date())
                                    + " "
                                    + getClass().getName());
                    e.printStackTrace();
                }
                if ((new java.util.Date().getTime() - startTime) >= timeout) {
                    return null;
                }
            }
            return con;
        }

	public synchronized void release() {
            Enumeration allConnections = freeConnections.elements();
            while (allConnections.hasMoreElements()) {
                Connection con = (Connection) allConnections.nextElement();
                try {
                    con.close();
                } catch (Exception e) {
                    logger
                            .error("错误：Cann't Close Connection from Connection Pool of Database");
                    logger.error(e);
                    System.out
                            .println("错误：Cann't Close Connection from Connection Pool of Database\n"
                                    + DateUtil.getInstance().dateTimeToStr(
                                            new Date())
                                    + " "
                                    + getClass().getName());
                    e.printStackTrace();
                }
            }
            freeConnections.removeAllElements();
        }

        private Connection newConnection() {
            Connection con = null;
            try {
                if (user == null) {
                    con = DriverManager.getConnection(URL);
                } else {
                    con = DriverManager.getConnection(URL, user, password);
                }
            } catch (SQLException e) {
                logger.error("错误：Cann't Create URL Connection");
                logger.error(e);
                System.out.println("错误：Cann't Create URL Connection\n"
                        + DateUtil.getInstance().dateTimeToStr(new Date())
                        + " " + getClass().getName());
                e.printStackTrace();
                return null;
            }
            return con;
        }
    }
}
