package com.androidnerds.githubsearch.presentation.components.recyclerview.emptyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.androidnerds.githubsearch.R;
import com.androidnerds.githubsearch.databinding.ListSearchEmptyBinding;

public class SearchEmptyView extends ConstraintLayout {


    private ListSearchEmptyBinding binding;

    public SearchEmptyView(@NonNull Context context) {
        this(context, null);
    }

    public SearchEmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SearchEmptyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_search_empty, this, true);
    }

    public void setMessage(String message) {
        binding.textViewLabel.setText(message);
    }

    public void setIcon(@DrawableRes int resId){
        binding.imageView.setImageResource(resId);
    }
}
