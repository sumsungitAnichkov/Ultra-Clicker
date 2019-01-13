package com.example.androidstud.game2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShopActivity extends AppCompatActivity {
    LinearLayout shopLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        shopLinearLayout = findViewById(R.id.shop_linear_layout);
        LayoutInflater inflater = LayoutInflater.from(this);
        Intent i = getIntent();
        int currentScore = i.getIntExtra("current_score",0);
        addCursor("Курсор за 40 гривен", R.drawable.cursor1,inflater,10,currentScore,100);
        addCursor("Элитный палец",R.drawable.cursor2,inflater,100,currentScore,1000);
        addCursor("Скорее всего, у вас его никогда не будет",R.drawable.cursor3,inflater,1000,currentScore,1000000);
    }
    void addCursor(String description, final int id, LayoutInflater inflater, final int multiplier, final int currentScore, final int cost)
    {
        View cursor = inflater.inflate(R.layout.shop_item,shopLinearLayout,false);
        TextView cursorDescription = cursor.findViewById(R.id.cursor_description);
        ImageView cursorImage = cursor.findViewById(R.id.cursor_image);
        cursorDescription.setText(description+". \nЦена: "+cost+".");
        cursorImage.setImageResource(id);
        cursor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentScore >= cost) {
                    Intent returnToMainActivity = new Intent();
                    returnToMainActivity.putExtra("image",id);
                    setResult(multiplier, returnToMainActivity);
                    finish();
                }
                else
                {
                    Intent returnToMainActivity = new Intent();
                    setResult(1, returnToMainActivity);
                    Toast.makeText(shopLinearLayout.getContext(),"У вас не хватает кликов",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
        shopLinearLayout.addView(cursor);
    }
}
