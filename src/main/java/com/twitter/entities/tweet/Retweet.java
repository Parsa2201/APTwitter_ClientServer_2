package com.twitter.entities.tweet;

import com.twitter.entities.tweet.content.ImageContent;
import com.twitter.entities.tweet.content.TextContent;
import com.twitter.entities.user.MiniUser;

import java.time.LocalDateTime;

public class Retweet extends BaseTweet
{
    private MiniUser retweetedBy;
    private LocalDateTime retweetDate;

    public Retweet(TextContent textContent, ImageContent imageContent)
    {
        super(textContent, imageContent);
        retweetDate = LocalDateTime.now();
    }

    public Retweet(TextContent textContent)
    {
        this(textContent, null);
    }

    public Retweet(ImageContent imageContent)
    {
        this(null, imageContent);
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
