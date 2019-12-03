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

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class LandedActivity extends Activity {

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landed);

        score = Player.score;
        TextView textView1 = (TextView) findViewById(R.id.txtscore);
        textView1.setText("Score: "+score);
    }

    public void save(View view) {
        EditText nameText = (EditText) findViewById(R.id.txtInputName);
        String name = nameText.getText().toString();

        if (name == null || name == "" || name == " ") {
            name = "Wilson";
        }
        String data = name+";"+score+"\n\r";
        writeToFile(data, this);
        startActivity(new Intent(this, MainActivity.class));
    }

    private void writeToFile(String data, Context context){
        try {
            String file = Settings.difficulty+".txt";
            if(fileExists(context, file)) {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file, Context.MODE_APPEND));
                outputStreamWriter.write(data);
                outputStreamWriter.close();
            }
            else{
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file, Context.MODE_PRIVATE));
                outputStreamWriter.write(data);
                outputStreamWriter.close();
            }
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private boolean fileExists(Context context, String filename) {
        File file = context.getFileStreamPath(filename);
        if(file == null || !file.exists()) {
            return false;
        }
        return true;
    }
}
