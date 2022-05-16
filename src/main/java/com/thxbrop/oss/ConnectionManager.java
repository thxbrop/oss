package com.thxbrop.oss;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.ha.PropertiesUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static ConnectionManager connectionManager;
    private final DataSource dataSource;

    private ConnectionManager() {
        Properties properties = PropertiesUtils.loadProperties("/application.properties");
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
