package com.acerolla.bouquiniste.presentation.favorites.view.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.advert.entity.AdvertData;
import com.acerolla.bouquiniste.di.DiManager;
import com.acerolla.bouquiniste.presentation.favorites.view.recycler.presenter.IRecyclerPresenter;

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

    private View.OnClickListener mListener;
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
        holder.item = item;
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
        if (mListener != null) {
             holder.itemView.setOnClickListener(mListener);
        }
    }

    public void setItemClickListener(View.OnClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mAdverts.size();
    }

    public void setData(List<AdvertData> adverts) {
        mAdverts = adverts;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public AdvertData item;

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
