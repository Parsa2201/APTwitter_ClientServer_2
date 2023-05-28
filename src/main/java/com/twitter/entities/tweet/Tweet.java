package com.twitter.entities.tweet;

import com.twitter.entities.tweet.content.ImageContent;
import com.twitter.entities.tweet.content.TextContent;

public class Tweet extends BaseTweet
{
    public Tweet(TextContent textContent, ImageContent imageContent)
    {
        super(textContent, imageContent);
    }
}
