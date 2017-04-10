package edu.matc.controller;

import edu.matc.CaloriesCalculator.Duration;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;


/**
 * Servlet to access CaloriesCalculator REST api
 *
 * @author Olena Collins
 */
@WebServlet(
        name = "calculateDurationActionServlet",
        urlPatterns = { "/calculateDurationActionServlet" }
)
public class CalculateDurationActionServlet extends HttpServlet {
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
        //Create session container
        HttpSession session = request.getSession(true);

        //Get info from the user
        int weight = Integer.parseInt(request.getParameter("weight_text"));
        int calories = Integer.parseInt(request.getParameter("calories_text"));
        int activity = Integer.parseInt(request.getParameter("activity_select"));
        String unit = request.getParameter("weight_unit");

        //Call REST service to get duration result
        Client client = ClientBuilder.newClient();
        //String url = "http://localhost:8080/CaloriesCalculator/duration/json/";
        String url = "http://52.14.26.13:8080/CaloriesCalculator/duration/json/";
        url = url + activity + "/" + weight + "/" + calories +"/" + unit;
        logger.info(url);
        WebTarget target = client.target(url);
        String responseFromREST = target.request().get(String.class);

        //Conver to Duration POJOs and get duration result for the
        //requested calories
        Duration duration = null;
        DurationCalculation durationCalculation = new DurationCalculation();
        Double durationDouble = durationCalculation.getDuration(responseFromREST, duration);

        //Format string to be displayed on the screen
        String durationString = durationCalculation.convertDurationToString(durationDouble);

        //Store duration string in context container
        session.setAttribute("DurationResult",  durationString);

        String responceurl = "/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(responceurl);
        dispatcher.forward(request, response);

    }


}