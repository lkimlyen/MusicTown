package com.demo.music.town.screen.view_video;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.architect.data.helper.Constants;
import com.demo.architect.data.model.Comment;
import com.demo.architect.data.model.TrendingVideo;
import com.demo.music.town.R;
import com.demo.music.town.adapter.CommentAdapter;
import com.demo.music.town.app.base.BaseFragment;
import com.demo.music.town.app.di.Precondition;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MSI on 26/11/2017.
 */

public class ViewVideoFragment extends BaseFragment implements ViewVideoContract.View {
    private final String TAG = ViewVideoFragment.class.getName();
    private ViewVideoContract.Presenter mPresenter;


    public ViewVideoFragment() {
        // Required empty public constructor
    }


    public static ViewVideoFragment newInstance() {
        ViewVideoFragment fragment = new ViewVideoFragment();
        return fragment;
    }

    private int mCurrentPosition = 0;

    @BindView(R.id.rv_comment)
    XRecyclerView rvComment;
    private CommentAdapter adapter;
    private List<Comment> commentList = new ArrayList<>();
    private TrendingVideo trendingVideo;
    private String videoId;
    private static final String PLAYBACK_TIME = "play_time";
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
        View view = inflater.inflate(R.layout.fragment_view_video, container, false);
        ButterKnife.bind(this, view);
//        if (savedInstanceState != null) {
//            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
//        }
        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.youtube_fragment, youTubePlayerFragment).commit();


        trendingVideo = (TrendingVideo) getActivity().getIntent().getSerializableExtra(Constants.BUNDLE_TRANDING_VIDEO);
        videoId = trendingVideo.getVideoUrl().substring(trendingVideo.getVideoUrl().lastIndexOf("=") + 1);
        // Set up the media controller widget and attach it to the video view.
        youTubePlayerFragment.initialize(getString(R.string.api_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoId);
                youTubePlayer.setFullscreen(false);
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getContext(), "Error :" + youTubeInitializationResult.toString(), Toast.LENGTH_LONG)
                        .show();
            }
        });

        adapter = new CommentAdapter(commentList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        rvComment.setLayoutManager(layoutManager);
        rvComment.setAdapter(adapter);

        rvComment.setItemAnimator(new DefaultItemAnimator());
        rvComment.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        rvComment.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);

        rvComment.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPresenter.sendRequestGetListComment(videoId, 1);
                rvComment.setLoadingMoreEnabled(true);
//                rcvData.refreshComplete();
                // refresh
            }

            @Override
            public void onLoadMore() {
                // load more
//                rcvData.loadMoreComplete();

                mPresenter.sendRequestGetListComment("", page);
            }
        });
        mPresenter.sendRequestGetListComment(videoId, 1);
        return view;
    }

    @Override
    public void setPresenter(ViewVideoContract.Presenter presenter) {
        this.mPresenter = Precondition.checkNotNull(presenter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current playback position (in milliseconds) to the
        // instance state bundle.
        //  outState.putInt(PLAYBACK_TIME, videoView.getCurrentPosition());
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
    public void onStart() {
        super.onStart();

        //initializePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
//            videoView.pause();
//        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //releasePlayer();
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
    public void displayListComment(List<Comment> list, int page) {
        this.page = page + 1;
        if (rvComment != null) {
            if (page == 1) {
                rvComment.setLoadingMoreEnabled(true);
                commentList.clear();
            }

            if (rvComment != null && list.size() < 20) {
                rvComment.setLoadingMoreEnabled(false);
            }
            this.commentList.addAll(list);
            adapter.notifyDataSetChanged();
            //tvEmpty.setVisibility(insuranceList.size() > 0 ? GONE : View.VISIBLE);
        }
    }
//
//    private void initializePlayer() {
//        // Show the "Buffering..." message while the video loads.
//        tvBuffering.setVisibility(VideoView.VISIBLE);
//
//        // Buffer and decode the video sample.
//        Uri videoUri = Uri.parse(trendingVideo.getVideoUrl());
//        videoView.setVideoURI(videoUri);
//
//        // Listener for onPrepared() event (runs after the media is prepared).
//        videoView.setOnPreparedListener(
//                new MediaPlayer.OnPreparedListener() {
//                    @Override
//                    public void onPrepared(MediaPlayer mediaPlayer) {
//
//                        // Hide buffering message.
//                        tvBuffering.setVisibility(VideoView.INVISIBLE);
//
//                        // Restore saved position, if available.
//                        if (mCurrentPosition > 0) {
//                            videoView.seekTo(mCurrentPosition);
//                        } else {
//                            // Skipping to 1 shows the first frame of the video.
//                            videoView.seekTo(1);
//                        }
//
//                        // Start playing!
//                        videoView.start();
//                    }
//                });
//
//        // Listener for onCompletion() event (runs after media has finished
//        // playing).
//        videoView.setOnCompletionListener(
//                new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mediaPlayer) {
////                        Toast.makeText(MainActivity.this,
////                                R.string.toast_message,
////                                Toast.LENGTH_SHORT).show();
//
//                        // Return the video position to the start.
//                        videoView.seekTo(0);
//                    }
//                });
//    }
//
//
//    // Release all media-related resources. In a more complicated app this
//    // might involve unregistering listeners or releasing audio focus.
//    private void releasePlayer() {
//        videoView.stopPlayback();
//    }
//
//    // Get a Uri for the media sample regardless of whether that sample is
//    // embedded in the app resources or available on the internet.
//    private Uri getMedia(String mediaName) {
//        if (URLUtil.isValidUrl(mediaName)) {
//            // Media name is an external URL.
//            return Uri.parse(mediaName);
//        } else {
//
//            // you can also put a video file in raw package and get file from there as shown below
//
//            return Uri.parse("android.resource://" + getContext().getPackageName() +
//                    "/raw/" + mediaName);
//
//
//        }
//    }
}
