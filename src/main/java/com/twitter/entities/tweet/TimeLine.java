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
        tweets = new ArrayList<BaseTweet>();
    }

    public void addTweet(BaseTweet tweet)
    {
        tweets.add(tweet);
    }

    public Tweet getTweet(int id)
    {
        for(BaseTweet tweet : tweets)
            if(tweet.getId() == id)
                return (Tweet) tweet;
        return null;
    }

    @NotNull
    @Override
    public Iterator<BaseTweet> iterator()
    {
        return tweets.iterator();
    }
}
