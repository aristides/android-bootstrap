package com.donnfelker.android.bootstrap.ui;

import android.content.Intent;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.donnfelker.android.bootstrap.BootstrapApplication;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

/**
 * Base activity for a Bootstrap activity which does not use fragments.
 */
@EActivity
public abstract class BootstrapActivity extends SherlockActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BootstrapApplication.getInstance().inject(this);
    }

    /**
     * This is the home button in the top left corner of the screen.
     * Dont call finish! Because activity could have been started by an outside activity and the home button would not operated as expected!
     * */
    @OptionsItem
    boolean homeSelected() {
        Intent homeIntent = new Intent(this, CarouselActivity_.class);
        homeIntent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(homeIntent);
        return true;
    }
}
