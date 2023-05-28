package com.twitter.entities.tweet;

import com.twitter.entities.tweet.content.ImageContent;
import com.twitter.entities.tweet.content.TextContent;
import com.twitter.entities.user.MiniUser;

import java.time.LocalDateTime;

public abstract class BaseTweet
{
    private int id;
    private MiniUser owner;
    private final TextContent textContent;
    private final ImageContent imageContent;
    private int likeCount;
    private int retweetCount;
    private LocalDateTime tweetDate;

    public BaseTweet(TextContent textContent, ImageContent imageContent)
    {
        id = -1;
        this.textContent = textContent;
        this.imageContent = imageContent;
        likeCount = 0;
        retweetCount = 0;
        tweetDate = LocalDateTime.now();
    }

    public BaseTweet(TextContent textContent)
    {
        this(textContent, null);
    }

    public BaseTweet(ImageContent imageContent)
    {
        this(null, imageContent);
    }

    public MiniUser getOwner()
    {
        return owner;
    }

    public void setOwner(MiniUser owner)
    {
        this.owner = owner;
    }

    public TextContent getTextContent()
    {
        return textContent;
    }

    public ImageContent getImageContent()
    {
        return imageContent;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getLikeCount()
    {
        return likeCount;
    }

    public void setLikeCount(int likeCount)
    {
        this.likeCount = likeCount;
    }

    public int getRetweetCount()
    {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount)
    {
        this.retweetCount = retweetCount;
    }

    public LocalDateTime getTweetDate()
    {
        return tweetDate;
    }

    public void setTweetDate(LocalDateTime tweetDate)
    {
        this.tweetDate = tweetDate;
    }
}
