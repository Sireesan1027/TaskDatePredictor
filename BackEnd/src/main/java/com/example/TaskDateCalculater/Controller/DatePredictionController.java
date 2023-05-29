package com.example.TaskDateCalculater.Controller;

import com.example.TaskDateCalculater.Entity.DatePredicition;
import com.example.TaskDateCalculater.Service.DatePredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class DatePredictionController {

    @Autowired
    DatePredictionService datePredictionService;

    @PostMapping(value = "/endDatePrediction/create")
    public ResponseEntity<Object> datePredicition(@RequestBody DatePredicition datePredicition)
    {
        return datePredictionService.save(datePredicition);
    }
}
