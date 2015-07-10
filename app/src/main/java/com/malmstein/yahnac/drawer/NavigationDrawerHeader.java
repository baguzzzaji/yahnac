package com.malmstein.yahnac.drawer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.malmstein.yahnac.R;
import com.malmstein.yahnac.updater.LoginSharedPreferences;
import com.novoda.notils.caster.Classes;
import com.novoda.notils.caster.Views;

public class NavigationDrawerHeader extends LinearLayout {

    private LoginSharedPreferences loginSharedPreferences;
    private Listener listener;

    public NavigationDrawerHeader(Context context) {
        super(context);
    }

    public NavigationDrawerHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NavigationDrawerHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        loginSharedPreferences = LoginSharedPreferences.newInstance();
        listener = Classes.from(getContext());

        if (loginSharedPreferences.isLoggedIn()) {
            LayoutInflater.from(getContext()).inflate(R.layout.view_drawer_header_logged_in, this, true);
            updateWithUsername();

        } else {
            LayoutInflater.from(getContext()).inflate(R.layout.view_drawer_header_logged_out, this, true);
            updateWithLoginMessage();
        }

    }

    private void updateWithUsername() {
        TextView username = Views.findById(this, R.id.view_drawer_header_username);
        String message = String.format(getResources().getString(R.string.navigation_drawer_welcome), loginSharedPreferences.getLogin().getUsername());
        username.setText(message);

//        View loginView = Views.findById(this, R.id.view_drawer_header_login);
//        loginView.setOnClickListener(null);
    }

    private void updateWithLoginMessage() {
        View loginView = Views.findById(this, R.id.view_drawer_header_login);
        loginView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLoginClicked();
            }
        });

//        TextView username = Views.findById(this, R.id.view_drawer_header_username);
//        username.setText(R.string.navigation_drawer_login);
    }

    public interface Listener {
        void onLoginClicked();
    }

}
