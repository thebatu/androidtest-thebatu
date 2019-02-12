package com.notebook.mirambeaucare.notebook.database;

import android.util.Log;
import com.notebook.mirambeaucare.notebook.network.LocalDataSource;
import com.notebook.mirambeaucare.notebook.util.AppExecutors;

import java.util.List;

/**
 * Application repository. servers as handler to database operations
 */
public class NotebookRepository {

    private static final Object LOCK = new Object();
    private static NotebookRepository sInstance;
    private GlycemiaDao glycemiaDao;
    private AppExecutors mExecutors;
    private LocalDataSource mLocalDataSource;

    //constructor
    public NotebookRepository(GlycemiaDao glycemiaDao,  AppExecutors mExecutors,
                              LocalDataSource localDataSource) {
        this.glycemiaDao = glycemiaDao;
        this.mExecutors = mExecutors;
        this.mLocalDataSource = localDataSource;
    }

    //singleton instance
    public synchronized static NotebookRepository getInstance(GlycemiaDao glycemiaDao,
                                                              AppExecutors executors,
                                                              LocalDataSource localDataSource) {
        Log.d("TAG", "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NotebookRepository(glycemiaDao, executors, localDataSource );
            }
        }
        return sInstance;
    }

    /**
     * Insert a new glycemia record to DB
     * @param glycemia glycemia instance
     */
    public void insert(Glycemia glycemia) {
        glycemiaDao.insert(glycemia);
    }

    /**
     * return all records
     * @return list of all glycemia recordds
     */
    public List<Glycemia> getAllRecord(){
        return glycemiaDao.getAllGlycemia();
    }

}
