package com.example.BankingManagementSystem.Encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;
import java.util.Base64;

@Component
public class EncryptionUtil {

    // Example of a simple key for AES encryption (16 bytes for AES-128)
    private final String SECRET_KEY = "EncryptCredntial"; // 16-byte key for AES-128

    // Encrypt a string using AES encryption
    public String encrypt(String data) throws Exception {
        // Convert the SECRET_KEY string into a SecretKeySpec (AES key)
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        // Initialize the cipher with AES encryption mode and PKCS5Padding
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Encrypt the data and return it as a Base64-encoded string
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // Decrypt a string using AES decryption
    public String decrypt(String encryptedData) throws Exception {
        // Convert the SECRET_KEY string into a SecretKeySpec (AES key)
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        // Initialize the cipher with AES decryption mode and PKCS5Padding
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);

        // Decode the Base64-encoded string and decrypt the data
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedBytes);

        // Return the decrypted data as a string
        return new String(decryptedData);
    }

    // Optional: Method to generate a random AES key (for testing purposes)
    public String generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // AES-128 encryption
        SecretKey secretKey = keyGenerator.generateKey();

        // Convert the key to a Base64 string for easy storage/retrieval
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
}
