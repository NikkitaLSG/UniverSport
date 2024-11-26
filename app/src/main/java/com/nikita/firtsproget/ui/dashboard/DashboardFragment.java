package com.nikita.firtsproget.ui.dashboard;

import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nikita.firtsproget.R;
import com.nikita.firtsproget.databinding.FragmentDashboardBinding;
import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private TrainerAdapter trainerAdapter;
    private TrainerViewModel viewModel;
    private Button Button_egit_Profile;
    private TextView Text1;
    private TextView Text2;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Initialize RecyclerView
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(TrainerViewModel.class);

        // Initialize TrainerAdapter
        trainerAdapter = new TrainerAdapter();
        recyclerView.setAdapter(trainerAdapter);

        // Observe LiveData from ViewModel
        viewModel.getTrainers().observe(getViewLifecycleOwner(), new Observer<List<Trainer>>() {
            @Override
            public void onChanged(List<Trainer> trainers) {
                trainerAdapter.setTrainers(trainers);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}