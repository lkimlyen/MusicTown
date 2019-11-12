package com.demo.architect.domain;

import android.util.Log;

import com.demo.architect.data.model.BaseListResponse;
import com.demo.architect.data.model.BaseResponse;
import com.demo.architect.data.model.Comment;
import com.demo.architect.data.repository.base.data.remote.DataRepository;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;


public class GetCommentListUseCase extends BaseUseCase<BaseResponse<Comment.CommentListRespond>> {
    private static final String TAG = GetCommentListUseCase.class.getSimpleName();
    private final DataRepository remoteRepository;

    public GetCommentListUseCase(DataRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Observable<BaseResponse<Comment.CommentListRespond>> buildUseCaseObservable() {
        Map<String, Object> params = ((RequestValue) requestValues).params;
        int page  = ((RequestValue) requestValues).page;
        return remoteRepository.sendRequestGetListComment(params, page);
    }

    @Override
    protected DisposableObserver<BaseResponse<Comment.CommentListRespond>> buildUseCaseSubscriber() {
        return new DefaultObserver<BaseResponse<Comment.CommentListRespond>>() {
            @Override
            public void onNext(BaseResponse<Comment.CommentListRespond> data) {
                Log.d(TAG, "onNext: " + String.valueOf(data));
                if (useCaseCallback != null) {
                    List<Comment> result = data.getResults().getObjects().getComments();
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
        public final Map<String, Object> params;
        public final int page;


        public RequestValue(Map<String, Object> params, int page) {
            this.params = params;
            this.page = page;
        }
    }

    public static final class ResponseValue implements ResponseValues {
        private List<Comment> result;

        public ResponseValue(List<Comment> result) {
            this.result = result;
        }

        public List<Comment> getResult() {
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
