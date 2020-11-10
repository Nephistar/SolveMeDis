package com.example.solvemedis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Math_Easy extends AppCompatActivity {
    private Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math__easy);
        back_button = (Button) findViewById(R.id.back_from_easy);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();

            }
        });
    }

    public void openHome(){
        Intent intent_home = new Intent(this, MainActivity.class);
        startActivity(intent_home);
    }
}