package com.twitter.entities.tweet.content.hashtag;

import com.twitter.entities.exception.hashtag.NameNotHashtagException;

import java.io.Serializable;
import java.util.Objects;

public class Hashtag implements Serializable
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag = (Hashtag) o;
        return Objects.equals(name, hashtag.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}
