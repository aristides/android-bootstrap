package com.donnfelker.android.bootstrap.ui;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.donnfelker.android.bootstrap.BootstrapApplication;
import com.donnfelker.android.bootstrap.R;
import com.donnfelker.android.bootstrap.core.PauseTimerEvent;
import com.donnfelker.android.bootstrap.core.ResumeTimerEvent;
import com.donnfelker.android.bootstrap.core.StopTimerEvent;
import com.donnfelker.android.bootstrap.core.TimerPausedEvent;
import com.donnfelker.android.bootstrap.core.TimerService;
import com.donnfelker.android.bootstrap.core.TimerTickEvent;
import javax.inject.Inject;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

@EActivity(R.layout.bootstrap_timer)
public class BootstrapTimerActivity extends BootstrapFragmentActivity {

    @Inject Bus BUS;

    @ViewById protected TextView chronometer;
    @ViewById protected Button start;
    @ViewById protected Button stop;
    @ViewById protected Button pause;
    @ViewById protected Button resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.timer);
    }

    @Override
    protected void onResume() {
        super.onResume();

        BUS.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        BUS.unregister(this);
    }


    /**
     * Starts the timer service
     */
    @Click
    void startClicked(){
        if(isTimerServiceRunning() == false) {
            final Intent i = new Intent(this, TimerService.class);
            startService(i);

            start.setVisibility(View.GONE);
            stop.setVisibility(View.VISIBLE);
            pause.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Posts a {@link StopTimerEvent} message to the {@link Bus}
     */
    @Click
    void stopClicked(){
        BUS.post(new StopTimerEvent());
    }

    /**
     * Posts a {@link PauseTimerEvent} message to the {@link Bus}
     */
    @Click
    void pauseClicked(){
        BUS.post(new PauseTimerEvent());
    }

    /**
     * Posts a {@link ResumeTimerEvent} message to the {@link Bus}
     */
    @Click
    void resumeClicked(){
        BUS.post(new ResumeTimerEvent());
    }

    private void produceResumeEvent() {

    }

    @Subscribe
    public void onTimerPausedEvent(TimerPausedEvent event) {
        if(event.isTimerIsPaused()) {
            resume.setVisibility(View.VISIBLE);
            stop.setVisibility(View.VISIBLE);
            pause.setVisibility(View.GONE);
            start.setVisibility(View.GONE);
        } else if(isTimerServiceRunning()) {
            pause.setVisibility(View.VISIBLE);
            stop.setVisibility(View.VISIBLE);
            resume.setVisibility(View.GONE);
            start.setVisibility(View.GONE);
        }
    }

    /**
     * Called by {@link Bus} when a tick event occurs.
     * @param event The event
     */
    @Subscribe
    public void onTickEvent(TimerTickEvent event) {
        setFormattedTime(event.getMillis());
    }



    /**
     * Called by {@link Bus} when a tick event occurs.
     * @param event The event
     */
    @Subscribe
    public void onPauseEvent(PauseTimerEvent event) {
        resume.setVisibility(View.VISIBLE);
        pause.setVisibility(View.GONE);
    }

    /**
     * Called by {@link Bus} when a tick event occurs.
     * @param event The event
     */
    @Subscribe
    public void onResumeEvent(ResumeTimerEvent event) {
        resume.setVisibility(View.GONE);
        pause.setVisibility(View.VISIBLE);
    }

    /**
     * Called by {@link Bus} when a tick event occurs.
     * @param event The event
     */
    @Subscribe
    public void onStopEvent(StopTimerEvent event) {
        resume.setVisibility(View.GONE);
        pause.setVisibility(View.GONE);
        start.setVisibility(View.VISIBLE);
        stop.setVisibility(View.GONE);
        setFormattedTime(0); // Since its stopped, zero out the timer.
    }

    /**
     * Checks to see if the timer service is running or not.
     * @return true if the service is running otherwise false.
     */
    private boolean isTimerServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (TimerService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the formatted time
     * @param millis the elapsed time
     */
    private void setFormattedTime(long millis) {
        final String formattedTime = formatTime(millis);
        chronometer.setText(formattedTime);
    }

    /**
     * Formats the time to look like "HH:MM:SS"
     * @param millis The number of elapsed milliseconds
     * @return A formatted time value
     */
    public static String formatTime(long millis) {

        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        seconds = seconds % 60;
        minutes = minutes % 60;
        hours = hours % 60;

        String secondsD = String.valueOf(seconds);
        String minutesD = String.valueOf(minutes);
        String hoursD = String.valueOf(hours);

        if (seconds < 10)
            secondsD = "0" + seconds;
        if (minutes < 10)
            minutesD = "0" + minutes;
        if (hours < 10)
            hoursD = "0" + hours;

        // HH:MM:SS
        return String.format("%1$s:%2$s:%3$s" , hoursD , minutesD , secondsD);

    }


}
