package model;

import java.util.Date;
import java.util.Objects;

public class User_Test_Answer {
    private int user_test_user_id;
    private int user_test_test_id;
    private int answer_id;
    private boolean correct;
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getUser_test_user_id() {
        return user_test_user_id;
    }

    public void setUser_test_user_id(int user_test_user_id) {
        this.user_test_user_id = user_test_user_id;
    }

    public int getUser_test_test_id() {
        return user_test_test_id;
    }

    public void setUser_test_test_id(int user_test_test_id) {
        this.user_test_test_id = user_test_test_id;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User_Test_Answer that = (User_Test_Answer) o;
        return user_test_user_id == that.user_test_user_id && user_test_test_id == that.user_test_test_id && answer_id == that.answer_id && correct == that.correct && selected == that.selected;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_test_user_id, user_test_test_id, answer_id, correct, selected);
    }

    @Override
    public String toString() {
        return "User_Test_Answer{" +
                "user_test_user_id=" + user_test_user_id +
                ", user_test_test_id=" + user_test_test_id +
                ", answer_id=" + answer_id +
                ", correct=" + correct +
                ", selected=" + selected +
                '}';
    }
}
