package com.acerolla.bouquiniste.presentation.adverts.view;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAdvertsView {

    void setContentData(List<AdvertData> data);
    void addContentData(List<AdvertData> data);
    void setContentVisibility(boolean isVisible);
    void setLoaderVisibility(boolean isVisible);
    void setErrorVisibility(boolean isVisible);

    void navigateToDetail();
    void showCategory();
    void stopRefreshing();

    void setTitle(String title);
    void setSearchVisibility(boolean isVisible);
}
