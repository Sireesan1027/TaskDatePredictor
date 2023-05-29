package com.example.TaskDateCalculater.Entity;

import java.util.List;

public class TaskResponse {
    private List<Task> data;
    private List<HeaderItem> header;

    public TaskResponse(List<Task> data, List<HeaderItem> header) {
        this.data = data;
        this.header = header;
    }

    public List<Task> getData() {
        return data;
    }

    public void setData(List<Task> data) {
        this.data = data;
    }

    public List<HeaderItem> getHeader() {
        return header;
    }

    public void setHeader(List<HeaderItem> header) {
        this.header = header;
    }
}

