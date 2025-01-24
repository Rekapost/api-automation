package utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    public static Properties props;
    private Properties properties;
   
/*   
    public static void loadProperties() {
    props = new Properties();
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }
*/
        // Constructor initializes properties
        public ConfigManager() {
            properties = new Properties();
        }

    public Properties readProperties() throws IOException{
        FileInputStream stream = null;
        try{
        stream = new FileInputStream("./src/test/resources/config.properties");
        properties.load(stream);
        } catch (IOException e) {
            throw new IOException("Failed to load properties from the file", e);
        }finally {
            if (stream!= null) {
                stream.close();
            }
        }
        return properties;
    }
     // Getter for properties to access them later
     public Properties getProperties() {
        return properties;
    }

}
