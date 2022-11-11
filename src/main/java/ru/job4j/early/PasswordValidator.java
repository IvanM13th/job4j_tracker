package ru.job4j.early;

public class PasswordValidator {
    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        char[] array = password.toCharArray();
        if (!hasUpperCaseCheck(array)) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        if (!hasLowerCaseCheck(array)) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        if (!hasDigitCheck(array)) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!hasSymbolCheck(password)) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
        if (noSubtextCheck(password)) {
            throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
        }
        return password;
    }

    public static boolean hasUpperCaseCheck(char[] array) {
        boolean upperCase = false;
        for (char x : array) {
            if (Character.isUpperCase(x)) {
                upperCase = true;
                break;
            }
        }
        return upperCase;
    }

    public static boolean hasLowerCaseCheck(char[] array) {
        boolean lowerCase = false;
        for (char x : array) {
            if (Character.isLowerCase(x)) {
                lowerCase = true;
                break;
            }
        }
        return lowerCase;
    }

    public static boolean hasDigitCheck(char[] array) {
        boolean hasDigit = false;
        for (char x : array) {
            if (Character.isDigit(x)) {
                hasDigit = true;
                break;
            }
        }
        return hasDigit;
    }

    public static boolean hasSymbolCheck(String password) {
        boolean hasSymbol = false;
        for (int i = 0; i < password.length(); i++) {
            int code = password.codePointAt(i);
            if (code == 36 || code == 95) {
                hasSymbol = true;
                break;
            }
        }
        return hasSymbol;
    }

    public static boolean noSubtextCheck(String password) {
        boolean subtextContains;
        String toLowCasePass = password.toLowerCase();
        String[] subtext = {"qwerty", "12345", "password", "admin", "user"};
        for (String s : subtext) {
             subtextContains = toLowCasePass.contains(s);
            if (subtextContains) {
                return true;
            }
        }
        return false;
    }

}
