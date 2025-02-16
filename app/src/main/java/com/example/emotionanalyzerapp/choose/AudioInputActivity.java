package com.example.emotionanalyzerapp.choose;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Toast;

import com.example.emotionanalyzerapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class AudioInputActivity extends Activity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 100;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_input);

        db = FirebaseFirestore.getInstance();

        findViewById(R.id.btnStartSpeech).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now...");

        startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == RESULT_OK) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String speechText = result.get(0);
            analyzeTextSentiment(speechText);
        }
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
                .addOnSuccessListener(documentReference -> Toast.makeText(AudioInputActivity.this, "Mood saved!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(AudioInputActivity.this, "Error saving mood.", Toast.LENGTH_SHORT).show());

    }
}
