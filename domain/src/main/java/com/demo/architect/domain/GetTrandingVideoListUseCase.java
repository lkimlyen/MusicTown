package com.demo.architect.domain;

import android.util.Log;

import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.TrendingVideo;
import com.demo.architect.data.repository.base.data.remote.DataRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;


public class GetTrandingVideoListUseCase extends BaseUseCase<BaseResponse<TrendingVideo.TrandingVideoRespond>> {
    private static final String TAG = GetTrandingVideoListUseCase.class.getSimpleName();
    private final DataRepository remoteRepository;

    public GetTrandingVideoListUseCase(DataRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable<BaseResponse<TrendingVideo.TrandingVideoRespond>> buildUseCaseObservable() {
        int limit = ((RequestValue) requestValues).limit;
        int page  = ((RequestValue) requestValues).page;
        return remoteRepository.sendReqestGetTrandingVideo(limit, page);
    }

    @Override
    protected DisposableObserver<BaseResponse<TrendingVideo.TrandingVideoRespond>> buildUseCaseSubscriber() {
        return new DefaultObserver<BaseResponse<TrendingVideo.TrandingVideoRespond>>() {
            @Override
            public void onNext(BaseResponse<TrendingVideo.TrandingVideoRespond> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data));
                if (useCaseCallback != null) {
                    List<TrendingVideo> result = data.getResults().getObjects().getRows();
                    if (result != null && data.getStatus() ==200) {
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
        public final int limit;
        public final int page;


        public RequestValue(int limit, int page) {
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
