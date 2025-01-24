package utilities;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptDecrypt{
    private static final String SECRET_KEY = "MySecretKey12345"; // 16 characters for AES-128
    private static final String ALGORITHM = "AES";
   
    // Encrypt a plain text using AES
    public static String encrypt(String strToEncrypt) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");  
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(strToEncrypt.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt an encrypted text using AES
   public static String decrypt(String strToDecrypt) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");  
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(strToDecrypt);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }


    /*public static String decrypt(String strToDecrypt) throws Exception {
        // Key and IV must match what was used during encryption
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");  
        byte[] iv = "RandomInitVector".getBytes(); // Example 16-byte IV (use the actual IV from encryption)
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
    
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
    
        // Decode Base64-encoded encrypted data
        byte[] decodedBytes = Base64.getDecoder().decode(strToDecrypt);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
    
        return new String(decryptedBytes);
    }
  */
  
  public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

    // Generate IV
    byte[] iv = new byte[16];
    new SecureRandom().nextBytes(iv);
    IvParameterSpec ivSpec = new IvParameterSpec(iv);

    // Initialize cipher and encrypt
    cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

    // Combine IV and ciphertext
    byte[] ivAndCiphertext = new byte[iv.length + encryptedBytes.length];
    System.arraycopy(iv, 0, ivAndCiphertext, 0, iv.length);
    System.arraycopy(encryptedBytes, 0, ivAndCiphertext, iv.length, encryptedBytes.length);

    // Encode to Base64 and return
    return Base64.getEncoder().encodeToString(ivAndCiphertext);
}


public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
    byte[] ivAndCiphertext = Base64.getDecoder().decode(encryptedText);

    // Validate input length (IV + ciphertext)
    if (ivAndCiphertext.length < 16) {
        throw new IllegalArgumentException("Encrypted data is too short to contain IV and ciphertext.");
    }

    // Extract IV and ciphertext
    byte[] iv = new byte[16];
    System.arraycopy(ivAndCiphertext, 0, iv, 0, 16);
    byte[] ciphertext = new byte[ivAndCiphertext.length - 16];
    System.arraycopy(ivAndCiphertext, 16, ciphertext, 0, ciphertext.length);

    // Initialize cipher and decrypt
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    IvParameterSpec ivSpec = new IvParameterSpec(iv);
    cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
    byte[] decryptedBytes = cipher.doFinal(ciphertext);

    return new String(decryptedBytes);
}

public static SecretKey generateKey() throws Exception {
    KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
    keyGen.init(128);
    return keyGen.generateKey();
}

public static SecretKey getKeyFromBytes(byte[] keyBytes) {
    return new SecretKeySpec(keyBytes, ALGORITHM);
}

    // Generate a SecretKey for AES encryption
   /*  public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // AES supports 128, 192, and 256-bit key sizes
        return keyGenerator.generateKey();
    }
    */
/* 
    // Encrypt the text
    String encryptedPwd = encrypt("3admin3");
    System.out.println("Encrypted Text: " + encryptedPwd);

    // Decrypt the text
    String decryptedText = decrypt(encryptedText, secretKey);
    System.out.println("Decrypted Text: " + decryptedText);

    Original Text: Hello, AES Encryption!
    Encrypted Text: e1lUw83+K2+ndQURtGFlEw==
    Decrypted Text: Hello, AES Encryption!

    String key = "1234567890123456"; // 16 characters for a 128-bit key
    SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
*/ 
}