package com.nikita.firtsproget.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nikita.firtsproget.R;

import java.util.ArrayList;
import java.util.List;

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.TrainerViewHolder> {

    private List<Trainer> trainers;

    public TrainerAdapter() {
        this.trainers = new ArrayList<>();
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Replace R.layout.trainer_item with the actual layout resource for a single trainer item
        View view = LayoutInflater.from(parent.getContext()).inflate(com.nikita.firtsproget.R.layout.trainer_card, parent, false);
        return new TrainerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainerViewHolder holder, int position) {
        // Bind the trainer data to the views in the TrainerViewHolder
        Trainer trainer = trainers.get(position);
        holder.bind(trainer);
    }

    @Override
    public int getItemCount() {
        return trainers.size();
    }

    static class TrainerViewHolder extends RecyclerView.ViewHolder {
        public TrainerViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Trainer trainer) {
            // Implement the logic to bind the trainer data to the views in the item layout
        }
    }
}
