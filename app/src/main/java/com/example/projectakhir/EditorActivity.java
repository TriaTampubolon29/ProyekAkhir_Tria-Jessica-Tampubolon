package com.example.projectakhir;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditorActivity extends AppCompatActivity {

    private EditText editTopik, editMotivation;
    private Button btn_save;
    private DatabaseHelper db = new DatabaseHelper(this);
    private String id, topik, motivation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editTopik = findViewById(R.id.edit_topik);
        editMotivation = findViewById(R.id.edit_motivation);
        btn_save = findViewById(R.id.btn_save);

        id = getIntent().getStringExtra("id");
        topik = getIntent().getStringExtra("topik");
        motivation = getIntent().getStringExtra("motivation");

        if (id == null || id.equals("")){
            setTitle("Create Motivation");
        } else {
            setTitle("Update Motivation");
            editTopik.setText(topik);
            editMotivation.setText(motivation);
        }

        btn_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    if (id == null || id.equals("")){
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e){
                    Log.e("Saving", e.getMessage());
                }
            }
        });

    }
    private void save(){
        if (String.valueOf(editTopik.getText()).equals("")|| String.valueOf(editTopik.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Please add all Data!", Toast.LENGTH_SHORT).show();
        } else {
            db.insert(editTopik.getText().toString(), editMotivation.getText().toString());

            navigateUpToMotivationActivity();
        }
    }

    private void navigateUpToMotivationActivity() {
        Intent intent = new Intent(EditorActivity.this, MotivationActivity.class);
        startActivity(intent);
    }

    private void edit(){
        if (String.valueOf(editTopik.getText()).equals("")|| String.valueOf(editTopik.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Please add all Data!", Toast.LENGTH_SHORT).show();
        } else {
            db.update(Integer.parseInt(id), editTopik.getText().toString(), editMotivation.getText().toString());
            finish();

            navigateUpToMotivationActivity();
        }
    }
}