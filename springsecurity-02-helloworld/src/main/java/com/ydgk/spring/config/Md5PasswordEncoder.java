package com.ydgk.spring.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-09 9:44
 */
//@Component
public class Md5PasswordEncoder implements PasswordEncoder {
    /*
    次方法是用于对明文进行加密的方法
     */
    @Override
    public String encode(CharSequence charSequence) {

        String s = privateEncoder(charSequence);

        return s;
    }

//    public static void main(String[] args) {
//        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
//        String encode = encoder.encode("123123");
//        System.out.println(encode);//4297F44B13955235245B2497399D7A93
//    }


//    public static void main(String[] args) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
////        String encode = bCryptPasswordEncoder.encode("123123");
////        System.out.println("encode = " + encode);
//        // $2a$10$b/1m9cEKlbW1l4Vt6I7gz.GsuVtyMFUxKv2LN8.4ot3olobM0o2.m
//        // $2a$10$1DuQXQLYEIxi8lLtVI1.O..ll.rU7u7g5RPHd2XpZZhtOceAgyafG
//        // $2a$10$ijIEMTfxWD26y07GYdlho.4oVrVvZYnJfs2s8dQTe.nuCCcFq9ip.
//
//        String encodedPassword = "$2a$10$1DuQXQLYEIxi8lLtVI1.O..ll.rU7u7g5RPHd2XpZZhtOceAgyafG";
//
//        boolean matches = bCryptPasswordEncoder.matches("123123", encodedPassword);
//        System.out.println("matches = " + matches);
//    }

    private String privateEncoder(CharSequence charSequence) {
        try {
            String digestName = "md5";

            MessageDigest instance = MessageDigest.getInstance(digestName);

            // charSequence 转换成 String
            String source = charSequence.toString();

            byte[] bytes = source.getBytes();

            byte[] digest = instance.digest(bytes);// 加密

            // 用来表示BigInteger是正值还是负值的变量
            int sigNum = 1;

            BigInteger bigInteger = new BigInteger(sigNum, digest);

            String s = bigInteger.toString(16).toUpperCase();

            return s;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    明文和秘文比较的方法
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {

        // charSequence 明文    s 秘文
        String s1 = privateEncoder(charSequence);

        return Objects.equals(s,s1);
    }
}
