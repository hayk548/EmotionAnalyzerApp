package com.example.emotionanalyzerapp.choose;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emotionanalyzerapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class TextInputActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);

        db = FirebaseFirestore.getInstance();

        final EditText inputText = findViewById(R.id.editTextInput);

        findViewById(R.id.btnAnalyzeText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = inputText.getText().toString().trim();
                if (!userInput.isEmpty()) {
                    analyzeTextSentiment(userInput);
                } else {
                    Toast.makeText(TextInputActivity.this, "Please enter some text!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void analyzeTextSentiment(String text) {
        String sentiment = "positive";
        saveMoodToFirebase(sentiment);
    }

    private void saveMoodToFirebase(String sentiment) {
        String userId = "userId";
        MoodData moodData = new MoodData(sentiment, System.currentTimeMillis());

        db.collection("moods").document(userId)
                .collection("moodEntries")
                .add(moodData)
                .addOnSuccessListener(documentReference -> Toast.makeText(TextInputActivity.this, "Mood saved!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(TextInputActivity.this, "Error saving mood.", Toast.LENGTH_SHORT).show());
    }
}
