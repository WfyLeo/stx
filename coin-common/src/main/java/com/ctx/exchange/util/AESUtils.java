package com.ctx.exchange.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public static void main(String[] args) {
        String encryptedPassword = "U2FsdGVkX1/dtU38FYfSjGrl0YVW/Ogygi7t+eid5Ho="; // 确保这是从前端得到的密文
        String secretKey = "5879463157864521"; // 替换为你的密钥，必须是 16 字节
        String iv = "5879463157864521"; // 替换为你的 IV，必须是 16 字节

        try {
            String decryptedPassword = decrypt(encryptedPassword, secretKey, iv);
            System.out.println("解密后的密码: " + decryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decrypt(String encryptedPassword, String secretKey, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes("UTF-8"));

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));

        return new String(decryptedBytes, "UTF-8");
    }
}

