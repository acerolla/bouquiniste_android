package com.acerolla.bouquiniste.presentation.main.view;

import android.content.Context;
import android.graphics.Color;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.presentation.utils.ValuesConverter;

/**
 * Created by Acerolla (Evgeniy Solovev) on 22.05.2018.
 */
public class MainView extends RelativeLayout {

    public static final int ID_CONTENT_FRAME = 1;
    public static final int ID_BOTTOM_MENU = 2;

    private static final int ID_SHADOW = 3;

    private FrameLayout mContentFrame;
    private BottomNavigationView mBottomMenu;

    public MainView(Context context) {
        super(context);
        initViews();
    }

    private void initViews() {
        mContentFrame = new FrameLayout(getContext());
        mContentFrame.setId(ID_CONTENT_FRAME);

        LayoutParams contentParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        contentParams.addRule(ABOVE, ID_SHADOW);
        mContentFrame.setLayoutParams(contentParams);
        addView(mContentFrame);

        View shadow = new View(getContext());
        shadow.setId(ID_SHADOW);
        shadow.setBackgroundResource(R.drawable.menu_shadow);

        LayoutParams shadowParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ValuesConverter.dp2px(ValuesConverter.DP_4));
        shadowParams.addRule(ABOVE, ID_BOTTOM_MENU);
        shadow.setLayoutParams(shadowParams);
        addView(shadow);

        mBottomMenu = new BottomNavigationView(getContext());
        mBottomMenu.setId(ID_BOTTOM_MENU);
        mBottomMenu.setBackgroundColor(Color.WHITE);
        mBottomMenu.inflateMenu(R.menu.menu_main_bottom);

        LayoutParams menuParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        menuParams.addRule(ALIGN_PARENT_BOTTOM);
        mBottomMenu.setLayoutParams(menuParams);
        addView(mBottomMenu);
    }

    public void setMenuListener(BottomNavigationView.OnNavigationItemSelectedListener listener) {
        mBottomMenu.setOnNavigationItemSelectedListener(listener);
    }
}
