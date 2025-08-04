package com.lordscave.shared;

public class Alert {
    public static String getClientGenericErrorMessage() {
        return "An internal server error occurred. Please try again later.";
    }
    public static String getServerGenericErrorMessage() {
        return "";
    }

    public static String getServerConfigFileException() {
        return "Not able to read db config file:";
    }

    public static String getServerMessageNotSaved() {
        return "Message not saved in db:";
    }


}
