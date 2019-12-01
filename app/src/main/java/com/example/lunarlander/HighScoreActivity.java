package com.example.lunarlander;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HighScoreActivity extends Activity {

    private Difficulty dif;
    private List<Record> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        list = new ArrayList<Record>();
        dif = Settings.difficulty;
        readFromFile(this);
    }

    private void print(){
        View view = new View(this);
        Context context = getApplicationContext();
        final TableLayout tableLayout = (TableLayout)findViewById(R.id.table_layout_table);
        int sequence = 1;
        Collections.sort(list);
        for(Record r : list){
            TableRow row = new TableRow(context);
            // Set new table row layout parameters.
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(layoutParams);

            TextView textView = new TextView(context);
            textView.setText(r.getScore());
            row.addView(textView, 0);

            TextView textView1 = new TextView(context);
            textView1.setText(r.getName());
            row.addView(textView1, 0);

            TextView textView2 = new TextView(context);
            textView2.setText("#"+sequence);
            row.addView(textView2, 0);

            tableLayout.addView(row);

            sequence++;
        }
    }

    private void readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(dif+".txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                    String[] row = receiveString.split(";");
                    if(row != null && row.length > 1 && tryParseInt(row[1])){
                        list.add(new Record(row[0], row[1]));
                        Log.d("row", "name: "+row[0]+" score: "+row[1]);
                    }
                    else
                        Log.e("File row:", "==NULL");
                }
                inputStream.close();
            }
            print();

            //final ScoreAdapter adapter = new ScoreAdapter(context, R.layout.activity_high_score, list);
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

    }

    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
