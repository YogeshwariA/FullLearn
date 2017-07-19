package com.fulllearn.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fulllearn.helper.HttpConnectionHelper;
import com.fulllearn.model.User;

@SuppressWarnings("serial")
public class UserChallenge extends HttpServlet {

	private static final DateFormat DATE_FORMATER = new SimpleDateFormat("dd/MM/yyyy");
	private static final String API_KEY = "b2739ff0eb7543e5a5c43e88f3cb2a0bd0d0247d";

	@Override
	public void init() throws ServletException {
		System.out.println("Init Called.");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getTimeDuration(req, resp);
	}

	private void getTimeDuration(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String email = user.getLogin();

		try {
			long startTime = getDateAsLong("10/07/2017");
			long endTime = getDateAsLong("19/07/2017");

			String urlparameter = "apiKey=" + API_KEY + "&email=" + email + "&startTime=" + startTime + "&endTime="
					+ endTime;
			String response = HttpConnectionHelper.getJson("GET","https://mint4-dot-live-adaptivecourse.appspot.com/v1/completedMinutes", urlparameter, null);
			System.out.println(response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private long getDateAsLong(String dateAsString) throws ParseException {
		if (dateAsString != null) {
			return DATE_FORMATER.parse(dateAsString).getTime();
		}

		return 0;
	}
}
