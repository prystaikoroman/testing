package Controller;

import Controller.Admin.Answer.AddAnswer;
import Controller.Admin.Answer.DeleteAnswer;
import Controller.Admin.Answer.EditAnswer;

public class LoginCommandContainer {
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
