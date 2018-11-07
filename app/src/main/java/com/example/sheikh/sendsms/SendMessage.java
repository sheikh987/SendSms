package com.example.sheikh.sendsms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        final String nameString = intent.getExtras().getString("name");


        Random generator = new Random();
        int randInteger = 100000 + generator.nextInt(900000);
        final String otpString = String.valueOf(randInteger);
        final String randomString = "Hi. Your OTP is " + String.valueOf(randInteger);

        btn_send = (Button) findViewById(R.id.btn_send);
        text_message = (TextView) findViewById(R.id.et_message);
        text_mobile = (TextView) findViewById(R.id.et_mobile);
        text_message.setText(randomString);
        text_mobile.setText(mobileString);



        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get a reference to the ConnectivityManager to check state of network connectivity
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);

                // Get details on the currently active default data network
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                // If there is a network connection, fetch data
                if (networkInfo != null && networkInfo.isConnected()) {
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

                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            Date date = new Date();
                            String dateString = formatter.format(date);


                            JSONObject person = new JSONObject();
                            try {
                                person.put("otp", otpString);
                                person.put("name", nameString);
                                person.put("date",dateString);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            SharedPreferences sharedPreferences = getSharedPreferences("prefId", Context.MODE_PRIVATE);
                            String text = sharedPreferences.getString("jsonArray", null);
                            if (text==null){

                                JSONArray personArray = new JSONArray();
                                personArray.put(person);
                                SharedPreferences.Editor editorLogin = sharedPreferences.edit();
                                editorLogin.putString("jsonArray",personArray.toString());
                                editorLogin.apply();
                                Log.i("jsonTEst",personArray.toString());
                                startActivity(new Intent(SendMessage.this,MainActivity.class));
                                finish();
                            } else {
                                JSONArray personArray = new JSONArray(text);
                                personArray.put(person);
                                SharedPreferences.Editor editorLogin = sharedPreferences.edit();
                                editorLogin.putString("jsonArray",personArray.toString());
                                editorLogin.apply();
                                Log.i("jsonTEst",personArray.toString());
                                startActivity(new Intent(SendMessage.this,MainActivity.class));
                                finish();
                            }



                        }
                        rd.close();


                    } catch (Exception e) {
                        Toast.makeText(SendMessage.this, e.toString(), Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(SendMessage.this,"Check your connection",Toast.LENGTH_LONG).show();
                }


            }
        });

        StrictMode.ThreadPolicy st = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(st);


    }
}
