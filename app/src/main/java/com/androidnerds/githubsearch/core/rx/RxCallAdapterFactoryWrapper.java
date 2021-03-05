package com.androidnerds.githubsearch.core.rx;

import com.androidnerds.githubsearch.core.annotation.ErrorType;
import com.androidnerds.githubsearch.core.remote.NetworkException;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

public class RxCallAdapterFactoryWrapper extends CallAdapter.Factory {

    private final RxJava3CallAdapterFactory rxJava3CallAdapterFactory;

    @Inject
    public RxCallAdapterFactoryWrapper(RxJava3CallAdapterFactory rxJava3CallAdapterFactory) {
        this.rxJava3CallAdapterFactory = rxJava3CallAdapterFactory;

    }

    @Override
    public CallAdapter<?, ?> get(@NotNull Type returnType, @NotNull Annotation[] annotations, @NotNull Retrofit retrofit) {
        CallAdapter<Object, Object> rxJava3CallAdapter = (CallAdapter<Object, Object>) rxJava3CallAdapterFactory.get(returnType, annotations, retrofit);
        return new RxCallAdapterWrapper(annotations, retrofit, rxJava3CallAdapter);
    }

    private Throwable handleError(Annotation[] annotations, Retrofit retrofit, Throwable throwable) {
        ErrorType errorType = find(annotations);
        if (null != errorType && throwable instanceof HttpException) {
            try {
                Object object = parseError(retrofit, (HttpException) throwable, errorType.value());
                return new NetworkException(throwable, object);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return throwable;
    }

    private ErrorType find(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof ErrorType) {
                return (ErrorType) annotation;
            }
        }
        return null;
    }

    private Object parseError(Retrofit retrofit, HttpException httpException, Class<?> clazz) throws IOException {
        if (httpException.response().isSuccessful()) {
            return null;
        }
        ResponseBody responseBody = httpException.response().errorBody();
        Annotation[] annotations = new Annotation[]{};
        Converter<ResponseBody, Object> converter = retrofit.responseBodyConverter(clazz, annotations);
        return converter.convert(responseBody);
    }


    private class RxCallAdapterWrapper implements CallAdapter<Object, Object> {

        private final Annotation[] annotations;
        private final Retrofit retrofit;
        private final CallAdapter<Object, Object> callAdapter;

        RxCallAdapterWrapper(Annotation[] annotations, Retrofit retrofit, CallAdapter<Object, Object> callAdapter) {
            this.annotations = annotations;
            this.retrofit = retrofit;
            this.callAdapter = callAdapter;
        }

        @NotNull
        @Override
        public Type responseType() {
            return callAdapter.responseType();
        }

        @NotNull
        @Override
        public Object adapt(@NotNull Call<Object> call) {
            Object object = callAdapter.adapt(call);
            if (object instanceof Observable) {
                return ((Observable<?>) object).onErrorResumeNext(throwable -> Observable.error(handleError(annotations, retrofit, (Throwable) throwable)));
            }
            if (object instanceof Maybe) {
                return ((Maybe<?>) object).onErrorResumeNext(throwable -> Maybe.error(handleError(annotations, retrofit, (Throwable) throwable)));
            }

            if (object instanceof Single) {
                return ((Single<?>) object).onErrorResumeNext(throwable -> Single.error(handleError(annotations, retrofit, (Throwable) throwable)));
            }

            if (object instanceof Flowable) {
                return ((Flowable<?>) object).onErrorResumeNext(throwable -> Flowable.error(handleError(annotations, retrofit, (Throwable) throwable)));
            }

            if (object instanceof Completable) {
                return ((Completable) object).onErrorResumeNext(throwable -> Completable.error(handleError(annotations, retrofit, (Throwable) throwable)));
            }

            return object;
        }
    }

}