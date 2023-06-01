package com.twitter.client.view;

import com.twitter.client.controller.ControllerCommands;
import com.twitter.client.controller.Data;
import com.twitter.client.controller.Program;
import com.twitter.entities.exception.EmailOrPhoneRequiredException;
import com.twitter.entities.exception.TwitException;
import com.twitter.entities.exception.UnknownException;
import com.twitter.entities.exception.io.FileNotExistException;
import com.twitter.entities.exception.io.FileNotImageException;
import com.twitter.entities.exception.io.FileSizeException;
import com.twitter.entities.exception.io.ImageSizeException;
import com.twitter.entities.exception.io.server.*;
import com.twitter.entities.exception.text.TextTooLongException;
import com.twitter.entities.exception.user.CountryException;
import com.twitter.entities.exception.user.PermissionDeniedException;
import com.twitter.entities.exception.user.email.EmailFormatException;
import com.twitter.entities.exception.user.password.InvalidPasswordException;
import com.twitter.entities.exception.user.password.PasswordConfirmException;
import com.twitter.entities.exception.user.password.PasswordFormatException;
import com.twitter.entities.exception.user.password.PasswordHashException;
import com.twitter.entities.user.Country;
import com.twitter.entities.user.User;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Command
{
    private final ControllerCommands controllerCommands;

    public Command()
    {
        controllerCommands = new ControllerCommands();
    }

    public void signUp() throws DateTimeParseException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, PasswordHashException, PasswordConfirmException, CountryException, EmailFormatException, DataNotFoundException, PasswordFormatException, EmailOrPhoneRequiredException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        String userName, name, family, email, phoneNumber, password, passwordConfirm, country;
        int year, month, day;

        userName = TwitterLog.nextLine("Username: ");
        name = TwitterLog.nextLine("Name: ");
        family = TwitterLog.nextLine("Family Name: ");
        email = TwitterLog.nextLine("Email: ");
        phoneNumber = TwitterLog.nextLine("Phone Number: ");
        password = TwitterLog.nextLine("Password: ");
        passwordConfirm = TwitterLog.nextLine("Confirm you password: ");
        TwitterLog.println("Country List:");
        TwitterLog.println(Country.getInstance().toString());
        country = TwitterLog.nextLine("Country: ");

        String date = TwitterLog.nextLine("Birth Date: (yyyy-mm-dd) ");
        LocalDate localDate = LocalDate.parse(date);

        year = localDate.getYear();
        month = localDate.getMonthValue();
        day = localDate.getDayOfMonth();


        controllerCommands.signUp(userName, name, family, email, phoneNumber, password, passwordConfirm, country, year, month, day);
    }

    public void signIn() throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, PasswordHashException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException
    {
        String username, password;

        username = TwitterLog.nextLine("Username: ");
        password = TwitterLog.nextLine("Password: ");


        User user = controllerCommands.signIn(username, password);

        TwitterLog.println("You successfully logged in as : " + user.getName() + " " + user.getFamily());
    }

    public void showUserInfo()
    {
        User user = Data.getInstance().getUser();

        TwitterLog.println("Username: " + user.getUserName());
        TwitterLog.println("Name: " + user.getName());
        TwitterLog.println("Family: " + user.getFamily());
        TwitterLog.println("Email: " + user.getEmail());
        TwitterLog.println("Phone Number: " + user.getPhoneNumber());
        TwitterLog.println("Country: " + user.getCountry());
        TwitterLog.println("Birth Date: " + user.getBirthDate());

        if(user.getBio() != null && !user.getBio().toString().equals(""))
            TwitterLog.println("Bio: " + user.getBio());

        if(user.getLocation() != null && !user.getLocation().equals(""))
            TwitterLog.println("Location: " + user.getLocation());
    }

    public void signOut() throws PermissionDeniedException
    {
        controllerCommands.signOut();
    }

    public void setAvatar() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, FileNotExistException, FileNotImageException, DatabaseFailedException, ImageSizeException, UnknownException, InvalidPasswordException, FileSizeException, PermissionDeniedException, TextTooLongException, ServerInvalidObjectException
    {
        String path = TwitterLog.nextLine("Enter your image path (*.jpg): ");
        controllerCommands.setAvatar(path);
    }

    public void setHeader() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, FileNotExistException, FileNotImageException, DatabaseFailedException, ImageSizeException, UnknownException, InvalidPasswordException, FileSizeException, PermissionDeniedException, TextTooLongException, ServerInvalidObjectException
    {
        String path = TwitterLog.nextLine("Enter your image path (*.jpg): ");
        controllerCommands.setHeader(path);
    }

    public void changePassword() throws ServerConnectionFailedException, PasswordConfirmException, DataNotFoundException, PasswordFormatException, InvalidPasswordException, ServerRespondFailedException, UnknownException, PermissionDeniedException, PasswordHashException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException
    {
        String oldPassword, newPassword, newPasswordConfirm;
        oldPassword = TwitterLog.nextLine("Enter your old password: ");
        newPassword = TwitterLog.nextLine("Enter your new password: ");
        newPasswordConfirm = TwitterLog.nextLine("Confirm your new password: ");

        controllerCommands.changePassword(oldPassword, newPassword, newPasswordConfirm);
    }

    public void changeName() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException
    {
        String name = TwitterLog.nextLine("Enter your new name: ");
        controllerCommands.changeName(name);
    }

    public void changeFamily() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException
    {
        String family = TwitterLog.nextLine("Enter your new family name: ");
        controllerCommands.changeFamily(family);
    }

    public void changeEmail() throws ServerConnectionFailedException, EmailFormatException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException
    {
        String email = TwitterLog.nextLine("Enter your new email: ");
        controllerCommands.changeEmail(email);
    }

    public void changePhoneNumber() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException
    {
        String phoneNumber = TwitterLog.nextLine("Enter your new phone-number: ");
        controllerCommands.changePhoneNumber(phoneNumber);
    }

    public void changeBirthDate() throws DateTimeParseException, ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException
    {
        String date = TwitterLog.nextLine("Enter your new birth-date: (yyyy-mm-dd) ");
        LocalDate localDate = LocalDate.parse(date);

        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        controllerCommands.changeBirthDate(year, month, day);
    }

    public void changeCountry() throws CountryException, ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException
    {
        TwitterLog.println(Country.getInstance().toString());
        String country = TwitterLog.nextLine("Enter your new country: ");
        controllerCommands.changeCountry(country);
    }

    public void changeBio() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException
    {
        String bio = TwitterLog.nextLine("Enter your new bio: ");
        controllerCommands.changeBio(bio);
    }

    public void changeLocation() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException
    {
        String location = TwitterLog.nextLine("Enter your new location: ");
        controllerCommands.changeLocation(location);
    }

    public void changeWebsite() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException
    {
        String website = TwitterLog.nextLine("Enter your new website: ");
        controllerCommands.changeWebsite(website);
    }
}
