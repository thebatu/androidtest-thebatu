package com.notebook.mirambeaucare.notebook.util;

import android.content.Context;
import com.notebook.mirambeaucare.notebook.database.NotebookDatabase;
import com.notebook.mirambeaucare.notebook.database.NotebookRepository;
import com.notebook.mirambeaucare.notebook.network.LocalDataSource;
import com.notebook.mirambeaucare.notebook.ui.NotesViewModelFactory;

/**
 * Provides static methods to inject the various classes needed for HomeFoodie
 * should be replaced with Dagger2 lib
 */
public class InjectorUtils {

    public static NotebookRepository provideRepository(Context context) {

        NotebookDatabase database = NotebookDatabase.getInstance(context
                .getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();

        LocalDataSource localDataSource =
                LocalDataSource.getInstance(context.getApplicationContext(), executors);

        return NotebookRepository.getInstance(database.glycemiaDao(), executors, localDataSource);
    }

    public static NotesViewModelFactory provideNotesViewModelFactory(Context context) {
        NotebookRepository repository = provideRepository(context.getApplicationContext());
        return new NotesViewModelFactory(repository);
    }


}
