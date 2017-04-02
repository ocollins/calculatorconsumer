package edu.matc.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.CaloriesCalculator.Calculation1;
import edu.matc.CaloriesCalculator.Calculation2;
import edu.matc.CaloriesCalculator.Calculations;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;


/**
 * Servlet to access CaloriesCalculator REST api
 * @author Olena Collins
 */
@WebServlet(
        name = "calculateCaloriesActionServlet",
        urlPatterns = { "/calculateCaloriesActionServlet" }
)
public class CalculateCaloriesActionServlet extends HttpServlet {
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
        //Create context container
        ServletContext context = getServletContext();

        logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$ Getting into Calories action servlet");
        //Get info from the user
        int weight = Integer.parseInt(request.getParameter("weight_text"));
        double duration = Double.parseDouble(request.getParameter("duration_select"));
        int activity = Integer.parseInt(request.getParameter("activity_select"));
        String unit = request.getParameter("weight_unit");

        //Call REST service to get calories burned result
        Client client = ClientBuilder.newClient();
        String url = "http://localhost:8080/CaloriesCalculator/activities/json/";
        url = url + activity + "/" + weight + "/" + duration +"/" + unit;
        logger.info(url);
        WebTarget target = client.target(url);
        String responseFromREST = target.request().get(String.class);

        Calculations calculations = null;

        //Conver to Calculations POJOs and get calories result for the
        //requested duration and store in context container
        context.setAttribute("RequestedCaloriesResult",  getCalculation1(responseFromREST, calculations));

        //Conver to Calculations POJOs and get calories result if exercised more
        //and store in context container
        context.setAttribute("MoreCaloriesResult",  getCalculation2(responseFromREST, calculations));

        String responceurl = "/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(responceurl);
        dispatcher.forward(request, response);

    }

    /**
     * Convert JSON response string into Calories objects to get the calories burned value
     *
     * @param responseFromREST the response from rest
     * @return Calculation result object
     */
    public Calculation1 getCalculation1(String responseFromREST, Calculations calculations) {
        Calculation1 calcResult = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            calculations = objectMapper.readValue(responseFromREST, Calculations.class);
            calcResult = calculations.getCalculation1();

        } catch (JsonGenerationException jge) {
            logger.info(jge);
        } catch (JsonMappingException jme) {
            logger.info(jme);
        } catch (IOException ioe) {
            logger.info(ioe);
        }
        return calcResult;

    }

    /**
     * Gets calories if exercised more.
     *
     * @param responseFromREST the response from rest
     * @param calculations     the calculations
     * @return the calculation 2
     */
    public Calculation2 getCalculation2(String responseFromREST, Calculations calculations) {
        Calculation2 calcResult = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            calculations = objectMapper.readValue(responseFromREST, Calculations.class);
            calcResult = calculations.getCalculation2();

        } catch (JsonGenerationException jge) {
            logger.info(jge);
        } catch (JsonMappingException jme) {
            logger.info(jme);
        } catch (IOException ioe) {
            logger.info(ioe);
        }
        return calcResult;

    }
}