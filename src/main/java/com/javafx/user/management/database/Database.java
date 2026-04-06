package com.javafx.user.management.database;

import com.javafx.user.management.user.User;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private final List<User> database = new ArrayList<>();

    public List<User> getUsers() {
        return database;
    };
}
