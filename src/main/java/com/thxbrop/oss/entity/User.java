package com.thxbrop.oss.entity;

public class User {
    public static final int ROLE_DEFAULT = 1;
    public static final int ROLE_ADMIN = 2;
    private final String email;
    private final String password;
    private final String username;
    private final int role;
    private int id;

    public User(String email, String password, String username, int role) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public User(int id, String email, String password, String username, int role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}
