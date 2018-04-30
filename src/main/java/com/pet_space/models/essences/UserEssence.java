package com.pet_space.models.essences;

import com.pet_space.models.messages.Message;
import com.pet_space.models.messages.MessageOfUser;
import com.pet_space.models.pets.Pet;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "user_essence")
public class UserEssence implements Serializable {
    private String password;
    private String email;
    private LocalDateTime birthday;
    private UUID userEssenceId;
    private String nickname;
    private String name;
    private String surname;
    private String patronymic;
    private String aboutOfSelf;
    private RoleEssence role;
    private StatusEssence statusEssence;
    private Set<Pet> pets = new HashSet<>();
    private Set<Pet> followByPets = new HashSet<>();
    private Set<Message> messagesFrom = new HashSet<>();
    private Set<MessageOfUser> messagesTo = new HashSet<>();
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

    @NotBlank
    @Size(min = 4, max = 200, message = "Password must be between 4 and 200 characters long.")
    @Column(nullable = false)
    public String getPassword() {
        return this.password;
    }

    public UserEssence setPassword(String password) {
        this.password = password;
        return this;
    }

    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",
            message = "Mail isn't valid")
    @Column(nullable = false, unique = true)
    public String getEmail() {
        return this.email;
    }

    public UserEssence setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(nullable = false, unique = true)
    public String getNickname() {
        return this.nickname;
    }

    public UserEssence setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    @NotBlank
    @Size(min = 2, max = 200, message = "Name must be between 2 and 200 characters long.")
    @Column(nullable = false)
    public String getName() {
        return this.name;
    }

    public UserEssence setName(String name) {
        this.name = name;
        return this;
    }

    @NotBlank
    @Size(min = 2, max = 200, message = "Name must be between 2 and 200 characters long.")
    @Column(nullable = false)
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

    @javax.validation.constraints.NotNull
    @ManyToOne()
    @JoinColumn(name = "role", nullable = false)
    public RoleEssence getRole() {
        return this.role;
    }

    public UserEssence setRole(RoleEssence role) {
        this.role = role;
        return this;
    }

    @Column(name = "about_of_self")
    public String getAboutOfSelf() {
        return this.aboutOfSelf;
    }

    public UserEssence setAboutOfSelf(String aboutOfSelf) {
        this.aboutOfSelf = aboutOfSelf;
        return this;
    }

    @ManyToOne()
    @JoinColumn(name = "status", nullable = false)
    public StatusEssence getStatusEssence() {
        return this.statusEssence;
    }

    public UserEssence setStatusEssence(StatusEssence statusEssence) {
        this.statusEssence = statusEssence;
        return this;
    }

    @DateTimeFormat(iso = ISO.DATE_TIME)
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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "follow_pets",
            joinColumns = {@JoinColumn(name = "user_essence_id")}, inverseJoinColumns = {@JoinColumn(name = "pet_id")},
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    public Set<Pet> getFollowByPets() {
        return this.followByPets;
    }

    public UserEssence setFollowByPets(Set<Pet> followByPets) {
        this.followByPets = followByPets;
        return this;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    public Set<Message> getMessagesFrom() {
        return this.messagesFrom;
    }

    public UserEssence setMessagesFrom(Set<Message> messagesFrom) {
        this.messagesFrom = messagesFrom;
        return this;
    }

    @OneToMany(mappedBy = "primaryKey.owner", cascade = CascadeType.ALL)
    public Set<MessageOfUser> getMessagesTo() {
        return this.messagesTo;
    }

    public UserEssence setMessagesTo(Set<MessageOfUser> messagesTo) {
        this.messagesTo = messagesTo;
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
        UserEssence that = (UserEssence) o;
        return Objects.equals(this.getUserEssenceId(), that.getUserEssenceId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserEssenceId());
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
