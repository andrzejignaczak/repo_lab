package com.example.zaliczeniedwa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;



public class Druga_aktywonosc extends Activity
{
    int z = 1;
    int id_n=0;
    int zlicz=0;
    private int Zdj[] = {
            R.drawable.zdj01,R.drawable.zdj02,R.drawable.zdj03,
            R.drawable.zdj04,R.drawable.zdj05,R.drawable.zdj06,
            R.drawable.zdj07,R.drawable.zdj08,R.drawable.zdj09,
            R.drawable.zdj10,R.drawable.zdj11,R.drawable.zdj12,
            R.drawable.zdj13,R.drawable.zdj14,R.drawable.zdj15};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.druga_akt);


        //obiekty w layout
        Button tyl = (Button) findViewById(R.id.button3);
        Button prz = (Button) findViewById(R.id.button4);
        Button stop = (Button)findViewById(R.id.button2);
        ImageView okno = (ImageView) findViewById(R.id.imageView);
        Button kosz = (Button)findViewById(R.id.button);
        Button informacja = (Button)findViewById(R.id.button5);

        //stworzenie intentu 2 aktywnośc
        Intent i = getIntent();
        z = i.getIntExtra("par", -1);

        id_n=z+1;
// Konwertowanie char na String
        String znakString = String.valueOf(z+1);




        Bitmap foto = BitmapFactory.decodeResource(getResources(),Zdj[z]);
        Bitmap foto1 = Bitmap.createScaledBitmap(foto,480,320,false);
        okno.setImageBitmap(foto1);
        //obsługa na przycisk do poprzedni.
        tyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView okno = (ImageView) findViewById(R.id.imageView);
                if(z == 0){
                    z=Zdj.length-1;
                    id_n=z+1;
                }
                else
                  --z;
                id_n=z+1;
                Bitmap foto = BitmapFactory.decodeResource(getResources(),Zdj[Math.abs(z%Zdj.length)]);
                Bitmap foto1 = Bitmap.createScaledBitmap(foto,480,320,false);
                okno.setImageBitmap(foto1);

            }
        });
        //obsługa na przycisk do stop.
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });
        //obsługa na przycisk do następny.
        prz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView okno = (ImageView) findViewById(R.id.imageView);
                if(z == Zdj.length-1){
                    z=0;
                    id_n=z+1;
                }
                else
                    ++z;
                id_n=z+1;
                Bitmap foto = BitmapFactory.decodeResource(getResources(),Zdj[Math.abs(z%Zdj.length)]);
                Bitmap foto1 = Bitmap.createScaledBitmap(foto,480,320,false);
                okno.setImageBitmap(foto1);
                setResult(RESULT_OK,i);

            }
        });

        //obsługa na przycisk do poprzedni.
        kosz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Rozpocznij pobieranie danych z serwera PHP


                // Otwórz aktywność kosz
                startActivity(new Intent(Druga_aktywonosc.this, koszyk.class));
            }
        });

        informacja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Rozpocznij pobieranie danych z serwera PHP


                // Otwórz aktywność Informacje

                Intent id = new Intent(Druga_aktywonosc.this, Informacje.class);

                // Przekazanie "id_narzen" jako dodatkowych danych do Intentu
                id.putExtra("id_narzen", id_n);

                // Rozpoczęcie nowej aktywności z przekazanymi danymi
                startActivity(id);
            }
        });


    }



}
