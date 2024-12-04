package com.nikita.firtsproget.ui.register;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nikita.firtsproget.R;
import com.nikita.firtsproget.ui.DataBass.BD_connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonRegister, buttonCancel;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_profel);

        // Инициализация UI элементов
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        buttonRegister = findViewById(R.id.Btm_in);
        buttonCancel = findViewById(R.id.Btm_input);

        // Установка слушателей для кнопок
        buttonRegister.setOnClickListener(v -> registerUser());
        buttonCancel.setOnClickListener(v -> finish());
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Проверка на пустые поля
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        // Проверка подключения к сети
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "Нет подключения к интернету", Toast.LENGTH_SHORT).show();
            return;
        }

        // Подключение к базе данных
        BD_connect dbConnect = new BD_connect();
        dbConnect.getNewConnection(new BD_connect.ConnectionCallback() {
            @Override
            public void onConnectionSuccess(Connection con) {
                try {
                    String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
                    try (PreparedStatement stmt = con.prepareStatement(sql)) {
                        stmt.setString(1, email);
                        stmt.setString(2, password);
                        int rows = stmt.executeUpdate();
                        if (rows > 0) {
                            Log.d(TAG, "Пользователь зарегистрирован.");
                            Toast.makeText(RegisterActivity.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (SQLException e) {
                    Log.e(TAG, "Ошибка SQL: " + e.getMessage(), e);
                    Toast.makeText(RegisterActivity.this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onConnectionError(SQLException error) {
                Log.e(TAG, "Ошибка подключения к базе данных: " + error.getMessage(), error);
                Toast.makeText(RegisterActivity.this, "Ошибка подключения", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
