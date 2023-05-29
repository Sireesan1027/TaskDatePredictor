package com.example.TaskDateCalculater.Service;

import com.example.TaskDateCalculater.Entity.DatePredicition;
import org.springframework.http.ResponseEntity;

public interface DatePredictionService {


    ResponseEntity<Object> save(DatePredicition datePredicition);
}
