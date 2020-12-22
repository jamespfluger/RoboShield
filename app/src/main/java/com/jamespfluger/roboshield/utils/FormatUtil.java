package com.jamespfluger.roboshield.utils;

public class FormatUtil {
    public static String formatPhoneNumber(String input) {
        if (input.length() == 0 || input.length() > 11) {
            return input;
        } else if (input.length() == 10) {
            return String.format("(%s) %s-%s", input.substring(0, 3), input.substring(3, 6), input.substring(6, 10));
        } else if (input.length() == 11) {
            return String.format("%s (%s) %s-%s", input.substring(0, 1), input.substring(1, 4), input.substring(4, 7), input.substring(7, 11));
        } else if (input.length() == 0) {
            return input;
        } else {
            StringBuilder formattedPhoneNumber = new StringBuilder();
            formattedPhoneNumber.append("(");

            if (input.length() <= 3) {
                formattedPhoneNumber.append(input);
                return formattedPhoneNumber.toString();
            }
            formattedPhoneNumber.append(input.substring(0, 3));
            formattedPhoneNumber.append(") ");

            if (input.length() <= 6) {
                formattedPhoneNumber.append(input.substring(3));
                return formattedPhoneNumber.toString();
            }
            formattedPhoneNumber.append(input.substring(3, 6));
            formattedPhoneNumber.append('-');
            formattedPhoneNumber.append(input.substring(6));

            return formattedPhoneNumber.toString();
        }
    }
}
