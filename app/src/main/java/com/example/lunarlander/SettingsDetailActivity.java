package com.example.lunarlander;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SettingsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_detail);

        String width = "Load game first";
        if(Settings.widthOfImage != null) {
            width = "Width: " + Settings.widthOfImage.toString();

            TextView textView1 = (TextView) findViewById(R.id.txtHeightOfScreen);
            textView1.setText("Height of screen: "+Settings.heightOfScreen);
            TextView textView2 = (TextView) findViewById(R.id.txtImgWidth);
            textView1.setText("Width of image: "+Settings.widthOfImage);
            TextView textView3 = (TextView) findViewById(R.id.txtWidthOfScreen);
            textView1.setText("Height of screen: "+Settings.heightOfScreen);
        }
        TextView textView = (TextView) findViewById(R.id.txtImgWidth);
        textView.setText(width);



    }
}
