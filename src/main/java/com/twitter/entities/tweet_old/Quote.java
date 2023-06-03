package com.twitter.entities.tweet_old;

import com.twitter.entities.tweet_old.content.ImageContent;
import com.twitter.entities.tweet_old.content.TextContent;
import com.twitter.entities.user.MiniUser;

import java.time.LocalDate;

public class Quote extends BaseTweet
{
    private MiniUser quotedBy;
    private LocalDate quoteDate;
    private TextContent quotedTextContent;
    private ImageContent quotedImageContent;

    public Quote(Tweet tweet, MiniUser quotedBy, TextContent quotedTextContent, ImageContent quotedImageContent)
    {
        super(tweet.getTextContent(), tweet.getImageContent());
        this.quotedBy = quotedBy;
        this.quotedTextContent = quotedTextContent;
        this.quotedImageContent = quotedImageContent;
        this.quoteDate = LocalDate.now();

        setOwner(tweet.getOwner());
        setLikeCount(tweet.getLikeCount());
        setRetweetCount(tweet.getRetweetCount());
        setQuoteCount(tweet.getQuoteCount());
        setTweetDate(tweet.getTweetDate());
    }

    public MiniUser getQuotedBy()
    {
        return quotedBy;
    }

    public void setQuotedBy(MiniUser quotedBy)
    {
        this.quotedBy = quotedBy;
    }

    public LocalDate getQuoteDate()
    {
        return quoteDate;
    }

    public void setQuoteDate(LocalDate quoteDate)
    {
        this.quoteDate = quoteDate;
    }

    public TextContent getQuotedTextContent()
    {
        return quotedTextContent;
    }

    public void setQuotedTextContent(TextContent quotedTextContent)
    {
        this.quotedTextContent = quotedTextContent;
    }

    public ImageContent getQuotedImageContent()
    {
        return quotedImageContent;
    }

    public void setQuotedImageContent(ImageContent quotedImageContent)
    {
        this.quotedImageContent = quotedImageContent;
    }
}
