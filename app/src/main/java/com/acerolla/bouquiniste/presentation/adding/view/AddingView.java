package com.acerolla.bouquiniste.presentation.adding.view;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AddingView extends RelativeLayout {

    public AddingView(Context context) {
        super(context);
        initViews();
    }

    private void initViews() {
        setClickable(true);
        setBackgroundColor(Color.WHITE);


    }
}
