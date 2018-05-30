package com.acerolla.bouquiniste.presentation.favorites.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class FavoritesView extends RelativeLayout {

    public FavoritesView(Context context) {
        super(context);
    }

    public void initViews() {
        setClickable(true);
        setBackgroundColor(Color.WHITE);
    }

    public void setItemClickListener(OnClickListener listener) {

    }

    public void setContentData(List<AdvertData> data) {

    }

    public void setContentVisibility(int visibility) {

    }

    public void setLoaderVisibility(int visibility) {

    }

    public void setErrorVisibility(int visibility) {

    }

    public void setEmptyMessageVisibility(int visibility) {

    }

    public AdvertData getDataByView(View v) {

        return null;
    }
}
