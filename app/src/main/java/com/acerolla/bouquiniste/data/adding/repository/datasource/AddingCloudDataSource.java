package com.acerolla.bouquiniste.data.adding.repository.datasource;

import com.acerolla.bouquiniste.BouquinisteApplication;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.entity.AdvertRequest;
import com.acerolla.bouquiniste.data.advert.entity.AdvertResponse;
import com.acerolla.bouquiniste.data.profile.ResultListener;
import com.acerolla.bouquiniste.data.utils.cloud.BaseResponseObject;
import com.acerolla.bouquiniste.presentation.utils.Logger;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
class AddingCloudDataSource implements IAddingDataSource {

    @Override
    public void postAdvert(ResultListener<AdvertData> listener, AdvertData advertData) {
        AdvertRequest requestBody = new AdvertRequest();
        requestBody.title = advertData.getTitle();
        requestBody.author = advertData.getAuthor();
        requestBody.description = advertData.getDescription();
        requestBody.price = advertData.getPrice();
        requestBody.status = advertData.getStatus();
        requestBody.category_id = advertData.getCategoryId();
        requestBody.user_id = advertData.getUserId();
        requestBody.phone = advertData.getPhone();
        requestBody.image = new File(advertData.getImage());

        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .postAdvert(requestBody)
                .enqueue(new Callback<BaseResponseObject<AdvertResponse>>() {
                    @Override
                    public void onResponse(Call<BaseResponseObject<AdvertResponse>> call, Response<BaseResponseObject<AdvertResponse>> response) {
                        Logger.d(response.message());
                        if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                            AdvertData advert = new AdvertData(
                                    response.body().data.id,
                                    response.body().data.title,
                                    response.body().data.author,
                                    response.body().data.description,
                                    response.body().data.price,
                                    response.body().data.phone,
                                    response.body().data.status,
                                    response.body().data.category_id,
                                    response.body().data.user_id,
                                    response.body().data.image);
                            listener.onResult(advert);
                        } else {
                            listener.onResult(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponseObject<AdvertResponse>> call, Throwable t) {
                        Logger.e(t.getMessage());
                        listener.onResult(null);
                    }
                });
    }
}
