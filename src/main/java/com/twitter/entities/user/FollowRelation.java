package com.twitter.entities.user;

import jakarta.persistence.*;

@Entity
@Table(name = "follows")
public class FollowRelation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final String username;
    private final String followedUsername;


    public FollowRelation(String username, String followedUsername)
    {
        this.username = username;
        this.followedUsername = followedUsername;
    }

    public FollowRelation()
    {
        this.username = null;
        this.followedUsername = null;
    }

    public String getUsername()
    {
        return username;
    }

    public String getFollowedUsername()
    {
        return followedUsername;
    }

    public boolean equals(FollowRelation followRelation)
    {
        if(this.username.equals(followRelation.getUsername()))
        {
            return this.followedUsername.equals(followRelation.getFollowedUsername());
        }
        return false;
    }
}
