package com.twitter.entities.tweet;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class TimeLine implements Iterable<BaseTweet>, Serializable
{
    private final ArrayList<BaseTweet> tweets;

    public TimeLine()
    {
        tweets = new ArrayList<>();
    }

    public void addTweet(BaseTweet tweet)
    {
        tweets.add(tweet);
    }

    public BaseTweet getBaseTweet(long id)
    {
        for (BaseTweet tweet : tweets)
            if (tweet.getId() == id)
                return tweet;
        return null;
    }

    public Tweet getTweet(long id)
    {
        for (BaseTweet tweet : tweets)
            if(tweet.getId() == id)
            {
                if(tweet instanceof Tweet)
                    return (Tweet) tweet;
                else if(tweet instanceof Retweet)
                    return ((Retweet) tweet).getTweet();
            }
        return null;
    }

    public Tweet getTweetIdOfRetweet(long retweetId)
    {
        for (BaseTweet tweet : tweets)
            if (tweet.getId() == retweetId && tweet instanceof Retweet)
                return ((Retweet) tweet).getTweet();
        return null;
    }

    public Quote getQuote(long tweetId, long quoteId)
    {
        for (BaseTweet tweet : tweets)
            if (tweet.getId() == tweetId && tweet instanceof Tweet)
                return ((Tweet) tweet).getQuote(quoteId);
        return null;
    }

    public Reply getReply(long tweetId, long replyId)
    {
        for (BaseTweet tweet : tweets)
            if (tweet.getId() == tweetId && tweet instanceof Tweet)
                return ((Tweet) tweet).getReply(replyId);
        return null;
    }

    @NotNull
    @Override
    public Iterator<BaseTweet> iterator()
    {
        return tweets.iterator();
    }
}
