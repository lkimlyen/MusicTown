package com.demo.music.town.screen.tranding_video;

import android.util.Log;

import androidx.annotation.NonNull;

import com.demo.architect.data.helper.Constants;
import com.demo.architect.data.model.TrendingVideo;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.BaseUseCase;
import com.demo.architect.domain.GetTrandingVideoListUseCase;
import com.demo.music.town.R;
import com.demo.music.town.app.CoreApplication;
import com.demo.music.town.util.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class TrandingVideoPresenter implements TrandingVideoContract.Presenter {

    private final String TAG = TrandingVideoPresenter.class.getName();
    private final TrandingVideoContract.View view;
    private final GetTrandingVideoListUseCase getTrandingVideoListUseCase;
    @Inject
    LocalRepository localRepository;


    @Inject
    TrandingVideoPresenter(@NonNull TrandingVideoContract.View view, GetTrandingVideoListUseCase getTrandingVideoListUseCase) {
        this.view = view;
        this.getTrandingVideoListUseCase = getTrandingVideoListUseCase;
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
    public void sendRequestGetTrandingVideoList(String displayType, int page) {
        if (!NetworkUtils.isConnected(CoreApplication.getInstance())) {
            view.endLoadingList();
            view.showError(CoreApplication.getInstance().getString(R.string.text_err_connect_internet));
            return;
        }
        getTrandingVideoListUseCase.executeIO(new GetTrandingVideoListUseCase.RequestValue(Constants.DEFAULT_LIMIT_PAGE, page),
                new BaseUseCase.UseCaseCallback() {
                    @Override
                    public void onSuccess(Object successResponse) {
                        if (successResponse instanceof GetTrandingVideoListUseCase.ResponseValue){
                            List<TrendingVideo> list = ((GetTrandingVideoListUseCase.ResponseValue)successResponse).getResult();
                            view.displayTrandingVideoList(list,page);
                            view.endLoadingList();
                        }
                    }

                    @Override
                    public void onError(Object errorResponse) {
                        view.endLoadingList();
                    }
                });
    }
}
