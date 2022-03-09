package com.zenofung.common.utils;

import cn.hutool.Hutool;
import cn.hutool.crypto.SecureUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.security.provider.MD5;

public class Md5 {

    public static void main(String[] args) {
        BCryptPasswordEncoder pwdEncoder=new BCryptPasswordEncoder();
        // 生成密文
        String encode = pwdEncoder.encode("123456+redId");
        System.out.println("encode = " + encode);
        // 生成密文
        String encode1 = pwdEncoder.encode("123456");
        System.out.println("encode1 = " + encode1);
        // 验证密文
        boolean b = pwdEncoder.matches("123456", encode);
        System.out.println("b = " + b);
        // 验证密文
        boolean b1 = pwdEncoder.matches("123456", encode1);
        System.out.println("b1 = " + b1);
    }
}
