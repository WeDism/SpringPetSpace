package com.pet_space.models.essences;

import com.pet_space.models.Pet;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "user_essence")
public class UserEssence implements Serializable {
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    private LocalDateTime birthday;
    private UUID userEssenceId;
    @Column(nullable = false, unique = true)
    private String nickname;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    private String patronymic;
    @Column(name = "about_of_self")
    private String aboutOfSelf;
    private RoleEssence role;
    private StatusEssence statusEssence;
    private Set<Pet> pets = new HashSet<>();
    private Set<Pet> followByPets = new HashSet<>();
    private Set<Friends> requestedFriendsFrom = new HashSet<>();
    private Set<Friends> requestedFriendsTo = new HashSet<>();

    public UserEssence() {
    }

    public static INickname builder() {
        return new BuilderUserEssence();
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_essence_id", columnDefinition = "uuid")
    public UUID getUserEssenceId() {
        return this.userEssenceId;
    }

    public UserEssence setUserEssenceId(UUID userEssenceId) {
        this.userEssenceId = userEssenceId;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public UserEssence setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public UserEssence setEmail(String email) {
        this.email = email;
        return this;
    }


    public String getNickname() {
        return this.nickname;
    }

    public UserEssence setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public UserEssence setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return this.surname;
    }

    public UserEssence setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPatronymic() {
        return this.patronymic;
    }

    public UserEssence setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    @ManyToOne()
    @JoinColumn(name = "role")
    public RoleEssence getRole() {
        return this.role;
    }

    public UserEssence setRole(RoleEssence role) {
        this.role = role;
        return this;
    }

    public String getAboutOfSelf() {
        return this.aboutOfSelf;
    }

    public UserEssence setAboutOfSelf(String aboutOfSelf) {
        this.aboutOfSelf = aboutOfSelf;
        return this;
    }

    @ManyToOne()
    @JoinColumn(name = "status")
    public StatusEssence getStatusEssence() {
        return this.statusEssence;
    }

    public UserEssence setStatusEssence(StatusEssence statusEssence) {
        this.statusEssence = statusEssence;
        return this;
    }

    public LocalDateTime getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "owner")
    public Set<Pet> getPets() {
        return this.pets;
    }

    public UserEssence setPets(Set<Pet> pets) {
        this.pets = pets;
        return this;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "follow_pets",
            joinColumns = {@JoinColumn(name = "user_essence_id")},
            inverseJoinColumns = {@JoinColumn(name = "pet_id")}
    )
    public Set<Pet> getFollowByPets() {
        return this.followByPets;
    }

    public UserEssence setFollowByPets(Set<Pet> followByPets) {
        this.followByPets = followByPets;
        return this;
    }

    @OneToMany(mappedBy = "primaryKey.userEssence", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Friends> getRequestedFriendsFrom() {
        return this.requestedFriendsFrom;
    }

    public UserEssence setRequestedFriendsFrom(Set<Friends> requestedFriendsFrom) {
        this.requestedFriendsFrom = requestedFriendsFrom;
        return this;
    }

    @OneToMany(mappedBy = "primaryKey.friend", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Friends> getRequestedFriendsTo() {
        return this.requestedFriendsTo;
    }

    public UserEssence setRequestedFriendsTo(Set<Friends> requestedFriendsTo) {
        this.requestedFriendsTo = requestedFriendsTo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEssence)) return false;
        UserEssence userEssence = (UserEssence) o;
        return Objects.equals(getUserEssenceId(), userEssence.getUserEssenceId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserEssenceId());
    }

    public interface INickname {
        IName nickname(@NotNull String nickname);
    }

    public interface IName {
        ISurname name(@NotNull String name);
    }

    public interface ISurname {
        IRole surname(@NotNull String surname);
    }

    public interface IRole {
        IStatusEssence role(@NotNull RoleEssence role);
    }

    public interface IStatusEssence {
        IEmail statusEssence(@NotNull StatusEssence statusEssence);
    }

    public interface IEmail {
        IPassword email(@NotNull String email);
    }

    public interface IPassword {
        IBuild password(@NotNull String email);
    }

    public interface IBuild {
        IBuild patronymic(String patronymic);

        IBuild birthday(LocalDateTime birthday);

        IBuild aboutOfSelf(String text);

        UserEssence build();
    }

    public static class BuilderUserEssence implements INickname, ISurname, IName, IRole, IStatusEssence, IEmail, IPassword, IBuild {

        UserEssence userEssence = new UserEssence();

        private BuilderUserEssence() {
        }

        @Override
        public IName nickname(@NotNull String nickname) {
            this.userEssence.setNickname(nickname);
            return this;
        }

        @Override
        public ISurname name(@NotNull String name) {
            this.userEssence.setName(name);
            return this;
        }

        @Override
        public IRole surname(@NotNull String surname) {
            this.userEssence.setSurname(surname);
            return this;
        }

        @Override
        public IStatusEssence role(@NotNull RoleEssence role) {
            this.userEssence.setRole(role);
            return this;
        }

        @Override
        public IEmail statusEssence(@NotNull StatusEssence statusEssence) {
            this.userEssence.setStatusEssence(statusEssence);
            return this;
        }

        @Override
        public IPassword email(@NotNull String email) {
            this.userEssence.setEmail(email);
            return this;
        }

        @Override
        public IBuild password(@NotNull String password) {
            this.userEssence.setPassword(password);
            return this;
        }

        public UserEssence build() {
            List<?> setArguments = Arrays.asList(this.userEssence.nickname,
                    this.userEssence.name, this.userEssence.surname, this.userEssence.role,
                    this.userEssence.statusEssence, this.userEssence.email, this.userEssence.password);
            if (setArguments.stream().anyMatch(Objects::isNull)) {
                throw new IllegalArgumentException(
                        String.format("The arguments(%s) are null", setArguments.stream().filter(Objects::isNull).collect(Collectors.toList())));
            }
            return this.userEssence;
        }

        public BuilderUserEssence patronymic(String patronymic) {
            this.userEssence.setPatronymic(patronymic);
            return this;
        }

        public BuilderUserEssence birthday(LocalDateTime birthday) {
            this.userEssence.setBirthday(birthday);
            return this;
        }

        @Override
        public IBuild aboutOfSelf(String text) {
            this.userEssence.setAboutOfSelf(text);
            return this;
        }
    }
}
