package com.example.lunarlander;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class LandedActivity extends Activity {

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landed);
    }

    public void save(View view) {
        EditText nameText = (EditText) findViewById(R.id.txtInputName);
        String name = nameText.getText().toString();

        score = 10;

        TextView textView1 = (TextView) findViewById(R.id.txtscore);
        textView1.setText("Score: "+score);

        if (name == null) {
            name = "Wilson";
        }
        String data = name+";"+score;
        writeToFile(data, this);
        startActivity(new Intent(this, MainActivity.class));
    }

    private void writeToFile(String data, Context context){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(Settings.difficulty+".txt", Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
