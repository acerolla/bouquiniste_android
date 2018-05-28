package com.acerolla.bouquiniste.data.adding.repository.datasource;

import com.acerolla.bouquiniste.BouquinisteApplication;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.entity.AdvertRequest;
import com.acerolla.bouquiniste.data.advert.entity.AdvertResponse;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.utils.cloud.BaseResponseObject;
import com.acerolla.bouquiniste.presentation.utils.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("title", advertData.getTitle());
        requestBody.put("author", advertData.getAuthor());
        requestBody.put("description", advertData.getDescription());
        requestBody.put("price", Float.toString(advertData.getPrice()));
        requestBody.put("status", advertData.getStatus());
        requestBody.put("category_id", Integer.toString(advertData.getCategoryId()));
        requestBody.put("phone", advertData.getPhone());
        requestBody.put("location", advertData.getLocation());

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
                                    response.body().data.image,
                                    response.body().data.is_favorite,
                                    response.body().data.location);
                            if (listener != null) {
                                listener.onResult(advert);
                            }
                        } else {
                            if (listener != null) {
                                listener.onResult(null);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponseObject<AdvertResponse>> call, Throwable t) {
                        Logger.e(t.getMessage());
                        if (listener != null) {
                            listener.onResult(null);
                        }
                    }
                });
    }
}
