package com.testing.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User_Test  implements Serializable {
    private int user_id;
    private int test_id;
    private Date started;
    private boolean finished;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public Date getStarted() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User_Test user_test = (User_Test) o;
        return user_id == user_test.user_id && test_id == user_test.test_id && finished == user_test.finished && Objects.equals(started, user_test.started);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, test_id, started, finished);
    }

    @Override
    public String toString() {
        return "User_Test{" +
                "user_id=" + user_id +
                ", test_id=" + test_id +
                ", started=" + started +
                ", finished=" + finished +
                '}';
    }
}
