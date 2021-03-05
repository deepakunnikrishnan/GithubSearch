package com.androidnerds.githubsearch.domain.usecase;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.androidnerds.githubsearch.core.rx.DefaultScheduler;
import com.androidnerds.githubsearch.core.rx.SchedulerProvider;
import com.androidnerds.githubsearch.domain.IGithubRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchOrganizationRepositoriesUseCaseTest {

    @Rule
    public final InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Mock
    private IGithubRepository githubRepository;
    private SchedulerProvider schedulerProvider;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        schedulerProvider = new DefaultScheduler();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testExecute() {
        SearchOrganizationRepositoriesUseCase useCase = new SearchOrganizationRepositoriesUseCase(githubRepository,
                schedulerProvider);
        useCase.execute("testname",3);
    }
}