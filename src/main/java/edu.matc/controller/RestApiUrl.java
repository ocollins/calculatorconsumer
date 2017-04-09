package edu.matc.controller;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by student on 4/8/17.
 * Reads Rest API properties file and retreives rest url
 */
public class RestApiUrl {
    private final Logger logger = Logger.getLogger(this.getClass());
    /**
     * Gets rest url.
     * @return the rest url
     */
    public String getRestUrl() {
        Properties properties = new Properties();
        String url = null;
        InputStream propertiesStream = this.getClass().getResourceAsStream("/home/student/EnterpriseRepos/calculatorconsumer/src/main/resources/restapi.properties");
        try {
            properties.load(propertiesStream);
            url = properties.getProperty("rest.api.url");

            logger.info("REST API URL " + url);
        } catch (IOException iOException) {
            logger.info("Cannot load the properties file");
            iOException.printStackTrace();
        } catch (Exception exception) {
            logger.info("Error loading properties file");
            exception.printStackTrace();
        }
        return url;

    }
}
