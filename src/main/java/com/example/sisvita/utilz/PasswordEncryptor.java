package com.example.sisvita.utilz;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordEncryptor {
    public static void main(String[] args) {
        String password = "123";
        String encrupted = BCrypt.hashpw(password, BCrypt.gensalt());

        System.out.println(encrupted);
    }
}
