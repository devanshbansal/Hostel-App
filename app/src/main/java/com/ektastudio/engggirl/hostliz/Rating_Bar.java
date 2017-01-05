package com.ektastudio.engggirl.hostliz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Rating_Bar extends AppCompatActivity {
    RatingBar ratings;
    Button btnRate,back;
    TextView textRateShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating__bar);
        ratings=(RatingBar)findViewById(R.id.ratingBar);
        btnRate=(Button)findViewById(R.id.btnRate);
        textRateShow=(TextView)findViewById(R.id.textShowRating);
        ratings.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textRateShow.setText(String.valueOf(rating));
            }

        });

        btnRate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), String.valueOf(ratings.getRating()), Toast.LENGTH_SHORT).show();
                Toast.makeText(Rating_Bar.this, "Thanks for Rating", Toast.LENGTH_SHORT).show();
            }
        });
        back=(Button)findViewById(R.id.buttonRate);
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(getBaseContext(),student_info_zone.class);
                startActivity(back);
            }
        });
    }

}
