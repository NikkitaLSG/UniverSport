package com.nikita.firtsproget.ui.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nikita.firtsproget.R;

public class RegisterFragment extends Fragment {

    private EditText editTextEmail, editTextPassword;
    private Button buttonRegister, buttonCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_profel, container, false);

        // Инициализация UI элементов
        editTextEmail = view.findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = view.findViewById(R.id.editTextTextPassword);
        buttonRegister = view.findViewById(R.id.Btm_in);
        buttonCancel = view.findViewById(R.id.Btm_input);

        // Установка слушателей для кнопок
        buttonRegister.setOnClickListener(v -> registerUser());
        buttonCancel.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().finish();
            }
        });

        return view;
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Валидация полей
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getActivity(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        // Симуляция успешной регистрации
        Toast.makeText(getActivity(), "Регистрация успешна", Toast.LENGTH_SHORT).show();
    }
}
