package com.example.helpcasamobile;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class LoginActivity extends AppCompatActivity {

    EditText eteMail  =findViewById(R.id.eteMail)
            ,etePasswd=findViewById(R.id.etePasswd);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            String mail= eteMail.getText().toString();
            String passwd= etePasswd.getText().toString();

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(!InputValidator.isEmail(mail) && !InputValidator.isPhone(mail)){
                    eteMail.setError("invalid email or phone number");
                } else if (!InputValidator.isPassword(passwd)) {
                    etePasswd.setError("invalid password");
                }else {
                    MessageDigest digest = null;
                    try {
                        digest = MessageDigest.getInstance("SHA-256");
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                    String hashed = Base64.getEncoder().encodeToString(digest.digest(passwd.getBytes(StandardCharsets.UTF_8)));

                    URL url = null;
                    try {
                        url = new URL("10.0.0.2:5000");
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    HttpURLConnection conn = null;
                    try {
                        conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.setDoOutput(true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    String urlParameters = "email="+mail+ "&password="+hashed;
                    try(OutputStream os = conn.getOutputStream()) {
                        byte[] input = urlParameters.getBytes(StandardCharsets.UTF_8);
                        os.write(input, 0, input.length);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    try(BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine = null;
                        while ((responseLine = br.readLine())!= null) {
                            response.append(responseLine.trim());
                        }
                        System.out.println(response.toString());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
    }


}