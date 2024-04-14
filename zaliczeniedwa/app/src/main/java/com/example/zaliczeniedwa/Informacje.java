package com.example.zaliczeniedwa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Informacje extends AppCompatActivity {


    int id_narz = 1;
    private static final String TAG = "InformacjeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacje);

        Intent q = getIntent();
        id_narz = q.getIntExtra("id_narzen", -1);


        // Wywołanie klasy do pobrania danych
        new FetchDataFromServer().execute();

        Button cofn = (Button) findViewById(R.id.button8);
        cofn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    // Klasa AsyncTask do pobrania danych z serwera
    public class FetchDataFromServer extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonData = null;

            try {
                // Adres URL do serwera PHP
                URL url = new URL("http://172.21.240.1/phplogin/Strona_inf.php");

                // Nawiązanie połączenia
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Odczytanie danych z InputStream
                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                jsonData = buffer.toString();
            } catch (IOException e) {
                Log.e(TAG, "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(TAG, "Error closing stream", e);
                    }
                }
            }
            return jsonData;
        }

        @Override
        protected void onPostExecute(String jsonData) {
            if (jsonData != null) {
                try {
                    // Przetwarzanie danych JSON
                    JSONArray jsonArray = new JSONArray(jsonData);

                    // Przykład wyświetlenia danych
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String idNarzedzia = jsonObject.getString("Id_Narzedzia");
                        if (idNarzedzia.equals(String.valueOf(id_narz))) {
                            String nazwa = jsonObject.getString("Nazwa");
                            TextView naz = findViewById(R.id.textView);
                            naz.setText(nazwa);

                            String Cena= jsonObject.getString("Cena");
                            TextView cen = findViewById(R.id.cenaaa);
                            cen.setText(Cena);


                            String Opis = jsonObject.getString("Opis");
                            TextView opi = findViewById(R.id.Opisss);
                            opi.setText(Opis);
                        }
                        else{}

                        // Przykład: Wyświetlenie nazwy z pola "nazwa"


                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Error ", e);
                }
            }
        }
    }
}