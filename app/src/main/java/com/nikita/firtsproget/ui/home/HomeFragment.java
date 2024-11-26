package com.nikita.firtsproget.ui.home;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nikita.firtsproget.R;
import com.nikita.firtsproget.ui.notifications.NotificationsFragment;

public class HomeFragment extends Fragment {

    private Button buttonRedact;
    private EditText FIOText;
    private EditText Manager;
    private Button SaveBtm;
    private Button Bolezn1;
    private Button Bolezn2;
    private Button Bolezn3;
    private Button Bolezn4;
    private Button Bolezn5;
    private Button Bolezn6;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        buttonRedact = view.findViewById(R.id.button_Redact);
        FIOText = view.findViewById(R.id.editTextFIO);
        Manager = view.findViewById(R.id.editTextManager);
        SaveBtm = view.findViewById(R.id.button_save);
        Bolezn1 = view.findViewById(R.id.checkBox);
        Bolezn2 = view.findViewById(R.id.checkBox2);
        Bolezn3 = view.findViewById(R.id.checkBox3);
        Bolezn4 = view.findViewById(R.id.checkBox4);
        Bolezn5 = view.findViewById(R.id.checkBox5);
        Bolezn6 = view.findViewById(R.id.checkBox6);

        buttonRedact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FIOText.setInputType(InputType.TYPE_CLASS_TEXT);
                Manager.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        });

        SaveBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FIOText.setInputType(InputType.TYPE_NULL);
                Manager.setInputType(InputType.TYPE_NULL);

                // Получите состояние чекбоксов и передайте в NotificationsFragment
                boolean[] selectedDiets = getSelectedDiets();
                NotificationsFragment notificationsFragment = new NotificationsFragment();
                Bundle args = new Bundle();
                args.putBooleanArray("selectedDiets", selectedDiets);
                notificationsFragment.setArguments(args);

                // Здесь добавьте код для перехода к NotificationsFragment
                // Например, используя FragmentManager
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.checkBox, notificationsFragment) // Убедитесь, что у вас есть контейнер с этим ID
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private boolean[] getSelectedDiets() {
        boolean[] selectedDiets = new boolean[6];
        selectedDiets[0] = Bolezn1.isPressed();
        selectedDiets[1] = Bolezn2.isPressed();
        selectedDiets[2] = Bolezn3.isPressed();
        selectedDiets[3] = Bolezn4.isPressed();
        selectedDiets[4] = Bolezn5.isPressed();
        selectedDiets[5] = Bolezn6.isPressed();
        return selectedDiets;
    }
}
