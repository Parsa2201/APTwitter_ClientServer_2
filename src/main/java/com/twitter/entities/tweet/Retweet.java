package com.twitter.entities.tweet;

import com.twitter.entities.user.MiniUser;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("retweet")
public class Retweet extends BaseTweet implements Serializable
{
    @OneToOne
    private final Tweet tweet;

    public Retweet(Tweet tweet, MiniUser retweetedBy)
    {
        super(retweetedBy);
        this.tweet = tweet;
    }

    public Retweet(Tweet tweet, Long id, LocalDateTime date, MiniUser retweetedBy)
    {
        super(id, date, retweetedBy);
        this.tweet = tweet;
    }

    public Retweet() {
        tweet = null;
    }

    public Tweet getTweet()
    {
        return tweet;
    }
}
