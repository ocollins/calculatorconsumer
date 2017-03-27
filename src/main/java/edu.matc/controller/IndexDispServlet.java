package edu.matc.controller;

/**
 * Display servlet, wich will call index.jsp page
 * It will call /activities resource to retrieve
 * a list of activities so the user can select one.
 * @author Calories Calculator team
 */

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.CaloriesCalculator.Activities;
import edu.matc.CaloriesCalculator.Activity;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

//@WebServlet(
//        name = "indexDispServlet",
//        urlPatterns = { "/indexDispServlet" }
//        //loadOnStartup = 1
//)

public class IndexDispServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Handles HTTP GET requests.
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        //Call the service to get a list of all activities to populate the dropbox
        Client client = ClientBuilder.newClient();
        String url = "http://localhost:8080/CaloriesCalculator/activities";
        WebTarget target = client.target(url + "/list");

        //Get responce
        String restResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);
        logger.info("response from the call to REST " + restResponse);

        //Map to the Activities POJO
        ObjectMapper objectMapper = new ObjectMapper();
        Activities activities = null;
        Activity activity = null;
        try {
            activities = objectMapper.readValue(restResponse, Activities.class);
            List<Activity> activityList = activities.getActivities();
            //request.setAttribute("activities", activityList);
            session.setAttribute("activities", activityList);

        } catch (JsonGenerationException jge) {
            logger.info(jge);
        } catch (JsonMappingException jme) {
            logger.info(jme);
        } catch (IOException ioe) {
            logger.info(ioe);
        }

        //Redirect to the index.jsp
        String jspUrl = "/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jspUrl);
        dispatcher.forward(request, response);

    }
}