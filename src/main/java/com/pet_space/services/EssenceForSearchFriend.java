package com.pet_space.services;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;

public class EssenceForSearchFriend implements Iterable<String> {
    private String name;
    private String surname;
    private String patronymic;
    private List<String> listParams = new LinkedList<>();
    private String resultString;
    private StringBuilder resultStringBuilder = new StringBuilder();
    private final List<Runnable> construct = Arrays.asList(
            () -> {
                if (!isNullOrEmpty(this.name)) {
                    this.listParams.add(this.name);
                    this.resultStringBuilder.append("LOWER(name)=LOWER(?)");
                }
            },
            () -> {
                if (!isNullOrEmpty(this.surname)) {
                    this.listParams.add(this.surname);
                    if (this.listParams.size() > 0) this.resultStringBuilder.append(" %1$s ");
                    this.resultStringBuilder.append("LOWER(surname)=LOWER(?)");
                }
            },
            () -> {
                if (!isNullOrEmpty(this.patronymic)) {
                    this.listParams.add(this.patronymic);
                    if (this.listParams.size() > 1) this.resultStringBuilder.append(" %1$s ");
                    this.resultStringBuilder.append("LOWER(patronymic)=LOWER(?)");
                }
            }
    );


    public EssenceForSearchFriend(String name, String surname, String patronymic) {
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

    public String resultPath() {
        return this.resultString;
    }
}
