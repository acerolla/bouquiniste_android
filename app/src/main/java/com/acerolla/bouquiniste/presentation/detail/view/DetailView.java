package com.acerolla.bouquiniste.presentation.detail.view;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;

/**
 * Created by Evgeniy Solovev
 * Date: 25.05.2018
 * Email: solevur@gmail.com
 */
public class DetailView extends RelativeLayout {

    public DetailView(Context context) {
        super(context);
        initViews();
    }

    private void initViews() {
        setBackgroundColor(Color.WHITE);
    }
}
