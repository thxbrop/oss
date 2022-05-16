package com.thxbrop.oss.dao;

import com.thxbrop.oss.entity.User;

import java.util.List;

public interface UserDao {

    void insert(User user);

    void delete(User user);

    boolean deleteByEmail(String email);

    void updateUsername(String email, String username);

    void updatePassword(String email, String password);

    void updateRole(String email, int role);

    User getById(int id);

    List<User> getByEmail(String email);

    List<User> getAll();
}
