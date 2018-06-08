package com.acerolla.bouquiniste.data.utils.cloud;

import com.acerolla.bouquiniste.data.auth.repository.IAuthRepository;
import com.acerolla.bouquiniste.di.DiManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

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

    @Inject
    IAuthRepository mRepository;

    private static final int TIMEOUT_SEC = 10;

    private static final String API_URL = "http://85.119.144.206/";

    private static final String CONTENT_KEY = "Accept";
    private static final String CONTENT_VALUE = "application/json";
    private static final String AUTH_KEY = "Authorization";
    private static final String AUTH_VALUE = "Bearer ";

    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";

    private IRestApi mRestApi;

    public ApiManager() {
        initCloud();
    }

    private void initCloud() {
        DiManager.getRepositoryComponent().inject(this);

        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Request.Builder builder = request.newBuilder();
                    builder.addHeader(CONTENT_KEY, CONTENT_VALUE);
                    if (requestNeedsAuth(request.url().toString()) && mRepository.getTokenAsync() != null) {
                        builder.addHeader(AUTH_KEY, AUTH_VALUE + mRepository.getTokenAsync().getToken());
                    }
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

    private boolean requestNeedsAuth(String url) {
        url = url.toLowerCase();

        String comparable = LOGIN;
        comparable = comparable.toLowerCase();
        if (url.contains(comparable)) {
            return false;
        }

        comparable = REGISTER;
        comparable = comparable.toLowerCase();
        if (url.contains(comparable)) {
            return false;
        }

        return true;
    }

    public IRestApi getRestApi() {
        return mRestApi;
    }

    public void release() {
        mRestApi = null;
    }
}
