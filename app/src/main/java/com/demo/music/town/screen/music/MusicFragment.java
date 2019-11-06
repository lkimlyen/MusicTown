package com.demo.music.town.screen.music;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.architect.data.model.SongEntity;
import com.demo.music.town.R;
import com.demo.music.town.adapter.MusicAdapter;
import com.demo.music.town.app.base.BaseFragment;
import com.demo.music.town.app.di.Precondition;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MSI on 26/11/2017.
 */

public class MusicFragment extends BaseFragment implements MusicContract.View {
    private final String TAG = MusicFragment.class.getName();
    private MusicContract.Presenter mPresenter;

    public MusicFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.rv_data)
    RecyclerView rvData;

    private MusicAdapter adapter;
    private List<SongEntity> songList = new ArrayList<>();

    public static MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
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
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        ButterKnife.bind(this, view);

        mPresenter.getListMusic();
        return view;
    }

    @Override
    public void setPresenter(MusicContract.Presenter presenter) {
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
//        mPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        //  mPresenter.stop();
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
    public void showSuccess(String message) {
        startDialogNoti(message);
    }


    @Override
    public void showListSong(List<SongEntity> songList) {
//        songList.clear();
//        songList.addAll(songList);
//        adapter.notifyDataSetChanged();
        adapter = new MusicAdapter(songList);
        rvData.setLayoutManager(new LinearLayoutManager(getContext()));
        rvData.setAdapter(adapter);
    }
}
