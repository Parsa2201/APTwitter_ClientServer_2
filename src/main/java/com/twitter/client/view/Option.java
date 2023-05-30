package com.twitter.client.view;

public enum Option
{
    SIGN_IN,
    SIGN_UP,
    SHOW_USER_INFO
    ;


    @Override
    public String toString()
    {
        return switch (this)
        {
            case SIGN_IN -> "SignIn";
            case SIGN_UP -> "SignUP";
            case SHOW_USER_INFO -> "Show my user information";
        };
    }
}
