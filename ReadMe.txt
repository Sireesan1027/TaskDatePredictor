{
    "taskName":"test",
    "endDate":"2023-05-29T07:51:06.000Z",
    "noOfDays": 0,
    "startDate": "2023-05-29T07:51:06.000Z",
    "description":"desc"
}

http://localhost:8085/createTask
http://localhost:8085/getAllTasks
http://localhost:8085/endDatePrediction/create

 //API For Find Holiday in Sri Lanka

RestTemplate restTemplate = new RestTemplate();
        String apiKey = "c33083e83168a8353dbc7baa6b8b08ba4dc7a3ea"; // Replace with your Calendarific API key
        String country = "LK"; // Country code for Sri Lanka
        String year = String.valueOf(currentDate.getYear()); // Year for which you want to check the holidays
        String url = "https://calendarific.com/api/v2/holidays?api_key=" + apiKey + "&country=" + country + "&year=" + year;