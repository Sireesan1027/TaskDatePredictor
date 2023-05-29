package com.example.TaskDateCalculater.ServiceImpl;

import com.example.TaskDateCalculater.Entity.DatePredicition;
import com.example.TaskDateCalculater.Service.DatePredictionService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;


@Service
public class DatePredictionServiceImpl implements DatePredictionService {

@Autowired
TaskServiceImpl taskServiceImpl;

    @Override
    public ResponseEntity<Object> save(DatePredicition datePredicition) {
        LocalDate startDate = datePredicition.getStartDate();
        int workingDaysNeeded = datePredicition.getNoOfDays();

        LocalDate endDate = taskServiceImpl.calculateEndDate(startDate, workingDaysNeeded);

        Map<String, Object> response = new HashMap<>();
        response.put("startDate", startDate);
        response.put("endDate", endDate);
        response.put("noOfDays", workingDaysNeeded);

        return ResponseEntity.ok(response);
    }

}
