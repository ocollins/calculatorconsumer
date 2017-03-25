package edu.matc.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.CaloriesCalculator.Activities;
import edu.matc.CaloriesCalculator.Activity;
import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        WebTarget target = client.target(url + "/list");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        logger.info("response from the call to REST " + response);


        ObjectMapper objectMapper = new ObjectMapper();
        Activities activities = null;
        Activity activity = null;
        try {
            activities = objectMapper.readValue(response, Activities.class);
            List<Activity> myList = activities.getActivities();
            for(Activity anActivity : myList) {
                logger.info("Activity from the list " + anActivity.getName());
            }
            activity = activities.getActivities().get(0);

        } catch (JsonGenerationException jge) {
            logger.info(jge);
        } catch (JsonMappingException jme) {
            logger.info(jme);
        } catch (IOException ioe) {
            logger.info(ioe);
        }

        assertEquals("Activity is not walking ", "walking", activity.getName());
        //logger.info("Returning all activities JSON " + activity.getName());
    }

    @Test
    public void getCaloriesBurnedText() throws Exception {
        url = url + "/text/1/70/1.5";
        WebTarget target = client.target(url);
        String response = target.request().get(String.class);
        logger.info("Returning calories " + response);

    }

//    @Test
//    public void getCaloriesBurnedJSON() throws Exception {
//        url = url + "/json/1/70/1.5";
//        WebTarget target = client.target(url);
//        String response = target.request().get(String.class);
//        ObjectMapper mapper = new ObjectMapper();
//        Response obj = mapper.readValue(response, Response.class);
//        String normalView = mapper.writerWithView(Views.Normal.class).writeValueAsString(staff);
//
//        logger.info("Returning calories " + response);
//    }

    @Test
    public void getCaloriesBurnedHTML() throws Exception {

    }

}
