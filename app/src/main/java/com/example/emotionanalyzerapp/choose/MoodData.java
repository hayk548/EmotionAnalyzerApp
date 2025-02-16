package com.example.emotionanalyzerapp.choose;

public class MoodData {
    private String sentiment;
    private long timestamp;

    public MoodData(String sentiment, long timestamp) {
        this.sentiment = sentiment;
        this.timestamp = timestamp;
    }

    public String getSentiment() {
        return sentiment;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
