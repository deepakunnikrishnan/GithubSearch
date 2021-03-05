package com.androidnerds.githubsearch.presentation.search;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.androidnerds.githubsearch.R;
import com.androidnerds.githubsearch.databinding.ActivityMainBinding;
import com.androidnerds.githubsearch.di.AppModule;
import com.androidnerds.githubsearch.di.DaggerAppComponent;
import com.androidnerds.githubsearch.domain.model.Repo;
import com.androidnerds.githubsearch.presentation.list.RepoListAdapter;
import com.jakewharton.rxbinding4.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainViewModelFactory mainViewModelFactory;
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private RepoListAdapter adapter;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build()
                .inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this, mainViewModelFactory).get(MainViewModel.class);
        setupRepoResultList();
        bindSearchTextChangeListener();
        bindSearchResults();
    }

    private void bindSearchTextChangeListener() {
        Observable<String> observable = RxTextView.textChanges(binding.editTextOrganization)
                .filter(text -> text.length() > 3)
                .debounce(200, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString);
        Disposable disposable = observable.subscribe(org -> mainViewModel.searchRepositoriesForOrganization(org));
        compositeDisposable.add(disposable);
    }

    private void bindSearchResults() {
        mainViewModel.getSearchResult()
                .observe(this, listApiErrorResult -> {
                    if(listApiErrorResult.getData() != null) {
                        adapter.submitList(listApiErrorResult.getData());
                        if(listApiErrorResult.getData().isEmpty()) {
                            showEmptyView("No results");
                            hideResultsList();
                        } else {
                            showResultsList();
                            hideEmptyView();
                        }
                    }
                    if(listApiErrorResult.getError() != null) {
                        String message = listApiErrorResult.getError().getMessage();
                        showEmptyView(message);
                        hideResultsList();
                    }
                });
    }

    private void showResultsList() {
        binding.recyclerViewResults.setVisibility(View.VISIBLE);
    }
    private void hideResultsList() {
        binding.recyclerViewResults.setVisibility(View.GONE);
    }

    private void showEmptyView(String message) {
        binding.emptyViewLayout.setVisibility(View.VISIBLE);
        binding.emptyViewLayout.setMessage(message);
    }

    private void hideEmptyView() {
        binding.emptyViewLayout.setVisibility(View.GONE);
    }

    private void setupRepoResultList() {
        adapter = new RepoListAdapter(this::onRepoItemClicked);
        binding.recyclerViewResults.setHasFixedSize(true);
        binding.recyclerViewResults.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.recyclerViewResults.setAdapter(adapter);
    }

    private void onRepoItemClicked(Repo repo) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabColorSchemeParams params = new CustomTabColorSchemeParams.Builder()
                .setToolbarColor(getColor(R.color.purple_500)).build();
        builder.setColorSchemeParams(CustomTabsIntent.COLOR_SCHEME_LIGHT, params);
        builder.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);
        builder.setExitAnimations(this, R.anim.slide_in_left, R.anim.slide_out_right);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(repo.getUrl()));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}