package com.javafx.user.management.user;

public class User {
    private int id;
    private String username, password, email;
    private boolean active;
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public int getId() {
        return id;
    }
    protected void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
