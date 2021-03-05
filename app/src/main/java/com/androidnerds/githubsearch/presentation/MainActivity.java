package com.androidnerds.githubsearch.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.androidnerds.githubsearch.R;
import com.androidnerds.githubsearch.databinding.ActivityMainBinding;
import com.androidnerds.githubsearch.di.AppModule;
import com.androidnerds.githubsearch.di.DaggerAppComponent;
import com.androidnerds.githubsearch.domain.model.Repo;
import com.androidnerds.githubsearch.presentation.list.RepoListAdapter;
import com.jakewharton.rxbinding4.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

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
                .debounce(2, TimeUnit.SECONDS)
                .map(CharSequence::toString);
        Disposable disposable = observable.subscribe(org -> mainViewModel.searchRepositoriesForOrganization(org));
        compositeDisposable.add(disposable);
    }

    private void bindSearchResults() {
        mainViewModel.getRepoList()
                .observe(this, repos -> adapter.submitList(repos));
    }
    private void setupRepoResultList() {
        adapter = new RepoListAdapter();
        binding.recyclerViewResults.setHasFixedSize(true);
        binding.recyclerViewResults.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        binding.recyclerViewResults.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}