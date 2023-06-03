package com.twitter.entities.tweet.content.hashtag;

import com.twitter.entities.exception.hashtag.NameNotHashtagException;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "hashtags")
public class Hashtag implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final String name;

    public Long getId() {
        return id;
    }


    public Hashtag(String hashtag) throws NameNotHashtagException
    {
        if(!hashtag.startsWith("#"))
            throw new NameNotHashtagException();
        name = hashtag.substring(1);
    }

    public Hashtag()
    {
        name = null;
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
