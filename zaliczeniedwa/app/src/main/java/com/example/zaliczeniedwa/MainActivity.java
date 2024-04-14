package com.example.zaliczeniedwa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    int kod = 1010;
    String zlicz=null;

    //Obsługa kliknięcia na obrazek
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView mGrid = (GridView) findViewById(R.id.gridView);
        mGrid.setAdapter(new ImageAdapter(MainActivity.this));

        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent h = new Intent(MainActivity.this, Druga_aktywonosc.class) ;
                h.putExtra("par", i);
                startActivityForResult(h,kod);
                setResult(RESULT_OK, h);
            }
        });
    }
// Reakcja na kliknięcie stop na 2 aktywności
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == kod) && (resultCode == RESULT_OK ))
        {


        }
    }
}