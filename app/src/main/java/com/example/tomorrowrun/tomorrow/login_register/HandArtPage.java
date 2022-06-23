package com.example.tomorrowrun.tomorrow.login_register;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tomorrow.R;
import com.example.tomorrow.customer.CustomerInterface;

import java.util.Timer;
import java.util.TimerTask;


public class HandArtPage extends AppCompatActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hand_artpage);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(HandArtPage.this, CustomerInterface.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
