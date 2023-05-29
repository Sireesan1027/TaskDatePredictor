package com.example.TaskDateCalculater.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DatePredicition {

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "no_of_days")
    private int noOfDays;

    @Column(name = "start_date")
    private LocalDate startDate;


}
