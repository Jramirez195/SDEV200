<%-- Program Name: AdditionQuiz.jsp --%>
<%-- Author: Jose Ramirez --%>
<%-- Date Last Updated: 3/3/2024 --%>
<%-- Summary: The JSP file for the project! --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Addition Quiz</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
    </style>
</head>
<body>

<%
    // Number of quiz questions
    int numberOfQuestions = 5;

    // Generate random numbers for the quiz questions
    int[] numbers1 = new int[numberOfQuestions];
    int[] numbers2 = new int[numberOfQuestions];
    for (int i = 0; i < numberOfQuestions; i++) {
        numbers1[i] = (int) (Math.random() * 10) + 1;
        numbers2[i] = (int) (Math.random() * 10) + 1;
    }

    // Store correct answers
    int[] correctAnswers = new int[numberOfQuestions];
    for (int i = 0; i < numberOfQuestions; i++) {
        correctAnswers[i] = numbers1[i] + numbers2[i];
    }

    // Process user input
    if (request.getMethod().equalsIgnoreCase("post")) {
        // Retrieve user answers
        int[] userAnswers = new int[numberOfQuestions];
        for (int i = 0; i < numberOfQuestions; i++) {
            String answerParam = "answer" + i;
            userAnswers[i] = Integer.parseInt(request.getParameter(answerParam));
        }

        // Check answers and calculate score
        int score = 0;
        for (int i = 0; i < numberOfQuestions; i++) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }

        // Display results
%>
<h2>Your Quiz Results</h2>
<p>Number of correct answers: <%= score %></p>
<%
} else {
    // Display quiz questions
%>
<h2>Addition Quiz</h2>
<form method="post" action="<%= request.getRequestURI() %>">
    <% for (int i = 0; i < numberOfQuestions; i++) { %>
    <p>Question <%= i + 1 %>: <%= numbers1[i] %> + <%= numbers2[i] %> =
        <input type="text" name="answer<%= i %>" required pattern="[0-9]+">
    </p>
    <% } %>
    <br>
    <input type="submit" value="Submit">
</form>
<%
    }
%>

</body>
</html>
