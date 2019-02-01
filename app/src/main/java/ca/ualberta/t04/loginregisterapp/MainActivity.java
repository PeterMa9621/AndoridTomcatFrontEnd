package ca.ualberta.t04.loginregisterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    EditText userNameEditText, passwordEditText;
    Button submitButton;
    String userName, password;

    RequestParams params;
    AsyncHttpClient client;

    String MYURL = "http://192.168.191.2:8080/MyFirstApp/Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.userName);
        passwordEditText = findViewById(R.id.password);
        submitButton = findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = userNameEditText.getText().toString();
                password = passwordEditText.getText().toString();

                params = new RequestParams();
                params.put("k1", userName);
                params.put("k2", password);
                client = new AsyncHttpClient();
                client.post(MYURL,params, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        Toast.makeText(MainActivity.this, "Submit success" + response, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
