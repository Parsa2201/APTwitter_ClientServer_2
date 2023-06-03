package com.twitter.entities.exception.hashtag;

import com.twitter.entities.exception.TwitException;
import com.twitter.entities.tweet.content.hashtag.Hashtag;

public class HashtagException extends TwitException
{
    public HashtagException()
    {
        super();
    }

    public HashtagException(String message)
    {
        super(message);
    }
}
