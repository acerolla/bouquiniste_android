package com.acerolla.bouquiniste.presentation.favorites.view;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IFavoritesView {

    void navigateToDetail();

    void setContentData(List<AdvertData> data);
    void setContentVisibility(boolean isVisible);
    void setLoaderVisibility(boolean isVisible);
    void setErrorVisibility(boolean isVisible);
    void setEmptyMessageVisibility(boolean isVisibility);

    void stopRefreshing();
}
