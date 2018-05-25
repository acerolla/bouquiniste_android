package com.acerolla.bouquiniste.data.utils.cloud;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
public class ApiManager {

    private static final int TIMEOUT_SEC = 10;

    private static final String API_URL = "";

    private static final String CONTENT_KEY = "Content-type";
    private static final String CONTENT_VALUE = "application/json";
    private static final String AUTH_KEY = "Authorization";
    private static final String AUTH_VALUE = "Bearer ";

    private IRestApi mRestApi;

    public ApiManager() {
        initCloud();
    }

    private void initCloud() {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Request.Builder builder = request.newBuilder();
                    builder.addHeader(CONTENT_KEY, CONTENT_VALUE);
                    /*if (requestNeedsAuth(request.url().toString())) {
                        builder.addHeader(AUTH_KEY, AUTH_VALUE + mRepository.getSessionToken());
                    }*/
                    return chain.proceed(builder.build());
                })
                .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build();

        mRestApi = retrofit.create(IRestApi.class);
    }

    public IRestApi getRestApi() {
        return mRestApi;
    }

    public void release() {
        mRestApi = null;
    }
}
