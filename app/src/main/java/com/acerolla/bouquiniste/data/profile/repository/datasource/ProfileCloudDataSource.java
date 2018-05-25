package com.acerolla.bouquiniste.data.profile.repository.datasource;

import com.acerolla.bouquiniste.BouquinisteApplication;
import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.profile.entity.ProfileData;
import com.acerolla.bouquiniste.data.profile.entity.ProfileResponse;
import com.acerolla.bouquiniste.data.utils.cloud.BaseResponseObject;
import com.acerolla.bouquiniste.presentation.utils.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Acerolla (Evgeniy Solovev) on 23.05.2018.
 */
public class ProfileCloudDataSource implements IProfileDataSource {

    @Override
    public void loadProfile(ResultListener<ProfileData> listener) {
        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .getUser()
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
    public void saveProfile(ProfileData profile) {
        //ignore
    }

    @Override
    public void editProfile(ResultListener<ProfileData> listener, ProfileData userData) {
        ProfileResponse requestBody = new ProfileResponse();
        requestBody.name = userData.getUser();
        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .editUser(requestBody)
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
}
