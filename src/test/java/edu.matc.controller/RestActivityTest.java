package edu.matc.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.CaloriesCalculator.*;
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
        //logger.info("response from the call to REST " + response);

        ObjectMapper objectMapper = new ObjectMapper();
        Activities activities = null;
        Activity testActivity = null;
        try {
            activities = objectMapper.readValue(response, Activities.class);
            //List<Activity> myList = activities.getActivities();
//            for(Activity anActivity : myList) {
//                logger.info("Activity from the list " + anActivity.getName());
//            }
            testActivity = activities.getActivities().get(0);

        } catch (JsonGenerationException jge) {
            logger.info(jge);
        } catch (JsonMappingException jme) {
            logger.info(jme);
        } catch (IOException ioe) {
            logger.info(ioe);
        }

        assertEquals("Activity is not walking ", "walking", testActivity.getName());
    }

    @Test
    public void getCaloriesBurnedJSON() throws Exception {
        url = url + "/json/1/85/1.5/kg";
        WebTarget target = client.target(url);
        String response = target.request().get(String.class);
        logger.info("Returning calories " + response);

        Calculations calculations = null;
        Calculation1 calculationForRequestedDuration = null;
        Calculation2 calculationForDoubleTime = null;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            calculations = objectMapper.readValue(response, Calculations.class);
            calculationForRequestedDuration = calculations.getCalculation1();
            logger.info("Returning calories " + calculationForRequestedDuration.getCaloriesBurned());

            calculationForDoubleTime = calculations.getCalculation2();
            logger.info("Returning calories " + calculationForDoubleTime.getCaloriesBurned());
        } catch (JsonGenerationException jge) {
            logger.info(jge);
        } catch (JsonMappingException jme) {
            logger.info(jme);
        } catch (IOException ioe) {
            logger.info(ioe);
        }

        double testCalories = 318.75;
        double returnCalories = calculationForRequestedDuration.getCaloriesBurned();
        assertEquals("Returned calories are not correct ", testCalories, returnCalories, 0);
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
