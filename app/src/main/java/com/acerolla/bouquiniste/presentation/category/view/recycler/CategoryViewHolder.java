package com.acerolla.bouquiniste.presentation.category.view.recycler;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acerolla.bouquiniste.R;
import com.acerolla.bouquiniste.data.category.entity.CategoryParentData;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

/**
 * Created by Evgeniy Solovev
 * Email: solevur@gmail.com
 */
public class CategoryViewHolder extends ParentViewHolder {

    private static final int DEGREES_BEFORE = 0;
    private static final int DEGREES_AFTER = 180;
    private static final int ROTATION_DURATION = 300;

    private ImageView mIvArrow;
    private TextView mTvTitle;

    public CategoryParentData item;
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvTitle = itemView.findViewById(R.id.tv_title);
        mIvArrow = itemView.findViewById(R.id.iv_arrow);
        mIvArrow.setOnClickListener(v -> {
            if (isExpanded()) {
                collapseView();
                ObjectAnimator animator = ObjectAnimator.ofFloat(mIvArrow, View.ROTATION, DEGREES_AFTER, DEGREES_BEFORE);
                animator.setDuration(ROTATION_DURATION);
                animator.start();
            } else {
                expandView();
                ObjectAnimator animator = ObjectAnimator.ofFloat(mIvArrow, View.ROTATION, DEGREES_BEFORE, DEGREES_AFTER);
                animator.setDuration(ROTATION_DURATION);
                animator.start();
            }
        });
    }

    public void bind(CategoryParentData parent) {
        mTvTitle.setText(parent.getTitle());
        item = parent;
    }

    @Override
    public boolean shouldItemViewClickToggleExpansion() {
        return false;
    }
}
