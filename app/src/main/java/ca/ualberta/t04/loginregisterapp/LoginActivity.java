package ca.ualberta.t04.loginregisterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    EditText userNameEditText, passwordEditText;
    String username, password;

    AsyncHttpClient client;
    RequestParams params;

    String MYURL = "http://18.222.210.6:8080/AnimalHelper/Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEditText = findViewById(R.id.login_username);
        passwordEditText = findViewById(R.id.login_password);

        final Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = userNameEditText.getText().toString();
                password = passwordEditText.getText().toString();

                params = new RequestParams();
                params.put("username", username);
                params.put("password", password);
                client = new AsyncHttpClient();
                client.post(MYURL, params, new JsonHttpResponseHandler(){
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        //super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        //super.onSuccess(statusCode, headers, response);
                        Log.d("mjy", response.toString());
                        Toast.makeText(LoginActivity.this, "Login Success " + response, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}
