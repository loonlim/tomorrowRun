package com.example.tomorrowrun.tomorrow.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tomorrow.R;
import com.example.tomorrow.login_register.LoginFirstPage;
import com.example.tomorrow.login_register.SharedPrefManager;
import com.example.tomorrow.login_register.User;

public class CustomerIdFrame  extends AppCompatActivity {
    TextView textViewId, textViewUsername, textViewEmail, textViewGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.customeridframe );

        //if the user is not logged in
        //starting the login activity
        if (!SharedPrefManager.getInstance( this ).isLoggedIn()) {
            finish();
            startActivity( new Intent( this, LoginFirstPage.class ) );
        }


        textViewId = (TextView) findViewById( R.id.CustomerIdView );
        textViewUsername = (TextView) findViewById( R.id.CustomerNameShow );
        textViewEmail = (TextView) findViewById( R.id.CustomerEmailShow );
        textViewGender = (TextView) findViewById( R.id.CustomergoogleNameShow );


        //getting the current user
        User user = SharedPrefManager.getInstance( this ).getUser();

        //setting the values to the textviews
        textViewId.setText( String.valueOf( user.getId() ) );
        textViewUsername.setText( user.getUsername() );
        textViewEmail.setText( user.getEmail() );
        textViewGender.setText( user.getGender() );

        //when the user presses logout button
        //calling the logout method
        findViewById( R.id.Customerbuttonsingout ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance( getApplicationContext() ).logout();
            }
        } );
    }}
