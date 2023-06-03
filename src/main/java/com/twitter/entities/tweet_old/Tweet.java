package com.twitter.entities.tweet_old;

import com.twitter.entities.tweet_old.content.ImageContent;
import com.twitter.entities.tweet_old.content.TextContent;

public class Tweet extends BaseTweet
{
    public Tweet(TextContent textContent, ImageContent imageContent)
    {
        super(textContent, imageContent);
    }
}
