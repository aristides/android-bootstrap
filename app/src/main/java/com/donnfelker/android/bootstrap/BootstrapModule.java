package com.donnfelker.android.bootstrap;

import android.accounts.AccountManager;
import android.content.Context;
import com.donnfelker.android.bootstrap.authenticator.BootstrapAuthenticatorActivity_;
import com.donnfelker.android.bootstrap.authenticator.LogoutService;
import com.donnfelker.android.bootstrap.core.TimerService;
import com.donnfelker.android.bootstrap.ui.*;
import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Dagger module for setting up provides statements.
 * Register all of your entry points below.
 */
@Module
(
        complete = false,

        injects = {
                BootstrapApplication.class,
                BootstrapAuthenticatorActivity_.class,
                CarouselActivity_.class,
                BootstrapTimerActivity_.class,
                CheckInsListFragment.class,
                NewsActivity_.class,
                NewsListFragment.class,
                UserActivity_.class,
                UserListFragment.class,
                TimerService.class
        }

)
public class BootstrapModule  {

    @Singleton
    @Provides
    Bus provideOttoBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    LogoutService provideLogoutService(final Context context, final AccountManager accountManager) {
        return new LogoutService(context, accountManager);
    }

}
