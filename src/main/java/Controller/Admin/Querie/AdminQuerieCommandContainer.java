package Controller.Admin.Querie;

import Controller.Command;

public class AdminQuerieCommandContainer {
    public static Command getCommand (String name) {
        switch (name){
            case "addQuerie" :
                return new AddQuerie();
            case "editQuerie" :
                return new EditQuerie();
            case "deleteQuerie" :
                return new DeleteQuerie();
            default:
                return null;
        }
    }
}
