package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties = new Properties();

    public static void load(String env) {
        try {
        	
            FileInputStream fis = new FileInputStream("config/" + env + ".properties");
            
            properties.load(fis);
        }
        catch (IOException e) {
        	
            throw new RuntimeException("Failed to load config file for env: " + env, e);
        }
    }

    public static String get(String key) {
    	
        return properties.getProperty(key);
        
    }
}
