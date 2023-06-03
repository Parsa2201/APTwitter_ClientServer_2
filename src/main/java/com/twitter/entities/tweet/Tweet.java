package com.twitter.entities.tweet;

import com.twitter.entities.tweet.content.ImageContent;
import com.twitter.entities.tweet.content.TextContent;
import com.twitter.entities.user.MiniUser;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tweet extends BaseTweet implements Serializable
{
    private final TextContent textContent;
    private final ImageContent imageContent;
    private int likeCount;
    private int retweetCount;
    private int quoteCount;
    private boolean isFavstar;
    private final ArrayList<Quote> quotes;
    private final ArrayList<Reply> replies;

    public Tweet(MiniUser owner, TextContent textContent, ImageContent imageContent)
    {
        super(owner);
        this.textContent = textContent;
        this.imageContent = imageContent;
        likeCount = 0;
        retweetCount = 0;
        quoteCount = 0;
        quotes = new ArrayList<>();
        replies = new ArrayList<>();
    }

    public Tweet(MiniUser owner, TextContent textContent, ImageContent imageContent, int id, LocalDateTime tweetDate)
    {
        super(id, tweetDate, owner);
        this.textContent = textContent;
        this.imageContent = imageContent;
        likeCount = 0;
        retweetCount = 0;
        quoteCount = 0;
        quotes = new ArrayList<>();
        replies = new ArrayList<>();
    }

    public TextContent getTextContent()
    {
        return textContent;
    }

    public ImageContent getImageContent()
    {
        return imageContent;
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

    public int getQuoteCount()
    {
        return quoteCount;
    }

    public void setQuoteCount(int quoteCount)
    {
        this.quoteCount = quoteCount;
    }

    public boolean isFavstar()
    {
        return isFavstar;
    }

    public void setFavstar(boolean favstar)
    {
        isFavstar = favstar;
    }

    public ArrayList<Quote> getQuotes()
    {
        return quotes;
    }

    public ArrayList<Reply> getReplies()
    {
        return replies;
    }

    public Quote getQuote(long quoteId)
    {
        for (Quote quote : quotes)
            if (quote.getId() == quoteId)
                return quote;
        return null;
    }

    public Reply getReply(long replyId)
    {
        for (Reply reply : replies)
            if (reply.getId() == replyId)
                return reply;
        return null;
    }
}
