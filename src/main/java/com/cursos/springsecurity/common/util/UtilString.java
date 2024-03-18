package com.cursos.springsecurity.common.util;

public class UtilString {

    public static String emailConvertMask(String email) {
        int centerCharacter = email.indexOf("@");
        int startCharacter = centerCharacter / 2;
        for (int i = startCharacter; i <= centerCharacter; i++) {
            email = email.substring(0, i - 1) + "*" + email.substring(i);
        }
        return email;
    }

}
