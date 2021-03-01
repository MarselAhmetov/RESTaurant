package team404.restaurant.dto;

import java.util.UUID;

public abstract class UuidIdDto {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
