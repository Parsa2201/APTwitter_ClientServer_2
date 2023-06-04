package com.twitter.server.model;

import com.twitter.entities.exception.io.server.DataNotFoundException;
import com.twitter.entities.tweet.LikeRelation;
import com.twitter.entities.user.BlockRelation;
import com.twitter.entities.user.User;
import com.twitter.entities.user.follow.FollowRelation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class DatabaseManager
{
    public SessionFactory sessionFactory;

    public DatabaseManager()
    {
        StandardServiceRegistry serviceRegistry;
        Metadata metadata;

        serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public User findUser(String userName , Session session) throws DataNotFoundException
    {
        Query<User> userQuery = session.createQuery("select u from User u where u.userName = :userName", User.class);
        userQuery.setParameter("userName", userName);
        User user = userQuery.list().get(0);
        if(user == null)
        {
            throw new DataNotFoundException();
        }
        else
        {
            return user;
        }
    }
    public FollowRelation isFollowRelationExist(FollowRelation followRelation, Session session)
    {
        // TODO : change this model
//        List<FollowRelation> followRelations = session.createQuery("select f from FollowRelation f", FollowRelation.class).list();
//        for (FollowRelation followRe:followRelations)
//        {
//            if(followRe.equals(followRelation))
//            {
//                return followRe;
//            }
//        }
//        return null;
        Query<FollowRelation> followRelationQuery = session.createQuery("select f from FollowRelation f where f.user.userName = :user and f.followedUser.userName = :fuser", FollowRelation.class);
        followRelationQuery.setParameter("user", followRelation.getUser().getUserName());
        followRelationQuery.setParameter("fuser", followRelation.getFollowedUser().getUserName());
        return followRelationQuery.list().get(0);
    }

    public LikeRelation isLikeRelationExist(LikeRelation likeRelation, Session session)
    {
        Query<LikeRelation> likeRelationQuery = session.createQuery("select l from LikeRelation l where l.user.userName = :user and l.tweet.id = :tweet", LikeRelation.class);
        likeRelationQuery.setParameter("user", likeRelation.getUser().getUserName());
        likeRelationQuery.setParameter("tweet", likeRelation.getTweet().getId());
        return likeRelationQuery.list().get(0);
    }

    public BlockRelation isBlockRelationExist(BlockRelation blockRelation, Session session)
    {
        Query<BlockRelation> blockRelationQuery = session.createQuery("select b from BlockRelation b where b.blocker.userName = :user and b.blocked.userName = :buser", BlockRelation.class);
        blockRelationQuery.setParameter("user", blockRelation.getBlocker().getUserName());
        blockRelationQuery.setParameter("buser", blockRelation.getBlocked().getUserName());
        return blockRelationQuery.list().get(0);
    }
}
