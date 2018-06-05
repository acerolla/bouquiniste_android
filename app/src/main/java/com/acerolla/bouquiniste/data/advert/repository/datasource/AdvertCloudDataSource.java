package com.acerolla.bouquiniste.data.advert.repository.datasource;

import com.acerolla.bouquiniste.BouquinisteApplication;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.entity.AdvertRequest;
import com.acerolla.bouquiniste.data.advert.entity.AdvertResponse;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.utils.cloud.BaseResponseObject;
import com.acerolla.bouquiniste.presentation.utils.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
class AdvertCloudDataSource implements IAdvertDataSource {

    @Override
    public void getAdvertList(ResultListener<List<AdvertData>> listener) {
        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .getAdverts()
                .enqueue(new Callback<BaseResponseObject<List<AdvertResponse>>>() {
                    @Override
                    public void onResponse(Call<BaseResponseObject<List<AdvertResponse>>> call, Response<BaseResponseObject<List<AdvertResponse>>> response) {
                        Logger.d(response.message());
                        if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                            if (listener != null) {
                                listener.onResult(proceedResponse(response.body().data));
                            }
                        } else {
                            if (listener != null) {
                                listener.onResult(null);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponseObject<List<AdvertResponse>>> call, Throwable t) {
                        Logger.e(t.getMessage());
                        if (listener != null) {
                            listener.onResult(null);
                        }
                    }
                });
    }

    private List<AdvertData> proceedResponse(List<AdvertResponse> response) {
        List<AdvertData> adverts = new ArrayList<>();
        for (AdvertResponse item : response) {
            adverts.add(new AdvertData(
                    item.id,
                    item.title,
                    item.author,
                    item.description,
                    item.price,
                    item.phone,
                    item.status,
                    item.category_id,
                    item.image,
                    item.is_favorite,
                    item.location));
        }

        return adverts;
    }


    @Override
    public void getAdvert(ResultListener<AdvertData> listener, int advertId) {
        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .getAdvert(advertId)
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

    @Override
    public void getUserAdverts(ResultListener<List<AdvertData>> listener, int userId) {
        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .getUserAdverts(userId)
                .enqueue(new Callback<BaseResponseObject<List<AdvertResponse>>>() {
                    @Override
                    public void onResponse(Call<BaseResponseObject<List<AdvertResponse>>> call, Response<BaseResponseObject<List<AdvertResponse>>> response) {
                        Logger.d(response.message());
                        if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                            if (listener != null) {
                                listener.onResult(proceedResponse(response.body().data));
                            }
                        } else {
                            if (listener != null) {
                                listener.onResult(null);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponseObject<List<AdvertResponse>>> call, Throwable t) {
                        Logger.e(t.getMessage());
                        if (listener != null) {
                            listener.onResult(null);
                        }
                    }
                });
    }

    @Override
    public AdvertData getAdvertAsync() {
        //ignore
        return null;
    }

    @Override
    public void saveAdvertToCache(AdvertData advert) {
        //ignore
    }

    @Override
    public void clearAdverts() {
        //ignore
    }

    @Override
    public void saveAdverts(List<AdvertData> adverts) {

    }

    @Override
    public void release() {

    }
}
