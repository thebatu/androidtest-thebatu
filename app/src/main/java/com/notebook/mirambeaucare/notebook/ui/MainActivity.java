package com.notebook.mirambeaucare.notebook.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.notebook.mirambeaucare.notebook.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //fab button
    @BindView(R.id.fab)
    FloatingActionButton fab;

    //recyclerView declaration
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    //adapter declaration
    private NotesAdapter mNotesAdapter;

    // scroll position
    private int mPosition = RecyclerView.NO_POSITION;

    //progressbar indicator
    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //recycler view and adapter setup
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mNotesAdapter);

    }


    /**
     * show main content if available
     */
    private void showMainDishDataView() {
        // First, hide the loading indicator
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        // Finally, make sure the dishes list data is visible
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * show loading indicator if main content is loading
     */
    private void showLoading() {
        // Then, hide the dishes list data
        mRecyclerView.setVisibility(View.INVISIBLE);
        // Finally, show the loading indicator
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    /**
     * onResume scroll to previous position
     */
    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerView.scrollToPosition(mPosition);
    }
}