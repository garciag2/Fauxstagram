package me.garciag2.fauxstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private EditText emailInput;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        usernameInput = findViewById(R.id.etUsername);
        passwordInput = findViewById(R.id.etPassword);
        emailInput = findViewById(R.id.etEmail);
        signupBtn = findViewById(R.id.signupBtn);


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();
                final String email = emailInput.getText().toString();
                signup(username,password,email);

            }
        });


        if(ParseUser.getCurrentUser() != null) {
            Intent intent = new Intent(SignUpActivity.this, PostingActivity.class);
            startActivity(intent);
        }
    }

    private void signup(String username, String password, String email){
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        // Set custom properties
        user.put("phone", "650-253-0000");
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("SignupActivity", "Signup successful");
                    Toast.makeText(SignUpActivity.this, "Signup successful!!!", Toast.LENGTH_SHORT).show();
                    //TODO change intent to go to TimelineActivity
                    final Intent intent = new Intent(SignUpActivity.this, PostingActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("SignupActivity", "Signup Failure");
                    Toast.makeText(SignUpActivity.this, "Signup Failed try again", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }

}


