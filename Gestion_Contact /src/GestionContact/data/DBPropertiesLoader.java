package GestionContact.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertiesLoader {

    public static Properties loadPoperties(String pName) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream propInputStream = loader.getResourceAsStream(pName);
        Properties properties = new Properties();
        properties.load(propInputStream);
        return properties;
    }




}
