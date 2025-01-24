package tests;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import utilities.EncryptDecrypt;

public class Login{
    @Test
    public void login() {
        given()
            .baseUri("https://postman-echo.com")
            .header("Content-type","application/json")
            .body("{ \"username\": \"rekaharisri@gmail.com\", \"password\": \"****\" }")
        .when()
            .log().all()
            .post("/post")
        .then()
            .log().all()
            .statusCode(200)
            //.time(lessThan(3000L))
            .extract().response(); 
                
    }

    @Test
    public void EncryptedPassword() throws Exception {
        String encryptedPwd = EncryptDecrypt.encrypt("****");
        System.out.println("Encrypted Text: " + encryptedPwd);
    }
    //Encrypted Text: qIfnD4LI9snS29jKRnJwpQ==
}