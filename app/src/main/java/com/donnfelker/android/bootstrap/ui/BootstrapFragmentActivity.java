package com.donnfelker.android.bootstrap.ui;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.donnfelker.android.bootstrap.BootstrapApplication;

/**
 * Base class for all Bootstrap Activities that need fragments.
 */
public class BootstrapFragmentActivity extends SherlockFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BootstrapApplication.getInstance().inject(this);
    }

}
