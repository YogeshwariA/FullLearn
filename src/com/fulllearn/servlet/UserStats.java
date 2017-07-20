package com.fulllearn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fulllearn.helper.HttpConnectionHelper;
import com.fulllearn.model.AWStatsData;
import com.fulllearn.model.AWStatsResponse;
import com.fulllearn.model.User;
import com.fulllearn.helper.Constants;
@SuppressWarnings("serial")
public class UserStats extends HttpServlet {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public void init() throws ServletException {
		System.out.println("init called.");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AWStatsData data = getUserDetails(req, resp);
		resp.setContentType("application/json");
		resp.getWriter().write(MAPPER.writeValueAsString(data));
	}

	private AWStatsData getUserDetails(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String userId = user.getId();
		String jsonResponse = HttpConnectionHelper.getJson("GET",Constants.FULL_LEARN_API+"/api/learn/stats/userId/" + userId, null, null);
		AWStatsResponse statsResponse = MAPPER.readValue(jsonResponse, AWStatsResponse.class);
		AWStatsData data = statsResponse.getData();
		System.out.println("Four Week Avg " + data.getFourWeekAvg());
		System.out.println(jsonResponse);
		return data;
	}

}
