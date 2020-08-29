package com.example.todo;

public class Todo {
    private long ID;
    private String task;
    private String date;
    private String time;

    Todo(){}

    Todo(String task, String date, String time){
        this.task = task;
        this.date = date;
        this.time = time;
    }

    Todo(long ID, String task, String date, String time){
        this.ID = ID;
        this.task = task;
        this.date = date;
        this.time = time;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
