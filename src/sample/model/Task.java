package sample.model;

import java.sql.Timestamp;

public class Task {
    private int userID;
    private int Taskid;
    private String discription;
    private Timestamp datecreated;
    private String task;
    private String refreshedTask;

    public Task() {
    }

    public Task(Timestamp datecreated, String discription, String task) {

        this.discription = discription;
        this.datecreated = datecreated;
        this.task = task;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTaskid() {
        return Taskid;
    }

    public void setTaskid(int taskid) {
        Taskid = taskid;
    }

    public String getRefreshedTask() {
        return refreshedTask;
    }

    public void setRefreshedTask(String refreshedTask) {
        this.refreshedTask = refreshedTask;
    }
}



