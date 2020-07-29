package com.coding.weddingbook.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class SecurityUtils {

    public static String encryptSha256(String str) {
        String sha = "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            byte[] byteDatas = md.digest();
            StringBuffer sb = new StringBuffer();

            for (byte byteData : byteDatas) {
                sb.append(Integer.toString((byteData&0xff) + 0x100, 16).substring(1));
            }

            sha = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            log.info("Encrypt Error : NoSuchAlgorithmException");
            sha = null;
        }

        return sha;
    }

}
