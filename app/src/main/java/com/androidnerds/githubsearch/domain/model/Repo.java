package com.androidnerds.githubsearch.domain.model;


import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Repo {

    private long id;
    private String name;
    private String fullName;
    private String description;
    private String url;
    private int starCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Repo)) return false;
        Repo repo = (Repo) o;
        return getId() == repo.getId() &&
                getStarCount() == repo.getStarCount() &&
                Objects.equals(getName(), repo.getName()) &&
                Objects.equals(getFullName(), repo.getFullName()) &&
                Objects.equals(getDescription(), repo.getDescription()) &&
                Objects.equals(getUrl(), repo.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getFullName(), getDescription(), getUrl(), getStarCount());
    }
}
