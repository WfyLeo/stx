package com.ctx.exchange.util;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSAUtil {
    private static final String ALGORITHM = "RSA";
    // 这是后端生成的私钥
    private static final String PRIVATE_KEY =
            "-----BEGIN PRIVATE KEY-----\n"
                    + "MIIEvQIBADANBgkqhkiG9w0BAQEFAASC...\n" // 替换为有效的私钥内容
                    + "-----END PRIVATE KEY-----";

    public static String decrypt(String encryptedPassword) throws Exception {
        // 打印收到的加密字符串，检查其格式是否正确
        System.out.println("Received encrypted password: " + encryptedPassword);

        String privateKeyPEM = PRIVATE_KEY.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").replaceAll("\\s+", "");
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] originalBytes = cipher.doFinal(decodedBytes);
        return new String(originalBytes, "UTF-8");
    }
}
