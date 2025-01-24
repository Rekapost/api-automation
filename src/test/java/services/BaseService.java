package services;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class BaseService {
    static {
        RestAssured.filters(new RequestLoggingFilter());
    }

    protected RequestSpecification requestSpec;

    protected BaseService() {
        this.requestSpec = RestAssured.given()
            .contentType(ContentType.JSON);
    }
      // Example method to send GET request (You can add more common methods like POST, PUT, DELETE, etc.)
      public RequestSpecification getRequestSpec() {
        return this.requestSpec;
    }
}
