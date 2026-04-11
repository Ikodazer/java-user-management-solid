package com.javafx.user.management.user;

import com.javafx.user.management.database.Database;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MemoryUserRepositoryTest {
    @Test
    void saveKeepsNewUsersActive() {
        MemoryUserRepository repository = new MemoryUserRepository(new Database());
        User user = new User("alice", "hashed-password", "alice@example.com");

        repository.save(user);

        assertEquals(1, user.getId());
        assertTrue(user.isActive());
        assertEquals(1, repository.findAllActiveUsers().size());
    }

    @Test
    void deactivateRemovesUsersFromActiveQueries() {
        MemoryUserRepository repository = new MemoryUserRepository(new Database());
        User user = new User("alice", "hashed-password", "alice@example.com");
        repository.save(user);

        repository.deactivate(user);

        assertFalse(user.isActive());
        assertTrue(repository.findById(user.getId()).isEmpty());
        assertTrue(repository.findByUsername(user.getUsername()).isEmpty());
        assertTrue(repository.findAllActiveUsers().isEmpty());
    }
}
