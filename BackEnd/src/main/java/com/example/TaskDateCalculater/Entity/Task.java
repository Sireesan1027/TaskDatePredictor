package com.example.TaskDateCalculater.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "no_of_days")
    private int noOfDays;

    @Column(name = "start_date")
    private LocalDate startDate;

    private String description;

}
