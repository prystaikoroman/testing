package com.testing.Controller.Subject;

import com.testing.Controller.Command;

public class AdminSubjectCommandContainer {
    public static Command getCommand (String name) {
        switch (name){
            case "addSubject" :
                return new AddSubject();
            case "editSubject" :
                return new EditSubject();
            case "deleteSubject" :
                return new DeleteSubject();
            default:
                return null;
        }
    }
}
