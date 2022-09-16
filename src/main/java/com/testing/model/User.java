package com.testing.model;

import java.io.Serializable;
import java.util.Objects;

public class User  implements Serializable {
    private int id;
    private String login;
    private String email;
    private String lastname;
    private String password;
    private Boolean admin;
    private Boolean locked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && login.equals(user.login) && Objects.equals(email, user.email) && Objects.equals(lastname, user.lastname) && password.equals(user.password) && admin.equals(user.admin) && locked.equals(user.locked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, lastname, password, admin, locked);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", lastname='" + lastname + '\'' +
                ", admin=" + admin +
                ", locked=" + locked +
                '}';
    }

}
