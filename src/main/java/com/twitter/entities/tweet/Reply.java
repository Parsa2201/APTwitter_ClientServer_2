package com.twitter.entities.tweet;

import com.twitter.entities.tweet.content.TextContent;
import com.twitter.entities.user.MiniUser;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "replies")
public class Reply implements Serializable
{
    // this is automatically generated by the database
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String userName;
    @OneToOne
    private final MiniUser replier;
    @OneToOne
    private final Tweet tweet;
    private final LocalDateTime date;
    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "reply_text"))
    private final TextContent textContent;

    public Reply(MiniUser replier, Tweet tweet, LocalDateTime date, TextContent textContent)
    {
        id = -1L;
        this.replier = replier;
        this.tweet = tweet;
        this.date = date;
        this.textContent = textContent;
        userName = replier.getUserName();
    }

    public Reply(MiniUser replier, Tweet tweet, TextContent textContent)
    {
        this(replier, tweet, LocalDateTime.now(), textContent);
    }

    public Reply() {

        textContent = null;
        date = null;
        tweet = null;
        replier = null;
        userName = null;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public MiniUser getReplier()
    {
        return replier;
    }

    public Tweet getTweet()
    {
        return tweet;
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    public TextContent getTextContent()
    {
        return textContent;
    }
}
