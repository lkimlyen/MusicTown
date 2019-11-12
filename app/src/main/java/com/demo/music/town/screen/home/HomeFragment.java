package com.demo.music.town.screen.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.architect.data.helper.Constants;
import com.demo.architect.data.model.ArtistCategory;
import com.demo.architect.data.model.TrendingVideoCategory;
import com.demo.music.town.R;
import com.demo.music.town.adapter.ArtistAdapter;
import com.demo.music.town.adapter.PlaylistAdapter;
import com.demo.music.town.app.base.BaseFragment;
import com.demo.music.town.app.di.Precondition;
import com.demo.music.town.screen.playlist.PlayListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    private final String TAG = HomeFragment.class.getName();
    private HomeContract.Presenter mPresenter;


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @BindView(R.id.rv_artist)
    RecyclerView rvArtist;
    @BindView(R.id.rv_popular)
    RecyclerView rvPopular;

    @BindView(R.id.tv_popular)
    TextView tvPopular;
    @BindView(R.id.rv_recommended)
    RecyclerView rvRecommended;
    private ArtistAdapter artistAdapter;
    private PlaylistAdapter popularPlaylistAdapter;
    private List<TrendingVideoCategory> popularVideoList = new ArrayList<>();
    private List<ArtistCategory> artistCategoryList = new ArrayList<>();

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int padding16 = getResources().getDimensionPixelSize(R.dimen.distance_16dp);
        int witdhItem = (width - padding16 * 3) / 3;
        artistAdapter = new ArtistAdapter(artistCategoryList, witdhItem);
        rvArtist.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvArtist.setAdapter(artistAdapter);
        popularPlaylistAdapter = new PlaylistAdapter(popularVideoList, witdhItem);
        rvPopular.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvPopular.setAdapter(popularPlaylistAdapter);
        mPresenter.sendRequestGetArtistCategory();
        mPresenter.sendRequestGetTrendingCategory(Constants.POPULAR);

        return view;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
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
    public void displayArtistCategoryList(List<ArtistCategory> list) {
        this.artistCategoryList.addAll(list);
        artistAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayTrendingVideoCategoryList(List<TrendingVideoCategory> list, String displayType) {
        switch (displayType) {
            case Constants.POPULAR:

                popularVideoList.addAll(list);
                popularPlaylistAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void showSuccess(String message) {
        startDialogNoti(message);
    }

    @OnClick({R.id.tv_more_popular, R.id.tv_more_artist})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_more_popular:
                PlayListActivity.start(getContext(),Constants.POPULAR);
                break;
            case R.id.tv_more_artist:
                PlayListActivity.start(getContext(),Constants.ARTIST);
                break;

        }
    }

}
