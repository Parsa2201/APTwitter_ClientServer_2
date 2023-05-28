package com.twitter.entities.tweet.content;

import com.twitter.entities.exception.text.TextTooLongException;

public class TextContent extends Content
{
    private final String text;

    public TextContent(String text) throws TextTooLongException
    {
        if(text.length() > 280)
            throw new TextTooLongException("Text is too long");
        this.text = text;
    }

    public String getText()
    {
        return text;
    }
}
