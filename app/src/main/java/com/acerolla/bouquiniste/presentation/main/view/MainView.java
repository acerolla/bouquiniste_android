package com.acerolla.bouquiniste.presentation.main.view;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

    private static final int ID_SHADOW = 4;

    private Toolbar mToolbar;
    private MenuItem mSearchItem;
    private MenuItem mFilterItem;
    private MenuItem mClearAllItem;
    private MenuItem mLogoutItem;

    private ImageView mIvNavigationBack;

    private FrameLayout mContentFrame;
    private BottomNavigationView mBottomMenu;

    public interface SearchListener {
        void invoke(String text);
    }

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
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setSubtitleTextColor(Color.WHITE);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setElevation(ValuesConverter.dp2px(ValuesConverter.DP_8));


        AppBarLayout.LayoutParams toolbarParams = new AppBarLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mToolbar.setLayoutParams(toolbarParams);
        appBarLayout.addView(mToolbar);

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

    public void initToolbarMenu(SearchListener listener, Menu menu, AppCompatActivity activity) {
        mSearchItem = menu.findItem(R.id.menu_item_toolbar_search);
        mFilterItem = menu.findItem(R.id.menu_item_toolbar_filter);
        mClearAllItem = menu.findItem(R.id.menu_item_toolbar_filter);
        mLogoutItem = menu.findItem(R.id.menu_item_toolbar_logout);

        mSearchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                mFilterItem.setVisible(false);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                mToolbar.setNavigationIcon(null);
                mFilterItem.setVisible(true);
                return true;
            }
        });

        SearchView mSearchView = (SearchView) menu.findItem(R.id.menu_item_toolbar_search).getActionView();
        SearchManager searchManager = (SearchManager) activity.getSystemService(AppCompatActivity.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listener.invoke(newText);
                return false;
            }
        });

        EditText searchEditText = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchEditText.setHintTextColor(getResources().getColor(R.color.white));
        searchEditText.setHint("Поиск");

        ImageView closeSearch = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        closeSearch.setImageDrawable(null);

    }

    public void collapseSearch() {
        if (mSearchItem == null) {
            return;
        }
        mSearchItem.collapseActionView();
    }



    public void setSearchButtonVisibility(boolean isVisible) {
        if (mSearchItem == null) {
            return;
        }

       mSearchItem.setVisible(isVisible);
    }

    public void setFilterVisibility(boolean isVisible) {
        if (mFilterItem == null) {
            return;
        }

        mFilterItem.setVisible(isVisible);
    }

    public void setClearAllVisibility(boolean isVisible) {
        if (mClearAllItem == null) {
            return;
        }

        mClearAllItem.setVisible(isVisible);
    }

    public void setLogoutVisibility(boolean isVisible) {
        if (mLogoutItem == null) {
            return;
        }

        mLogoutItem.setVisible(isVisible);
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
        //mIvCategory = null;
        //mIvClearAll = null;
        //mIvLogout = null;
        mContentFrame = null;
        mBottomMenu = null;
    }
}
