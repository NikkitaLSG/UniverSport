package com.nikita.firtsproget.ui.register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.nikita.firtsproget.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonRegister, buttonCancel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_profel); // Измените на ваш корректный layout файл для регистрации

        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        buttonRegister = findViewById(R.id.Btm_in);
        buttonCancel = findViewById(R.id.Btm_input);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser ();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Логика для отмены регистрации (например, возврат на предыдущий экран)
                finish(); // Закрывает текущую активность
            }
        });
    }

    private void registerUser () {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        // Здесь можно добавить логику для регистрации пользователя, например, отправку данных на сервер

        Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
        // После успешной регистрации можно перейти на другой экран
    }
}