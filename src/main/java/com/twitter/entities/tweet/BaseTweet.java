package com.twitter.entities.tweet;

import com.twitter.entities.exception.UnknownException;
import com.twitter.entities.user.MiniUser;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tweets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class BaseTweet
{
    // this is automatically generated by the database
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final LocalDateTime date;
    @Embedded
    private final MiniUser owner;

    public BaseTweet(long id, LocalDateTime date, MiniUser owner)
    {
        this.id = id;
        this.date = date;
        this.owner = owner;
    }

    public BaseTweet(MiniUser owner)
    {
        this(-1, LocalDateTime.now(), owner);
    }

    public BaseTweet()
    {
        this.date = null;
        this.owner = null;
    }

    public long getId()
    {
        return id;
    }

    // this is the date the tweet was created
    public LocalDateTime getDate()
    {
        return date;
    }

    public Tweet toTweet() throws UnknownException
    {
        if(this instanceof Tweet)
            return (Tweet) this;
        else if(this instanceof Retweet)
            return ((Retweet) this).getTweet();
        else
            throw new UnknownException("Unknown tweet type");
    }

    public MiniUser getOwner()
    {
        return owner;
    }
}
