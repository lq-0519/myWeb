package lq.myWeb.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author LQ
 * @create 2020-06-07 11:44
 */
public class PasswordUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encoder(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String encoder = encoder("123");
        System.out.println(encoder);
    }
}
