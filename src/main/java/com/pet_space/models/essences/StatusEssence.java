package com.pet_space.models.essences;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "status_essence")
public class StatusEssence implements Serializable {
    public enum StatusEssenceEnum {
        INACTIVE, DELETED, ACTIVE
    }

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEssenceEnum statusEssenceEnum;

    public StatusEssence() {
    }

    public StatusEssence(StatusEssenceEnum statusEssenceEnum) {
        super();
        this.statusEssenceEnum = statusEssenceEnum;
    }

    public StatusEssenceEnum getStatusEssenceEnum() {
        return this.statusEssenceEnum;
    }

    public StatusEssence setStatusEssenceEnum(StatusEssenceEnum statusEssenceEnum) {
        this.statusEssenceEnum = statusEssenceEnum;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatusEssence)) return false;
        StatusEssence that = (StatusEssence) o;
        return getStatusEssenceEnum() == that.getStatusEssenceEnum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatusEssenceEnum());
    }
}
