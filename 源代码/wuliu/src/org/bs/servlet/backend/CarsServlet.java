package org.bs.servlet.backend;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bs.model.Cars;
import org.bs.model.Finance;
import org.bs.service.CarsService;
import org.bs.service.FinanceService;
import org.bs.utils.JDBCHandler;
import org.bs.utils.PageContext;
import org.bs.utils.ResultUtils;

public class CarsServlet extends BaseServlet {
	private CarsService carsService;
	private FinanceService financeService;

	@Override
	public void addInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/backend/cars/add_cars.jsp").forward(
				request, response);
	}

	public void prepare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("carsList", carsService.search(""));
	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cars cars = ResultUtils.copyParams(Cars.class, request);
		carsService.add(cars);
		Finance finance = new Finance();
		finance.setName("车辆" + cars.getName() + "每月油费");
		finance.setMingxi("每月油费" + cars.getYoufei() + "元");
		finance.setSettime(new Date());
		finance.setDescp("cars" + cars.getId());
		financeService.add(finance);
		request.getRequestDispatcher("/backend/cars/add_cars_success.jsp")
				.forward(request, response);
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		carsService.delete(carsService.findById(id));
		for (Finance f : financeService.search("cars" + id)) {
			financeService.delete(f);
		}
		request.getRequestDispatcher("CarsServlet?method=list").forward(
				request, response);
	}

	public void deleteBetch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] carsIds = request.getParameterValues("carsCheckbox");
		for (int i = 0; i < carsIds.length; i++) {
			carsService.delete(carsService.findById(Integer
					.parseInt(carsIds[i])));
		}
		request.getRequestDispatcher("CarsServlet?method=list").forward(
				request, response);
	}

	@Override
	public void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("cars", carsService.findById(id));
		request.getRequestDispatcher("/backend/cars/update_cars_input.jsp")
				.forward(request, response);
	}

	public void show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("cars", carsService.findById(id));
		request.getRequestDispatcher("/backend/cars/show_cars.jsp").forward(
				request, response);
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cars cars = ResultUtils.copyParams(Cars.class, request);
		carsService.update(cars);
		request.getRequestDispatcher("CarsServlet?method=list").forward(
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
			request.setAttribute("carsList", carsService.search(key));
		} else {
			request.setAttribute("carsList", carsService.search(""));
		}
		request.setAttribute("page", PageContext.getPage());
		request.getRequestDispatcher("/backend/cars/cars_list.jsp").forward(
				request, response);
	}

	public void setCarsService(CarsService carsService) {
		this.carsService = new JDBCHandler<CarsService>()
				.createProxy(carsService);
	}

	public void setFinanceService(FinanceService financeService) {
		this.financeService = new JDBCHandler<FinanceService>()
				.createProxy(financeService);
	}
}