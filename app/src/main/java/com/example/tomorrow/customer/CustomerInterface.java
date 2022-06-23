package com.example.tomorrow.customer;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tomorrow.R;
import com.example.tomorrow.databinding.ActivityCustomerInterfaceBinding;
import com.example.tomorrow.login_register.LoginFirstPage;
import com.example.tomorrow.login_register.SharedPrefManager;
import com.example.tomorrow.login_register.User;
import com.google.android.material.navigation.NavigationView;

public class CustomerInterface extends AppCompatActivity {
    private ActivityCustomerInterfaceBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    private Object acct;
    private TextView  textViewId, textViewUsername, textViewGender, textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );


        textViewId = findViewById( R.id.CustomerIdView );
        textViewUsername = findViewById( R.id.CustomerNameShow );
        textViewEmail = findViewById( R.id.CustomerEmailShow );
        textViewGender = findViewById( R.id.CustomergoogleNameShow );

        //if the user is not logged in
        //starting the login activity
        if (!SharedPrefManager.getInstance( this ).isLoggedIn()) {
            finish();
            startActivity( new Intent( this, LoginFirstPage.class ) );
        }
        //getting the current user
        if (!SharedPrefManager.getInstance( this ).isLoggedIn()) {
            User user = SharedPrefManager.getInstance( this ).getUser();
            //setting the values to the textviews
        }

        binding = ActivityCustomerInterfaceBinding.inflate( getLayoutInflater() );
        setContentView( binding.drawerLayout );
        setSupportActionBar( binding.appBarCustomerInterface.mText );
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.productProgressFragment, R.id.productCheckFragment,
                R.id.chatFragment, R.id.coutomerInformationFragment, R.id.contectFragment )
                .setOpenableLayout( drawer ).build();
        NavController navController = Navigation.findNavController( this,
                R.id.nav_host_fragment_content_customer_interface );
        NavigationUI.setupActionBarWithNavController( this, navController, mAppBarConfiguration );
        NavigationUI.setupWithNavController( navigationView, navController );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customer_interface, menu);
        return true;

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_content_customer_interface);

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();}

}