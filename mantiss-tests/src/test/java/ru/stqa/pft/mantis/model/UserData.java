package ru.stqa.pft.mantis.model;

public class UserData {
    private String username;
    private String email;
    private String id;

    public UserData username(String username) {
        this.username = username;
        return this;
    }

    public UserData email(String email) {
        this.email = email;
        return this;
    }

    public UserData id(String id) {
        this.id = id;
        return this;
    }

    public String username() {
        return username;
    }

    public String email() {
        return email;
    }

    public String id() {
        return id;
    }
}
