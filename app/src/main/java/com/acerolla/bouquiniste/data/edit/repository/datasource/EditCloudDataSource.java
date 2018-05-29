package com.acerolla.bouquiniste.data.edit.repository.datasource;

import com.acerolla.bouquiniste.BouquinisteApplication;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.entity.AdvertResponse;
import com.acerolla.bouquiniste.data.utils.cloud.BaseResponseObject;
import com.acerolla.bouquiniste.presentation.utils.Logger;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
class EditCloudDataSource implements IEditDataSource {

    @Override
    public void closeAdvert(ResultListener<Boolean> listener, int advertId) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("status", "closed");

        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .editAdvert(advertId, requestBody)
                .enqueue(new Callback<BaseResponseObject<AdvertResponse>>() {
                    @Override
                    public void onResponse(Call<BaseResponseObject<AdvertResponse>> call, Response<BaseResponseObject<AdvertResponse>> response) {
                        Logger.d(response.message());
                        if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                            String status = response.body().data.status;
                            if (status.equals("closed")) {
                                if (listener != null) {
                                    listener.onResult(true);
                                }
                            } else {
                                if (listener != null) {
                                    listener.onResult(null);
                                }
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

    @Override
    public void editAdvert(ResultListener<AdvertData> listener, AdvertData data) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("title", data.getTitle());
        requestBody.put("author", data.getAuthor());
        requestBody.put("description", data.getDescription());
        requestBody.put("price", Float.toString(data.getPrice()));
        requestBody.put("category_id", Integer.toString(data.getCategoryId()));
        requestBody.put("phone", data.getPhone());
        requestBody.put("location", data.getLocation());

        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .editAdvert(data.getId(), requestBody)
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
