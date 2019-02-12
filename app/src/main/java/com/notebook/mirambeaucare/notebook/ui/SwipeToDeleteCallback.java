package com.notebook.mirambeaucare.notebook.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.notebook.mirambeaucare.notebook.R;
import com.notebook.mirambeaucare.notebook.database.Glycemia;

/**
 * handle swipe actions.
 */
public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private NotesAdapter mAdapter;
    private NotesViewModel mNotesViewModel;
    private MainActivity mMainActivity;

    private Drawable icon;
    private Drawable icon2;
    private final ColorDrawable background;

    //constructor
    public SwipeToDeleteCallback(NotesAdapter notesAdapter, NotesViewModel notesViewModel, MainActivity mainActivity) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mAdapter = notesAdapter;
        mNotesViewModel = notesViewModel;
        mMainActivity = mainActivity;
        background = new ColorDrawable(Color.RED);
        icon = ContextCompat.getDrawable(mMainActivity,
                R.drawable.ic_delete_black_24dp);

        icon2 = ContextCompat.getDrawable(mMainActivity,
                R.drawable.ic_edit_black_24dp);
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
        }
    }


    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 20; //so background is behind the rounded corners of itemView

        int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconBottom = iconTop + icon.getIntrinsicHeight();

        if (dX > 0) { // Swiping to the right
            int iconLeft = itemView.getLeft() + iconMargin + icon.getIntrinsicWidth();
            int iconRight = itemView.getLeft() + iconMargin;
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(itemView.getLeft(), itemView.getTop(),
                    itemView.getLeft() + ((int) dX) + backgroundCornerOffset, itemView.getBottom());

        } else if (dX < 0) { // Swiping to the left
            int iconLeft = itemView.getRight() - iconMargin - icon.getIntrinsicWidth();
            int iconRight = itemView.getRight() - iconMargin;
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
        } else { // view is unSwiped
            background.setBounds(0, 0, 0, 0);
        }

        background.draw(c);
        icon.draw(c);
        icon2.draw(c);

    }

}
