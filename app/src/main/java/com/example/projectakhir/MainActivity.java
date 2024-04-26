package com.example.projectakhir;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMotivation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMotivation = findViewById(R.id.motivation);
        btnMotivation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Log.d("MainActivity", "Button clicked");
                Intent intent = new Intent(MainActivity.this, MotivationActivity.class);
                startActivity(intent);
            }
        });
    }
}