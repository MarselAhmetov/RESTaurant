package team404.restaurant.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class UuidIdEntity implements Identified {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : System.identityHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof UuidIdEntity) && (id != null) && (id.equals(((UuidIdEntity) obj).getId()));
    }
}
