package model;

import java.util.Objects;

public class Answer {
    private int id;
    private String answer;
    private boolean correct;
    private int querie_id;
    private boolean userChecking;

    public boolean isUserChecking() {
        return userChecking;
    }

    public void setUserChecking(boolean userChecking) {
        this.userChecking = userChecking;
    }


    public Answer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public int getQuerie_id() {
        return querie_id;
    }

    public void setQuerie_id(int querie_id) {
        this.querie_id = querie_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer1 = (Answer) o;
        return id == answer1.id && correct == answer1.correct && querie_id == answer1.querie_id && answer.equals(answer1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answer, correct, querie_id);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                ", correct=" + correct +
                ", querie_id=" + querie_id +
                ", userChecking=" + userChecking +
                '}';
    }
}
