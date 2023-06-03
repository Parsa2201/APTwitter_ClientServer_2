package com.twitter.entities.tweet;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class TimeLine implements Iterable<Tweet>, Serializable
{
    private final ArrayList<Tweet> tweets;

    public TimeLine()
    {
        tweets = new ArrayList<Tweet>();
    }

    public void addTweet(Tweet tweet)
    {
        tweets.add(tweet);
    }

    public Tweet getTweet(int id)
    {
        for(Tweet tweet : tweets)
            if(tweet.getId() == id)
                return tweet;
        return null;
    }

    public Retweet getRetweet(int tweetId, int retweetId)
    {
        for(Tweet tweet : tweets)
            if(tweet.getId() == tweetId)
                return tweet.getRetweet(retweetId);
        return null;
    }

    public Quote getQuote(int tweetId, int quoteId)
    {
        for(Tweet tweet : tweets)
            if(tweet.getId() == tweetId)
                return tweet.getQuote(quoteId);
        return null;
    }

    public Reply getReply(int tweetId, int replyId)
    {
        for(Tweet tweet : tweets)
            if(tweet.getId() == tweetId)
                return tweet.getReply(replyId);
        return null;
    }

    @NotNull
    @Override
    public Iterator<Tweet> iterator()
    {
        return tweets.iterator();
    }
}
