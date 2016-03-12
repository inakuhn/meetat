package de.android.meetat.util;

/**
 * Created by raina on 12.03.2016.
 */
public enum Navigation {
    //    // update the main content by replacing fragments
        /*<item>Login</item>
        <item>Search</item>
        <item>add reminder</item>
        <item>My reminders</item>
        <item>Configuration</item>*/
    Login, Search, addReminder, MyReminders, Configuration;

    public static Navigation fromInteger(int x) {
        switch (x) {
            case 0:
                return Login;
            case 1:
                return Search;
            case 2:
                return addReminder;
            case 3:
                return MyReminders;
            case 4:
                return Configuration;
        }
        return null;
    }
    public static int fromEnum(Navigation x) {
        switch (x) {
            case Login:
                return 0;
            case Search:
                return 1;
            case addReminder:
                return 2;
            case MyReminders:
                return 3;
            case Configuration:
                return 4;
        }
        return -1;
    }

    }