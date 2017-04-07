<%--
  Calculate calories burned during exercising index page
  Author: Calories Calculator team
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link href="css/index.css" rel="stylesheet" type="text/css"/>
    <title>Calories Calculator</title>
</head>


<body>
<div id="container">

    <div id="main_container_div">
        <h2 id="title_header">Calories Burned Calculator</h2>
        <div class="outer_div">
            <div class="content_div">
                <div class="titlebox">What are calories?</div>
                <p class="info_p">A calorie is a measure of energy, just as a pound is a measure of weight
                    and a mile is a measure of distance. So the amount of energy you exert in
                   doing an activity is measured by the calories burn rate.
                </p>
            </div>
            <div class="content_div">
                <div class="titlebox">How to burn calories?</div>
                <p class="info_p"> That's easy, just be alive! Your body is constantly burning calories to keep
                  your body functioning. To burn more calories, do more activities, and the more
                 strenuous the activity the greater the calorie burn.
                </p>
            </div>
            <div class="content_div">
               <div class="titlebox">How many calories did you burn?</div>
              <p class="info_p"> Count how many calories you burn doing your favorite activities or how
                  long you should do an activity to lose weight. Enter your weight in kilograms or pounds, and number
                    of minutes for any of the exercise you do.
              </p>
            </div>
        </div>


        <div class="outer_div">
            <form action="calculateCaloriesActionServlet" id="calories_form" method="get">
                <div class="user_info_div">
            <%--enter weight --%>
                <c:import url="weight.jsp"></c:import>
                <%--select activity --%>
                <c:import url="activity.jsp"></c:import>

                <div class="content_div">
                    <div class="titlebox">Select duration</div>
                    <p class="info_p">
                        <select id="duration_select" name="duration_select">
                            <option value="0.5">half hour</option>
                            <option value="1">one hour</option>
                            <option value="1.5">hour and half</option>
                            <option value="2.0">two hours</option>
                            <option value="2.5">two and half hours</option>
                            <option value="3.0">three hours</option>
                            <option value="3.5">three and half hours</option>
                            <option value="4.0">four hours</option>
                            <option value="4.5">four and half hours</option>
                            <option value="5.0">five hours</option>
                            <option value="6.5">five and half hours</option>
                        </select>
                    </p>
                </div>
                </div>
                <input type="submit" class="submit_button" value="Calculate Calories">
            </form>
        </div>


    <div id="result_div">
        <c:if test="${not empty RequestedCaloriesResult}">
            <form id="calories_result_form" action="cleanupServlet">
                <p class="result_p_mess">You burned</p>
                <p class="result_p" id="result1">${RequestedCaloriesResult.caloriesBurned} <span id="calories_span">calories</span></p>
                <p class="result_p_mess">If you exercise 20 more minutes you will burn</p>
                <p class="result_p" id="result2">${MoreCaloriesResult.caloriesBurned} <span id="calories_span">calories</span></p>
                <p class="reset_button_p"><input type="submit" class="reset_button" value="Reset"/></p>

            </form>
        </c:if>
    </div>

    <div class="outer_div">
        <form action="calculateDurationActionServlet" id="duration_form" method="get">
            <div class="user_info_div">
         <%--enter weight --%>
            <c:import url="weight.jsp"></c:import>
        <%--select activity --%>
            <c:import url="activity.jsp"></c:import>
                <div class="content_div">
                    <div class="titlebox">Enter calories you wish to burn</div>
                        <p class="info_p">
                        <input type="text" name="calories_text" id="calories_text" value="" required>
                    </p>
                </div>
            </div>
            <input type="submit" class="submit_button" value="Calculate Duration">
        </form>
    </div>

    <div id="result_div2">
        <c:if test="${not empty DurationResult}">
            <form id="duration_result_form" action="cleanupServlet">
                <p class="result_p" id="result3">${DurationResult}</p>
                <p class="reset_button_p"><input type="submit" class="reset_button" value="Reset"/></p>
            </form>
        </c:if>
    </div>
    </div>
</div>

<div id="footer_div">
    <img src="images/tennis_fit1.jpg" alt="Fitness Picture" id="tennis_fit1_image"></img>
    <p id="dummy"></p>
    <img src="images/tennis_fit2.jpg" alt="Fitness Picture" id="tennis_fit2_image"></img>
</div>

</body>

</html>
