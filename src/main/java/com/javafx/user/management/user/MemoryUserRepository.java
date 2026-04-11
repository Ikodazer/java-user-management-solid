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
        return findAllActiveUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public Optional<User> findById(int id) {
        return findAllActiveUsers().stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    public Collection<User> findAllActiveUsers() {
        return database.selectAll().stream().filter(User::isActive).toList();
    }

    @Override
    public void save(User user) {
        user.setId(database.generateId());
        database.insert(user);
    }

    @Override
    public void deactivate(User user) {
        Optional<User> optionalStoredUser = findStoredUserById(user.getId());
        optionalStoredUser.ifPresent(User::deactivate);
    }

    @Override
    public void update(User user) {
        Optional<User> optionalStoredUser = findStoredUserById(user.getId());
        if (optionalStoredUser.isPresent()) {
            User storedUser = optionalStoredUser.get();
            storedUser.setUsername(user.getUsername());
            storedUser.setPassword(user.getPassword());
            storedUser.setEmail(user.getEmail());
        }
    }

    private Optional<User> findStoredUserById(int id) {
        return database.selectAll().stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }
}
