package com.twitter.entities.tweet;

import com.twitter.entities.tweet.content.ImageContent;
import com.twitter.entities.tweet.content.TextContent;
import com.twitter.entities.user.MiniUser;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Quote implements Serializable
{
    // this is automatically generated by the database
    private long id;
    private final Tweet tweet;
    private final MiniUser quotedBy;
    private final LocalDateTime date;

    private final TextContent textContent;
    private final ImageContent imageContent;

    public Quote(Tweet tweet, MiniUser quotedBy, TextContent textContent, ImageContent imageContent)
    {
        this.id = -1;
        this.tweet = tweet;
        this.quotedBy = quotedBy;
        this.date = LocalDateTime.now();
        this.textContent = textContent;
        this.imageContent = imageContent;
    }

    public Quote(Tweet tweet, MiniUser quotedBy, TextContent textContent, ImageContent imageContent, LocalDateTime date)
    {
        this.id = -1;
        this.tweet = tweet;
        this.quotedBy = quotedBy;
        this.date = date;
        this.textContent = textContent;
        this.imageContent = imageContent;
    }

    public long getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Tweet getTweet()
    {
        return tweet;
    }

    public MiniUser getQuotedBy()
    {
        return quotedBy;
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    public TextContent getTextContent()
    {
        return textContent;
    }

    public ImageContent getImageContent()
    {
        return imageContent;
    }
}
