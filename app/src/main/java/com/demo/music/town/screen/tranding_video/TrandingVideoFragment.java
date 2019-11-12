package com.demo.music.town.screen.tranding_video;

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
import com.demo.architect.data.model.TrendingVideo;
import com.demo.architect.data.model.TrendingVideoCategory;
import com.demo.music.town.R;
import com.demo.music.town.adapter.TrendingVideoAdapter;
import com.demo.music.town.app.base.BaseFragment;
import com.demo.music.town.app.di.Precondition;
import com.demo.music.town.screen.download.DownloadActivity;
import com.demo.music.town.screen.view_video.ViewVideoActivity;
import com.demo.music.town.screen.view_video.WatchActivity;
import com.demo.music.town.screen.view_video.YouTubePlayerActivity;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MSI on 26/11/2017.
 */

public class TrandingVideoFragment extends BaseFragment implements TrandingVideoContract.View {
    private final String TAG = TrandingVideoFragment.class.getName();
    private TrandingVideoContract.Presenter mPresenter;


    public TrandingVideoFragment() {
        // Required empty public constructor
    }


    public static TrandingVideoFragment newInstance() {
        TrandingVideoFragment fragment = new TrandingVideoFragment();
        return fragment;
    }

    @BindView(R.id.rv_video)
    XRecyclerView rvVideo;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    private TrendingVideoCategory trendingVideoCategory;
    private TrendingVideoAdapter adapter;
    private List<TrendingVideo> trendingVideoList = new ArrayList<>();
    private int page = 1;

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
        View view = inflater.inflate(R.layout.fragment_tranding_video, container, false);
        ButterKnife.bind(this, view);
        trendingVideoCategory =(TrendingVideoCategory) getActivity().getIntent().getSerializableExtra(Constants.BUNDLE_CATEGORY);
        tvTitle.setText(trendingVideoCategory.getName());
        adapter = new TrendingVideoAdapter(trendingVideoList, new TrendingVideoAdapter.OnClickItemListener() {
            @Override
            public void onClickItem(TrendingVideo trendingVideo) {
//                YouTubePlayerActivity.start(getContext(), trendingVideo);
                ViewVideoActivity.start(getContext(),trendingVideo);
            }

            @Override
            public void onDownload(String url) {
                DownloadActivity.start(getContext(), url);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        rvVideo.setLayoutManager(layoutManager);
        rvVideo.setAdapter(adapter);

        rvVideo.setItemAnimator(new DefaultItemAnimator());
        rvVideo.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        rvVideo.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);

        rvVideo.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (trendingVideoCategory != null){
                    mPresenter.sendRequestGetTrandingVideoList(trendingVideoCategory.getId(), 1);
                }
                rvVideo.setLoadingMoreEnabled(true);
//                rcvData.refreshComplete();
                // refresh
            }

            @Override
            public void onLoadMore() {
                // load more
//                rcvData.loadMoreComplete();

                if (trendingVideoCategory != null){
                    mPresenter.sendRequestGetTrandingVideoList(trendingVideoCategory.getId(), page);
                }
            }
        });
        if (trendingVideoCategory != null){
            mPresenter.sendRequestGetTrandingVideoList(trendingVideoCategory.getId(), page);
        }

        return view;
    }

    @Override
    public void setPresenter(TrandingVideoContract.Presenter presenter) {
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
    public void displayTrandingVideoList(List<TrendingVideo> list, int page) {
        this.page = page + 1;
        if (rvVideo != null) {
            if (page == 1) {
                rvVideo.setLoadingMoreEnabled(true);
                trendingVideoList.clear();
            }

            if (rvVideo != null && list.size() < Constants.DEFAULT_LIMIT_PAGE) {
                rvVideo.setLoadingMoreEnabled(false);
            }
            this.trendingVideoList.addAll(list);
            adapter.notifyDataSetChanged();
            //tvEmpty.setVisibility(insuranceList.size() > 0 ? GONE : View.VISIBLE);
        }

    }

    @Override
    public void endLoadingList() {
        rvVideo.loadMoreComplete();
        rvVideo.refreshComplete();
    }

    @Override
    public void showSuccess(String message) {
        startDialogNoti(message);
    }


}
