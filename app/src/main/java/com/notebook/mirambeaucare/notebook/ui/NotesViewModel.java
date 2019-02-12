package com.notebook.mirambeaucare.notebook.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.notebook.mirambeaucare.notebook.database.Glycemia;
import com.notebook.mirambeaucare.notebook.database.NotebookRepository;

import java.util.Date;
import java.util.List;

/**
 * ViewModel class for notes. survives configuration changes.
 * stores data for activity
 */
public class NotesViewModel extends ViewModel {

    //app repository
    private NotebookRepository mNotebookRepository;

    //constructor
    public NotesViewModel(NotebookRepository mNotebookRepository) {
        this.mNotebookRepository = mNotebookRepository;
    }

    /**
     * insert a glycemia to local DB
     * @param glycemia glycemia
     */
    private void insetRecord(Glycemia glycemia) {
        mNotebookRepository.insert(glycemia);
    }


    /**
     * create Glycemia object, forward the object for DB insertion
     *
     * @param glycemia_
     * @param insulin_
     * @param current_date
     * @param formattedTime
     */
    public void prepareInsertGlycemia(String glycemia_, String insulin_, Date current_date, String formattedTime) {
        if (insulin_.isEmpty()){
            insulin_ = "0";
        }
        Glycemia glycemia = new Glycemia(current_date, Float.parseFloat(insulin_), Float.parseFloat(glycemia_));
        insetRecord(glycemia);
    }

    /**
     * @return all records of glycemia
     */
    public LiveData<List<Glycemia>> getAllRecords(){
       return  mNotebookRepository.getAllRecord();
    }


    /**
     * delete a record from local DB
     * @param glycemiaAt
     */
    public void delete(Glycemia glycemiaAt) {
        mNotebookRepository.delete(glycemiaAt);
    }
}
