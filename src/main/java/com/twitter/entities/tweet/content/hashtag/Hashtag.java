package com.twitter.entities.tweet.content.hashtag;

import com.twitter.entities.exception.hashtag.NameNotHashtagException;

public class Hashtag
{
    private final String name;

    public Hashtag(String hashtag) throws NameNotHashtagException
    {
        if(!hashtag.startsWith("#"))
            throw new NameNotHashtagException();
        name = hashtag.substring(1);
    }

    public String nameWithoutHashtag()
    {
        return name;
    }

    public String nameWithHashtag()
    {
        return "#" + name;
    }
}
