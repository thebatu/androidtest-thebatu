package com.notebook.mirambeaucare.notebook.ui;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.notebook.mirambeaucare.notebook.database.NotebookRepository;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link NotebookRepository}
 */
public class NotesViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private NotebookRepository mRepository;

    public NotesViewModelFactory(NotebookRepository mRepository) {
        this.mRepository = mRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new NotesViewModel(mRepository);

    }



}
