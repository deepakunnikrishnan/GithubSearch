package com.androidnerds.githubsearch.presentation.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.androidnerds.githubsearch.R;
import com.androidnerds.githubsearch.databinding.ListItemRepositoryBinding;
import com.androidnerds.githubsearch.domain.model.Repo;

public class RepoListAdapter extends ListAdapter<Repo, RepoListItemViewHolder> {

    private static final DiffUtil.ItemCallback<Repo> itemCallback = new DiffUtil.ItemCallback<Repo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return oldItem.equals(newItem);
        }
    };

    public RepoListAdapter() {
        super(itemCallback);
    }

    @NonNull
    @Override
    public RepoListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemRepositoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_repository, parent, false);
        return new RepoListItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoListItemViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
