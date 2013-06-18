

package com.donnfelker.android.bootstrap.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.donnfelker.android.bootstrap.R;
import com.donnfelker.android.bootstrap.R.id;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.ViewById;
import com.viewpagerindicator.TitlePageIndicator;

/**
 * Activity to view the carousel and view vp_pages tpi_header with fragments.
 */
@EActivity(R.layout.carousel_view)
public class CarouselActivity extends BootstrapFragmentActivity {

    @ViewById TitlePageIndicator tpi_header;
    @ViewById ViewPager vp_pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        super.onCreate(savedInstanceState);
    }

    @AfterViews
    protected void configureAdapter(){
        vp_pages.setAdapter(new BootstrapPagerAdapter(getResources(), getSupportFragmentManager()));

        tpi_header.setViewPager(vp_pages);
        vp_pages.setCurrentItem(1);
    }

    @OptionsItem
    boolean timerSelected() {
        final Intent i = new Intent(this, BootstrapTimerActivity.class);
        startActivity(i);
        return true;
    }
}
