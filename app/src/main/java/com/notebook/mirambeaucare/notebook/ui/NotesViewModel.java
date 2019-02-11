package com.notebook.mirambeaucare.notebook.ui;

import android.arch.lifecycle.ViewModel;

import com.notebook.mirambeaucare.notebook.database.Glycemia;
import com.notebook.mirambeaucare.notebook.database.NotebookRepository;

import java.util.ArrayList;
import java.util.List;

public class NotesViewModel extends ViewModel {

    NotebookRepository mNotebookRepository;

    List<Glycemia> glycemias = new ArrayList<>();

    public NotesViewModel() {

    }



    public void getAllRecords() {
       // glycemias = GlycemiaDao.getAllGlycemia();
    }

    public  void insetRecord(Glycemia glycemia){

//        Date date1 = (Date) DateConverter.toDate(date);
//        Date mdate = new Date(date);

//        mNotebookRepository.insert(glycemia);
        mNotebookRepository.insert(glycemia);
    }





}
