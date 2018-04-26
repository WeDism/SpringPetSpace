package com.pet_space.custom_repositories;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;

public class PathEssenceForSearchFriend implements Iterable<String> {
    private String name;
    private String surname;
    private String patronymic;
    private List<String> listParams = new LinkedList<>();
    private String resultString;
    private StringBuilder resultStringBuilder = new StringBuilder();
    private static final String FIND_EXPRESSION = "LOWER(%s)=LOWER(?)";
    private final List<Runnable> construct = Arrays.asList(
            () -> {
                if (!isNullOrEmpty(this.name)) {
                    this.listParams.add(this.name);
                    if (this.listParams.size() > 1) this.resultStringBuilder.append(" %1$s ");
                    this.resultStringBuilder.append(String.format(FIND_EXPRESSION, "name"));
                }
            },
            () -> {
                if (!isNullOrEmpty(this.surname)) {
                    this.listParams.add(this.surname);
                    if (this.listParams.size() > 1) this.resultStringBuilder.append(" %1$s ");
                    this.resultStringBuilder.append(String.format(FIND_EXPRESSION, "surname"));
                }
            },
            () -> {
                if (!isNullOrEmpty(this.patronymic)) {
                    this.listParams.add(this.patronymic);
                    if (this.listParams.size() > 1) this.resultStringBuilder.append(" %1$s ");
                    this.resultStringBuilder.append(String.format(FIND_EXPRESSION, "patronymic"));
                }
            }
    );


    public PathEssenceForSearchFriend(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;

        if (isNullOrEmpty(this.name) && isNullOrEmpty(this.surname) && isNullOrEmpty(this.patronymic))
            throw new IllegalArgumentException();

        construct.forEach(Runnable::run);

        if (this.listParams.size() == 2) this.resultString = String.format(this.resultStringBuilder.toString(), "AND");
        else this.resultString = String.format(this.resultStringBuilder.toString(), "");
    }

    @NotNull
    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return listParams.size() > 0;
            }

            @Override
            public String next() {
                if (!this.hasNext())
                    return null;
                return listParams.remove(0);
            }
        };
    }

    @Override
    public String toString() {
        return this.resultString;
    }

}
