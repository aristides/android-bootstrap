package com.donnfelker.android.bootstrap.ui;

import static com.donnfelker.android.bootstrap.core.Constants.Extra.USER;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.donnfelker.android.bootstrap.BootstrapApplication;
import com.donnfelker.android.bootstrap.R;
import com.donnfelker.android.bootstrap.core.AvatarLoader;
import com.donnfelker.android.bootstrap.core.User;
import javax.inject.Inject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.user_view)
public class UserActivity extends BootstrapActivity {

    @ViewById protected ImageView iv_avatar;
    @ViewById protected TextView tv_name;

    @Inject protected AvatarLoader avatarLoader;

    protected User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @AfterViews
    protected void loadUser(){
        if(getIntent() != null && getIntent().getExtras() != null) {
            user = (User) getIntent().getExtras().getSerializable(USER);
        }
        avatarLoader.bind(iv_avatar, user);
        tv_name.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
    }


}
