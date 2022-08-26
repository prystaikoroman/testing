package Controller.Admin.User;

import Controller.Command;

public class AdminUserCommandContainer {
    public static Command getCommand (String name) {
        switch (name){
            case "addUser" :
                return new AddUser(name);
            case "regUser" :
                return new AddUser(name);
            case "editUser" :
                return new EditUser();
            case "deleteUser" :
                return new DeleteUser();
            default:
                return null;
        }
    }
}
