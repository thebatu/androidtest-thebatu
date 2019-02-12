package com.notebook.mirambeaucare.notebook.ui;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import com.notebook.mirambeaucare.notebook.R;
import butterknife.BindView;

public class BaseActivity extends AppCompatActivity {

    //progressbar indicator
    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;

    /**
     * show main content if available
     */
    private void showMainDishDataView() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);

    }

    /**
     * show loading indicator if main content is loading
     */
    private void showLoading() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }


}
