package com.example.tomorrow.login_register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tomorrow.R;
import com.example.tomorrow.customer.CustomerInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class  LoginRegisterpage extends AppCompatActivity {
   EditText editfullname, editusername,editpassword,editemail,editphonenum,editaddress;
   private Button button5;
   private ProgressBar progressBar;
   RadioGroup radioGroupGender;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_registerpage);

        //if the user is already logged in we will directly start the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, CustomerInterface.class));
            return;
        }
        editfullname = (EditText) findViewById(R.id.fullname);
        editusername = (EditText) findViewById(R.id.username);
        editemail    = (EditText) findViewById(R.id.email);
        editpassword = (EditText) findViewById(R.id.password);
        editphonenum = (EditText) findViewById(R.id.phonenum);
        editaddress  = (EditText) findViewById(R.id.address);
        radioGroupGender = (RadioGroup) findViewById(R.id.radioGender);



        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                registerUser();
            }
        });

        findViewById(R.id.RetrunLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on login
                //we will open the login screen
                finish();
                startActivity(new Intent(LoginRegisterpage.this, LoginFirstPage.class));
            }
        });

    }

    private void registerUser() {
        final String phonenum = editphonenum.getText().toString().trim();
        final String address = editaddress.getText().toString().trim();
        final String fullname = editfullname.getText().toString().trim();
        final String username = editusername.getText().toString().trim();
        final String email = editemail.getText().toString().trim();
        final String password = editpassword.getText().toString().trim();
        final String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();

        //first we will do the validations

        if (TextUtils.isEmpty(username)) {
            editusername.setError("Please enter username");
            editusername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(phonenum)) {
            editphonenum.setError("Please enter phonenum");
            editphonenum.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(address)) {
            editaddress.setError("Please enter address");
            editaddress.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(fullname)) {
            editfullname.setError("Please enter fullname");
            editfullname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editemail.setError("Please enter your email");
            editemail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemail.setError("Enter a valid email");
            editemail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editpassword.setError("Enter a password");
            editpassword.requestFocus();
            return;
        }

        //if it passes all the validations

        class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                params.put("gender", gender);
                params.put("fullname", fullname);
                params.put("phonenum", phonenum);
                params.put("address", address);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
            }

            @SuppressLint("WrongViewCast")
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) findViewById(R.id.loading);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getInt("id"),
                                userJson.getString("username"),
                                userJson.getString("email"),
                                userJson.getString("gender"),
                                userJson.getString("fullname"),
                                userJson.getString("phonenum"),
                                userJson.getString("address")
                        );


                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), CustomerInterface.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }

}