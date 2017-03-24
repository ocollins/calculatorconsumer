package edu.matc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.CaloriesCalculator.Response;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;

import java.sql.ResultSet;

import static org.junit.Assert.*;

/**
 * Created by student on 3/17/17.
 */
public class RestActivityTest {
    private final Logger logger = Logger.getLogger(this.getClass());
    Client client;
    String url;

    @Before
    public void setup() {
        client = ClientBuilder.newClient();
        url = "http://localhost:8080/CaloriesCalculator/activities";
    }


    @Test
    public void getAllActivities() throws Exception {
        WebTarget target = client.target(url);
        String response = target.request().get(String.class);
        logger.info("Returning activities " + response);
    }

    @Test
    public void getCaloriesBurnedText() throws Exception {
        url = url + "/text/1/70/1.5";
        WebTarget target = client.target(url);
        String response = target.request().get(String.class);
        logger.info("Returning calories " + response);

    }

    @Test
    public void getCaloriesBurnedJSON() throws Exception {
        url = url + "/json/1/70/1.5";
        WebTarget target = client.target(url);
        String response = target.request().get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Response obj = mapper.readValue(response, Response.class);
        //String normalView = mapper.writerWithView(Views.Normal.class).writeValueAsString(staff);

        logger.info("Returning calories " + response);
    }

    @Test
    public void getCaloriesBurnedHTML() throws Exception {

    }

}
