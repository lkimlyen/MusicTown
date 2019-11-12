package com.demo.architect.domain;

import android.util.Log;

import com.demo.architect.data.model.BaseListResponse;
import com.demo.architect.data.model.TrendingVideo;
import com.demo.architect.data.repository.base.data.remote.DataRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;


public class GetVideoListByCategoryIdUseCase extends BaseUseCase<BaseListResponse<TrendingVideo.TrandingVideoRespond>> {
    private static final String TAG = GetVideoListByCategoryIdUseCase.class.getSimpleName();
    private final DataRepository remoteRepository;

    public GetVideoListByCategoryIdUseCase(DataRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable<BaseListResponse<TrendingVideo.TrandingVideoRespond>> buildUseCaseObservable() {
        String filter  = ((RequestValue) requestValues).filter;
        int limit = ((RequestValue) requestValues).limit;
        int page = ((RequestValue) requestValues).page;
        return remoteRepository.sendReqestGetVideoByCategoryId(filter,limit, page);
    }

    @Override
    protected DisposableObserver<BaseListResponse<TrendingVideo.TrandingVideoRespond>> buildUseCaseSubscriber() {
        return new DefaultObserver<BaseListResponse<TrendingVideo.TrandingVideoRespond>>() {
            @Override
            public void onNext(BaseListResponse<TrendingVideo.TrandingVideoRespond> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data));
                if (useCaseCallback != null) {
                    List<TrendingVideo> result = data.getResults().getObjects().getRows();
                    if (result != null && data.getStatus() == 200) {
                        useCaseCallback.onSuccess(new ResponseValue(result));
                    } else {
                        useCaseCallback.onError(new ErrorValue(data.getStatus()));
                    }
                }

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                if (useCaseCallback != null) {
                    useCaseCallback.onError(new ErrorValue(-1));
                }
            }
        };
    }


    public static final class RequestValue implements RequestValues {
        private final String filter;
        public final int limit;
        public final int page;


        public RequestValue(String filter, int limit, int page) {
            this.filter = filter;
            this.limit = limit;
            this.page = page;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private List<TrendingVideo> result;

        public ResponseValue(List<TrendingVideo> result) {
            this.result = result;
        }

        public List<TrendingVideo> getResult() {
            return result;
        }
    }

    public static final class ErrorValue implements ErrorValues {
        private final int error;

        public ErrorValue(int error) {
            this.error = error;
        }

        public int getData() {
            return error;
        }
    }
}
