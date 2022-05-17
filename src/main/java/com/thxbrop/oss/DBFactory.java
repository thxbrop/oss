package com.thxbrop.oss;

import com.alibaba.druid.pool.ha.PropertiesUtils;
import com.thxbrop.oss.controller.*;
import com.thxbrop.oss.util.CloseableScope;

import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;

public class DBFactory {

    public static CommodityController getCommodityController() {
        if (isMockMode()) return new CommodityControllerMock();
        else return new CommodityControllerImpl();
    }

    public static UserController getUserController() {
        if (isMockMode()) return new UserControllerMock();
        else return new UserControllerImpl();
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
