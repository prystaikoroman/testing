package com.testing.DTO;

public class LoginDto {
   private String login ;
   private String password;

    @Override
    public String toString() {
        return "LoginDto{" +
                "login='" + login + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
