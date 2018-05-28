package com.acerolla.bouquiniste.presentation.adding.view;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public interface IAddingView {

    void showChooseFileActivity();
    AdvertData collectData();
    void navigateToDetail(int advertId);
    void setContentVisibility(boolean isVisible);
    void setLoaderVisibility(boolean isVisible);
    void setErrorVisibility(boolean isVisible);
    void changeFragment();
    void navigateToCategories();
    void showCategory(int id, String title);
}
