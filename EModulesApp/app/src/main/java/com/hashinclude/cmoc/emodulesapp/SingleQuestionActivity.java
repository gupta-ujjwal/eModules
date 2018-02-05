package com.hashinclude.cmoc.emodulesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

//Will be used to load a single question from the db
public class SingleQuestionActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_question);

        //Recieved the intent from MainActivity and showed the corresponding text
        Intent intent=getIntent();
        int position=intent.getIntExtra("position",0);
        textView=findViewById(R.id.questionTextView);
        textView.setText("Q. No. = " + position + ".");
    }
}
