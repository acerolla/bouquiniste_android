package com.acerolla.bouquiniste.presentation.category.view.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.category.entity.CategoryChildrenData;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class CategoryAdapter extends ExpandableRecyclerAdapter<CategoryParentData, CategoryChildrenData, CategoryViewHolder, SubcategoryViewHolder> {

    private LayoutInflater mInflater;
    private View.OnClickListener mChildListener;
    private View.OnClickListener mParentListener;

    public CategoryAdapter(Context context, List<CategoryParentData> categoryList, View.OnClickListener childListener, View.OnClickListener parentListener) {
        super(categoryList);
        mInflater = LayoutInflater.from(context);
        mChildListener = childListener;
        mParentListener = parentListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View parentView = mInflater.inflate(R.layout.item_category_parent, parentViewGroup, false);
        parentView.setOnClickListener(mParentListener);
        return new CategoryViewHolder(parentView);
    }

    @NonNull
    @Override
    public SubcategoryViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View childrenView = mInflater.inflate(R.layout.item_category_children, childViewGroup, false);
        childrenView.setOnClickListener(mChildListener);
        return new SubcategoryViewHolder(childrenView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull CategoryViewHolder parentViewHolder, int parentPosition, @NonNull CategoryParentData parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull SubcategoryViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull CategoryChildrenData child) {
        childViewHolder.bind(child);
    }
}
