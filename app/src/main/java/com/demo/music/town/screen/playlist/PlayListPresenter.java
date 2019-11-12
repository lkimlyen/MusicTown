package com.demo.music.town.screen.playlist;

import android.util.Log;

import androidx.annotation.NonNull;

import com.demo.architect.data.model.ArtistCategory;
import com.demo.architect.data.model.TrendingVideoCategory;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.BaseUseCase;
import com.demo.architect.domain.GetArtistCategoryListUseCase;
import com.demo.architect.domain.GetTrandingVideoCategoryListUseCase;
import com.demo.music.town.R;
import com.demo.music.town.app.CoreApplication;
import com.demo.music.town.util.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class PlayListPresenter implements PlayListContract.Presenter {

    private final String TAG = PlayListPresenter.class.getName();
    private final PlayListContract.View view;
    private final GetTrandingVideoCategoryListUseCase getTrandingVideoCategoryListUseCase;

    private final GetArtistCategoryListUseCase getArtistCategoryListUseCase;
    @Inject
    LocalRepository localRepository;


    @Inject
    PlayListPresenter(@NonNull PlayListContract.View view, GetTrandingVideoCategoryListUseCase getTrandingVideoCategoryListUseCase, GetArtistCategoryListUseCase getArtistCategoryListUseCase) {
        this.view = view;
        this.getTrandingVideoCategoryListUseCase = getTrandingVideoCategoryListUseCase;
        this.getArtistCategoryListUseCase = getArtistCategoryListUseCase;
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
    public void sendRequestGetTrendingCategory(String displayType, int page) {
        if (!NetworkUtils.isConnected(CoreApplication.getInstance())) {
            view.showError(CoreApplication.getInstance().getString(R.string.text_err_connect_internet));
            return;
        }
        String filter = "[\"$all\",{\"trending_videos\": [\"$all\",{\"$filter\":{\"display_type\":\""+displayType+"\"}}]}]";
        getTrandingVideoCategoryListUseCase.executeIO(new GetTrandingVideoCategoryListUseCase.RequestValue(filter,50, page),
                new BaseUseCase.UseCaseCallback() {
                    @Override
                    public void onSuccess(Object successResponse) {
                        if (successResponse instanceof GetTrandingVideoCategoryListUseCase.ResponseValue){
                            List<TrendingVideoCategory> list = ((GetTrandingVideoCategoryListUseCase.ResponseValue)successResponse).getResult();
                            view.displayTrendingVideoCategoryList(list,page);
                        }
                    }

                    @Override
                    public void onError(Object errorResponse) {

                    }
                });

    }


    @Override
    public void sendRequestGetArtistCategory(int page) {
        if (!NetworkUtils.isConnected(CoreApplication.getInstance())) {
            view.showError(CoreApplication.getInstance().getString(R.string.text_err_connect_internet));
            return;
        }
        getArtistCategoryListUseCase.executeIO(new GetArtistCategoryListUseCase.RequestValue(50, page),
                new BaseUseCase.UseCaseCallback() {
                    @Override
                    public void onSuccess(Object successResponse) {
                        if (successResponse instanceof GetArtistCategoryListUseCase.ResponseValue){
                            List<ArtistCategory> list = ((GetArtistCategoryListUseCase.ResponseValue)successResponse).getResult();
                            view.displayArtistCategoryList(list, page);
                        }
                    }

                    @Override
                    public void onError(Object errorResponse) {

                    }
                });

    }
}
