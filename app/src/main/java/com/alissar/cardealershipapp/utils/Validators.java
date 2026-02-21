package com.alissar.cardealershipapp.utils;

import java.util.regex.Pattern;

public class Validators {
    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$";
        return Pattern.compile(passwordRegex).matcher(password).matches();
    }
}
