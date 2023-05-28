package com.twitter.entities.tweet;

import com.twitter.entities.user.MiniUser;

import java.time.LocalDateTime;

public class Reply
{
    private final MiniUser replier;
    private final BaseTweet baseTweet;
    private LocalDateTime replyDate;

    public Reply(MiniUser replier, BaseTweet baseTweet)
    {
        this.replier = replier;
        this.baseTweet = baseTweet;
        replyDate = LocalDateTime.now();
    }

    public MiniUser getReplier()
    {
        return replier;
    }

    public BaseTweet getBaseTweet()
    {
        return baseTweet;
    }

    public LocalDateTime getReplyDate()
    {
        return replyDate;
    }

    public void setReplyDate(LocalDateTime replyDate)
    {
        this.replyDate = replyDate;
    }
}
