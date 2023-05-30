package com.example.TaskDateCalculater.ServiceImpl;


import com.example.TaskDateCalculater.Entity.Task;
import com.example.TaskDateCalculater.Repository.TaskRepository;
import com.example.TaskDateCalculater.Service.TaskService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {


  @Autowired
  TaskRepository taskRepository;


    @Override
    public Task save(Task taskData) {
        LocalDate startDate = taskData.getStartDate();
        int workingDaysNeeded = taskData.getNoOfDays();

        LocalDate endDate = calculateEndDate(startDate, workingDaysNeeded);
        taskData.setEndDate(endDate);

        String description = taskData.getDescription();
        description = removeParagraphTags(description);
        taskData.setDescription(description);

        return taskRepository.save(taskData);
    }

    public String removeParagraphTags(String input) {
        // Remove opening paragraph tag
        String output = input.replace("<p>", "");
        // Remove closing paragraph tag
        output = output.replace("</p>", "");
        // Trim any leading or trailing spaces
        output = output.trim();

        return output;
    }




    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }


    public static LocalDate calculateEndDate(LocalDate startDate, int workingDaysNeeded) {
        int workingDaysCount = 0;
        LocalDate currentDate = startDate;

        RestTemplate restTemplate = new RestTemplate();
        String apiKey = "c33083e83168a8353dbc7baa6b8b08ba4dc7a3ea"; // Replace with your Calendarific API key
        String country = "LK"; // Country code for Sri Lanka
        String year = String.valueOf(currentDate.getYear()); // Year for which you want to check the holidays
        String url = "https://calendarific.com/api/v2/holidays?api_key=" + apiKey + "&country=" + country + "&year=" + year;

        // Make the API request and retrieve the JSON response
        String jsonResponse = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Parse the JSON response
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode holidaysNode = root.path("response").path("holidays");

            while (workingDaysCount < workingDaysNeeded) {
                // Skip weekends (Saturday and Sunday)
                if (currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && currentDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    // Check if the current date is a holiday
                    if (isHoliday(holidaysNode, currentDate)) {
                        currentDate = currentDate.plusDays(1);
                        continue;
                    }
                    workingDaysCount++;
                }
                currentDate = currentDate.plusDays(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentDate.minusDays(1); // Subtract 1 day to get the actual end date
    }

    private static boolean isHoliday(JsonNode holidaysNode, LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (JsonNode holidayNode : holidaysNode) {
            String holidayDate = holidayNode.path("date").path("iso").asText();

            try {
                LocalDate parsedDate = LocalDate.parse(holidayDate, formatter);
                if (parsedDate.equals(date)) {
                    return true;
                }
            } catch (DateTimeParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
