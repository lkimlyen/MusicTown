package com.demo.music.town.screen.splash;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.music.town.R;
import com.demo.music.town.app.base.BaseFragment;
import com.demo.music.town.app.di.Precondition;
import com.demo.music.town.screen.dashboard.DashboardActivity;

import butterknife.ButterKnife;

/**
 * Created by MSI on 26/11/2017.
 */

public class SplashFragment extends BaseFragment implements SplashContract.View {
    private final String TAG = SplashFragment.class.getName();

    private SplashContract.Presenter mPresenter;

    public SplashFragment() {
        // Required empty public constructor
    }


    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, view);
        CountDownTimer countDownTimer = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                goToDashboard();
            }
        };
        countDownTimer.start();

        return view;
    }


    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        this.mPresenter = Precondition.checkNotNull(presenter);
    }

    @Override
    public void showProgressBar() {
        showProgressDialog();
    }

    @Override
    public void hideProgressBar() {
        hideProgressDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.stop();
    }


    private void startDialogNoti(String content) {
        Activity activity = getActivity();
        if (activity != null) {
            new AlertDialog.Builder(activity)
                    .setTitle(getString(R.string.text_sweet_dialog_title))
                    .setMessage(content)
                    .setPositiveButton(getString(R.string.text_ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();

                        }
                    }).show();
        }
    }


    @Override
    public void showError(String error) {
        startDialogNoti(error);
    }

    @Override
    public void goToDashboard() {
        DashboardActivity.start(getContext());
    }

    @Override
    public void showSuccess(String message) {
        startDialogNoti(message);
    }


}
