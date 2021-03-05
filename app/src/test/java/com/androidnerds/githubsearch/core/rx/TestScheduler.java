package com.androidnerds.githubsearch.core.rx;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TestScheduler implements SchedulerProvider {


    @Override
    public Scheduler mainThread() {
        return Schedulers.trampoline();
    }


    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }


    @Override
    public Scheduler newThread() {
        return Schedulers.trampoline();
    }
}