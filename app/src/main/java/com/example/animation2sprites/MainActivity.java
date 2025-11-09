package com.example.animation2sprites;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BoardGame boardGame = new BoardGame(this);
        setContentView(boardGame);
    }
}