package com.testing.Controller;

public class LoginCommandContainer {
    public LoginCommandContainer() {
    }

    public static Command getCommand (String name) {
        switch (name){
            case "login" :
                return new Login();
            case "register" :
                return new Register();

            default:
                return null;
        }
    }

}
