package com.ticketvault.ticketvault.user;

import com.ticketvault.ticketvault.support.PostgresTestConfiguration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Import(PostgresTestConfiguration.class)
@Transactional
public class UserRepositoryIntegrationTests {

    private final UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryIntegrationTests(
            UserRepository userRepository,
            JdbcTemplate jdbcTemplate
    ) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test
    void findByIdReturnsExistingUser() {

        // arrange
        UUID userId = UUID.randomUUID();
        OffsetDateTime createdAt = OffsetDateTime.parse("2026-09-15T10:00:00Z");
        String displayName = "Alice";

        User expectedUser = new User(userId, displayName, createdAt);

        int rowsCreated = jdbcTemplate.update(
                "INSERT INTO users(id, display_name, created_at) VALUES (?, ?, ?)",
                userId, displayName, createdAt
        );

        // act
        User actualUser = userRepository.findById(userId).orElseThrow();

        // assert
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void findByIdReturnsEmptyForUnknownUser() {

        UUID randomId = UUID.randomUUID();

        Optional<User> user = userRepository.findById(randomId);

        assertTrue(user.isEmpty());
    }

}
