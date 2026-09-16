package com.ticketvault.ticketvault.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository {

    private static final RowMapper<User> USER_ROW_MAPPER = (rs, rowNum) -> {
        return new User(
                rs.getObject("id", UUID.class),
                rs.getObject("display_name", String.class),
                rs.getObject("created_at", OffsetDateTime.class));
    };
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> findById(UUID userId) {

        return jdbcTemplate.query(
                "SELECT id, display_name, created_at FROM users WHERE id = ?",
                USER_ROW_MAPPER,
                userId
        ).stream().findFirst();
    }
}