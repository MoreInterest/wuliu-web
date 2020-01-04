package org.bs.servlet.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.bs.dao.impl.UserDaoImpl;
import org.bs.model.User;

public class LoginServlet extends HttpServlet {

	private boolean isSupportCheckCode = false;
	private static Logger logger = Logger.getLogger(LoginServlet.class);
	private HttpSession application;

	@Override
	public void init(ServletConfig config) throws ServletException {

		isSupportCheckCode = config.getServletContext()
				.getInitParameter("isSupportCheckCode").equals("1") ? true
				: false;
		super.init(config);
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method == null || method.equalsIgnoreCase("login")) {
			login(request, response);
		} else if (method.equalsIgnoreCase("loginOut")) {
			loginOut(request, response);
		} else if (method.equalsIgnoreCase("loginf")) {
			loginf(request, response);
		} else if (method.equalsIgnoreCase("loginOutf")) {
			loginOutf(request, response);
		}
	}

	protected void loginOut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/backend/login.jsp");
	}

	protected void login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String key = request.getParameter("key");
		String code = request.getParameter("code");
		String check_code = (String) request.getSession().getAttribute(
				"check_code");
		UserDaoImpl userDao = new UserDaoImpl();
		// MemberDao memberDao = new MemberDaoImpl();
		User user = null;
		if (isSupportCheckCode) {
			if (!code.equalsIgnoreCase(check_code)) {
				request.getSession().setAttribute("login_error", "验证码输入有误");
				response.sendRedirect(request.getContextPath()
						+ "/backend/login.jsp");
				return;
			}
		}
		try {
			user = userDao.login(username, password);
		} catch (RuntimeException e) {
			request.getSession().setAttribute("login_error", e.getMessage());
		}
		if (user != null) {
			logger.info(user.getRole().getName() + user.getUsername() + "成功登陆.");
			request.getSession().removeAttribute("login_error");
			request.getSession().setAttribute("login_user", user);
			// request.getSession().setAttribute("member",
			// memberDao.getByUserId(user.getId()));
			// 取得Application对象
			ServletContext application = this.getServletContext();
			List<HttpSession> sessions = (List<HttpSession>) application
					.getAttribute("sessionList");
			if (sessions == null || sessions.size() == 0) {
				sessions = new ArrayList<HttpSession>();
			}
			sessions.add(request.getSession(true));
			application.setAttribute("sessionList", sessions);

			response.sendRedirect(request.getContextPath()
					+ "/backend/main.jsp");

		} else {

			response.sendRedirect(request.getContextPath()
					+ "/backend/login.jsp");
		}
	}

	protected void loginf(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDaoImpl userDao = new UserDaoImpl();
		// MemberDao memberDao = new MemberDaoImpl();
		User user = null;
		String error = "";
		PrintWriter out = response.getWriter();
		try {
			user = userDao.login(username, password);
		} catch (RuntimeException e) {
			error += e.getMessage();
		}
		if (user != null) {
			logger.info(user.getRole().getName() + user.getUsername() + "成功登陆.");
			request.getSession().setAttribute("login_user", user);
			// request.getSession().setAttribute("member",
			// memberDao.getByUserId(user.getId()));

			ServletContext application = this.getServletContext();
			List<HttpSession> sessions = (List<HttpSession>) application
					.getAttribute("sessionList");
			if (sessions == null || sessions.size() == 0) {
				sessions = new ArrayList<HttpSession>();
			}
			sessions.add(request.getSession(true));
			application.setAttribute("sessionList", sessions);

			out.write("true");
		} else {
			out.write(error);
		}
		out.flush();
		out.close();
	}

	protected void loginOutf(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
	}

}
