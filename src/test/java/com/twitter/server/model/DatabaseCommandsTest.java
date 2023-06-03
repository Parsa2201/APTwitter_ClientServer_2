package com.twitter.server.model;

import com.twitter.entities.exception.io.FileNotExistException;
import com.twitter.entities.exception.io.FileNotImageException;
import com.twitter.entities.exception.io.FileSizeException;
import com.twitter.entities.exception.io.ImageSizeException;
import com.twitter.entities.exception.io.server.DataNotFoundException;
import com.twitter.entities.exception.user.CountryException;
import com.twitter.entities.exception.user.email.EmailFormatException;
import com.twitter.entities.exception.user.password.InvalidPasswordException;
import com.twitter.entities.exception.user.password.PasswordFormatException;
import com.twitter.entities.exception.user.password.PasswordHashException;
import com.twitter.entities.image.Avatar;
import com.twitter.entities.image.Header;
import com.twitter.entities.user.Password;
import com.twitter.entities.user.User;
import org.glassfish.jaxb.core.v2.TODO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DatabaseCommandsTest
{
    private User makeUser1() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException
    {
        User user = new User();
        user.setUserName("test user name");
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
    public void signUp() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException
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
    public void signIn() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException, DataNotFoundException, InvalidPasswordException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        User user = databaseCommands.signIn("test user name", new Password("NaNoOOl;#329"));
        Assertions.assertEquals(user, makeUser1());
    }

    @Test
    public void setAvatar() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException, DataNotFoundException, ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException
    {
        // FIXME
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.setAvatar("test user name", new Avatar("src/main/java/assets/Parsa Salamatipour 400X400.jpg"));
    }

    @Test
    public void setHeader() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException, DataNotFoundException, ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException
    {
        // FIXME
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.setHeader("test user name", new Header("src/main/java/assets/Parsa Salamatipour 400X400.jpg"));
    }

    @Test
    public void changeUserPassword() throws PasswordHashException, DataNotFoundException, InvalidPasswordException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.changeUserPassword("test user name", new Password("1234!@#$abAB"));
        databaseCommands.signIn("test user name", new Password("1234!@#$abAB"));
    }

    @Test
    public void changeName() throws DataNotFoundException, PasswordHashException, InvalidPasswordException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.changeName("test user name", "Mammad");
        User user = databaseCommands.signIn("test user name", new Password("1234!@#$abAB"));
        Assertions.assertEquals(user.getName(), "Mammad");
    }

    @Test
    public void changeFamily() throws DataNotFoundException, PasswordHashException, InvalidPasswordException
    {
        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.changeFamily("test user name", "Mammadi");
        User user = databaseCommands.signIn("test user name", new Password("1234!@#$abAB"));
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

    // TODO: sendTweet()

    // TODO: sendRetweet()

    // TODO: sendQuote()

    // TODO: likeTweet()

    // TODO: likeTweet()

    // TODO: dislikeTweet()

    // TODO: showTimeLine()

    // TODO: block()

    // TODO: unblock()
}
