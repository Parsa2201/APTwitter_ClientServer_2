package com.twitter.client.view;

import com.twitter.client.controller.ControllerCommands;
import com.twitter.client.controller.Data;
import com.twitter.entities.exception.EmailOrPhoneRequiredException;
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
import com.twitter.entities.tweet.*;
import com.twitter.entities.user.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Command
{
    private final ControllerCommands controllerCommands;

    public Command()
    {
        controllerCommands = new ControllerCommands();
    }

    public void signUp() throws DateTimeParseException, ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, PasswordHashException, PasswordConfirmException, CountryException, EmailFormatException, DataNotFoundException, PasswordFormatException, EmailOrPhoneRequiredException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException
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

        TwitterLog.println("You successfully signed up as : " + name + " " + family);
    }

    public void signIn() throws ServerConnectionFailedException, ServerRespondFailedException, DatabaseFailedException, ServerInvalidObjectException, PasswordHashException, DataNotFoundException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException
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

        if (user.getBio() != null && !user.getBio().toString().equals(""))
            TwitterLog.println("Bio: " + user.getBio());

        if (user.getLocation() != null && !user.getLocation().equals(""))
            TwitterLog.println("Location: " + user.getLocation());
    }

    public void signOut() throws PermissionDeniedException
    {
        controllerCommands.signOut();
    }

    public void setAvatar() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, FileNotExistException, FileNotImageException, DatabaseFailedException, ImageSizeException, UnknownException, InvalidPasswordException, FileSizeException, PermissionDeniedException, TextTooLongException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        String path = TwitterLog.nextLine("Enter your image path (*.jpg): ");
        controllerCommands.setAvatar(path);

        TwitterLog.println("Your avatar successfully changed.");
    }

    public void setHeader() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, FileNotExistException, FileNotImageException, DatabaseFailedException, ImageSizeException, UnknownException, InvalidPasswordException, FileSizeException, PermissionDeniedException, TextTooLongException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        String path = TwitterLog.nextLine("Enter your image path (*.jpg): ");
        controllerCommands.setHeader(path);

        TwitterLog.println("Your header successfully changed.");
    }

    public void changePassword() throws ServerConnectionFailedException, PasswordConfirmException, DataNotFoundException, PasswordFormatException, InvalidPasswordException, ServerRespondFailedException, UnknownException, PermissionDeniedException, PasswordHashException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        String oldPassword, newPassword, newPasswordConfirm;
        oldPassword = TwitterLog.nextLine("Enter your old password: ");
        newPassword = TwitterLog.nextLine("Enter your new password: ");
        newPasswordConfirm = TwitterLog.nextLine("Confirm your new password: ");

        controllerCommands.changePassword(oldPassword, newPassword, newPasswordConfirm);

        TwitterLog.println("Your password successfully changed.");
    }

    public void changeName() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        String name = TwitterLog.nextLine("Enter your new name: ");
        controllerCommands.changeName(name);

        TwitterLog.println("Your name successfully changed to " + name + ".");
    }

    public void changeFamily() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        String family = TwitterLog.nextLine("Enter your new family name: ");
        controllerCommands.changeFamily(family);

        TwitterLog.println("Your family name successfully changed to " + family + ".");
    }

    public void changeEmail() throws ServerConnectionFailedException, EmailFormatException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        String email = TwitterLog.nextLine("Enter your new email: ");
        controllerCommands.changeEmail(email);

        TwitterLog.println("Your email successfully changed to " + email + ".");
    }

    public void changePhoneNumber() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        String phoneNumber = TwitterLog.nextLine("Enter your new phone-number: ");
        controllerCommands.changePhoneNumber(phoneNumber);

        TwitterLog.println("Your phone-number successfully changed to " + phoneNumber + ".");
    }

    public void changeBirthDate() throws DateTimeParseException, ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        String date = TwitterLog.nextLine("Enter your new birth-date: (yyyy-mm-dd) ");
        LocalDate localDate = LocalDate.parse(date);

        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        controllerCommands.changeBirthDate(year, month, day);

        TwitterLog.println("Your birth-date successfully changed to " + date + ".");
    }

    public void changeCountry() throws CountryException, ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        TwitterLog.println(Country.getInstance().toString());
        String country = TwitterLog.nextLine("Enter your new country: ");
        controllerCommands.changeCountry(country);

        TwitterLog.println("Your country successfully changed to " + country + ".");
    }

    public void changeBio() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        String bio = TwitterLog.nextLine("Enter your new bio: ");
        controllerCommands.changeBio(bio);

        TwitterLog.println("Your bio successfully changed.");
    }

    public void changeLocation() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        String location = TwitterLog.nextLine("Enter your new location: ");
        controllerCommands.changeLocation(location);

        TwitterLog.println("Your location successfully changed.");
    }

    public void changeWebsite() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, DatabaseFailedException, ServerInvalidObjectException, ServerInvalidCommandException
    {
        String website = TwitterLog.nextLine("Enter your new website: ");
        controllerCommands.changeWebsite(website);

        TwitterLog.println("Your website successfully changed to " + website + ".");
    }

    public void sendTweet() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, FileNotExistException, ServerInvalidCommandException, FileNotImageException, DatabaseFailedException, ImageSizeException, UnknownException, InvalidPasswordException, PermissionDeniedException, FileSizeException, TextTooLongException, ServerInvalidObjectException
    {
        String text = TwitterLog.nextLine("Enter your tweet text: ");
        String answer = TwitterLog.nextLine("Do you want your tweet to have a picture? (y/n): ");
        if (answer.equalsIgnoreCase("y"))
        {
            String path = TwitterLog.nextLine("Enter your image path (*.jpg): ");
            controllerCommands.sendTweet(text, path);
        } else if (answer.equalsIgnoreCase("n"))
            controllerCommands.sendTweet(text, null);
        else
            TwitterLog.printlnError("Invalid input!");

        TwitterLog.println("Your tweet successfully sent.");
    }

    public void sendRetweet() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException
    {
        String tweetId = TwitterLog.nextLine("Enter the tweet id: ");
        controllerCommands.sendRetweet(tweetId);

        TwitterLog.println("Your retweet successfully sent.");
    }

    public void sendQuote() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, FileNotExistException, ServerInvalidCommandException, FileNotImageException, DatabaseFailedException, ImageSizeException, UnknownException, InvalidPasswordException, PermissionDeniedException, FileSizeException, TextTooLongException, ServerInvalidObjectException
    {
        String tweetId = TwitterLog.nextLine("Enter the tweet id: ");
        String text = TwitterLog.nextLine("Enter your quote text: ");
        String answer = TwitterLog.nextLine("Do you want your quote to have a picture? (y/n): ");
        if (answer.equalsIgnoreCase("y"))
        {
            String path = TwitterLog.nextLine("Enter your image path (*.jpg): ");
            controllerCommands.sendQuote(tweetId, text, path);
        } else if (answer.equalsIgnoreCase("n"))
            controllerCommands.sendQuote(tweetId, text, null);
        else
            TwitterLog.printlnError("Invalid input!");

        TwitterLog.println("Your quote successfully sent.");
    }

    public void likeTweet() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException
    {
        String tweetId = TwitterLog.nextLine("Enter the tweet id: ");
        controllerCommands.likeTweet(tweetId);

        TwitterLog.println("The tweet successfully liked.");
    }

    public void dislikeTweet() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException
    {
        String tweetId = TwitterLog.nextLine("Enter the tweet id: ");
        controllerCommands.dislikeTweet(tweetId);

        TwitterLog.println("The tweet successfully disliked.");
    }

    public void showTimeLine() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException
    {
        TimeLine timeLine = controllerCommands.showTimeLine();
        for (BaseTweet baseTweet : timeLine)
        {
            Tweet tweet = baseTweet.toTweet();


            TwitterLog.println("_________________________________");
            // if the baseTweet is a retweet, the owner of the baseTweet is the reTweeter
            TwitterLog.println(baseTweet.getOwner().getName() + " " + baseTweet.getOwner().getFamily());
            TwitterLog.printlnNested(tweet.getTextContent().toString());
            TwitterLog.println("Likes: " + tweet.getLikeCount());
            TwitterLog.println("Retweets: " + tweet.getRetweetCount());
            TwitterLog.println("Quotes: " + tweet.getQuoteCount());
            TwitterLog.println("Date: " + tweet.getDate().toString());


            TwitterLog.println("Quotes:");
            for (Quote quote : tweet.getQuotes())
            {
                TwitterLog.startNest();
                TwitterLog.println("\n" + quote.getQuotedBy().getName() + " " + quote.getQuotedBy().getFamily());
                TwitterLog.printlnNested(quote.getTextContent().toString());
                TwitterLog.println(quote.getDate().toString());
                TwitterLog.endNest();

                TwitterLog.println("#  #  #  #  #  #  #  #  #  #  #  #  #  #  #");
            }

            TwitterLog.println("Replies:");
            for (Reply reply : tweet.getReplies())
            {
                TwitterLog.startNest();
                TwitterLog.println("\n" + reply.getReplier().getName() + " " + reply.getReplier().getFamily());
                TwitterLog.printlnNested(reply.getTextContent().toString());
                TwitterLog.println(reply.getDate().toString());
                TwitterLog.endNest();

                TwitterLog.println("#  #  #  #  #  #  #  #  #  #  #  #  #  #  #");
            }
        }
    }

    public void showMyFollowers() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException
    {
        Followers followers = controllerCommands.showFollowers();
        for(MiniUser miniUser : followers)
            TwitterLog.println(miniUser.getName() + miniUser.getFamily());
    }

    public void showMyFollowings() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException
    {
        Followings followings = controllerCommands.showFollowings();
        for(MiniUser miniUser : followings)
            TwitterLog.println(miniUser.getName() + miniUser.getFamily());
    }

    public void showSomeoneFollowers() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException
    {
        String username = TwitterLog.nextLine("Enter the username: ");
        Followers followers = controllerCommands.showFollowers(username);
        for(MiniUser miniUser : followers)
            TwitterLog.println(miniUser.getName() + miniUser.getFamily());
    }

    public void showSomeoneFollowings() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException
    {
        String username = TwitterLog.nextLine("Enter the username: ");
        Followings followings = controllerCommands.showFollowings(username);
        for(MiniUser miniUser : followings)
            TwitterLog.println(miniUser.getName() + miniUser.getFamily());
    }

    public void follow() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException
    {
        String username = TwitterLog.nextLine("Enter the username you want to follow: ");
        controllerCommands.follow(username);
    }

    public void unfollow() throws ServerConnectionFailedException, DataNotFoundException, ServerRespondFailedException, UnknownException, InvalidPasswordException, PermissionDeniedException, TextTooLongException, ServerInvalidCommandException, DatabaseFailedException, ServerInvalidObjectException
    {
        String username = TwitterLog.nextLine("Enter the username you want to unfollow: ");
        controllerCommands.unfollow(username);
    }
}
