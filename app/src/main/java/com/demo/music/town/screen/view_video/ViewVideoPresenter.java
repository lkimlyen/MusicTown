package com.demo.music.town.screen.view_video;

import android.util.Log;

import androidx.annotation.NonNull;

import com.demo.architect.data.helper.Constants;
import com.demo.architect.data.model.Comment;
import com.demo.architect.data.model.TrendingVideo;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.BaseUseCase;
import com.demo.architect.domain.GetCommentListUseCase;
import com.demo.architect.domain.GetTrandingVideoListUseCase;
import com.demo.music.town.R;
import com.demo.music.town.app.CoreApplication;
import com.demo.music.town.util.NetworkUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class ViewVideoPresenter implements ViewVideoContract.Presenter {

    private final String TAG = ViewVideoPresenter.class.getName();
    private final ViewVideoContract.View view;
    private final GetCommentListUseCase getCommentListUseCase;
    @Inject
    LocalRepository localRepository;


    @Inject
    ViewVideoPresenter(@NonNull ViewVideoContract.View view, GetCommentListUseCase getCommentListUseCase) {
        this.view = view;
        this.getCommentListUseCase = getCommentListUseCase;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


    @Override
    public void sendRequestGetListComment(String videoId, int page) {
        if (!NetworkUtils.isConnected(CoreApplication.getInstance())) {
            view.showError(CoreApplication.getInstance().getString(R.string.text_err_connect_internet));
            return;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("video_id", videoId);

        getCommentListUseCase.executeIO(new GetCommentListUseCase.RequestValue(params, page),
                new BaseUseCase.UseCaseCallback() {
                    @Override
                    public void onSuccess(Object successResponse) {
                        if (successResponse instanceof GetCommentListUseCase.ResponseValue) {
                            List<Comment> list = ((GetCommentListUseCase.ResponseValue) successResponse).getResult();
                            view.displayListComment(list,page);

                        }
                    }

                    @Override
                    public void onError(Object errorResponse) {

                    }
                });
    }
}
