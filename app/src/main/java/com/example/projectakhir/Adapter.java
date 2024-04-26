package com.example.projectakhir;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> lists;

    public Adapter(Activity activity, List<Data> lists){
        this.activity = activity;
        this.lists = lists;
    }

    @Override
    public int getCount() {

        return lists.size();
    }

    @Override
    public Object getItem(int i) {

        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null && inflater != null){
            view = inflater.inflate(R.layout.activity_list, null);
        }
        if (view != null) {
            TextView topik = view.findViewById(R.id.topik);
            TextView motivation = view.findViewById(R.id.motivation);

            Data data = lists.get(i);
            topik.setText(data.getTopik());
            motivation.setText(data.getMotivation());
        }

        return view;
    }
}
