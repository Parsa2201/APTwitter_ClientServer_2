package com.twitter.client.view;

import com.twitter.client.controller.ControllerCommands;
import com.twitter.client.controller.Data;
import com.twitter.client.controller.Program;
import com.twitter.entities.exception.EmailOrPhoneRequiredException;
import com.twitter.entities.exception.TwitException;
import com.twitter.entities.exception.io.server.DatabaseFailedException;
import com.twitter.entities.exception.io.server.ServerConnectionFailedException;
import com.twitter.entities.exception.io.server.ServerInvalidObjectException;
import com.twitter.entities.exception.io.server.ServerRespondFailedException;
import com.twitter.entities.exception.user.CountryException;
import com.twitter.entities.exception.user.email.EmailFormatException;
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

    public void signUp() throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, PasswordHashException
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
        LocalDate localDate;
        try
        {
            localDate = LocalDate.parse(date);
        }
        catch (DateTimeParseException e)
        {
            TwitterLog.println("Wrong Date format!");
            return;
        }

        year = localDate.getYear();
        month = localDate.getMonthValue();
        day = localDate.getDayOfMonth();

        try
        {
            controllerCommands.signUp(userName, name, family, email, phoneNumber, password, passwordConfirm, country, year, month, day);
        } catch (PasswordConfirmException e)
        {
            // TODO: handle the exceptions
        }catch (CountryException e)
        {
            TwitterLog.printlnError("Wrong Country!");
        } catch (EmailFormatException e)
        {
            TwitterLog.printlnError("Wrong Email Format!");
        } catch (PasswordFormatException e)
        {
            TwitterLog.printlnError("Wrong Password Format!");
        } catch (EmailOrPhoneRequiredException e)
        {
            TwitterLog.printlnError("Email or Phone Number is required!");
        }
    }

    public void signIn() throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, PasswordHashException
    {
        String username, password;

        username = TwitterLog.nextLine("Username: ");
        password = TwitterLog.nextLine("Password: ");


        User user = controllerCommands.signIn(username, password);

        TwitterLog.println("You successfully logged in as : " + user.getName() + " " + user.getFamily());
        Data.getInstance().setUser(user);

        Data.getInstance().setProgramState(ProgramState.MAIN_MENU);
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
        if(!user.getBio().toString().equals(""))
            TwitterLog.println("Bio: " + user.getBio());
        if(!user.getLocation().equals(""))
            TwitterLog.println("Location: " + user.getLocation());
    }
}
