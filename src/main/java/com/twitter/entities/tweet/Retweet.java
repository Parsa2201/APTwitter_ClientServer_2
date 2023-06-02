package com.twitter.entities.tweet;

import com.twitter.entities.tweet.content.ImageContent;
import com.twitter.entities.tweet.content.TextContent;
import com.twitter.entities.user.MiniUser;

import java.time.LocalDateTime;

public class Retweet extends BaseTweet
{
    private MiniUser retweetedBy;
    private LocalDateTime retweetDate;

    public Retweet(Tweet tweet, MiniUser retweetedBy)
    {
        super(tweet.getTextContent(), tweet.getImageContent());
        this.retweetDate = LocalDateTime.now();
        this.retweetedBy = retweetedBy;

        setOwner(tweet.getOwner());
        setLikeCount(tweet.getLikeCount());
        setRetweetCount(tweet.getRetweetCount());
        setQuoteCount(tweet.getQuoteCount());
        setTweetDate(tweet.getTweetDate());
    }

    public MiniUser getRetweetedBy()
    {
        return retweetedBy;
    }

    public void setRetweetedBy(MiniUser retweetedBy)
    {
        this.retweetedBy = retweetedBy;
    }

    public LocalDateTime getRetweetDate()
    {
        return retweetDate;
    }

    public void setRetweetDate(LocalDateTime retweetDate)
    {
        this.retweetDate = retweetDate;
    }
}
