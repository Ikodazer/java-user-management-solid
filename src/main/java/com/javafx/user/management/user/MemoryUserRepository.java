package com.javafx.user.management.user;

import com.javafx.user.management.database.Database;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryUserRepository implements UserRepository {
    private final Database database;
    public MemoryUserRepository(Database database) {
        this.database = database;
    }
    @Override
    public Optional<User> findByUsername(String username) {
        return findAllUsers().stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    @Override
    public Optional<User> findById(int id) {
        return findAllUsers().stream().filter(user -> user.getId() == id).findFirst();
    }

    @Override
    public Collection<User> findAllUsers() {
        return database.selectAll().stream().filter(User::isActive).toList();
    }

    @Override
    public void save(User user) {
        user.setId(database.generateId());
        database.insert(user);
    }

    @Override
    public void delete(User user) {
        Optional<User> optionalMemoryUser = findById(user.getId());
        optionalMemoryUser.ifPresent(memoryUser -> memoryUser.setActive(false));
    }

    @Override
    public void update(User user) {
        Optional<User> optionalMemoryUser = findById(user.getId());
        if (optionalMemoryUser.isPresent()) {
            User memoryUser = optionalMemoryUser.get();
            memoryUser.setUsername(user.getUsername());
            memoryUser.setPassword(user.getPassword());
            memoryUser.setEmail(user.getEmail());
        }
    }
}
