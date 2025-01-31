package tests;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Properties;

import javax.crypto.SecretKey;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import models.request.LoginRequest;
import models.response.LoginResponse;
import services.AuthService;
import utilities.ConfigManager;
import utilities.EncryptDecrypt;


public class AuthTest {
    private AuthService authService;
    
    String encryptedPwd;
    String username;

    @BeforeClass
    public void setup() {
        authService = new AuthService();
    }    

    @Test
    public void testSuccessfulLogin() throws Exception {  
        //ConfigManager.loadProperties();
        ConfigManager configManager = new ConfigManager();
        Properties properties = configManager.readProperties();  // Access properties
        
        String secretKeyBase64 = properties.getProperty("secretKey");
        SecretKey secretKey;
        if (secretKeyBase64 != null) {
            byte[] secretKeyBytes = Base64.getDecoder().decode(secretKeyBase64);
            secretKey = EncryptDecrypt.getKeyFromBytes(secretKeyBytes);
        } else {
            secretKey = EncryptDecrypt.generateKey();
            properties.setProperty("secretKey", Base64.getEncoder().encodeToString(secretKey.getEncoded()));
            // Save the properties file with the new secret key
        }

        username = properties.getProperty("username"); 
        //encryptedPwd = properties.getProperty("password");       
        String plainTextPwd = "reka123";
        encryptedPwd = EncryptDecrypt.encrypt(plainTextPwd, secretKey);
        properties.setProperty("password", encryptedPwd);
        
        // Save properties to file
        try (FileOutputStream output = new FileOutputStream("config.properties")) {
            properties.store(output, "Updated password");
        }
            
        // Load encrypted password from properties and decrypt
        try (FileInputStream input = new FileInputStream("config.properties")) {
           properties.load(input);
        }

        String savedEncryptedPwd = properties.getProperty("password");
        String decryptedPwd = EncryptDecrypt.decrypt(savedEncryptedPwd, secretKey);
        System.out.println("Decrypted Password: " + decryptedPwd);
        
        // Ensure the username and password are not null
        if (username == null || encryptedPwd == null) {
            throw new IllegalArgumentException("Username or Password not found in properties file");
        }
       
        /*
        encryptedPwd = EncryptDecrypt.encrypt("reka123", secretKey);
        System.out.println("Encrypted Password: " + encryptedPwd);

        String decryptedPwd = EncryptDecrypt.decrypt(encryptedPwd, secretKey);
        System.out.println("Decrypted Password: " + decryptedPwd);
        */

        //LoginRequest request = new LoginRequest(username, EncryptDecrypt.decrypt(encryptedPwd,secretKey));
        LoginRequest request = new LoginRequest(username, EncryptDecrypt.decrypt(savedEncryptedPwd,secretKey));
        Response response = authService.login(request);
        // Print response body to debug
        System.out.println("Response Body: " + response.getBody().asString());
        assertEquals(200, response.getStatusCode());
        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertNotNull(loginResponse.getToken());
        System.out.println("Received token: " + loginResponse.getToken());
    }

}

