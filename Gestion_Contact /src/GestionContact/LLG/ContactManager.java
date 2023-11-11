package GestionContact.LLG;

import java.util.regex.Pattern;

public class ContactManager {
    public static boolean validatePhoneNumber(String number) {
        String pattern = "^(06|07|05)\\d{8}$";
        return number.matches(pattern);
    }


    public static boolean validateEmail(String email) {
        String pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(pattern);
    }



}
