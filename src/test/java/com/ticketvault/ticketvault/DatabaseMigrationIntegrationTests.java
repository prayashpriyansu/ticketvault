package com.ticketvault.ticketvault;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

@SpringBootTest
@Testcontainers
class DatabaseMigrationIntegrationTests {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    DatabaseMigrationIntegrationTests(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer postgreSQLContainer =
            new PostgreSQLContainer("postgres:18");

    @Test
    @Transactional
    void insertUserUsesDatabaseDefaultForCreatedAt() {

        // arrange
        UUID userId = UUID.randomUUID();
        String displayName = "Alice";

        // act
        int rowsInserted = jdbcTemplate.update(
                "INSERT INTO users (id, display_name) VALUES (?, ?)",
                userId,
                displayName
        );

        String actualDisplayName = jdbcTemplate.queryForObject(
                "SELECT display_name FROM users WHERE id = ?",
                String.class,
                userId
        );

        OffsetDateTime createdAt = jdbcTemplate.queryForObject(
            "SELECT created_at FROM users WHERE id = ?",
                OffsetDateTime.class,
                userId
        );

        // assert
        // exactly one row should have been inserted
        assertEquals(1, rowsInserted);
        assertEquals(displayName, actualDisplayName);
        assertNotNull(createdAt);
    }
}
