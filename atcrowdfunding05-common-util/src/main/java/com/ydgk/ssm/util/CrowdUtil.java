package com.ydgk.ssm.util;

import com.ydgk.ssm.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author kfstart
 * @descrption 此工具类提供了一些Crowd项目中的一些工具方法
 * @create 2020-06-30 9:49
 */
public class CrowdUtil {

    /**
     * 此方法用于判断请求类型是不是ajax请求
     * @return true/false  true 表示是一个ajax请求 false反之
     */
    public static boolean judgeRequestType(HttpServletRequest request){
        //1、获取请求头中的信息 accept 和 X-Requested-With 信息
        String accept = request.getHeader("Accept");
        String header = request.getHeader("X-Requested-With");
        //2、 判断 accept 中是否包含 application/json  或 header 中是否为 XMLHttpRequest
//        if (
//                (accept != null && !"".equals(accept) && accept.contains("application/json"))
//                ||
//                (header != null && ! "".equals(header) && "XMLHttpRequest".equalsIgnoreCase(header))
//            ) {
//            return true;
//        }
//        return false;
        return (accept != null && !"".equals(accept) && accept.contains("application/json"))
                ||
                (header != null && ! "".equals(header) && "XMLHttpRequest".equalsIgnoreCase(header));
    }

    /**
     * 此方法用于对传入的字符串进行 md5 加密
     * @return
     */
    public static String md5(String source){

        // 判断传入的字符串是否有效
        if ( source == null || "".equals(source)) {
            throw new RuntimeException(CrowdConstant.MESSAGE_INVALID_STRING);
        }

        try {
            // 进行加密
            String encryptType = "md5";// 加密方式
            // 获取加密对象  MessageDigest 对象
            MessageDigest messageDigest = MessageDigest.getInstance(encryptType);
            //  获取需要加密字符串的字节数组
            byte[] input = source.getBytes();
            // 5.执行加密
            byte[] output = messageDigest.digest(input);
            // 6.创建 BigInteger 对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);
            // 7.按照 16 进制将 bigInteger 的值转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();

            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
