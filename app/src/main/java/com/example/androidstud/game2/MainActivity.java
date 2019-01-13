package com.example.androidstud.game2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageButton clickButton;
    TextView score;
    Button shopButton;
    int scoreCount = 0;
    int scoreMultiplier = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickButton = findViewById(R.id.click_button);
        score = findViewById(R.id.score);
        shopButton = findViewById(R.id.shop_button);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreCount+=scoreMultiplier;

                score.setText(Integer.toString(scoreCount));
            }
        });
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openShopActivity = new Intent(MainActivity.this,ShopActivity.class);
                openShopActivity.putExtra("current_score",scoreCount);
                startActivityForResult(openShopActivity,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        scoreMultiplier = resultCode;
        if(data!=null)
        {
            int imgId = data.getIntExtra("image",R.drawable.cursor);
            clickButton.setBackgroundResource(imgId);
        }
    }
}
