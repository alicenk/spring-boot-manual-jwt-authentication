package com.manual.authentication.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoderUtil {

    public static String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}