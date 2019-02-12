package com.notebook.mirambeaucare.notebook.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.notebook.mirambeaucare.notebook.R;
import com.notebook.mirambeaucare.notebook.database.Glycemia;
import com.notebook.mirambeaucare.notebook.ui.timeDate.DatePickerFragment;
import com.notebook.mirambeaucare.notebook.ui.timeDate.TimePickerFragment;
import com.notebook.mirambeaucare.notebook.util.Constants;
import com.notebook.mirambeaucare.notebook.util.InjectorUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

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

    NotesViewModel mNotesViewModel;

    // lateVar be populated with user inputs
    private String glycemia_;
    private String insulin_;
    Date current_date;
    String formattedTime;
    public boolean isUpdate = false;
    public int recordID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //recycler view and adapter setup
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mNotesAdapter = new NotesAdapter(this);
        mRecyclerView.setAdapter(mNotesAdapter);


        //get ViewModel of mainActivity
        NotesViewModelFactory factory = InjectorUtils.provideNotesViewModelFactory
                (this.getApplicationContext());
        mNotesViewModel = ViewModelProviders.of(this, factory).get(NotesViewModel.class);

        //get list of all records and update the UI if there is change in the list
        LiveData<List<Glycemia>> glycemias = mNotesViewModel.getAllRecords();
        glycemias.observe(this, listOfGlycemia -> {
            if (listOfGlycemia != null) {
                mNotesAdapter.swapNotes(listOfGlycemia);
            }
        });

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback(mNotesAdapter, mNotesViewModel, this));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

//        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                if (direction == ItemTouchHelper.LEFT) {
//                    mNotesViewModel.delete(mNotesAdapter.getGlycemiaAt(viewHolder.getAdapterPosition()));
//                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.main_activity),
//                            R.string.deleted, Snackbar.LENGTH_SHORT);
//                    mySnackbar.show();
//                }
//            }
//
//            @Override
//            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//                // view the background view
//            }
//        };
//
//// attaching the touch helper to recycler view
//        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);


        // fab click listener (add new entry)
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

    }

    /**
     * Display alert dialog to allow user to input glycemia and insulin levels
     * verify input is valid, if valid show date picker.
     */
    public void showAlertDialog() {
        //great Dialog builder and show it.
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_levels, null);
        final EditText tv_glycemia = (EditText) mView.findViewById(R.id.glycemia);
        final EditText tv_insuline = (EditText) mView.findViewById(R.id.insulin);

        builder.setMessage("enter glycemia/insulin levels ");
        builder.setPositiveButton("Ok", (dialog, which) -> {
            if (tv_glycemia.getText().toString() != "" && !tv_glycemia.getText().toString().isEmpty()) {
                glycemia_ = tv_glycemia.getText().toString();

                if (tv_insuline.getText().toString() != "") {
                    insulin_ = tv_insuline.getText().toString();
                }

                if (MainActivity.this.glycemia_ != "" && !MainActivity.this.glycemia_.toString().isEmpty()) {
                    // if user enters glycemia levels, show date picker.
                    Handler handler = new Handler();
                    handler.post(() -> {
                        DialogFragment datePicker = new DatePickerFragment();
                        datePicker.show(getSupportFragmentManager(), "date picker");
                    });
                }
            }
            dialog.cancel();
        });
        builder.setView(mView);
        builder.create();
        builder.show();
    }

    /**
     * When date is picked using calender dialog it will be passed to this callback.
     * date is formatted to the phone's local defaults.
     * @param view  datePicker view
     * @param year  year
     * @param month month
     * @param dayOfMonth dayOfMonth
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.DAY_OF_MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat sdfDate = new SimpleDateFormat(Constants.date_format,
                Locale.getDefault());

        String formatted_date_str = sdfDate.format(c.getTime());

        SimpleDateFormat format = new SimpleDateFormat(Constants.date_format, Locale.getDefault());
        try {
            current_date = format.parse(formatted_date_str);
            Handler handler = new Handler();
            handler.post(() -> {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                Log.d("handler", "inside handler: ");
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Callback for timeDialog. will be called upon user selecting time.
     * @param view      timeView
     * @param hourOfDay selected hourOfDay by user
     * @param minute    selected minute by user
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        view.setIs24HourView(true);

        //parse time and format it to readable format
        String time = hourOfDay + ":" + minute;
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = null;
        try {
            date = fmt.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat fmtOut = new SimpleDateFormat("hh:mm aa", Locale.getDefault());
        formattedTime = fmtOut.format(date);

        Log.d("TIME", formattedTime);
        Handler handler = new Handler();
        handler.post(this::sendDataToViewModel);
    }

    /**
     * onResume scroll to previous position
     */
    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerView.scrollToPosition(mPosition);
    }

    /**
     * send date/time, glycemia, and insulin to DB handlers for insertion
     */
    private void sendDataToViewModel() {
        mNotesViewModel.prepareInsertGlycemia(glycemia_,
                insulin_,
                current_date,
                formattedTime,
                isUpdate,
                recordID);
        isUpdate = false;

    }



}