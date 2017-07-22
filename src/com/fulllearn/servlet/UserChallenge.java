package com.fulllearn.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fulllearn.helper.Constants;
import com.fulllearn.helper.HttpConnectionHelper;
import com.fulllearn.model.AUResponse;
import com.fulllearn.model.ChallengeDetail;
import com.fulllearn.model.User;

@SuppressWarnings("serial")
public class UserChallenge extends HttpServlet {
	static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public void init() throws ServletException {
		System.out.println("Init Called.");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ChallengeDetail challengeDetails = getChallengeDetails(req, resp);
		resp.setContentType("application/json");
		resp.getWriter().write(MAPPER.writeValueAsString(challengeDetails));
	}

	private ChallengeDetail getChallengeDetails(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String email = user.getLogin();
		long startTime = getStartDate(0);
		long endTime = getEndDate(0);
		String urlparameter = "apiKey=" + Constants.API_KEY + "&email=" + email + "&startTime=" + startTime
				+ "&endTime=" + endTime;
		String jsonResponse = HttpConnectionHelper.getJson("GET", Constants.CHALLENGE_API + "/v1/completedMinutes",
				urlparameter, null);
		System.out.println(jsonResponse);

		AUResponse response = MAPPER.readValue(jsonResponse, AUResponse.class);
		
		Map<String, ChallengeDetail> data = response.getData();

		ChallengeDetail details = data.get(email);
		details.setStartDate(startTime);
		details.setEndDate(endTime);
		System.out.println(details.toString());

		return details;

	}

	private long getStartDate(int week) {

		Calendar calendar = getStartTimeCalendarInstance();
		long result = 0;

		switch (week) {
		case 0:
			int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);
			calendar.add(Calendar.DATE, -(dayOfTheWeek - Calendar.MONDAY));
			break;

		case 4:
		case 12:
			int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
			calendar.set(Calendar.WEEK_OF_YEAR, currentWeek - week);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			break;

		}
		result = calendar.getTimeInMillis();
		System.out.println(week + "-" + result);
		return result;
	}

	private long getEndDate(int week) {
		Calendar calendar = getEndTimeCalendarInstance();
		long result = 0;

		switch (week) {
		case 4:
		case 12:
			int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
			calendar.set(Calendar.WEEK_OF_YEAR, currentWeek);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			break;
		}
		result = calendar.getTimeInMillis();
		System.out.println(week + ": " + result);
		return result;
	}

	private Calendar getStartTimeCalendarInstance() {

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	private Calendar getEndTimeCalendarInstance() {

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 59);
		return calendar;
	}
}