package com.twitter.client.controller;

import com.twitter.entities.exception.EmailOrPhoneRequiredException;
import com.twitter.entities.exception.UnknownException;
import com.twitter.entities.exception.io.server.*;
import com.twitter.entities.exception.user.CountryException;
import com.twitter.entities.exception.user.email.EmailFormatException;
import com.twitter.entities.exception.io.*;
import com.twitter.entities.exception.user.password.InvalidPasswordException;
import com.twitter.entities.exception.user.password.PasswordConfirmException;
import com.twitter.entities.exception.user.password.PasswordFormatException;
import com.twitter.entities.exception.user.password.PasswordHashException;
import com.twitter.entities.exception.text.TextTooLongException;
import com.twitter.entities.image.Avatar;
import com.twitter.entities.image.Header;
import com.twitter.entities.user.*;
import com.twitter.client.model.ModelCommands;

import java.time.LocalDate;

public class ControllerCommands
{
    protected final ModelCommands modelCommands;
    public ControllerCommands()
    {
        modelCommands = new ModelCommands();
    }

    public void signUp(String userName, String name, String family,
                       String email, String phoneNumber, String password, String passwordConfirm,
                       String country, int year, int month, int day)
            throws EmailOrPhoneRequiredException, PasswordConfirmException, EmailFormatException, PasswordFormatException,
            PasswordHashException, ServerConnectionFailedException, ServerRespondFailedException, ServerInvalidObjectException, DatabaseFailedException, CountryException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        if (email.equals("") && phoneNumber.equals(""))
            throw new EmailOrPhoneRequiredException();

        if (!password.equals(passwordConfirm))
            throw new PasswordConfirmException();

        User user = new User();
        user.setUserName(userName);
        user.setName(name);
        user.setFamily(family);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setCountry(country);
        user.setBirthDate(LocalDate.of(year, month, day));
        user.setSignUpDate(LocalDate.now());
        user.setLastChangeDate(LocalDate.now());

        modelCommands.signUp(user);
    }

    public User signIn(String userName, String password) throws PasswordHashException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        Password passwordHash = new Password(password);

        return modelCommands.signIn(userName, passwordHash);
    }

    public Avatar setAvatar(User user, String path)
            throws FileSizeException, FileNotExistException, FileNotImageException, ImageSizeException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        Avatar avatar = new Avatar(path);
        modelCommands.setAvatar(user.getUserName(), avatar);

        user.setAvatar(new Avatar(path));
        return avatar;
    }

    public Header setHeader(User user, String path)
            throws ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        Header header = new Header(path);
        modelCommands.setHeader(user.getUserName(), header);

        user.setHeader(new Header(path));
        return header;
    }

    public void changeUserInformation(User user, String bioText, String location, String website) throws TextTooLongException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException
    {
        Bio bio = new Bio(bioText);
        modelCommands.changeUserInformation(user.getUserName(), bio, location, website);

        user.setBio(bio);
        user.setLocation(location);
        user.setWebsite(website);
    }

    public Followers showFollowers(User user) throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        return modelCommands.showFollowers(user.getUserName());
    }

    public Followings showFollowings(User user) throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        return modelCommands.showFollowings(user.getUserName());
    }

    public void follow(User user, MiniUser miniUser) throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        FollowRelation followRelation = new FollowRelation(user.getUserName(), miniUser.getUserName());
        modelCommands.follow(followRelation);
    }

    public void unfollow(User user, MiniUser miniUser) throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        FollowRelation followRelation = new FollowRelation(user.getUserName(), miniUser.getUserName());
        modelCommands.unfollow(followRelation);
    }

    public MiniUser showUser(MiniUser miniUser) throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        return modelCommands.showUser(miniUser.getUserName());
    }
}
