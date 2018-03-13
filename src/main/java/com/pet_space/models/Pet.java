package com.pet_space.models;

import com.pet_space.models.essences.UserEssence;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Pet implements Serializable {
    private UUID petId;
    private String name;
    private Double weight;
    private LocalDateTime birthday;
    private UserEssence owner;
    private GenusPet genusPet;
    private Set<UserEssence> followersPet = new HashSet<>();

    public Pet() {
    }

    public static IName builder() {
        return new BuilderPet();
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "pet_id", columnDefinition = "uuid")
    public UUID getPetId() {
        return this.petId;
    }

    public Pet setPetId(UUID petId) {
        this.petId = petId;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Pet setName(String name) {
        this.name = name;
        return this;
    }

    public Double getWeight() {
        return this.weight;
    }

    public Pet setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_essence_id")
    public UserEssence getOwner() {
        return this.owner;
    }

    public Pet setOwner(UserEssence owner) {
        this.owner = owner;
        return this;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genus")
    public GenusPet getGenusPet() {
        return this.genusPet;
    }

    public Pet setGenusPet(GenusPet genusPet) {
        this.genusPet = genusPet;
        return this;
    }

    public LocalDateTime getBirthday() {
        return this.birthday;
    }

    public Pet setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "followByPets", fetch = FetchType.EAGER)
    public Set<UserEssence> getFollowersPet() {
        return this.followersPet;
    }

    public Pet setFollowersPet(Set<UserEssence> followersPet) {
        this.followersPet = followersPet;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return Objects.equals(this.getPetId(), pet.getPetId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getPetId());
    }

    public interface IName {
        IOwner name(@NotNull String name);
    }

    public interface IOwner {
        IGenusPet owner(@NotNull UserEssence owner);
    }

    public interface IGenusPet {
        IBuild genusPet(GenusPet genusPet);
    }

    public interface IBuild {
        IBuild weight(Double weight);

        IBuild birthday(LocalDateTime birthday);

        Pet build();
    }

    public static class BuilderPet implements IName, IOwner, IGenusPet, IBuild {
        Pet pet = new Pet();

        @Override
        public IOwner name(@NotNull String name) {
            this.pet.setName(name);
            return this;
        }

        @Override
        public IGenusPet owner(@NotNull UserEssence owner) {
            this.pet.setOwner(owner);
            return this;
        }

        @Override
        public IBuild genusPet(GenusPet genusPet) {
            this.pet.setGenusPet(genusPet);
            return this;
        }

        @Override
        public Pet build() {
            List<?> setArguments = Arrays.asList(this.pet.name, this.pet.owner, this.pet.genusPet);
            if (setArguments.stream().anyMatch(Objects::isNull)) {
                throw new IllegalArgumentException(
                        String.format("The arguments(%s) are null", setArguments.stream().filter(Objects::isNull).collect(Collectors.toList())));
            }
            return this.pet;
        }

        @Override
        public IBuild weight(Double weight) {
            this.pet.setWeight(weight);
            return this;
        }

        @Override
        public IBuild birthday(LocalDateTime birthday) {
            this.pet.setBirthday(birthday);
            return this;
        }
    }
}
