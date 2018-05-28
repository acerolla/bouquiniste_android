package com.acerolla.bouquiniste.presentation.category.view.recycler;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.category.entity.CategoryChildrenData;
import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

/**
 * Created by Evgeniy Solovev
 * Date: 28.05.2018
 * Email: solevur@gmail.com
 */
public class SubcategoryViewHolder extends ChildViewHolder {

    private TextView mTvTitle;
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public SubcategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvTitle = itemView.findViewById(R.id.tv_title);
    }

    public void bind(CategoryChildrenData children) {
        mTvTitle.setText(children.getTitle());
    }
}
