package com.example.exams.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exams.R;

public class SchoolClassView extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_view);

        TextView tvClassName = findViewById(R.id.lblClass);
        String className = getIntent().getStringExtra("class");

        if (className != null)
            tvClassName.setText(className);

        ImageView img1 = findViewById(R.id.img1);
        ImageView img2 = findViewById(R.id.img2);

        if (className.equals("BPM")){
            img1.setImageDrawable(getDrawable(R.mipmap.happy_foreground));
            img2.setImageDrawable(getDrawable(R.mipmap.shit_foreground));
        } else {
            img2.setImageDrawable(getDrawable(R.mipmap.happy_foreground));
            img1.setImageDrawable(getDrawable(R.mipmap.shit_foreground));
        }
    }
}
