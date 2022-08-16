package model;

import java.util.Objects;

public class Querie {
    private int id;
    private String question;
    private int test_id;

    public Querie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Querie querie = (Querie) o;
        return id == querie.id && test_id == querie.test_id && question.equals(querie.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, test_id);
    }

    @Override
    public String toString() {
        return "Querie{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", test_id=" + test_id +
                '}';
    }
}
