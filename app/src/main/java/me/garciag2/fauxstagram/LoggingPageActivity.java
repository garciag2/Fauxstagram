package me.garciag2.fauxstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoggingPageActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginBtn;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging_page);

        usernameInput = findViewById(R.id.etUsername);
        passwordInput = findViewById(R.id.etPassword);
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginBtn.setBackgroundColor(getResources().getColor(R.color.instagram_maroon));
                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();
                login(username, password);

            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoggingPageActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });


        if(ParseUser.getCurrentUser() != null) {
            Intent intent = new Intent(LoggingPageActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void login(String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null){
                    Log.d("LoginActivity", "Login successful");
                    Toast.makeText(LoggingPageActivity.this, "Login successful!!!", Toast.LENGTH_SHORT).show();
                    //TODO change intent to go to TimelineActivity
                    final Intent intent = new Intent(LoggingPageActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else{
                    Log.e("LoginActivity", "Login Failure");
                    Toast.makeText(LoggingPageActivity.this, "Login Failed try again", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }

}


