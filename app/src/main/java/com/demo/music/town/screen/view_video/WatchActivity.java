package com.demo.music.town.screen.view_video;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.demo.architect.data.helper.Constants;
import com.demo.architect.data.model.TrendingVideo;
import com.demo.music.town.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class WatchActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {

    public static void start(Context context, TrendingVideo trendingVideo) {
        Intent intent = new Intent(context, YouTubePlayerActivity.class);
        intent.putExtra(Constants.BUNDLE_TRANDING_VIDEO, trendingVideo);
        context.startActivity(intent);
    }
    private TrendingVideo trendingVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_view_video);

        trendingVideo = (TrendingVideo) getIntent().getSerializableExtra(Constants.BUNDLE_TRANDING_VIDEO);

      //  YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        //youTubeView.initialize(getString(R.string.api_key), this);


    }

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.xemphim_online, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		if (item.getItemId() == R.id.action_back) {
//			finish();
//		}
//		return super.onOptionsItemSelected(item);
//	}

    @Override
    public void onInitializationFailure(Provider arg0,
                                        YouTubeInitializationResult error) {
        Toast.makeText(this, "Error :" + error.toString(), Toast.LENGTH_LONG)
                .show();

    }

    @Override
    public void onInitializationSuccess(Provider arg0, YouTubePlayer player,
                                        boolean arg2) {
        final String videoId = trendingVideo.getVideoUrl().substring(trendingVideo.getVideoUrl().lastIndexOf("=") + 1);
        player.loadVideo(videoId);
        player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        player.setFullscreen(false);
    }


}
