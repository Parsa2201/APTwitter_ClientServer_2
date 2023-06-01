package com.twitter.client.view;

public enum Option
{
    BACK,
    SIGN_UP,
    SIGN_IN,
    SIGN_OUT,
    SHOW_USER_INFO,
    CHANGE_USER_INFO,
    SET_AVATAR,
    SET_HEADER,
    CHANGE_PASSWORD,
    CHANGE_NAME,
    CHANGE_FAMILY,
    CHANGE_EMAIL,
    CHANGE_PHONE_NUMBER,
    CHANGE_BIRTH_DATE,
    CHANGE_COUNTRY,
    CHANGE_BIO,
    CHANGE_LOCATION,
    CHANGE_WEBSITE,
    ;


    @Override
    public String toString()
    {
        return switch (this)
        {
            case BACK -> "Back";
            case SIGN_IN -> "SignIn";
            case SIGN_UP -> "SignUP";
            case SIGN_OUT -> "SignOut";
            case SHOW_USER_INFO -> "Show my user information";
            case CHANGE_USER_INFO -> "Change my user information";
            case SET_AVATAR -> "Change my avatar";
            case SET_HEADER -> "Change my header";
            case CHANGE_PASSWORD -> "Change my password";
            case CHANGE_NAME -> "Change my name";
            case CHANGE_FAMILY -> "Change my family";
            case CHANGE_EMAIL -> "Change my email";
            case CHANGE_PHONE_NUMBER -> "Change my phone-number";
            case CHANGE_BIRTH_DATE -> "Change my birth-date";
            case CHANGE_COUNTRY -> "Change my country";
            case CHANGE_BIO -> "Change my bio";
            case CHANGE_LOCATION -> "Change my location";
            case CHANGE_WEBSITE -> "Change my website";
        };
    }
}
