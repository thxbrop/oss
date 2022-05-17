package com.thxbrop.oss.controller;

import com.thxbrop.oss.ConnectionManager;
import com.thxbrop.oss.Result;
import com.thxbrop.oss.dao.UserDao;
import com.thxbrop.oss.dao.UserDaoImpl;
import com.thxbrop.oss.entity.User;
import com.thxbrop.oss.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserControllerImpl implements UserController {
    private final UserDao dao;
    private final Connection connection;

    public UserControllerImpl() {
        connection = ConnectionManager.getInstance().getConnection();
        dao = new UserDaoImpl(connection);
    }

    @Override
    public Result<User> register(String email, String username, String password) {
        if (StringUtil.isNullOrEmpty(email) || StringUtil.isNullOrEmpty(password))
            return new Result<>("Email and password must not be empty");
        List<User> list = dao.getByEmail(email);
        if (list.isEmpty()) {
            User user = new User(email, password, username, User.ROLE_DEFAULT);
            dao.insert(user);
            return new Result<>(user);
        }
        return new Result<>("Email has been registered");
    }

    public Result<User> register(String email, String password) {
        return register(email, "User_" + System.currentTimeMillis(), password);
    }

    @Override
    public Result<User> login(String email, String password) {
        if (StringUtil.isNullOrEmpty(email) || StringUtil.isNullOrEmpty(password))
            return new Result<>("Email and password must not be empty");
        List<User> list = dao.getByEmail(email);
        if (list.isEmpty() || list.stream().noneMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password))) {
            return new Result<>("The user does not exist or the password is entered incorrectly");
        } else {
            return new Result<>(list.get(0));
        }
    }

    @Override
    public Result<User> getByEmail(String email) {
        if (!StringUtil.isNullOrEmpty(email)) {
            List<User> list = dao.getByEmail(email);
            if (!list.isEmpty()) {
                return new Result<>(list.get(0));
            }
        }
        return new Result<>("The user does not exist");
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public boolean delete(String email) {
        return dao.deleteByEmail(email);
    }

    @Override
    public Result<User> update(@NotNull String email, String username, String password, Integer role) {
        if (StringUtil.isNullOrEmpty(email)) {
            return new Result<>("Email required");
        }
        if (!StringUtil.isNullOrEmpty(username)) {
            dao.updateUsername(email, username);
        }
        if (!StringUtil.isNullOrEmpty(password)) {
            dao.updatePassword(email, password);
        }
        if (role != null) {
            dao.updateRole(email, role);
        }
        return getByEmail(email);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
