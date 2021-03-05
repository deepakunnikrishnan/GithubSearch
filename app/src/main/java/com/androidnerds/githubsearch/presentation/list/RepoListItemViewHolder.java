package com.androidnerds.githubsearch.presentation.list;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnerds.githubsearch.databinding.ListItemRepositoryBinding;
import com.androidnerds.githubsearch.domain.model.Repo;

public class RepoListItemViewHolder extends RecyclerView.ViewHolder {

    private final ListItemRepositoryBinding binding;

    public RepoListItemViewHolder(@NonNull ListItemRepositoryBinding binding, RepoListAdapter.OnRepoListItemClickListener listItemClickListener) {
        super(binding.getRoot());
        this.binding = binding;
        this.binding.getRoot().setOnClickListener(v -> listItemClickListener.onListItemClicked(binding.getRepo()));
    }

    public void bind(Repo repo) {
        this.binding.setRepo(repo);
        this.binding.executePendingBindings();
    }
}
