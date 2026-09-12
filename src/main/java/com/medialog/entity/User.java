package com.medialog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String displayName;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings = new ArrayList<>();

    protected User() {
    }

    public User(String username, String displayName) {
        this.username = username;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
}
