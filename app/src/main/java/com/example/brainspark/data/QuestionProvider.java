package com.example.brainspark.data;

import com.example.brainspark.models.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionProvider {

    public static List<Question> getQuestions(){

        List<Question> questionList = new ArrayList<>();

        questionList.add(new Question("What is the capital of France?", "Berlin", "Madrid", "Paris", "Rome", "Paris"));

        questionList.add(new Question("Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Venus", "Mars"));

        questionList.add(new Question("Who wrote the play 'Romeo and Juliet'?", "Leo Tolstoy", "William Shakespeare", "Oscar Wilde", "Charles Dickens", "William Shakespeare"));

        questionList.add(new Question("Which element has the chemical symbol 'O'?", "Oxygen", "Gold", "Osmium", "Oganesson", "Oxygen"));

        questionList.add(new Question("In which year did India gain independence?", "1945", "1946", "1947", "1950", "1947"));

        questionList.add(new Question("What is the largest ocean on Earth?", "Atlantic Ocean", "Indian Ocean", "Pacific Ocean", "Arctic Ocean", "Pacific Ocean"));

        questionList.add(new Question("Which language is primarily used for Android development?", "Java", "Python", "Swift", "Kotlin", "Java"));

        questionList.add(new Question("Which country won the FIFA World Cup in 2022?", "Brazil", "Germany", "France", "Argentina", "Argentina"));

        questionList.add(new Question("What is the process of converting water into vapor called?", "Condensation", "Evaporation", "Precipitation", "Filtration", "Evaporation"));

        questionList.add(new Question("What is the square root of 144?", "10", "12", "14", "16", "12"));


        return  questionList;
    }

}
