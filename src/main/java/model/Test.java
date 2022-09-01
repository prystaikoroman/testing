package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Test  implements Serializable {
    private int id;
    private String task;
    private String name;
    private int difficulty;
    private short passingTimeMin;
    private int subject_id;

private Date  started;
private boolean finished;
private double percent_result;

    public double getPercent_result() {
        return percent_result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id == test.id && difficulty == test.difficulty && passingTimeMin == test.passingTimeMin && subject_id == test.subject_id && finished == test.finished && Double.compare(test.percent_result, percent_result) == 0 && Objects.equals(task, test.task) && Objects.equals(name, test.name) && Objects.equals(started, test.started);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task, name, difficulty, passingTimeMin, subject_id, started, finished, percent_result);
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
                ", started=" + started +
                ", finished=" + finished +
                ", percent_result=" + percent_result +
                '}';
    }

    public void setPercent_result(double percent_result) {
        this.percent_result = percent_result;
    }

    public Date getStarted() {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        return dateFormat.format(started);
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

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

}
