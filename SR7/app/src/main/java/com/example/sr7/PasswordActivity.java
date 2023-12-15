package com.example.sr7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {

    private StringBuilder enteredPassword = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        // Настройка обработчиков нажатий на кнопки
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(view -> handleButtonClick("1"));

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(view -> handleButtonClick("2"));

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(view -> handleButtonClick("3"));

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(view -> handleButtonClick("4"));

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(view -> handleButtonClick("5"));

        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(view -> handleButtonClick("6"));

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(view -> handleButtonClick("7"));

        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(view -> handleButtonClick("8"));

        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(view -> handleButtonClick("9"));

        Button button0 = findViewById(R.id.button0);
        button0.setOnClickListener(view -> handleButtonClick("0"));

        Button buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(view -> deleteLastDigit());
    }

    // Метод для обработки нажатия на кнопку с цифрой
    private void handleButtonClick(String digit) {
        enteredPassword.append(digit);
        updatePasswordDisplay();

        if (enteredPassword.length() == 4) {
            if (enteredPassword.toString().equals("1111")) {
                Intent intent = new Intent(PasswordActivity.this, CardActivity.class);
                startActivity(intent);
                enteredPassword.setLength(0);
                updatePasswordDisplay();
            } else {
                Toast.makeText(this, "Неправильный пароль", Toast.LENGTH_SHORT).show();
                enteredPassword.setLength(0);
                updatePasswordDisplay();
            }
        }

        TextView skipButton = findViewById(R.id.skipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Действия при нажатии на кнопку "Пропустить"
                Intent intent = new Intent(PasswordActivity.this, CardActivity.class);
                startActivity(intent);
            }
        });
    }

    // Метод для удаления последней введенной цифры
    private void deleteLastDigit() {
        if (enteredPassword.length() > 0) {
            enteredPassword.deleteCharAt(enteredPassword.length() - 1);
            updatePasswordDisplay();
        }
    }

    // Метод для обновления отображения введенного пароля
    private void updatePasswordDisplay() {
        TextView passwordTextView = findViewById(R.id.passwordTextView);
        passwordTextView.setText(enteredPassword.toString());
    }
}
