package login_register;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tomorrow.R;
import com.example.tomorrow.customer.CustomerInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginFirstPage extends AppCompatActivity {
     EditText editTextusername, editTextpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.login_loginpage );
        editTextusername = (EditText)findViewById( R.id.username1 );
        editTextpassword =(EditText) findViewById( R.id.password1 );
//        --------------------------------------------
        findViewById( R.id.buttonlogin ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        findViewById( R.id.buttonRegister ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open register screen
                finish();
                startActivity(new Intent(getApplicationContext(), LoginRegisterpage.class));

            }
        });
    }
    private void userLogin() {
        //first getting the values
        final String username =  editTextusername.getText().toString();
        final String password =  editTextpassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editTextusername.setError("Please enter your username");
            editTextusername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextpassword.setError("Please enter your password");
            editTextpassword.requestFocus();
            return;
        }

        //if everything is fine

        class UserLogin extends AsyncTask<Void, Void, String> {
            ProgressBar progressBar;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.loading);
                progressBar.setVisibility(View.VISIBLE);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject( s );

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getInt( "id" ),
                                userJson.getString( "username" ),
                                userJson.getString( "email" ),
                                userJson.getString( "gender" ),
                                userJson.getString( "fullname" ),
                                userJson.getString( "phonenum" ),
                                userJson.getString( "address" ) );

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), CustomerInterface.class));

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }
}


