package com.acerolla.bouquiniste.data.auth.repository.datasource;

import com.acerolla.bouquiniste.BouquinisteApplication;
import com.acerolla.bouquiniste.data.auth.entity.TokenData;
import com.acerolla.bouquiniste.data.auth.entity.login.LoginData;
import com.acerolla.bouquiniste.data.auth.entity.register.RegisterData;
import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.data.profile.entity.ProfileResponse;
import com.acerolla.bouquiniste.data.profile.repository.ProfileRepository;
import com.acerolla.bouquiniste.data.utils.cloud.BaseResponseObject;
import com.acerolla.bouquiniste.presentation.utils.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
class AuthCloudDataSource implements IAuthDataSource {

    @Override
    public void login(ResultListener<ProfileData> listener, LoginData login) {
        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .login(login.getEmail(), login.getPassword())
                .enqueue(new Callback<BaseResponseObject<ProfileResponse>>() {
                    @Override
                    public void onResponse(Call<BaseResponseObject<ProfileResponse>> call, Response<BaseResponseObject<ProfileResponse>> response) {
                        Logger.d(response.message());
                        if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                            ProfileData profile = new ProfileData(
                                    response.body().data.id,
                                    response.body().data.token,
                                    response.body().data.name,
                                    response.body().data.email);
                            listener.onResult(profile);
                        } else {
                            listener.onResult(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponseObject<ProfileResponse>> call, Throwable t) {
                        Logger.e(t.getMessage());
                        listener.onResult(null);
                    }
                });
    }

    @Override
    public void register(ResultListener<ProfileData> listener, RegisterData register) {
        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .register(register.getEmail())
                .enqueue(new Callback<BaseResponseObject<ProfileResponse>>() {
                    @Override
                    public void onResponse(Call<BaseResponseObject<ProfileResponse>> call, Response<BaseResponseObject<ProfileResponse>> response) {
                        Logger.d(response.message());
                        if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                            ProfileData profile = new ProfileData(
                                    response.body().data.id,
                                    response.body().data.token,
                                    response.body().data.name,
                                    response.body().data.email);
                            listener.onResult(profile);
                        } else {
                            listener.onResult(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponseObject<ProfileResponse>> call, Throwable t) {
                        Logger.e(t.getMessage());
                        listener.onResult(null);
                    }
                });
    }

    @Override
    public void logout(ResultListener<Object> listener) {
        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .logout()
                .enqueue(new Callback<BaseResponseObject>() {
                    @Override
                    public void onResponse(Call<BaseResponseObject> call, Response<BaseResponseObject> response) {
                        Logger.d(response.message());
                        listener.onResult(null);
                    }

                    @Override
                    public void onFailure(Call<BaseResponseObject> call, Throwable t) {
                        Logger.e(t.getMessage());
                        listener.onResult(null);
                    }
                });
    }

    @Override
    public void saveToken(TokenData token) {

    }

    @Override
    public void getToken(ResultListener<TokenData> listener) {

    }

    @Override
    public TokenData getTokenAsync() {
        return null;
    }
}
