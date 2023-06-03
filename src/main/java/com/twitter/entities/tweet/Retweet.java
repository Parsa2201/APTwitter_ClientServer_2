package com.twitter.entities.tweet;

import com.twitter.entities.user.MiniUser;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Retweet extends BaseTweet implements Serializable
{
    private final Tweet tweet;

    public Retweet(Tweet tweet, MiniUser retweetedBy)
    {
        super(retweetedBy);
        this.tweet = tweet;
    }

    public Retweet(Tweet tweet, int id, LocalDateTime date, MiniUser retweetedBy)
    {
        super(id, date, retweetedBy);
        this.tweet = tweet;
    }

    public Tweet getTweet()
    {
        return tweet;
    }
}
