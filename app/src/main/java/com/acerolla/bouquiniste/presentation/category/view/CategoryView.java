package com.acerolla.bouquiniste.presentation.category.view;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.presentation.category.view.recycler.CategoryAdapter;
import com.acerolla.bouquiniste.presentation.utils.ValuesConverter;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class CategoryView extends RelativeLayout {

    private static final int ZERO = 0;

    private static final int ID_TOOLBAR = 3;

    private Toolbar mToolbar;

    private RecyclerView mRvCategories;
    private CategoryAdapter mAdapter;
    private RecyclerView.LayoutManager mManager;



    public CategoryView(Context context) {
        super(context);
        initViews();
    }

    private void initViews() {
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));

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

        AppBarLayout.LayoutParams toolbarParams = new AppBarLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mToolbar.setLayoutParams(toolbarParams);
        appBarLayout.addView(mToolbar);

        mRvCategories = new RecyclerView(getContext());
        mRvCategories.setHasFixedSize(true);
        mRvCategories.setPadding(ZERO, ValuesConverter.dp2px(ValuesConverter.DP_5), ZERO, ZERO);

        mManager = new LinearLayoutManager(getContext());
        mRvCategories.setLayoutManager(mManager);

        LayoutParams recyclerParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        recyclerParams.addRule(BELOW, ID_TOOLBAR);
        mRvCategories.setLayoutParams(recyclerParams);
        addView(mRvCategories);
    }

    public void setChildClickListener(OnClickListener listener){
        mChildClickListener = listener;
    }

    public void setParentClickListener(OnClickListener listener) {
        mParentClickListener = listener;
    }

    public RecyclerView.ViewHolder getViewHolder(View v) {
        return mRvCategories.getChildViewHolder(v);
    }


    public void setData(List<CategoryParentData> categories) {
        mAdapter = new CategoryAdapter(getContext(), categories, mChildClickListener, mParentClickListener);
        mRvCategories.setAdapter(mAdapter);
    }


    public Toolbar getToolbar() {
        return mToolbar;
    }

    private OnClickListener mChildClickListener;
    private OnClickListener mParentClickListener;

}
