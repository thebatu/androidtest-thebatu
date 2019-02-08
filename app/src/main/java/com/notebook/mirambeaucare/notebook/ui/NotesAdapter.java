package com.notebook.mirambeaucare.notebook.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesAdapterViewHolder> {
    @NonNull
    @Override
    public NotesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapterViewHolder notesAdapterViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class NotesAdapterViewHolder extends RecyclerView.ViewHolder {
        public NotesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
