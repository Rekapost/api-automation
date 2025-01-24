package filters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.filter.FilterContext;

public class LoggingFilter implements io.restassured.filter.Filter {

    // Use SLF4J for logging
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {
        // Log request
        logRequest(requestSpec);

        // Get response
        Response response = ctx.next(requestSpec, responseSpec);

        // Log response
        logResponse(response);

        return response;
    }

    private void logRequest(FilterableRequestSpecification requestSpec) {
        logger.info("Request: {} {}", requestSpec.getMethod(), requestSpec.getURI());
        logger.info("Headers: {}", requestSpec.getHeaders());
    }

    private void logResponse(Response response) {
        logger.info("Response Status: {}", response.getStatusCode());
        logger.info("Response Body: {}", response.getBody().asString());
    }
}
