package com.nikita.firtsproget.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.nikita.firtsproget.databinding.ActivityMainBinding;

import com.nikita.firtsproget.R;

import java.util.ArrayList;
import java.util.List;

public class TrainerViewModel extends ViewModel {
    private final MutableLiveData<List<Trainer>> trainers;

    public TrainerViewModel() {
        trainers = new MutableLiveData<>(new ArrayList<>());
        loadTrainers();
    }

    private void loadTrainers() {
        List<Trainer> trainerList = new ArrayList<>();
        // Загрузите данные о тренерах
        trainerList.add(new Trainer("Иван Иванов", "Главный тренер", "10 лет", 4.5f,R.drawable.ic_dashboard_black_24dp));
        trainerList.add(new Trainer("Петр Петров", "Ассистент тренера", "5 лет", 4.0f, R.drawable.ic_dashboard_black_24dp));
        // Добавьте остальных тренеров...

        trainers.setValue(trainerList);
    }

    public LiveData<List<Trainer>> getTrainers() {
        return trainers;
    }
}
