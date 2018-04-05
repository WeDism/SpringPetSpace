package com.pet_space.models.pets;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "genus_pet")
public class GenusPet implements Serializable {

    @Id
    @NotBlank
    @Size(min = 2, max = 200, message = "Genus pet name must be between 2 and 200 characters long.")
    private String name;

    public GenusPet() {
    }

    public GenusPet(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public GenusPet setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenusPet)) return false;
        GenusPet genusPet = (GenusPet) o;
        return Objects.equals(getName(), genusPet.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
