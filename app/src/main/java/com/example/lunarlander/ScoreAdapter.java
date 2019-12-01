package com.example.lunarlander;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class ScoreAdapter extends ArrayAdapter<Record> {

    Context context;
    int layoutResourceId;
    List<Record> data = null;

    public ScoreAdapter(Context context, int layoutResourceId, List<Record> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

}
