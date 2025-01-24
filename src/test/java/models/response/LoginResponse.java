package models.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
public class LoginResponse {
    private String token;
    private String type;
    private String username;

// Getters and Setters
@JsonIgnoreProperties(ignoreUnknown = true)
public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
 
}
