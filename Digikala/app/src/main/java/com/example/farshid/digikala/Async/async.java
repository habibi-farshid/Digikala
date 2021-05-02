package com.example.farshid.digikala.Async;

import android.os.AsyncTask;

import com.example.farshid.digikala.Sign.Sign;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class async extends AsyncTask {

    public String link = "";

    public async(String link) {
        this.link = link;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            Sign.data = bufferedReader.toString();

        } catch (Exception e) {

        }


        return "";

    }
}
