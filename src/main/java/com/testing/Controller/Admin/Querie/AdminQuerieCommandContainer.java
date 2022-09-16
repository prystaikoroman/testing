package com.testing.Controller.Admin.Querie;

import com.testing.Controller.Command;

public class AdminQuerieCommandContainer {
    public static Command getCommand (String name) {
        switch (name){
            case "addQuerie" :
                return new AddQuerie();
            case "editQuerie" :
                return new EditQuerie();
            case "userPassing" :
                return new UserQueriesPass();
            case "userQuerieCommit" :
                return new UserQuerieCommit();
            case "deleteQuerie" :
                return new DeleteQuerie();
            default:
                return null;
        }
    }
}
