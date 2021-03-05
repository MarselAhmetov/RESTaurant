package team404.restaurant.general.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class UuidIdEntity implements Identified {
    @Id
    private UUID id;

    public UuidIdEntity() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
