package com.nikita.firtsproget.ui.notifications;

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
import com.nikita.firtsproget.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding; // Используем View Binding
    private EditText eventEditText;
    private Button zapisButton;
    private Dieta dieta;
    private EditText dietaForMe; // Исправлено имя переменной

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dieta = new Dieta(); // Инициализация класса Dieta

        // Получите состояния чекбоксов из аргументов
        if (getArguments() != null) {
            boolean[] selectedDiets = getArguments().getBooleanArray("selectedDiets");
            if (selectedDiets != null) {
                dieta.setExcludedDiets(selectedDiets);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout using View Binding
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Инициализация представлений с использованием binding
        eventEditText = binding.Event; // Предполагается, что у вас есть этот ID в вашем XML
        zapisButton = binding.ZapisSuka; // Предполагается, что у вас есть этот ID в вашем XML
        dietaForMe = binding.DietaForME; // Исправлено имя переменной

        // Установка слушателя нажатия кнопки
        zapisButton.setOnClickListener(v -> {
            String userInput = eventEditText.getText().toString(); // Получаем текст из EditText
            if (!userInput.isEmpty()) {
                String randomDiet = dieta.getRandomDiet(); // Получаем случайную диету
                Toast toast = Toast.makeText(getContext(), "Ваш ввод: " + userInput + "\nРекомендованная диета: " + randomDiet, Toast.LENGTH_LONG);
                toast.show(); // Правильный метод для отображения Toast

                // Устанавливаем текст в EditText
                dietaForMe.setText(randomDiet); // Устанавливаем случайную диету в EditText
            } else {
                Toast.makeText(getContext(), "Пожалуйста, введите событие", Toast.LENGTH_SHORT).show(); // Сообщение об ошибке для пустого ввода
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Очистка binding для избежания утечек памяти
        binding = null;
    }
}
