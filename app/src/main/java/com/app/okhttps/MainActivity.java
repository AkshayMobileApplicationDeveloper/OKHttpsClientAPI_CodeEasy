package com.app.okhttps;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    OkHttpClient client;
    String getURL = "http://10.0.2.2.80/codePHP/OkHttps3/get.php";
    String postURL = "http://10.0.2.2.80/codePHP/OkHttps3/post.php";
    TextView textView;
    Button GetButton, PostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization
        client = new OkHttpClient();
        textView = findViewById(R.id.Main_text_Data);
        GetButton = findViewById(R.id.Main_Btn_Get);
        PostButton = findViewById(R.id.Main_Btn_Post);

        // GetButton
        GetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get();
            }
        });

        // PostButton
        PostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postfunction();
            }
        });
    }

    // Get Function
    public void get() {
        Request request = new Request.Builder()
                .url(getURL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(myResponse);
                        }
                    });
                }
            }
        });
    }

    // Post Function
    public void postfunction() {
        RequestBody requestBody = new FormBody.Builder()
                .add("key_name", "Demo_value")
                .build();

        Request request = new Request.Builder()
                .url(postURL)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(myResponse);
                        }
                    });
                }
            }
        });
    }
}
