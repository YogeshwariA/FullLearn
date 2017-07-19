package com.fulllearn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fulllearn.helper.HttpConnectionHelper;
import com.fulllearn.model.User;

@SuppressWarnings("serial")
public class UserChallenge extends HttpServlet {
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
		String startTime = "1498089600000";
		String endTime = "1500422400000";
		String apiKey = "b2739ff0eb7543e5a5c43e88f3cb2a0bd0d0247d";
		String urlparameter = "apiKey=" + apiKey + "&email=" + email + "&starTime=" + startTime + "&endTime=" + endTime;
		String response = HttpConnectionHelper.getJson("GET","https://mint4-dot-live-adaptivecourse.appspot.com/v1/completedMinutes", urlparameter, null);
		System.out.println(response);
	}
}
