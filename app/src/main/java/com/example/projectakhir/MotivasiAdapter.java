package com.example.projectakhir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MotivasiAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HashMap<String, String>> motivasiList;

    public MotivasiAdapter(Context context, ArrayList<HashMap<String, String>> motivasiList) {
        this.context = context;
        this.motivasiList = motivasiList;
    }

    @Override
    public int getCount() {
        return motivasiList.size();
    }

    @Override
    public Object getItem(int position) {
        return motivasiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_motivasi, parent, false);
        }

        TextView textViewTopik = convertView.findViewById(R.id.textViewTopik);
        TextView textViewMotivasi = convertView.findViewById(R.id.textViewMotivasi);

        HashMap<String, String> motivasi = motivasiList.get(position);
        textViewTopik.setText(motivasi.get("topik"));
        textViewMotivasi.setText(motivasi.get("motivation"));

        return convertView;
    }
}

