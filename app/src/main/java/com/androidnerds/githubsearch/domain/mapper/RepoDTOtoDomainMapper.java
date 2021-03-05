package com.androidnerds.githubsearch.domain.mapper;

import com.androidnerds.githubsearch.core.mapper.Mapper;
import com.androidnerds.githubsearch.data.remote.dto.RepoDTO;
import com.androidnerds.githubsearch.domain.model.Repo;

import javax.inject.Inject;

public class RepoDTOtoDomainMapper implements Mapper<RepoDTO, Repo> {

    @Inject
    public RepoDTOtoDomainMapper() {

    }

    @Override
    public Repo map(RepoDTO input) {
        Repo repo = new Repo();
        repo.setId(input.getId());
        repo.setName(input.getName());
        repo.setFullName(input.getFullName());
        repo.setDescription(input.getDescription());
        repo.setUrl(input.getUrl());
        repo.setStarCount(input.getStarCount());
        return repo;
    }
}
