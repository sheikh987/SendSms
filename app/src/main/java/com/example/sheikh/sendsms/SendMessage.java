package com.example.sheikh.sendsms;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class SendMessage extends AppCompatActivity {

    Button btn_send;
    TextView text_mobile;
    TextView text_message;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_message);

        Intent intent = getIntent();
        final String mobileString = intent.getExtras().getString("phoneNumber");


        Random generator = new Random();
        int randInteger = 100000 + generator.nextInt(900000);
        final String randomString = "Hi. Your OTP is " + String.valueOf(randInteger);

        btn_send = (Button) findViewById(R.id.btn_send);
        text_message = (TextView) findViewById(R.id.et_message);
        text_mobile = (TextView) findViewById(R.id.et_mobile);
        text_message.setText(randomString);
        text_mobile.setText(mobileString);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Construct data
                    String apiKey = "apikey=" + "IJevOu2/koU-GA6vBGXe3IXWjCAihLn5EYQ8dm8yRm";
                    String message = "&message=" + randomString;
                    String sender = "&sender=" + "TXTLCL";
                    String numbers = "&numbers=" + mobileString;

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        Toast.makeText(SendMessage.this, line, Toast.LENGTH_SHORT).show();
                        Log.i("check",line);
                    }
                    rd.close();


                } catch (Exception e) {
                    Toast.makeText(SendMessage.this, e.toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });

        StrictMode.ThreadPolicy st = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(st);

    }
}
