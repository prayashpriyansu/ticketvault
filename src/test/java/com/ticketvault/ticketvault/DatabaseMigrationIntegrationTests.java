package com.ticketvault.ticketvault;

import com.ticketvault.ticketvault.support.PostgresTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Import(PostgresTestConfiguration.class)
class DatabaseMigrationIntegrationTests {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    DatabaseMigrationIntegrationTests(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
