package com.twitter.entities.tweet;

import com.twitter.entities.tweet.content.ImageContent;
import com.twitter.entities.tweet.content.TextContent;
import com.twitter.entities.user.MiniUser;

import java.time.LocalDate;

public class Quote extends BaseTweet
{
    private MiniUser quotedBy;
    private LocalDate quoteDate;

    public Quote(TextContent textContent, ImageContent imageContent)
    {
        super(textContent, imageContent);
    }

    public Quote(TextContent textContent)
    {
        this(textContent, null);
    }

    public Quote(ImageContent imageContent)
    {
        this(null, imageContent);
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
}
