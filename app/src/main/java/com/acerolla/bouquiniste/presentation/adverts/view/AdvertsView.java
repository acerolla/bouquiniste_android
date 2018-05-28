package com.acerolla.bouquiniste.presentation.adverts.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;

import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Date: 24.05.2018
 * Email: solevur@gmail.com
 */
public class AdvertsView extends RelativeLayout {

    public AdvertsView(Context context) {
        super(context);
        initViews();
    }

    private void initViews() {
        setClickable(true);
        setBackgroundColor(Color.WHITE);
    }

    public void setContentData(List<AdvertData> data) {

    }

    public void setContentVisibility(int visibility) {

    }

    public void setLoaderVisibility(int visibility) {

    }

    public void setErrorVisibility(int visibility) {

    }

    public void setCategoryVisibility(int visibility) {

    }

    public void setItemClickListener(OnClickListener listener) {

    }

    public AdvertData getDataByView(View v) {

        return null;
    }

    public void setCategoryData(List<CategoryParentData> data) {

    }
}
