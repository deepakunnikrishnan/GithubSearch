package com.androidnerds.githubsearch.domain.mapper;

import com.androidnerds.githubsearch.data.remote.dto.RepoDTO;
import com.androidnerds.githubsearch.domain.model.Repo;

import org.junit.Test;

import static org.junit.Assert.*;

public class RepoDTOtoDomainMapperTest {

    @Test
    public void map() {
        RepoDTO input = new RepoDTO();
        input.setName("material-design-icons");
        input.setFullName("google/material-design-icons");
        input.setDescription("Material Design icons by Google");
        input.setStarCount(41955);
        input.setUrl("https://github.com/google/material-design-icons");

        RepoDTOtoDomainMapper dtOtoDomainMapper = new RepoDTOtoDomainMapper();
        Repo repo = dtOtoDomainMapper.map(input);
        assertNotNull(repo);
        assertEquals("material-design-icons", repo.getName());
        assertEquals("google/material-design-icons", repo.getFullName());
        assertEquals("Material Design icons by Google", repo.getDescription());
        assertEquals(41955, repo.getStarCount());
        assertEquals("https://github.com/google/material-design-icons", repo.getUrl());
    }
}