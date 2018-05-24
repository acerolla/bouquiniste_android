package com.acerolla.bouquiniste.presentation.adverts.view;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;

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
}
