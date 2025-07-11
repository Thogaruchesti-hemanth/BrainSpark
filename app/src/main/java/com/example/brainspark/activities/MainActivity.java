package com.example.brainspark.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.brainspark.R;
import com.example.brainspark.models.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    private int currentQuestionIndex = 0;
    private LinearLayoutCompat optionsContainerLayout;
    private TextView questionNumberTextView;
    private TextView questionTextView;
    private ProgressBar progressBar;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        optionsContainerLayout = findViewById(R.id.options_container_layout);
        questionNumberTextView = findViewById(R.id.question_number_text_view);
        questionTextView = findViewById(R.id.question_text_view);
        progressBar = findViewById(R.id.progress_bar);

        optionsContainerLayout.animate().alpha(0f).setDuration(150).withEndAction(() -> {
            askQuestion(currentQuestionIndex);
            optionsContainerLayout.setAlpha(0f); // reset alpha before animating in
            optionsContainerLayout.animate().alpha(1f).setDuration(150).start();
        }).start();

        loadQuestions();
        Collections.shuffle(questionList);
        askQuestion(currentQuestionIndex);

        findViewById(R.id.next_question_button).setOnClickListener(view -> {
            String selectedAnswer = getSelectedAnswer();

            if (selectedAnswer == null) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            Question currentQuestion = questionList.get(currentQuestionIndex);
            if (selectedAnswer.equals(currentQuestion.getAnswer())) {
                score++;
            }

            currentQuestionIndex++;

            if (currentQuestionIndex < questionList.size()) {
                askQuestion(currentQuestionIndex);
            } else {
                // All questions completed
                Toast.makeText(this, "Quiz Completed!\nScore: " + score + "/" + questionList.size(), Toast.LENGTH_LONG).show();
            }
        });


    }

    private void askQuestion(int index) {
        optionsContainerLayout.removeAllViews();

        Question question = questionList.get(index);
        questionTextView.setText(question.getQuestion());

        String formatQuestionNumber = "Question " + (index + 1) + "/" + questionList.size();
        questionNumberTextView.setText(formatQuestionNumber);
        progressBar.setMax(questionList.size());
        progressBar.setProgress(index + 1);

        for (int i = 0; i < 4; i++) {
            View optionView = LayoutInflater.from(this).inflate(R.layout.option_layout, optionsContainerLayout, false);
            TextView optionTextView = optionView.findViewById(R.id.option_text_view);
            RadioButton radioButton = optionView.findViewById(R.id.option_radio);

            String optionText = "";
            switch (i) {
                case 0:
                    optionText = question.getOption1();
                    break;
                case 1:
                    optionText = question.getOption2();
                    break;
                case 2:
                    optionText = question.getOption3();
                    break;
                case 3:
                    optionText = question.getOption4();
                    break;
            }

            optionTextView.setText(optionText);

            // Exclusive selection logic
            radioButton.setOnClickListener(v -> {
                for (int j = 0; j < optionsContainerLayout.getChildCount(); j++) {
                    View otherOption = optionsContainerLayout.getChildAt(j);
                    RadioButton otherRadio = otherOption.findViewById(R.id.option_radio);
                    otherRadio.setChecked(false);
                }
                radioButton.setChecked(true);
            });

            optionsContainerLayout.addView(optionView);
        }

    }


    private void loadQuestions() {
        questionList = new ArrayList<>();
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

    }

    private String getSelectedAnswer() {
        for (int i = 0; i < optionsContainerLayout.getChildCount(); i++) {
            View optionView = optionsContainerLayout.getChildAt(i);
            RadioButton radio = optionView.findViewById(R.id.option_radio);
            TextView optionText = optionView.findViewById(R.id.option_text_view);

            if (radio.isChecked()) {
                return optionText.getText().toString().trim();
            }
        }
        return null;
    }
}