package com.notebook.mirambeaucare.notebook.ui;

import android.arch.lifecycle.ViewModel;

import com.notebook.mirambeaucare.notebook.database.Glycemia;
import com.notebook.mirambeaucare.notebook.database.NotebookRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotesViewModel extends ViewModel {

    private NotebookRepository mNotebookRepository;

    List<Glycemia> glycemias = new ArrayList<>();

    public NotesViewModel(NotebookRepository mNotebookRepository) {
        this.mNotebookRepository = mNotebookRepository;
    }


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
        Glycemia glycemia = new Glycemia(current_date, Float.parseFloat(insulin_), Float.parseFloat(glycemia_));
        insetRecord(glycemia);

    }

    public ArrayList<List<Glycemia>> getAllRecords(){
       return  mNotebookRepository.getAllRecord();
    }


}
