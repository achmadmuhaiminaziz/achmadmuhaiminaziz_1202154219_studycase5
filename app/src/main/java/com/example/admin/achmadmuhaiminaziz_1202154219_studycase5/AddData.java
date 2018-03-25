package com.example.admin.achmadmuhaiminaziz_1202154219_studycase5;

/**
 * Created by Admin on 3/25/2018.
 */

public class AddData {
    String toDo, desk, priority;

    //konstruktor untuk method AddData
    public AddData(String toDo, String desk, String priority) {
        this.toDo = toDo;
        this.desk = desk;
        this.priority = priority;
    }

    //method setter dan getter untuk to do , description dan priority
    public String getTodo() {
        return toDo;
    }
    public void setTodo(String toDo) {
        this.toDo = toDo;
    }
    public String getDesc() {
        return desk;
    }
    public void setDesc(String desk) {
        this.desk = desk;
    }
    public String getPrior() {
        return priority;
    }
    public void setPrior(String priority) {
        this.priority = priority;
    }
}
