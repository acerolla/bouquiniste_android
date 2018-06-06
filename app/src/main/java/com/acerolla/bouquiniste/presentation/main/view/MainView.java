package com.acerolla.bouquiniste.presentation.main.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.presentation.utils.ValuesConverter;

/**
 * Created by Acerolla (Evgeniy Solovev).
 */
public class MainView extends RelativeLayout {

    public static final int ID_CONTENT_FRAME = 1;
    public static final int ID_BOTTOM_MENU = 2;
    private static final int ID_TOOLBAR = 3;

    private static final int ID_SHADOW = 3;

    private Toolbar mToolbar;
    private AppCompatImageView mIvCategory;
    private AppCompatImageView mIvClearAll;
    private AppCompatImageView mIvLogout;
    private FrameLayout mContentFrame;
    private BottomNavigationView mBottomMenu;

    public MainView(Context context) {
        super(context);
        initViews();
    }

    @SuppressLint("RestrictedApi")
    private void initViews() {
        setBackgroundColor(Color.WHITE);

        AppBarLayout appBarLayout = new AppBarLayout(getContext());
        appBarLayout.setId(ID_TOOLBAR);
        appBarLayout.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                ValuesConverter.dp2px(ValuesConverter.DP_48)));
        addView(appBarLayout);

        mToolbar = new Toolbar(getContext());
        mToolbar.setId(android.R.id.toggle);
        mToolbar.setBackgroundColor(Color.BLACK);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setSubtitleTextColor(Color.WHITE);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setElevation(ValuesConverter.dp2px(ValuesConverter.DP_8));

        AppBarLayout.LayoutParams toolbarParams = new AppBarLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mToolbar.setLayoutParams(toolbarParams);
        appBarLayout.addView(mToolbar);

        mIvCategory = new AppCompatImageView(getContext());
        mIvCategory.setImageResource(R.drawable.ic_filter_list_white_24dp);
        mIvCategory.setVisibility(GONE);

        Toolbar.LayoutParams categoryParams = new Toolbar.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        categoryParams.gravity = Gravity.END;
        categoryParams.rightMargin = ValuesConverter.dp2px(ValuesConverter.DP_10);
        mIvCategory.setLayoutParams(categoryParams);
        mToolbar.addView(mIvCategory);

        mIvClearAll = new AppCompatImageView(getContext());
        mIvClearAll.setImageResource(R.drawable.ic_clear_all_black_24dp);
        mIvClearAll.setVisibility(GONE);

        Toolbar.LayoutParams clearAllParams = new Toolbar.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        clearAllParams.gravity = Gravity.END;
        clearAllParams.rightMargin = ValuesConverter.dp2px(ValuesConverter.DP_10);
        mIvClearAll.setLayoutParams(clearAllParams);
        mToolbar.addView(mIvClearAll);

        mIvLogout = new AppCompatImageView(getContext());
        mIvLogout.setImageResource(R.drawable.ic_exit_to_app_black_24dp);
        mIvLogout.setVisibility(GONE);

        Toolbar.LayoutParams logoutParams = new Toolbar.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        logoutParams.gravity = Gravity.END;
        logoutParams.rightMargin = ValuesConverter.dp2px(ValuesConverter.DP_10);
        mIvLogout.setLayoutParams(logoutParams);
        mToolbar.addView(mIvLogout);

        mContentFrame = new FrameLayout(getContext());
        mContentFrame.setId(ID_CONTENT_FRAME);

        LayoutParams contentParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        contentParams.addRule(ABOVE, ID_SHADOW);
        contentParams.topMargin = ValuesConverter.dp2px(ValuesConverter.DP_48);
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

    public void setCategoryClickListener(OnClickListener listener) {
        mIvCategory.setOnClickListener(listener);
    }

    public void setClearAllClickListener(OnClickListener listener) {
        mIvClearAll.setOnClickListener(listener);
    }

    public void setLogoutClickListener(OnClickListener listener) {
        mIvLogout.setOnClickListener(listener);
    }

    public void setFilterVisibility(int visibility) {
        mIvCategory.setVisibility(visibility);
    }

    public void setClearAllVisibility(int visibility) {
        mIvClearAll.setVisibility(visibility);
    }

    public void setLogoutVisibility(int visibility) {
        mIvLogout.setVisibility(visibility);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public BottomNavigationView getBottomMenu() {
        return mBottomMenu;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mToolbar = null;
        mIvCategory = null;
        mIvClearAll = null;
        mIvLogout = null;
        mContentFrame = null;
        mBottomMenu = null;
    }
}
