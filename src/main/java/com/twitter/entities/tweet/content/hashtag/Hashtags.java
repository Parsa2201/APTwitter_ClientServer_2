package com.twitter.entities.tweet.content.hashtag;

import com.twitter.entities.exception.hashtag.HashtagException;
import com.twitter.entities.exception.hashtag.NameNotHashtagException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hashtags implements Iterable<Hashtag>
{
    private final ArrayList<Hashtag> hashtags;

    public Hashtags()
    {
        hashtags = new ArrayList<>();
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


    @NotNull
    @Override
    public Iterator<Hashtag> iterator()
    {
        return hashtags.iterator();
    }
}
