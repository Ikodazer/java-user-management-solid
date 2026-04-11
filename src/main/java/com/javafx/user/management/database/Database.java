package com.javafx.user.management.database;

import com.javafx.user.management.user.User;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private final List<User> database = new ArrayList<>();
    private int idCounter = 0;

    public List<User> selectAll() {
        return List.copyOf(database);
    }

    public void insert (User user) {
        user.setActive(true);
        database.add(user);
    }

    public int generateId() {
        idCounter++;
        return idCounter;
    }
}
