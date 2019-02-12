package com.notebook.mirambeaucare.notebook.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.notebook.mirambeaucare.notebook.R;
import com.notebook.mirambeaucare.notebook.database.Glycemia;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesAdapterViewHolder> {

    private List<Glycemia> mGlycemiasList;
    private Context mContext;

    public NotesAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * @param viewGroup The ViewGroup of which ViewHolders are contained within
     * @param viewType  If your RecyclerView has more than one type of item we
     *                  can use this viewType integer to provide a different layout.
     * @return A new DishesAdapterViewHolder that holds the View for each list item
     */
    @NonNull
    @Override
    public NotesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int layoutId = (R.layout.notes_listview);
        View view = LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false);
        return new NotesAdapterViewHolder(view);
    }

    /**
     * bind the view to individual holders
     *
     * @param holder    holder
     * @param position  position of viewHolder
     */
    @Override
    public void onBindViewHolder(@NonNull NotesAdapterViewHolder holder, int position) {
        Glycemia glycemia = mGlycemiasList.get(position);
        holder.glycemiaLvl.setText(String.valueOf(glycemia.getGlycemia()));
        holder.insulinLvl.setText(String.valueOf(glycemia.getInsulin()));
        holder.date.setText(glycemia.getDate().toString());
    }

    /**
     * returns the size of the records of mGlycemiasList
     * @return records count
     */
    @Override
    public int getItemCount() {
        return mGlycemiasList == null ? 0 : mGlycemiasList.size();
    }

    /**
     * repopulate list
     * @param glycemias list of records from local DB
     */
    public void swapNotes(List<Glycemia> glycemias){
        //if there was no notes data, then repopulate list
        mGlycemiasList = glycemias;
        notifyDataSetChanged();
    }

    /**
     * A ViewHolder is a required part of the pattern for RecyclerViews. It mostly behaves as
     * a cache of the child views for a forecast item. It's also a convenient place to set an
     * OnClickListener, since it has access to the adapter and the views.
     */
    public class NotesAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView glycemiaLvl;
        TextView insulinLvl;
        TextView date;



        public NotesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            glycemiaLvl = itemView.findViewById(R.id.glycemia_lvl);
            insulinLvl = itemView.findViewById(R.id.insulin_lvl);
            date = itemView.findViewById(R.id.date);
        }
    }
}
