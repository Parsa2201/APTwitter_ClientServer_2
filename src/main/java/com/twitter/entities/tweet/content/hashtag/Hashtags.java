package com.twitter.entities.tweet.content.hashtag;

import com.twitter.entities.exception.hashtag.HashtagException;
import com.twitter.entities.exception.hashtag.NameNotHashtagException;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "hashtags")
public class Hashtags implements Iterable<Hashtag>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany
    private final ArrayList<Hashtag> hashtags;

    public Long getId()
    {
        return id;
    }


    public Hashtags()
    {
        hashtags = new ArrayList<>();
    }

    public Hashtags(String text) throws HashtagException
    {
        this();
        getHashtagsFromText(text);
    }

    public void add(Hashtag hashtag)
    {
        hashtags.add(hashtag);
    }

    public void add(String hashtag) throws NameNotHashtagException
    {
        hashtags.add(new Hashtag(hashtag));
    }

    public void getHashtagsFromText(String text) throws HashtagException
    {
        String regexPattern = "#//w+";

        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find())
            add(matcher.group(1));
    }

    public boolean contains(Hashtags hashtags)
    {
        for(Hashtag hashtag_i : this.hashtags)
            for(Hashtag hashtag_j : hashtags)
                if(hashtag_i.equals(hashtag_j))
                    return true;
        return false;
    }


    @NotNull
    @Override
    public Iterator<Hashtag> iterator()
    {
        return hashtags.iterator();
    }
}
