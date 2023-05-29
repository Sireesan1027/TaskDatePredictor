package com.example.TaskDateCalculater.Service;

import com.example.TaskDateCalculater.Entity.Task;

import java.util.List;

public interface TaskService {

    public Task save(Task taskDate);
    public List<Task> getAll();

}
