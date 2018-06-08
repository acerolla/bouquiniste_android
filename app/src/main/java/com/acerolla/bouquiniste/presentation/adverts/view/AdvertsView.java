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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.acerolla.bouquiniste.presentation.adverts.view.recycler.AdvertAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AdvertsView extends SwipeRefreshLayout {

    private RecyclerView mRvAdverts;
    private RecyclerView.LayoutManager mLayoutManager;
    private AdvertAdapter mAdapter;

    private ProgressBar mProgress;
    private TextView mTvError;

    private boolean mLoading = false;

    public interface LoadingCallback {
        void onLoad(int advertsCount);
    }

    public AdvertsView(Context context) {
        super(context);
    }

    public AdvertsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void initViews() {
        mProgress = findViewById(R.id.progress);
        mTvError = findViewById(R.id.tv_error);

        mRvAdverts = findViewById(R.id.rv_adverts);
        mRvAdverts.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRvAdverts.setLayoutManager(mLayoutManager);

        mAdapter = new AdvertAdapter();
        mRvAdverts.setAdapter(mAdapter);
    }

    @Override
    public void setOnRefreshListener(@Nullable OnRefreshListener listener) {
        super.setOnRefreshListener(listener);
    }

    public void setOnScrollListener(LoadingCallback callback) {
        mRvAdverts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (((LinearLayoutManager)mLayoutManager).findFirstCompletelyVisibleItemPosition() == 0) {
                    AdvertsView.this.setEnabled(true);
                } else {
                    AdvertsView.this.setEnabled(false);
                }
                int totalItemCount = mLayoutManager.getItemCount();
                int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
                if (!mLoading && totalItemCount <= lastVisibleItem + 5) {
                    mLoading = true;
                    callback.onLoad(totalItemCount);
                }
            }
        });
    }

    public void setContentData(List<AdvertData> data) {
        mAdapter.setData(data);
        setEnabled(true);
        mLoading = false;
    }

    public void addContentData(List<AdvertData> data) {
        mAdapter.addData(data);
        mLoading = false;
    }

    public void setContentVisibility(int visibility) {
        mRvAdverts.setVisibility(visibility);
    }

    public void setLoaderVisibility(int visibility) {
        mProgress.setVisibility(visibility);
    }

    public void setErrorVisibility(int visibility) {
        mTvError.setVisibility(visibility);
    }

    public void setItemClickListener(OnClickListener listener) {
        mAdapter.setItemClickListener(listener);
    }

    public AdvertData getDataByView(View v) {
        return ((AdvertAdapter.ViewHolder)mRvAdverts.getChildViewHolder(v)).item;
    }
}
