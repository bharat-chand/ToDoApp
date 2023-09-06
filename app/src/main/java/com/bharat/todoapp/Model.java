package com.bharat.todoapp;

import android.widget.EditText;

public class Model {
    private String title;
    private String description;

    public Model(){

    }

    public Model(String title, String description) {
        this.title = title;
        this.description = description;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

