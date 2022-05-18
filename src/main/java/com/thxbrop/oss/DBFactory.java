package com.thxbrop.oss;

import com.alibaba.druid.pool.ha.PropertiesUtils;
import com.thxbrop.oss.repository.*;
import com.thxbrop.oss.util.CloseableScope;

import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;

public class DBFactory {

    public static CommodityRepository getCommodityController() {
        if (isMockMode()) return new CommodityRepositoryMock();
        else return new CommodityRepositoryImpl();
    }

    public static UserRepository getUserController() {
        if (isMockMode()) return new UserRepositoryMock();
        else return new UserRepositoryImpl();
    }

    private static boolean isMockMode() {
        Properties properties = PropertiesUtils.loadProperties("/application.properties");
        try {
            int isMockMode = Integer.parseInt(properties.getProperty("isMockMode"));
            return isMockMode == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static <T extends Closeable> void autoClosed(T t, CloseableScope<T> scope) {
        try {
            scope.receive(t);
            t.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
