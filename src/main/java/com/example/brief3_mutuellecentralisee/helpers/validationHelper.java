package com.example.brief3_mutuellecentralisee.helpers;

public class validationHelper {
    // Valider la taille :
    public static boolean IsValidLength(String value,int length){
        return value.length()<=length;
    }

    // Valider CIN:
    public static boolean IsValidCIN(String value){
        return value.matches("^[a-zA-Z]{2}[0-9]{6}$");
    }

    // Valider Passport :
    public static boolean IsValidPASSPORT(String value){
        return value.matches("^[a-zA-Z]{2}[0-9]{7}$");
    }

    // Valider Phone :
    public static boolean IsValidPhone(String value){
        return value.matches("^(6|7)[0-9]{8}$");
    }

    // Valider Email :
    public static boolean IsValidEmail(String value){
        return value.matches("^[a-zA-Z_][a-zA-Z0-9_]+@[a-zA-Z_][a-zA-Z0-9_]+\\.[a-zA-Z]{2,3}$");
    }
    // verifier champ vide :
    public static boolean IsNotEmpty(String value){
        return value.matches("^[a-zA-Z][a-zA-Z0-9_ ]{2,49}$");
    }
    // verifier champ vide :
    public static boolean IsNotEmptyNumber(String value){

        return value.matches("^[0-9]{2,49}$");
    }

}
