package com.twitter.server.model;

import com.twitter.entities.exception.io.server.DataNotFoundException;
import com.twitter.entities.exception.user.CountryException;
import com.twitter.entities.exception.user.email.EmailFormatException;
import com.twitter.entities.exception.user.password.InvalidPasswordException;
import com.twitter.entities.exception.user.password.PasswordFormatException;
import com.twitter.entities.exception.user.password.PasswordHashException;
import com.twitter.entities.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DatabaseCommandsTest
{
    @Test
    public void signUp() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException
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

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.signUp(user);
    }

    @Test
    public void signIn() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException, DataNotFoundException, InvalidPasswordException
    {
        // FIXME
        User user = new User();
        user.setUserName("test user name");
        user.setPassword("NaNoOOl;#329");
        user.setName("test name");
        user.setFamily("test family");
        user.setEmail("test email");
        user.setPhoneNumber("test phone number");
        user.setCountry("iran");
        user.setBirthDate(LocalDate.of(2000, 1, 1));

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.signIn("test user name", user.getPassHash());
    }

    @Test
    public void setAvatar() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException, DataNotFoundException
    {
        // FIXME
        User user = new User();
        user.setUserName("test user name");
        user.setPassword("NaNoOOl;#329");
        user.setName("test name");
        user.setFamily("test family");
        user.setEmail("test email");
        user.setPhoneNumber("test phone number");
        user.setCountry("iran");
        user.setBirthDate(LocalDate.of(2000, 1, 1));

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.setAvatar("test user name", user.getAvatar());
    }

    @Test
    public void setHeader() throws PasswordFormatException, PasswordHashException, EmailFormatException, CountryException, DataNotFoundException
    {
        // FIXME
        User user = new User();
        user.setUserName("test user name");
        user.setPassword("NaNoOOl;#329");
        user.setName("test name");
        user.setFamily("test family");
        user.setEmail("test email");
        user.setPhoneNumber("test phone number");
        user.setCountry("iran");
        user.setBirthDate(LocalDate.of(2000, 1, 1));

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.setHeader("test user name", user.getHeader());
    }

    // TODO: add test for other methods
}
