package com.example.TaskDateCalculater.Controller;
import com.example.TaskDateCalculater.Entity.HeaderItem;
import com.example.TaskDateCalculater.Entity.Task;
import com.example.TaskDateCalculater.Entity.TaskResponse;
import com.example.TaskDateCalculater.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
@RestController
@CrossOrigin("*")
public class TaskController {
    @Autowired
    TaskService taskService;

@GetMapping("/getAllTasks")
public TaskResponse getAllTasks() {
    List<Task> tasks = taskService.getAll();

    List<HeaderItem> header = Arrays.asList(
            new HeaderItem("id", false, "text", "Id"),
            new HeaderItem("noOfDays", true, "text", "Task Days"),
            new HeaderItem("taskName", true, "text", "Task Name"),
            new HeaderItem("description", true, "text", "Task Description"),
            new HeaderItem("startDate", true, "date", "Start Date"),
            new HeaderItem("endDate", true, "date", "End Date")
    );

    return new TaskResponse(tasks, header);
}


    @PostMapping(value = "/createTask")
    public Task createTask(@RequestBody Task taskDate)
    {
        return taskService.save(taskDate);
    }

}
