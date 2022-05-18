package com.thxbrop.oss.repository;

import com.thxbrop.oss.Result;
import com.thxbrop.oss.entity.User;
import com.thxbrop.oss.util.Logger;
import com.thxbrop.oss.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import static com.thxbrop.oss.util.thread.ThreadUtil.repeat;

public class UserRepositoryMock implements UserRepository {
    private final ArrayList<User> list;

    public UserRepositoryMock() {
        list = new ArrayList<>();
        repeat(5, i -> list.add(new User(i, i + "", i + "", "User_" + i, 1)));
    }

    @Override
    public Result<User> register(String email, String username, String password) {
        int id = list.size();
        User user = new User(id, email, password, username == null ? "User_" + id : username, 1);
        list.add(user);
        return new Result<>(user);
    }

    @Override
    public Result<User> login(String email, String password) {
        if (StringUtil.isNullOrEmpty(email) || StringUtil.isNullOrEmpty(password))
            return new Result<>("Email and password must not be empty");
        for (User u : list) {
            if (u.getEmail().equals(email)) {
                if (u.getPassword().equals(password)) {
                    return new Result<>(u);
                } else {
                    return new Result<>("The user does not exist or the password is entered incorrectly");
                }
            }
        }
        return new Result<>("Email has not been registered");
    }

    @Override
    public Result<User> getByEmail(String email) {
        for (User user : list) {
            if (user.getEmail().equals(email)) {
                return new Result<>(user);
            }
        }
        return new Result<>("The user does not exist");
    }

    @Override
    public List<User> getAll() {
        return list;
    }

    @Override
    public boolean delete(String email) {
        return list.removeIf(user -> user.getEmail().equals(email));
    }

    @Override
    public Result<User> update(String email, String username, String password, Integer role) {
        User user = null;
        for (User u : list) {
            if (u.getEmail().equals(email)) {
                user = u;
                break;
            }
        }
        if (user == null) return new Result<>("The user does not exist");
        User newUser = new User(
                user.getId(),
                user.getEmail(),
                password == null ? user.getPassword() : password,
                username == null ? user.getUsername() : username,
                role == null ? user.getRole() : role
        );
        list.remove(user);
        list.add(newUser);
        return new Result<>(user);
    }

    @Override
    public void close() {
        Logger.t("UserControllerMock", "closed");
    }
}
