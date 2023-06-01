package com.twitter.client.controller;

import com.twitter.client.view.ProgramState;
import com.twitter.entities.exception.EmailOrPhoneRequiredException;
import com.twitter.entities.exception.UnknownException;
import com.twitter.entities.exception.io.server.*;
import com.twitter.entities.exception.user.CountryException;
import com.twitter.entities.exception.user.PermissionDeniedException;
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

    private User getCurrentUser() throws PermissionDeniedException
    {
        User user = Data.getInstance().getUser();
        if(user == null || Data.getInstance().getProgramState() == ProgramState.LOGGED_OUT)
            throw new PermissionDeniedException();

        return user;
    }

    public void signUp(String userName, String name, String family,
                       String email, String phoneNumber, String password, String passwordConfirm,
                       String country, int year, int month, int day)
            throws EmailOrPhoneRequiredException, PasswordConfirmException, EmailFormatException, PasswordFormatException,
            PasswordHashException, ServerConnectionFailedException, ServerRespondFailedException, ServerInvalidObjectException, DatabaseFailedException, CountryException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException
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

    public User signIn(String userName, String password) throws PasswordHashException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException
    {
            Password passwordHash = new Password(password);

            User user = modelCommands.signIn(userName, passwordHash);

            Data.getInstance().setUser(user);
            Data.getInstance().setProgramState(ProgramState.MAIN_MENU);
            return user;
    }

    public void signOut() throws PermissionDeniedException
    {
        getCurrentUser();
        Data.getInstance().setUser(null);
        Data.getInstance().setProgramState(ProgramState.LOGGED_OUT);
    }

    public Avatar setAvatar(String path)
            throws FileSizeException, FileNotExistException, FileNotImageException, ImageSizeException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException
    {
        User user = getCurrentUser();
        Avatar avatar = new Avatar(path);
        modelCommands.setAvatar(user.getUserName(), avatar);

        user.setAvatar(new Avatar(path));
        return avatar;
    }

    public Header setHeader(String path)
            throws ImageSizeException, FileSizeException, FileNotExistException, FileNotImageException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException
    {
        User user = getCurrentUser();
        Header header = new Header(path);
        modelCommands.setHeader(user.getUserName(), header);

        user.setHeader(new Header(path));
        return header;
    }

    public void changePassword(String oldPassword, String newPassword, String newPasswordConfirm) throws PermissionDeniedException, PasswordFormatException, PasswordHashException, InvalidPasswordException, ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, PasswordConfirmException, ServerInvalidCommandException
    {
        User user = getCurrentUser();

        if(!user.getPassHash().equals(new Password(oldPassword)))
            throw new InvalidPasswordException();
        Verification.verifyPassword(newPassword);
        if(!newPassword.equals(newPasswordConfirm))
            throw new PasswordConfirmException();

        modelCommands.changePassword(user.getUserName(), new Password(newPassword));

        user.setPassword(new Password(newPassword));
    }

    public void changeName(String newName) throws PermissionDeniedException, ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        User user = getCurrentUser();
        modelCommands.changeName(user.getUserName(), newName);

        user.setName(newName);
    }

    public void changeFamily(String newFamily) throws PermissionDeniedException, ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        User user = getCurrentUser();
        modelCommands.changeFamily(user.getUserName(), newFamily);

        user.setFamily(newFamily);
    }

    public void changeEmail(String newEmail) throws PermissionDeniedException, EmailFormatException, ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        User user = getCurrentUser();

        Verification.verifyEmail(newEmail);

        modelCommands.changeEmail(user.getUserName(), newEmail);

        user.setEmail(newEmail);
    }

    public void changePhoneNumber(String newPhoneNumber) throws PermissionDeniedException, ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        User user = getCurrentUser();
        modelCommands.changePhoneNumber(user.getUserName(), newPhoneNumber);

        user.setPhoneNumber(newPhoneNumber);
    }

    public void changeBirthDate(int year, int month, int day) throws PermissionDeniedException, ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        User user = getCurrentUser();
        modelCommands.changeBirthDate(user.getUserName(), LocalDate.of(year, month, day));

        user.setBirthDate(LocalDate.of(year, month, day));
    }

    public void changeCountry(String newCountry) throws PermissionDeniedException, CountryException, ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        User user = getCurrentUser();

        Verification.verifyCountry(newCountry);

        modelCommands.changeCountry(user.getUserName(), newCountry);

        user.setCountry(newCountry);
    }

    public void changeBio(String newBio) throws PermissionDeniedException, TextTooLongException, ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        User user = getCurrentUser();

        Verification.verifyBio(newBio);

        modelCommands.changeBio(user.getUserName(), new Bio(newBio));

        user.setBio(new Bio(newBio));
    }

    public void changeLocation(String newLocation) throws PermissionDeniedException, ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        User user = getCurrentUser();

        modelCommands.changeLocation(user.getUserName(), newLocation);

        user.setLocation(newLocation);
    }

    public void changeWebsite(String newWebsite) throws PermissionDeniedException, ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        User user = getCurrentUser();

        modelCommands.changeWebsite(user.getUserName(), newWebsite);

        user.setWebsite(newWebsite);
    }

//    public void changeUserInformation(String bioText, String location, String website) throws TextTooLongException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, PermissionDeniedException
//    {
//        User user = getCurrentUser();
//        Bio bio = new Bio(bioText);
//        modelCommands.changeUserInformation(user.getUserName(), bio, location, website);
//
//        user.setBio(bio);
//        user.setLocation(location);
//        user.setWebsite(website);
//    }

    public Followers showFollowers() throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException
    {
        User user = getCurrentUser();
        return modelCommands.showFollowers(user.getUserName());
    }

    public Followings showFollowings() throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException
    {
        User user = getCurrentUser();
        return modelCommands.showFollowings(user.getUserName());
    }

    public void follow(MiniUser miniUser) throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException
    {
        User user = getCurrentUser();
        FollowRelation followRelation = new FollowRelation(user.getUserName(), miniUser.getUserName());
        modelCommands.follow(followRelation);
    }

    public void unfollow(MiniUser miniUser) throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException, PermissionDeniedException, ServerInvalidCommandException
    {
        User user = getCurrentUser();
        FollowRelation followRelation = new FollowRelation(user.getUserName(), miniUser.getUserName());
        modelCommands.unfollow(followRelation);
    }

    public MiniUser showUser(MiniUser miniUser) throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException
    {
        return modelCommands.showUser(miniUser.getUserName());
    }
}
