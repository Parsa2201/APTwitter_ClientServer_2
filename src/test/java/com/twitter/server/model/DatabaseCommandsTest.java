package com.twitter.server.model;

import com.twitter.entities.exception.hashtag.HashtagException;
import com.twitter.entities.exception.io.FileNotExistException;
import com.twitter.entities.exception.io.FileNotImageException;
import com.twitter.entities.exception.io.FileSizeException;
import com.twitter.entities.exception.io.ImageSizeException;
import com.twitter.entities.exception.io.server.DataNotFoundException;
import com.twitter.entities.exception.text.TextTooLongException;
import com.twitter.entities.exception.user.CountryException;
import com.twitter.entities.exception.user.email.EmailFormatException;
import com.twitter.entities.exception.user.password.InvalidPasswordException;
import com.twitter.entities.exception.user.password.PasswordFormatException;
import com.twitter.entities.exception.user.password.PasswordHashException;
import com.twitter.entities.image.Avatar;
import com.twitter.entities.image.Header;
import com.twitter.entities.tweet.Retweet;
import com.twitter.entities.tweet.Tweet;
import com.twitter.entities.tweet.content.TextContent;
import com.twitter.entities.user.MiniUser;
import com.twitter.entities.user.Password;
import com.twitter.entities.user.User;
import com.twitter.entities.user.follow.Followers;
import com.twitter.entities.user.follow.Followings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class DatabaseCommandsTest
{
    User makeUser1() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException
    {
        User user = new User();
        user.setUserName("test user name 1");
        user.setPassword("NaNoOOl;#329");
        user.setName("test name");
        user.setFamily("test family");
        user.setEmail("test@email.com");
        user.setPhoneNumber("test phone number");
        user.setCountry("iran");
        user.setBirthDate(LocalDate.of(2000, 1, 1));

        return user;
    }

    User makeUser2() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException
    {
        User user = new User();
        user.setUserName("test user name 2");
        user.setPassword("NaNoOOl;#329");
        user.setName("test name");
        user.setFamily("test family");
        user.setEmail("test@email.com");
        user.setPhoneNumber("test phone number");
        user.setCountry("iran");
        user.setBirthDate(LocalDate.of(2000, 1, 1));

        return user;
    }

    @Test
    void signUp1() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException
    {
        User user = new User();
        user.setUserName("test user name 1");
        user.setPassword("NaNoOOl;#329");
        user.setName("test name");
        user.setFamily("test family");
        user.setEmail("test@email.com");
        user.setPhoneNumber("test phone number");
        user.setCountry("iran");
        user.setBirthDate(LocalDate.of(2000, 1, 1));

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.signUp(user);
    }

    @Test
    void signUp2() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException
    {
        User user = makeUser2();

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.signUp(user);
    }

    @Test
    void signIn() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException, DataNotFoundException, InvalidPasswordException, IOException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        User user = databaseCommands.signIn("test user name 1", new Password("NaNoOOl;#329"));
        Assertions.assertEquals(user, makeUser1());

        Avatar avatar = user.getAvatar();
        BufferedImage bufferedImage = avatar.getImage();
        Assertions.assertNotNull(bufferedImage);
        ImageIO.write(bufferedImage, "jpg", new File("Parsa Salamatipour 400X400 - from database.jpg"));

        Header header = user.getHeader();
        bufferedImage = header.getImage();
        Assertions.assertNotNull(bufferedImage);
        ImageIO.write(bufferedImage, "jpg", new File("Parsa Salamatipour 1500X500 - from database.jpg"));

    }

    @Test
    void setAvatar() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException, DataNotFoundException, ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.setAvatar("test user name 1", new Avatar("src/main/java/assets/Parsa Salamatipour 400X400.jpg"));
    }

    @Test
    void setHeader() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException, DataNotFoundException, ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        Header header = new Header("src/main/java/assets/Parsa Salamatipour 1500X500.jpg");
        databaseCommands.setHeader("test user name 1", header);
    }

    @Test
    void changeUserPassword() throws PasswordHashException, DataNotFoundException, InvalidPasswordException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.changeUserPassword("test user name 1", new Password("1234!@#$abAB"));
        databaseCommands.signIn("test user name 1", new Password("1234!@#$abAB"));
    }

    @Test
    void changeName() throws DataNotFoundException, PasswordHashException, InvalidPasswordException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.changeName("test user name 1", "Mammad");
        User user = databaseCommands.signIn("test user name 1", new Password("1234!@#$abAB"));
        Assertions.assertEquals(user.getName(), "Mammad");
    }

    @Test
    void changeFamily() throws DataNotFoundException, PasswordHashException, InvalidPasswordException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.changeFamily("test user name 1", "Mammadi");
        User user = databaseCommands.signIn("test user name 1", new Password("1234!@#$abAB"));
        Assertions.assertEquals(user.getFamily(), "Mammadi");
    }

    // TODO: changeEmail()

    // TODO: changePhone()

    // TODO: changeBirthDate()

    // TODO: changeCounty()

    // TODO: changeLocation()

    // TODO: changeWebsite()

    // TODO: changeBio()

    // TODO: showFollowers()

    // TODO: showFollowings()

    // TODO: follow()

    // TODO: unfollow()

    // TODO: showUser()

    @Test
    void sendTweet1() throws TextTooLongException, HashtagException
    {
        // FIXME
        MiniUser miniUser = new MiniUser();
        miniUser.setUserName("test user name 1");

        Tweet tweet = new Tweet(miniUser, new TextContent("this is a test text"), null);

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.sendTweet(tweet);
    }

    @Test
    void sendTweet2() throws TextTooLongException, HashtagException
    {
        // FIXME
        MiniUser miniUser = new MiniUser();
        miniUser.setUserName("test user name 1");

        Tweet tweet = new Tweet(miniUser, new TextContent("this is another test text"), null);

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.sendTweet(tweet);
    }

    @Test
    void sendRetweet() throws HashtagException, TextTooLongException
    {
        // FIXME
        MiniUser miniUser2 = new MiniUser();
        miniUser2.setUserName("test user name 2");

        MiniUser miniUser1 = new MiniUser();
        miniUser1.setUserName("test user name 1");

        Tweet tweet = new Tweet(miniUser1, new TextContent("this is a test text"), null);

        Retweet retweet = new Retweet(tweet, miniUser2);

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.sendRetweet(retweet);
    }

    // TODO: sendQuote()

    @Test
    void likeTweet() throws DataNotFoundException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        Tweet tweet = databaseCommands.findTweet(1L);
        int likes = tweet.getLikeCount();
        User user = databaseCommands.findUser("parsa2201");
        databaseCommands.likeTweet(tweet, "parsa2201");
        tweet = databaseCommands.findTweet(1L);
        Assertions.assertEquals(tweet.getLikeCount(), likes + 1);
    }

    // TODO: likeTweet()

    // TODO: dislikeTweet()

    // TODO: showTimeLine()

    @Test
    void block() throws DataNotFoundException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.block("test user name 1", "test user name 2");

        try
        {
            Followers followers = databaseCommands.showFollowers("test user name 1");
            for(MiniUser miniUser : followers)
                Assertions.assertEquals(miniUser.getUserName(),"test user name 2");

            Followings followings = databaseCommands.showFollowings("test user name 2");
            for(MiniUser miniUser : followings)
                Assertions.assertEquals(miniUser.getUserName(),"test user name 1");
        }
        catch (DataNotFoundException ignored){}
    }

    // TODO: unblock()

    // TODO: showBlackList()
}
