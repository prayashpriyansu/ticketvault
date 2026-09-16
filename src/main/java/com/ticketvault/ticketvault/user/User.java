package com.ticketvault.ticketvault.user;

import java.time.OffsetDateTime;
import java.util.UUID;

public record User(
        UUID id,
        String displayName,
        OffsetDateTime createdAt
) {
}
