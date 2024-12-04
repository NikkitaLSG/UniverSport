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
        setContentView(R.layout.register_profel); // Обновите с вашим layout

        // Инициализация UI элементов
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        buttonRegister = findViewById(R.id.Btm_in);
        buttonCancel = findViewById(R.id.Btm_input);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(); // Вызов метода регистрации
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Закрывает текущую активность (возвращает на предыдущую)
            }
        });
    }

    // Метод регистрации пользователя
    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Проверка на пустые поля
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        // Проверка доступности сети
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "Нет подключения к интернету", Toast.LENGTH_SHORT).show();
            return;
        }

        // Создаем объект BD_connect и инициируем подключение к базе данных
        BD_connect db_connect = new BD_connect();
        db_connect.getNewConnection(new BD_connect.ConnectionCallback() {
            @Override
            public void onConnectionSuccess(Connection con) {
                Log.d(TAG, "Подключение к базе данных успешно!");
                try {
                    // SQL запрос для вставки данных в таблицу пользователей
                    String sql = "INSERT INTO users (email, password) VALUES (?, ?)";

                    // Создаем PreparedStatement
                    try (PreparedStatement stmt = con.prepareStatement(sql)) {
                        stmt.setString(1, email);
                        stmt.setString(2, password); // Просто сохраняем пароль в открытом виде

                        // Выполняем запрос
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            Log.d(TAG, "Данные успешно сохранены в базе данных.");
                            Toast.makeText(RegisterActivity.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d(TAG, "Ошибка регистрации, строки не были добавлены.");
                            Toast.makeText(RegisterActivity.this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                        }
                    }

                    // Закрываем соединение после использования
                    con.close();
                } catch (SQLException e) {
                    Log.e(TAG, "Ошибка при выполнении SQL запроса: " + e.getMessage());
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "Ошибка при сохранении данных", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onConnectionError(SQLException error) {
                // Обработка ошибки подключения
                Log.e(TAG, "Ошибка подключения к базе данных: " + error.getMessage());
                Toast.makeText(RegisterActivity.this, "Ошибка подключения к базе данных", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
    }

    // Метод для проверки доступности сети
    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
