package com.acerolla.bouquiniste.data.category.repository.datasource;

import com.acerolla.bouquiniste.BouquinisteApplication;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.advert.entity.AdvertResponse;
import com.acerolla.bouquiniste.data.category.entity.CategoryChildrenData;
import com.acerolla.bouquiniste.data.category.entity.CategoryChildrenResponse;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentResponse;
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
public class CategoryCloudDataSource implements ICategoryDataSource {

    @Override
    public void loadCategories(ResultListener<List<CategoryParentData>> listener) {
        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .getCategoryList()
                .enqueue(new Callback<BaseResponseObject<List<CategoryParentResponse>>>() {
                    @Override
                    public void onResponse(Call<BaseResponseObject<List<CategoryParentResponse>>> call, Response<BaseResponseObject<List<CategoryParentResponse>>> response) {
                        Logger.d(response.message());
                        if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                            if (listener != null) {
                                listener.onResult(processCategoryResponse(response.body().data));
                            }
                        } else {
                            if (listener != null) {
                                listener.onResult(null);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponseObject<List<CategoryParentResponse>>> call, Throwable t) {
                        Logger.e(t.getMessage());
                        if (listener != null) {
                            listener.onResult(null);
                        }
                    }
                });
    }

    private ArrayList<CategoryParentData> processCategoryResponse(List<CategoryParentResponse> responseList) {
        ArrayList<CategoryParentData> categories = new ArrayList<>();
        for (CategoryParentResponse categoryResponse : responseList) {
            ArrayList<CategoryChildrenData> children = new ArrayList<>();
            for (CategoryChildrenResponse childrenResponse : categoryResponse.children.data) {
                children.add(new CategoryChildrenData(
                        childrenResponse.id,
                        childrenResponse.title));
            }
            categories.add(new CategoryParentData(
                    categoryResponse.id,
                    categoryResponse.title,
                    children));
        }

        return categories;
    }

    @Override
    public void saveCategories(List<CategoryParentData> categories) {
        //ignore
    }

    @Override
    public void loadAdvertsByCategory(ResultListener<List<AdvertData>> listener, int categoryId) {
        BouquinisteApplication.getInstance()
                .getApiManager()
                .getRestApi()
                .getAdvertsByCategory(categoryId)
                .enqueue(new Callback<BaseResponseObject<List<AdvertResponse>>>() {
                    @Override
                    public void onResponse(Call<BaseResponseObject<List<AdvertResponse>>> call, Response<BaseResponseObject<List<AdvertResponse>>> response) {
                        Logger.d(response.message());
                        if (response.isSuccessful() && response.body() != null && response.body().data != null) {
                            if (listener != null) {
                                listener.onResult(processAdvertResponse(response.body().data));
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

    private List<AdvertData> processAdvertResponse(List<AdvertResponse> response) {
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
    public void clear() {
        //ignore
    }

    @Override
    public List<CategoryParentData> getCategoriesAsync() {
        //ignore
        return null;
    }

    @Override
    public void release() {

    }
}
