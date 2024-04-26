package com.example.projectakhir;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MotivationActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> lists = new ArrayList<>();
    Adapter adapter;
    DatabaseHelper db = new DatabaseHelper(this);
    Button tambah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dialog = new AlertDialog.Builder(MotivationActivity.this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivation);

        setupLogoutButton();

        db = new DatabaseHelper(getApplicationContext());
        tambah = findViewById(R.id.tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.list_item);
        adapter = new Adapter(MotivationActivity.this, lists);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                final String id = lists.get(i).getId();
                final String topik = lists.get(i).getTopik();
                final String motivation = lists.get(i).getMotivation();
                final CharSequence[] dialogItem = {"Update", "Delete"};
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MotivationActivity.this, EditorActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("topik", topik);
                                intent.putExtra("motivation", motivation);
                                startActivity(intent);
                                break;
                            case 1:
                                db.delete(Integer.parseInt(id));
                                lists.clear();
                                // untuk memanggil data ulang
                                getData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getData();
    }

    private void getData(){
        ArrayList<HashMap<String, String>> rows = db.getAll();
        for (int i = 0; i<rows.size(); i++){
            String id = rows.get(i).get("id");
            String topik = rows.get(i).get("topik");
            String motivation = rows.get(i).get("motivation");

            Data data = new Data();
            data.setId(id);
            data.setTopik(topik);
            data.setMotivation(motivation);
            lists.add(data);
        }
        adapter.notifyDataSetChanged();
    }
    private void logout(){
        Intent intent = new Intent(MotivationActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Hapus semua aktivitas lain dari tumpukan
        startActivity(intent);
        finish();
    }
    private void setupLogoutButton() {
        Button logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(); // Panggil metode logout saat tombol logout ditekan
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        lists.clear();
        getData();
    }
}