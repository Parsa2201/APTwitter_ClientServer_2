package com.twitter.entities.tweet;

import com.twitter.entities.user.MiniUser;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Reply implements Serializable
{
    // this is automatically generated by the database
    private int id;
    private final MiniUser replier;
    private final Tweet tweet;
    private final LocalDateTime date;

    public Reply(MiniUser replier, Tweet tweet)
    {
        id = -1;
        this.replier = replier;
        this.tweet = tweet;
        date = LocalDateTime.now();
    }

    public Reply(MiniUser replier, Tweet tweet, LocalDateTime date)
    {
        id = -1;
        this.replier = replier;
        this.tweet = tweet;
        this.date = date;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public MiniUser getReplier()
    {
        return replier;
    }

    public Tweet getTweet()
    {
        return tweet;
    }

    public LocalDateTime getDate()
    {
        return date;
    }
}
