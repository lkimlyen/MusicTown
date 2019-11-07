package com.demo.music.town.screen.home;

import android.util.Log;

import androidx.annotation.NonNull;

import com.demo.architect.data.model.ArtistCategory;
import com.demo.architect.data.repository.base.local.LocalRepository;
import com.demo.architect.domain.BaseUseCase;
import com.demo.architect.domain.GetArtistCategoryListUseCase;
import com.demo.music.town.R;
import com.demo.music.town.app.CoreApplication;
import com.demo.music.town.util.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class HomePresenter implements HomeContract.Presenter {

    private final String TAG = HomePresenter.class.getName();
    private final HomeContract.View view;
    private GetArtistCategoryListUseCase getArtistCategoryListUseCase;
    @Inject
    LocalRepository localRepository;


    @Inject
    HomePresenter(@NonNull HomeContract.View view, GetArtistCategoryListUseCase getArtistCategoryListUseCase) {
        this.view = view;
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
    public void sendRequestGetArtistCategory() {
        if (!NetworkUtils.isConnected(CoreApplication.getInstance())) {
            view.showError(CoreApplication.getInstance().getString(R.string.text_err_connect_internet));
            return;
        }
        getArtistCategoryListUseCase.executeIO(new GetArtistCategoryListUseCase.RequestValue(10, 1),
                new BaseUseCase.UseCaseCallback() {
                    @Override
                    public void onSuccess(Object successResponse) {
                        if (successResponse instanceof GetArtistCategoryListUseCase.ResponseValue){
                            List<ArtistCategory> list = ((GetArtistCategoryListUseCase.ResponseValue)successResponse).getResult();
                            view.displayArtistCategoryList(list);
                        }
                    }

                    @Override
                    public void onError(Object errorResponse) {

                    }
                });

    }
}
