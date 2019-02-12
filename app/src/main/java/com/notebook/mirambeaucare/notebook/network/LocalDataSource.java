package com.notebook.mirambeaucare.notebook.network;

import android.content.Context;
import android.util.Log;

import com.notebook.mirambeaucare.notebook.util.AppExecutors;

/**
 * Provides an API for handling local operations with DB.
 */
public class LocalDataSource {

    private static final String LOG_TAG = LocalDataSource.class.getSimpleName();
    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static LocalDataSource sInstance;
    private Context mContext;
    private AppExecutors mExecutors;

    public LocalDataSource(Context mContext, AppExecutors appExecutors) {
        this.mContext = mContext;
        mExecutors = appExecutors;
    }

    //Singleton instance
    public static LocalDataSource getInstance(Context context, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null){
            synchronized (LOCK) {
                sInstance = new LocalDataSource(context.getApplicationContext(), executors);
                Log.d(LOG_TAG, "made new network data source");
            }
        }
        return sInstance;
    }







}
