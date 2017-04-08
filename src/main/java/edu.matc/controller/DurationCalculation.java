package edu.matc.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.CaloriesCalculator.Duration;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by student on 4/7/17.
 */
public class DurationCalculation {
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Convert JSON response string into Duration object to get the duration value
     *
     * @param responseFromREST the response from rest
     * @param duration         the duration
     * @return Calculation result object
     */
    public Double getDuration(String responseFromREST, Duration duration) {
        ObjectMapper objectMapper = new ObjectMapper();
        Double durationResult = 0.0;

        try {
            duration = objectMapper.readValue(responseFromREST, Duration.class);
            durationResult = duration.getDuration();

        } catch (JsonGenerationException jge) {
            logger.info(jge);
        } catch (JsonMappingException jme) {
            logger.info(jme);
        } catch (IOException ioe) {
            logger.info(ioe);
        }
        return durationResult;

    }

    /**
     * Convert duration to string.
     * @param durationDouble the duration double
     * @return the string
     */
    public String convertDurationToString(Double durationDouble) {
        logger.info("Duration double " + durationDouble);

        int durationHours= durationDouble.intValue();
        logger.info("Duration hours " + durationHours);
        Double durationDecimal = durationDouble - durationHours;
        long durationMinutes = Math.round(60 * durationDecimal);

        logger.info("Duration in minutes " + durationMinutes);

        if(durationMinutes == 0) {
            return "You would have to exercise for " + durationHours + " hour";
        } else if (durationHours == 0) {
            return "You would have to exercise for " + durationMinutes + " minutes";
        } else if (durationHours == 1){
            return "You would have to exercise for " + durationHours + " hour and " + durationMinutes + " minutes";
        } else {
            return "You would have to exercise for " + durationHours + " hours and " + durationMinutes + " minutes";
        }


    }
}
