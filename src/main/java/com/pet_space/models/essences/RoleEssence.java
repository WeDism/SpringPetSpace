package com.pet_space.models.essences;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "role_essence")
public class RoleEssence implements Serializable {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEssenceEnum roleEssenceEnum;
    public RoleEssence() {
    }

    public RoleEssence(RoleEssenceEnum roleEssenceEnum) {
        super();
        this.roleEssenceEnum = roleEssenceEnum;
    }

    public static RoleEssence of(RoleEssenceEnum roleEssenceEnum) {
        return new RoleEssence(roleEssenceEnum);
    }

    public RoleEssenceEnum getRoleEssenceEnum() {
        return this.roleEssenceEnum;
    }

    public void setRoleEssenceEnum(RoleEssenceEnum roleEssenceEnum) {
        this.roleEssenceEnum = roleEssenceEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleEssence)) return false;
        RoleEssence that = (RoleEssence) o;
        return this.getRoleEssenceEnum() == that.getRoleEssenceEnum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getRoleEssenceEnum());
    }

    @Override
    public String toString() {
        return this.roleEssenceEnum.name();
    }

    public enum RoleEssenceEnum {
        ROOT, ADMIN, USER
    }
}
