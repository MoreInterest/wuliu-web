package org.bs.servlet.backend;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bs.model.Message;
import org.bs.service.MessageService;
import org.bs.utils.JDBCHandler;
import org.bs.utils.PageContext;
import org.bs.utils.ResultUtils;
import org.bs.model.Staff;
import org.bs.service.StaffService;

public class MessageServlet extends BaseServlet {
	private MessageService messageService;
	private StaffService staffService;

	@Override
	public void addInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("staffList", staffService.search(""));
		request.getRequestDispatcher("/backend/message/add_message.jsp")
				.forward(request, response);
	}

	public void prepare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("messageList",
				messageService.search(""));
	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Message message = ResultUtils.copyParams(Message.class, request);
		messageService.add(message);
		request.getRequestDispatcher("/backend/message/add_message_success.jsp")
				.forward(request, response);
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		messageService.delete(messageService.findById(id));
		request.getRequestDispatcher("MessageServlet?method=list").forward(
				request, response);
	}

	public void deleteBetch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] messageIds = request.getParameterValues("messageCheckbox");
		for (int i = 0; i < messageIds.length; i++) {
			messageService.delete(messageService.findById(Integer
					.parseInt(messageIds[i])));
		}
		request.getRequestDispatcher("MessageServlet?method=list").forward(
				request, response);
	}

	@Override
	public void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("message", messageService.findById(id));
		request.setAttribute("staffList", staffService.search(""));
		request.getRequestDispatcher(
				"/backend/message/update_message_input.jsp").forward(request,
				response);
	}

	public void show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("message", messageService.findById(id));
		request.setAttribute("staffList", staffService.search(""));
		request.getRequestDispatcher("/backend/message/show_message.jsp")
				.forward(request, response);
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Message message = ResultUtils.copyParams(Message.class, request);
		messageService.update(message);
		request.getRequestDispatcher("MessageServlet?method=list").forward(
				request, response);
	}

	@Override
	public void get(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.get(request, response);
	}

	@Override
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key = request.getParameter("key");
		if (!"".equals(key) && key != null) {
			request.setAttribute("messageList", messageService.search(key));
		} else {
			request.setAttribute("messageList", messageService.search(""));
		}
		request.setAttribute("page", PageContext.getPage());
		request.getRequestDispatcher("/backend/message/message_list.jsp")
				.forward(request, response);
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = new JDBCHandler<MessageService>()
				.createProxy(messageService);
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = new JDBCHandler<StaffService>()
				.createProxy(staffService);
	}
}