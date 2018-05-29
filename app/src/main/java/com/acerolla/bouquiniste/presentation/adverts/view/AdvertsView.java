package com.acerolla.bouquiniste.presentation.adverts.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.presentation.adverts.view.recycler.AdvertAdapter;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AdvertsView extends SwipeRefreshLayout {

    private RecyclerView mRvAdverts;
    private RecyclerView.LayoutManager mLayoutManager;
    private AdvertAdapter mAdapter;

    public AdvertsView(Context context) {
        super(context);
    }

    public AdvertsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void initViews() {
        mRvAdverts = findViewById(R.id.rv_adverts);
        mRvAdverts.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRvAdverts.setLayoutManager(mLayoutManager);

        mAdapter = new AdvertAdapter();
        mRvAdverts.setAdapter(mAdapter);
    }

    public void setContentData(List<AdvertData> data) {
        mAdapter.setData(data);
    }

    public void setContentVisibility(int visibility) {

    }

    public void setLoaderVisibility(int visibility) {

    }

    public void setErrorVisibility(int visibility) {

    }

    public void setItemClickListener(OnClickListener listener) {
        mAdapter.setItemClickListener(listener);
    }

    public AdvertData getDataByView(View v) {
        return ((AdvertAdapter.ViewHolder)mRvAdverts.getChildViewHolder(v)).item;
    }

    public void setCategoryData(List<CategoryParentData> data) {

    }
}
