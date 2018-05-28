package com.acerolla.bouquiniste.presentation.adverts.view.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.ResultListener;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.adverts.view.recycler.presenter.IRecyclerPresenter;
import com.acerolla.bouquiniste.presentation.adverts.view.recycler.presenter.RecyclerPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.ViewHolder> {

    private static final String DEFAULT_CATEGORY = "UNKNOWN!";
    private static final String API_URL = "http://85.119.144.206/";

    private List<AdvertData> mAdverts;

    @Inject
    IRecyclerPresenter mPresenter;

    public AdvertAdapter() {
        mAdverts = new ArrayList<>();

        DiManager.getRecyclerComponent().inject(this);
        mPresenter.bindView(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdvertItemView root = (AdvertItemView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_advert, parent, false);
        root.initViews();

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AdvertData item = mAdverts.get(position);
        String url = "";
        if (item.getImage() != null && !item.getImage().isEmpty()) {
            url = API_URL + item.getImage();
        }
        ((AdvertItemView) holder.itemView).loadImage(url);
        ((AdvertItemView) holder.itemView).setTitle(item.getTitle());
        ((AdvertItemView) holder.itemView).setAuthor(item.getAuthor());
        ((AdvertItemView) holder.itemView).setPrice(item.getPrice());
        ((AdvertItemView) holder.itemView).setLocation(item.getLocation());
        mPresenter.onBind(result -> {
            if (result != null && !result.isEmpty()) {
                ((AdvertItemView) holder.itemView).setCategory(result);
            } else {
                ((AdvertItemView) holder.itemView).setCategory(DEFAULT_CATEGORY);
            }
        }, item.getCategoryId());
    }

    @Override
    public int getItemCount() {
        return mAdverts.size();
    }

    public void setData(List<AdvertData> adverts) {
        mAdverts = adverts;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
