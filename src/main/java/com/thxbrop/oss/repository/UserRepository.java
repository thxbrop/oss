package com.thxbrop.oss.repository;

import com.thxbrop.oss.Result;
import com.thxbrop.oss.entity.User;

import java.io.Closeable;
import java.util.List;

public interface UserRepository extends Closeable {
    Result<User> register(String email, String username, String password);

    Result<User> login(String email, String password);

    Result<User> getByEmail(String email);

    List<User> getAll();

    boolean delete(String email);

    Result<User> update(String email, String username, String password, Integer role);
}
