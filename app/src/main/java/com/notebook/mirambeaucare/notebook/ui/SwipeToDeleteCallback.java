package com.notebook.mirambeaucare.notebook.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.notebook.mirambeaucare.notebook.R;
import com.notebook.mirambeaucare.notebook.database.Glycemia;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private NotesAdapter mAdapter;
    private NotesViewModel mNotesViewModel;
    private MainActivity mMainActivity;


    public SwipeToDeleteCallback(NotesAdapter notesAdapter, NotesViewModel notesViewModel, MainActivity mainActivity) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mAdapter = notesAdapter;
        mNotesViewModel = notesViewModel;
        mMainActivity = mainActivity;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (direction == ItemTouchHelper.LEFT) {
            mNotesViewModel.delete(mAdapter.getGlycemiaAt(viewHolder.getAdapterPosition()));
            Snackbar mySnackbar = Snackbar.make(mMainActivity.findViewById(R.id.main_activity),
                    R.string.deleted, Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        }
        if (direction == ItemTouchHelper.RIGHT) {
            Glycemia glycemia = mAdapter.getGlycemiaAt(viewHolder.getAdapterPosition());
            int id = glycemia.getId();
            mMainActivity.recordID = id;
            mMainActivity.isUpdate = true;
            mMainActivity.showAlertDialog();

//
//            Snackbar mySnackbar = Snackbar.make(mMainActivity.findViewById(R.id.main_activity),
//                    R.string.app_name, Snackbar.LENGTH_SHORT);
//            mySnackbar.show();
        }
    }

}
