package com.example.sr7;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.editTextTextEmailAddress3);
        nextButton = findViewById(R.id.button1);

        nextButton.setEnabled(false); // Изначально делаем кнопку неактивной

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isValidEmail(s.toString().trim())) {
                    nextButton.setEnabled(true);
                    nextButton.setBackgroundResource(R.drawable.rounded_button); // Фон активной кнопки
                } else {
                    nextButton.setEnabled(false);
                    nextButton.setBackgroundResource(R.drawable.rounded_button1); // Фон неактивной кнопки
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Действия при нажатии на активную кнопку "Далее"
                Intent intent = new Intent(LoginActivity.this, CodeActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidEmail(CharSequence target) {
        return (target != null && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
