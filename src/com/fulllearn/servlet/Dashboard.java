package com.fulllearn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class Dashboard extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		welcomeUser(req, resp);

	}

	private void welcomeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if (session.getAttribute("user")!= null) {
			req.getRequestDispatcher("/jsp/dashboard.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("WEB-INF/html/login.html").forward(req, resp);
		}
	}

	/*
	 * private AWStatsData getUserDetails(HttpServletRequest req,
	 * HttpServletResponse resp) throws IOException { HttpSession session =
	 * req.getSession(); User user = (User) session.getAttribute("user"); String
	 * userId = user.getId(); // System.out.println(userId); String jsonResponse
	 * = HttpConnectionHelper.getJson("GET",
	 * "https://full-learn.appspot.com/api/learn/stats/userId/" + userId, null,
	 * null); AWStatsResponse statsResponse= MAPPER.readValue(jsonResponse,
	 * AWStatsResponse.class); AWStatsData data= statsResponse.getData();
	 * System.out.println("Four Week Avg "+data.getFourWeekAvg());
	 * System.out.println(jsonResponse); return data;
	 * 
	 * }
	 * 
	 * AWStatsData data = getUserDetails(req, resp);
	 * resp.setContentType("application/json");
	 * resp.getWriter().write(MAPPER.writeValueAsString(data));
	 */
}
