package com.example.TaskDateCalculater;

import com.example.TaskDateCalculater.Entity.Task;
import com.example.TaskDateCalculater.Repository.TaskRepository;
import com.example.TaskDateCalculater.ServiceImpl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldCalculateEndDateAndSaveTask() {
        // Arrange
        Task taskData = new Task();
        taskData.setStartDate(LocalDate.parse("2023-06-01"));
        taskData.setNoOfDays(5);

        when(taskRepository.save(any(Task.class))).thenReturn(taskData);

        // Act
        Task savedTask = taskService.save(taskData);

        // Assert
        assertEquals(LocalDate.parse("2023-06-08"), savedTask.getEndDate());
        verify(taskRepository, times(1)).save(taskData);
    }

    @Test
    void removeParagraphTags_ShouldRemoveOpeningAndClosingTags() {
        // Arrange
        String input = "<p>Test paragraph</p>";

        // Act
        String output = taskService.removeParagraphTags(input);

        // Assert
        assertEquals("Test paragraph", output);
    }

    @Test
    void getAll_ShouldReturnAllTasks() {
        // Arrange
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());

        when(taskRepository.findAll()).thenReturn(tasks);

        // Act
        List<Task> allTasks = taskService.getAll();

        // Assert
        assertEquals(2, allTasks.size());
        verify(taskRepository, times(1)).findAll();
    }
}
