package com.example.threads20;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyThread myThread = new MyThread();
    Thread thread;
    Handler handler;
    TextView textView, textView2;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        textView2 = findViewById(R.id.text2);

        thread = new Thread(myThread);
        thread.start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                textView.setText(String.valueOf(msg.arg1));

                if (msg.arg1 == 10) {
                    textView2.setText("You got 5 more ");
                } else if (msg.arg1 == 20) {
                    textView2.setVisibility(View.VISIBLE);
                    textView2.setText("You lost");
                } else if (msg.arg1 == 11) {
                    textView2.setVisibility(View.INVISIBLE);
                }
            }
        };

    }


    class MyThread implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i <= 20; i++) {
                Message message = Message.obtain();

                message.arg1 = i;
                handler.sendMessage(message);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
