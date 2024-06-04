package com.example.provaoficial2;

public class ValidationHelper {

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.length() == 10 && phone.matches("\\d+");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }

    public static boolean isValidZipCode(String zipCode) {
        // Verifica se o CEP tem o formato correto: 12345-678
        return zipCode != null && zipCode.matches("\\d{5}-\\d{3}");
    }

}

