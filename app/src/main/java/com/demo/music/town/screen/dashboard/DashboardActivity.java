package com.demo.music.town.screen.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.fragment.app.FragmentTransaction;

import com.demo.music.town.R;
import com.demo.music.town.app.CoreApplication;
import com.demo.music.town.app.base.BaseActivity;
import com.demo.music.town.app.di.Precondition;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class DashboardActivity extends BaseActivity {

    DashboardFragment fragment;

    public static void start(Context context) {
        start(context, false);
    }

    public static void start(Context context, boolean clearTop) {
        Intent intent = new Intent(context, DashboardActivity.class);
        if (clearTop) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initFragment();


    }

    private void initFragment() {
        fragment = (DashboardFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = DashboardFragment.newInstance();
            addFragmentToBackStack(fragment, R.id.fragmentContainer);
        }
    }

    private void addFragmentToBackStack(DashboardFragment fragment, int frameId) {
        Precondition.checkNotNull(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.findViewById(android.R.id.content).findViewById(R.id.fragmentContainer).setPadding(0, 0, 0, 0);
    }

}
