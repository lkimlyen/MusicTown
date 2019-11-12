package com.demo.music.town.screen.tranding_video;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

import com.demo.architect.data.helper.Constants;
import com.demo.architect.data.model.ArtistCategory;
import com.demo.architect.data.model.TrendingVideoCategory;
import com.demo.music.town.R;
import com.demo.music.town.app.CoreApplication;
import com.demo.music.town.app.base.BaseActivity;
import com.demo.music.town.app.di.Precondition;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class TrandingVideoActivity extends BaseActivity {

    TrandingVideoFragment fragment;

    @Inject
    TrandingVideoPresenter presenter;

    public static void start(Context context, TrendingVideoCategory category) {
        Intent intent = new Intent(context, TrandingVideoActivity.class);
        intent.putExtra(Constants.BUNDLE_CATEGORY, category);
        context.startActivity(intent);
    }
    public static void start(Context context, ArtistCategory category) {
        Intent intent = new Intent(context, TrandingVideoActivity.class);
        intent.putExtra(Constants.BUNDLE_CATEGORY, category);
        context.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initFragment();

        CoreApplication.getInstance().getApplicationComponent().plus(
                new TrandingVideoModule(fragment)).inject(this);
    }

    private void initFragment() {
        fragment = (TrandingVideoFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = TrandingVideoFragment.newInstance();
            addFragmentToBackStack(fragment, R.id.fragmentContainer);
        }
    }

    private void addFragmentToBackStack(TrandingVideoFragment fragment, int frameId) {
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
