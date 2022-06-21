package com.example.donoroncall;

import android.widget.TextView;

import java.util.regex.Pattern;

public class validation {

    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    ".{6,}" +        // at least 6 characters
                    "$");


    public static boolean validatePassword(TextView texPassword) {
        String passwordInput = texPassword.getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (passwordInput.isEmpty()) {
            texPassword.setError("Field can not be empty");
            return false;
        }
        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            texPassword.setError("Password is too weak make sure to use upper case letter,1 digit and 6 characters");
            return false;
        } else {
            texPassword.setError(null); //if no error
            return true;
        }
    }


    public static boolean validatename(TextView textname) {
        String nameInput = textname.getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (nameInput.isEmpty()) {
            textname.setError("Name can not be empty");
            return false;
        }
        else {
            textname.setError(null); //if no error
            return true;
        }
    }
    public static boolean validatePhone(TextView textphone) {
        String phoneInput = textphone.getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (phoneInput.isEmpty()) {
            textphone.setError("phone number can not be empty");
            return false;
        }
        if(phoneInput.length()<10){
            textphone.setError("phone number can not be less than 10 digits");
            return false;
        }
        else {
            textphone.setError(null); //if no error
            return true;
        }}
    public static boolean validateDate(TextView date ) {
        String dateInput = date.getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (dateInput.isEmpty()) {
            date.setError("Birthdate can not be empty");
            return false;
        }
        else {
            date.setError(null); //if no error
            return true;
        }}

    public static boolean validateEmail(TextView mail ) {
        String mailInput = mail.getText().toString().trim();

        if (mailInput.isEmpty()) {
            mail.setError("Email. can not be empty");
            return false;
        }
        int x = mail.getText().toString().indexOf('@');
        if ( x==-1 ) return false;
             return true;

        }

}
