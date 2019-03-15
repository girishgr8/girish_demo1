package com.example.android.chat_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChatMessage extends AppCompatActivity {

    public boolean left;
    public String message;

    public ChatMessage(boolean left, String message) {
        super();
        this.left = left;
        this.message = message;
    }

}
