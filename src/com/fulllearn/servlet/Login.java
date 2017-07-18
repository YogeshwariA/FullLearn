package com.fulllearn.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fulllearn.helper.Constants;
import com.fulllearn.helper.HttpConnectionHelper;
import com.fulllearn.model.AWData;
import com.fulllearn.model.AWResponse;
import com.fulllearn.model.User;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class Login extends HttpServlet {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ((req.getRequestURI().contains("/oauth/callback"))) {
			try {
				signInwithAnywhereWorks(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void signInwithAnywhereWorks(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String code = req.getParameter("code");
		String urlParameters = "code=" + code + "&client_id=" + Constants.CLIENT_ID + "&scope=awapis.identity"
				+ "&client_secret=" + Constants.CLIENT_SECRET + "&access_type=offline" + "&redirect_uri="
				+ Constants.REDIRECT_URL + "&grant_type=authorization_code";

		String result = HttpConnectionHelper.getJson("GET", Constants.OAUTH_ACCESS_TOKEN_URL, urlParameters, null);

		try {
			if (!result.isEmpty()) {

				JSONObject json = new JSONObject(result);
				String access_token = json.get("access_token").toString();
				Map<String, String> headers = new HashMap<>();
				headers.put("Content-Type", "application/json");
				headers.put("Authorization", "Bearer " + access_token);

				String userInfoJson = HttpConnectionHelper.getJson("GET", Constants.OAUTH_USER_API, null, headers);

				System.out.println(userInfoJson);

				AWResponse awResponse = MAPPER.readValue(userInfoJson, AWResponse.class);
				System.out.println(awResponse);

				AWData awData = awResponse.getData();
				if (null != awData) {
					User user = awData.getUser();
					HttpSession session = req.getSession();
					session.setAttribute("user", user);
				}

				 //req.getRequestDispatcher("").forward(req, resp);
				resp.sendRedirect("/dashboard");

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
