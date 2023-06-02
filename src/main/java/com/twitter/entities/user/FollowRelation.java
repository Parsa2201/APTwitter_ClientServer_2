package com.twitter.entities.user;

public class FollowRelation
{
    private final String username;
    private final String followedUsername;

    public FollowRelation(String username, String followedUsername)
    {
        this.username = username;
        this.followedUsername = followedUsername;
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
