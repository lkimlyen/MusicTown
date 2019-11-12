package com.demo.music.town.screen.view_video;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

import com.demo.architect.data.helper.Constants;
import com.demo.architect.data.model.TrendingVideo;
import com.demo.music.town.R;
import com.demo.music.town.app.CoreApplication;
import com.demo.music.town.app.base.BaseActivity;
import com.demo.music.town.app.di.Precondition;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class ViewVideoActivity extends BaseActivity {

    ViewVideoFragment fragment;

    @Inject
    ViewVideoPresenter presenter;
   // public static void start(Context context) {
//        start(context, false);
//    }

    public static void start(Context context, TrendingVideo trendingVideo) {
        Intent intent = new Intent(context, ViewVideoActivity.class);
       intent.putExtra(Constants.BUNDLE_TRANDING_VIDEO,trendingVideo );
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initFragment();

        CoreApplication.getInstance().getApplicationComponent().plus(
                new ViewVideoModule(fragment)).inject(this);
    }

    private void initFragment() {
        fragment = (ViewVideoFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = ViewVideoFragment.newInstance();
            addFragmentToBackStack(fragment, R.id.fragmentContainer);
        }
    }

    private void addFragmentToBackStack(ViewVideoFragment fragment, int frameId) {
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
