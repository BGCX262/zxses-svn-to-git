package com.zx.core.base.jdbc.SQL;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBConnection {

    private Logger logger = Logger.getLogger(DBConnection.class);
    private static DBConnection instance = null;
    private Connection connect;

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public DBConnection() {
        Connection con = DBPool.getInstance().getConnection("database");
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.setConnect(con);
        logger.info("信息：实例化数据库连接成功");
    }

    public Connection getConnect() {
        try {
            if (connect == null || connect.isClosed()) {
                setConnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }

    public void setConnect() {
        Connection con = DBPool.getInstance().getConnection(
                "database");
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.setConnect(con);
        System.out.println("信息：实例化数据库连接成功");
    }
}