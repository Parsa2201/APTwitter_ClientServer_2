package com.twitter.entities.tweet;

import com.twitter.entities.exception.hashtag.HashtagException;
import com.twitter.entities.tweet.content.ImageContent;
import com.twitter.entities.tweet.content.TextContent;
import com.twitter.entities.tweet.content.hashtag.Hashtags;
import com.twitter.entities.user.MiniUser;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("tweet")
public class Tweet extends BaseTweet implements Serializable
{
    @Embedded
    private final TextContent textContent;
    @Embedded
    private final ImageContent imageContent;
    private int likeCount;
    private int retweetCount;
    private int quoteCount;
    private boolean isFavstar;
    @OneToMany
    private final List<Quote> quotes;
    @OneToMany
    private final List<Reply> replies;
    @OneToOne
    private final Hashtags hashtags;

    public Tweet(MiniUser owner, TextContent textContent, ImageContent imageContent) throws HashtagException
    {
        super(owner);
        this.textContent = textContent;
        this.imageContent = imageContent;
        likeCount = 0;
        retweetCount = 0;
        quoteCount = 0;
        quotes = new ArrayList<>();
        replies = new ArrayList<>();
        hashtags = new Hashtags();

        hashtags.getHashtagsFromText(textContent.toString());
    }

    public Tweet(MiniUser owner, TextContent textContent, ImageContent imageContent, int id, LocalDateTime tweetDate) throws HashtagException
    {
        super(tweetDate, owner);
        this.textContent = textContent;
        this.imageContent = imageContent;
        likeCount = 0;
        retweetCount = 0;
        quoteCount = 0;
        quotes = new ArrayList<>();
        replies = new ArrayList<>();
        hashtags = new Hashtags();

        hashtags.getHashtagsFromText(textContent.toString());
    }

    public Tweet() {

        textContent = null;
        quotes = null;
        imageContent = null;
        replies = null;
        hashtags = null;
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

    public List<Quote> getQuotes()
    {
        return quotes;
    }

    public List<Reply> getReplies()
    {
        return replies;
    }

    public Hashtags getHashtags()
    {
        return hashtags;
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
