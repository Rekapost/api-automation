package services;
import io.restassured.response.Response;
public class AuthService extends BaseService {

    // Constructor that can be used to initialize anything if needed
    public AuthService() {
        super();  // This calls the constructor of BaseService
    }


  // Login method to send POST request with the payload
    public Response login(Object payload) {
       
       //return postRequest("/api/auth/login", payload);
        return this.getRequestSpec()
                .baseUri("https://ebc323ee-64a8-46ae-9459-3fc905ea9852.mock.pstmn.io")
                .header("Content-type","application/json")
                .body(payload)  // Set the payload in the request body
                .post("/api/auth/login");  // Send the POST request to the endpoint
        
    //http://127.0.0.1:3658/m1/793930-772461-default/api/auth/login
    /*return given()
                .baseUri("https://postman-echo.com")  // Replace with your real authentication service
                .header("Content-type", "application/json")
                .body(payload)  // Set the payload in the request body
                .log().all()  // Log the request for debugging
        .when()
                .post("/post")  // Replace with your real login endpoint
        .then()
                .log().all()  // Log the response for debugging
                .extract().response();  // Extract the response
    */
    }
}


