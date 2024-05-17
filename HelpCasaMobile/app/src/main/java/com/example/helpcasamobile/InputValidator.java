package com.example.helpcasamobile;

import android.text.TextUtils;
import java.util.regex.*;

public class InputValidator {
    private static final String EMAIL_PATTERN_STRING = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_PATTERN_STRING);

    public static boolean isEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private static final String PASSWORD_PATTERN_STRING = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_PATTERN_STRING);

    public static boolean isPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isPhone(String phone) {
        return phone.length()==8 && TextUtils.isDigitsOnly(phone);
    }
}
