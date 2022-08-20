package Controller.Admin.Test;

import Controller.Command;

public class AdminTestCommandContainer {
    public static Command getCommand (String name) {
        switch (name){
            case "addTest" :
                return new AddTest();
            case "editTest" :
                return new EditTest();
            case "deleteTest" :
                return new DeleteTest();
            default:
                return null;
        }
    }
}
