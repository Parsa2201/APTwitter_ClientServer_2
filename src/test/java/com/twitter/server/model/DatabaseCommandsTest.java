package com.twitter.server.model;

import com.twitter.entities.exception.user.CountryException;
import com.twitter.entities.exception.user.email.EmailFormatException;
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
}
