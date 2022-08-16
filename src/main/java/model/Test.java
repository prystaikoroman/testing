package model;

import java.util.Objects;

public class Test {
    private int id;
    private String task;
    private String name;
    private int difficulty;
    private short passingTimeMin;
    private int subject_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public short getPassingTimeMin() {
        return passingTimeMin;
    }

    public void setPassingTimeMin(short passingTimeMin) {
        this.passingTimeMin = passingTimeMin;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", passingTimeMin=" + passingTimeMin +
                ", subject_id=" + subject_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id == test.id && difficulty == test.difficulty && passingTimeMin == test.passingTimeMin && subject_id == test.subject_id && task.equals(test.task) && name.equals(test.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task, name, difficulty, passingTimeMin, subject_id);
    }
}