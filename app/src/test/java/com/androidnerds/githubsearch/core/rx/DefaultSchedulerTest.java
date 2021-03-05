package com.androidnerds.githubsearch.core.rx;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DefaultSchedulerTest {


    public Scheduler mainThread() {
        return Schedulers.trampoline();
    }


    public Scheduler io() {
        return Schedulers.trampoline();
    }


    public Scheduler newThread() {
        return Schedulers.trampoline();
    }
}