package com.acerolla.bouquiniste.presentation.auth.container.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class LoginContainerView extends RelativeLayout {

    public static final int ID_CONTENT_FRAME = 1;

    private FrameLayout mContentFrame;

    public LoginContainerView(Context context) {
        super(context);
        initViews();
    }

    private void initViews() {
        mContentFrame = new FrameLayout(getContext());
        mContentFrame.setId(ID_CONTENT_FRAME);

        LayoutParams contentParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContentFrame.setLayoutParams(contentParams);
        addView(mContentFrame);
    }
}
