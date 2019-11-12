package com.demo.music.town.screen.playlist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.architect.data.helper.Constants;
import com.demo.architect.data.model.ArtistCategory;
import com.demo.architect.data.model.TrendingVideoCategory;
import com.demo.music.town.R;
import com.demo.music.town.adapter.PlaylistMoreAdapter;
import com.demo.music.town.app.base.BaseFragment;
import com.demo.music.town.app.di.Precondition;
import com.demo.music.town.screen.tranding_video.TrandingVideoActivity;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MSI on 26/11/2017.
 */

public class PlayListFragment extends BaseFragment implements PlayListContract.View {
    private final String TAG = PlayListFragment.class.getName();
    private PlayListContract.Presenter mPresenter;


    public PlayListFragment() {
        // Required empty public constructor
    }


    public static PlayListFragment newInstance() {
        PlayListFragment fragment = new PlayListFragment();
        return fragment;
    }

    @BindView(R.id.rv_playlist)
    XRecyclerView rvPlayList;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    private PlaylistMoreAdapter adapter;
    private List<TrendingVideoCategory> trendingVideoList = new ArrayList<>();

    private List<ArtistCategory> artistCategoryList = new ArrayList<>();
    private int page = 1;
    private String displayType;

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
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        ButterKnife.bind(this, view);
        displayType = getActivity().getIntent().getStringExtra(Constants.BUNDLE_DISPLAY_TYPE);
        if (displayType.equals(Constants.ARTIST)){
            tvTitle.setText("POPULAR ARTIST");
        }
        adapter = new PlaylistMoreAdapter(trendingVideoList, artistCategoryList, displayType, new PlaylistMoreAdapter.OnItemClickListener() {
            @Override
            public void onItemTrendingCategory(TrendingVideoCategory trendingVideoCategory) {
                TrandingVideoActivity.start(getContext(),trendingVideoCategory);
            }

            @Override
            public void onItemArtistCategory(ArtistCategory trendingVideoCategory) {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        rvPlayList.setLayoutManager(layoutManager);
        rvPlayList.setAdapter(adapter);

        rvPlayList.setItemAnimator(new DefaultItemAnimator());
        rvPlayList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        rvPlayList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);

        rvPlayList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (displayType.equals(Constants.ARTIST)){
                    mPresenter.sendRequestGetArtistCategory(1);
                }else {

                    mPresenter.sendRequestGetTrendingCategory(displayType, 1);
                }
                rvPlayList.setLoadingMoreEnabled(true);
//                rcvData.refreshComplete();
                // refresh
            }

            @Override
            public void onLoadMore() {
                // load more
//                rcvData.loadMoreComplete();

                if (displayType.equals(Constants.ARTIST)){
                    mPresenter.sendRequestGetArtistCategory(page);
                }else {

                    mPresenter.sendRequestGetTrendingCategory(displayType, page);
                }
            }
        });
        if (displayType.equals(Constants.ARTIST)){
            mPresenter.sendRequestGetArtistCategory(page);
        }else {

            mPresenter.sendRequestGetTrendingCategory(displayType, page);
        }
        return view;
    }

    @Override
    public void setPresenter(PlayListContract.Presenter presenter) {
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
    public void displayTrendingVideoCategoryList(List<TrendingVideoCategory> list, int page) {
        this.page = page + 1;
        if (rvPlayList != null) {
            if (page == 1) {
                rvPlayList.setLoadingMoreEnabled(true);
                trendingVideoList.clear();
            }

            if (rvPlayList != null && list.size() < Constants.DEFAULT_LIMIT_PAGE) {
                rvPlayList.setLoadingMoreEnabled(false);
            }
            this.trendingVideoList.addAll(list);
            adapter.notifyDataSetChanged();
            //tvEmpty.setVisibility(insuranceList.size() > 0 ? GONE : View.VISIBLE);
        }
    }


    @Override
    public void endLoadingList() {
        rvPlayList.loadMoreComplete();
        rvPlayList.refreshComplete();
    }

    @Override
    public void displayArtistCategoryList(List<ArtistCategory> list, int page) {
        this.page = page + 1;
        if (rvPlayList != null) {
            if (page == 1) {
                rvPlayList.setLoadingMoreEnabled(true);
                artistCategoryList.clear();
            }

            if (rvPlayList != null && list.size() < Constants.DEFAULT_LIMIT_PAGE) {
                rvPlayList.setLoadingMoreEnabled(false);
            }
            this.artistCategoryList.addAll(list);
            adapter.notifyDataSetChanged();
            //tvEmpty.setVisibility(insuranceList.size() > 0 ? GONE : View.VISIBLE);
        }
    }

    @Override
    public void showSuccess(String message) {
        startDialogNoti(message);
    }


}
