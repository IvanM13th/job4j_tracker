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
        if (!HasUpperCaseCheck(array)) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        if (!HasLowerCaseCheck(array)) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        if (!HasDigitCheck(array)) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!HasSymbolCheck(password)) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
        if (!NoSubtextCheck(password)) {
            throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
        }
        return password;
    }

    public static boolean HasUpperCaseCheck(char[] array) {
        boolean upperCase = false;
        for (char x : array) {
            if (Character.isUpperCase(x)) {
                upperCase = true;
                break;
            }
        }
        return upperCase;
    }

    public static boolean HasLowerCaseCheck(char[] array) {
        boolean lowerCase = false;
        for (char x : array) {
            if (Character.isLowerCase(x)) {
                lowerCase = true;
                break;
            }
        }
        return lowerCase;
    }

    public static boolean HasDigitCheck(char[] array) {
        boolean hasDigit = false;
        for (char x : array) {
            if (Character.isDigit(x)) {
                hasDigit = true;
                break;
            }
        }
        return hasDigit;
    }

    public static boolean HasSymbolCheck(String password) {
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

    public static boolean NoSubtextCheck(String password) {
        boolean noSubtext = true;
        String toLowCasePass = password.toLowerCase();
        int index = -1;
        String[] subtext = {"qwerty", "12345", "password", "admin", "user"};
        for (String s : subtext) {
            index = toLowCasePass.indexOf(s);
            if (index >= 0) {
                noSubtext = false;
                break;
            }
        }
        return noSubtext;
    }

}
