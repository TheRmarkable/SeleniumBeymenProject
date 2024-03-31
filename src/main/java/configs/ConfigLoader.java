package configs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties;

    public ConfigLoader() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getChromeDriverPath() {
        return properties.getProperty("chromeDriverPath");
    }
    public String getTxtFilePath() {
        return properties.getProperty("txtFilePath");
    }
}
