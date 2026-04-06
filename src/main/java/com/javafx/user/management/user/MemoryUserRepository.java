package com.javafx.user.management.user;

import com.javafx.user.management.database.Database;

import java.util.Collection;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository {
    private final Database database;
    public MemoryUserRepository(Database database) {
        this.database = database;
    }
    @Override
    public Optional<User> findByUsername(String username) {
        return database.getUsers().stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    @Override
    public Optional<User> findById(int id) {
        return database.getUsers().stream().filter(user -> user.getId() == id).findFirst();
    }

    @Override
    public Collection<User> findAllUsers() {
        return database.getUsers().stream().filter(User::isActive).toList();
    }

    @Override
    public void save(User user) {
        user.setId(database.getNextId());
        database.getUsers().add(user);
    }

    @Override
    public void delete(User user) {
        Optional<User> optionalMemoryUser = findById(user.getId()).filter(User::isActive);
        optionalMemoryUser.ifPresent(memoryUser -> memoryUser.setActive(false));
    }

    @Override
    public void update(User user) {
        Optional<User> optionalMemoryUser = findById(user.getId()).filter(User::isActive);
        if (optionalMemoryUser.isPresent()) {
            User memoryUser = optionalMemoryUser.get();
            memoryUser.setUsername(user.getUsername());
            memoryUser.setPassword(user.getPassword());
            memoryUser.setEmail(user.getEmail());
        }
    }
}
