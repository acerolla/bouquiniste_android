package com.acerolla.bouquiniste.data.favorites.repository.datasoruce;

import com.acerolla.bouquiniste.BouquinisteApplication;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.entity.AdvertResponse;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.utils.cloud.BaseResponseObject;
import com.acerolla.bouquiniste.presentation.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
class FavoritesCloudDataSource implements IFavoritesDataSource {

    @Override
    public void loadFavoritesList(ResultListener<List<AdvertData>> listener) {
        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .getFavorites()
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
    public void addToFavorites(ResultListener<Boolean> listener, int advertId) {
        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .addToFavorite(advertId)
                .enqueue(new Callback<BaseResponseObject<AdvertResponse>>() {
                    @Override
                    public void onResponse(Call<BaseResponseObject<AdvertResponse>> call, Response<BaseResponseObject<AdvertResponse>> response) {
                        Logger.d(response.message());
                        if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                            if (response.body().data.is_favorite) {
                                if (listener != null) {
                                    listener.onResult(true);
                                }
                            } else {
                                if (listener != null) {
                                    listener.onResult(false);
                                }
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
    public void removeFromFavorites(ResultListener<Boolean> listener, int advertId) {
        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .removeFromFavorite(advertId)
                .enqueue(new Callback<BaseResponseObject<AdvertResponse>>() {
                    @Override
                    public void onResponse(Call<BaseResponseObject<AdvertResponse>> call, Response<BaseResponseObject<AdvertResponse>> response) {
                        Logger.d(response.message());
                        if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                            if (response.body().data.is_favorite) {
                                if (listener != null) {
                                    listener.onResult(true);
                                }
                            } else {
                                if (listener != null) {
                                    listener.onResult(false);
                                }
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
