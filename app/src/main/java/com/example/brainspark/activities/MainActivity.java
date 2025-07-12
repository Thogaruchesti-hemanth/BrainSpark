package com.example.brainspark.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
import com.example.brainspark.data.QuestionProvider;
import com.example.brainspark.models.Question;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    private int currentQuestionIndex = 0;

    private LinearLayoutCompat optionsContainerLayout;
    private TextView questionNumberTextView;
    private TextView questionTextView;
    private ProgressBar progressBar;
    private Button buttonNext;

    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Apply insets for immersive UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        questionList = QuestionProvider.getQuestions();
        Collections.shuffle(questionList);

        animateAndShowFirstQuestion();

        buttonNext.setOnClickListener(view -> {
            if (currentQuestionIndex >= questionList.size()) return;

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
                buttonNext.setEnabled(false); // Wait for next answer
            } else {
                showResultBottomSheet();
            }
        });
    }

    // Initializes all views
    private void initViews() {
        optionsContainerLayout = findViewById(R.id.options_container_layout);
        questionNumberTextView = findViewById(R.id.question_number_text_view);
        questionTextView = findViewById(R.id.question_text_view);
        progressBar = findViewById(R.id.progress_bar);
        buttonNext = findViewById(R.id.next_question_button);
        buttonNext.setEnabled(false);
    }

    // Animation before first question appears
    private void animateAndShowFirstQuestion() {
        optionsContainerLayout.animate().alpha(0f).setDuration(150).withEndAction(() -> {
            askQuestion(currentQuestionIndex);
            optionsContainerLayout.setAlpha(0f);
            optionsContainerLayout.animate().alpha(1f).setDuration(150).start();
        }).start();
    }

    // Shows a question based on index
    private void askQuestion(int index) {
        optionsContainerLayout.removeAllViews();
        Question question = questionList.get(index);
        String formatQuestionNumber = "Question " + (index + 1) + " / " + questionList.size();

        questionTextView.setText(question.getQuestion());
        questionNumberTextView.setText(formatQuestionNumber);
        progressBar.setMax(questionList.size());
        progressBar.setProgress(index + 1);

        String[] options = {question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4()};

        for (String option : options) {
            View optionView = LayoutInflater.from(this).inflate(R.layout.option_layout, optionsContainerLayout, false);
            TextView optionTextView = optionView.findViewById(R.id.option_text_view);
            RadioButton radioButton = optionView.findViewById(R.id.option_radio);

            optionTextView.setText(option);

            // Handle exclusive selection and enable next button
            radioButton.setOnClickListener(v -> {
                for (int j = 0; j < optionsContainerLayout.getChildCount(); j++) {
                    View otherOption = optionsContainerLayout.getChildAt(j);
                    RadioButton otherRadio = otherOption.findViewById(R.id.option_radio);
                    otherRadio.setChecked(false);
                }
                radioButton.setChecked(true);
                buttonNext.setEnabled(true); // Enable once selected
            });

            optionsContainerLayout.addView(optionView);
        }
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

    private void showResultBottomSheet() {
        View view = LayoutInflater.from(this).inflate(
                R.layout.quiz_result_bottom_sheet,
                findViewById(android.R.id.content),
                false
        );
        TextView textScore = view.findViewById(R.id.text_result_score);
        Button buttonRestart = view.findViewById(R.id.button_restart_quiz);
        String result = "Your Score: " + score + " / " + questionList.size();

        textScore.setText(result);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.show();

        buttonRestart.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            restartQuiz();
        });
    }

    private void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        Collections.shuffle(questionList);
        askQuestion(currentQuestionIndex);
        buttonNext.setEnabled(false);
    }


}
