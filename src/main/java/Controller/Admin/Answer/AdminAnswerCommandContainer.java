package Controller.Admin.Answer;

import Controller.Command;

public class AdminAnswerCommandContainer {
    public static Command getCommand (String name) {
        switch (name){
            case "addAnswer" :
                return new AddAnswer();
            case "editAnswer" :
                return new EditAnswer();
            case "deleteAnswer" :
                return new DeleteAnswer();
            default:
                return null;
        }
    }
}
