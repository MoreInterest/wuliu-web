package org.bs.servlet.backend;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bs.model.Finance;
import org.bs.service.FinanceService;
import org.bs.utils.JDBCHandler;
import org.bs.utils.PageContext;
import org.bs.utils.ResultUtils;
import java.io.File;
import jxl.Workbook;
import jxl.write.Label;
import java.util.List;
import jxl.format.VerticalAlignment;
import jxl.format.Alignment;
import jxl.write.WritableCellFormat;
import jxl.CellView;
import jxl.write.WritableFont;
import jxl.format.UnderlineStyle;
import org.bs.utils.SystemFunction;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class FinanceServlet extends BaseServlet {
	private FinanceService financeService;

	@Override
	public void addInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/backend/finance/add_finance.jsp")
				.forward(request, response);
	}

	public void prepare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("financeList",
				financeService.search(""));
	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Finance finance = ResultUtils.copyParams(Finance.class, request);
		financeService.add(finance);
		request.getRequestDispatcher("/backend/finance/add_finance_success.jsp")
				.forward(request, response);
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		financeService.delete(financeService.findById(id));
		request.getRequestDispatcher("FinanceServlet?method=list").forward(
				request, response);
	}

	public void deleteBetch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] financeIds = request.getParameterValues("financeCheckbox");
		for (int i = 0; i < financeIds.length; i++) {
			financeService.delete(financeService.findById(Integer
					.parseInt(financeIds[i])));
		}
		request.getRequestDispatcher("FinanceServlet?method=list").forward(
				request, response);
	}

	@Override
	public void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("finance", financeService.findById(id));
		request.getRequestDispatcher(
				"/backend/finance/update_finance_input.jsp").forward(request,
				response);
	}

	public void show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("finance", financeService.findById(id));
		request.getRequestDispatcher("/backend/finance/show_finance.jsp")
				.forward(request, response);
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Finance finance = ResultUtils.copyParams(Finance.class, request);
		financeService.update(finance);
		request.getRequestDispatcher("FinanceServlet?method=list").forward(
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
			request.setAttribute("financeList", financeService.search(key));
		} else {
			request.setAttribute("financeList", financeService.search(""));
		}
		request.setAttribute("page", PageContext.getPage());
		request.getRequestDispatcher("/backend/finance/finance_list.jsp")
				.forward(request, response);
	}

	public void dayin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Finance> finances = financeService.search("");

		String realPath = this.getServletContext().getRealPath("/upload_file");
		String realPathImage = this.getServletContext().getRealPath(
				"/upload_image");
		try {
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(new File(realPath
					+ "/" + "财务收支信息表.xls"));
			// 生成名为“第一页”的工作表，参数 0 表示这是第一页
			WritableSheet sheet = book.createSheet(" 第一页 ", 0);
			jxl.write.WritableFont wfcNav = new jxl.write.WritableFont(
					WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			WritableCellFormat wcfN = new WritableCellFormat(wfcNav);
			wcfN.setAlignment(Alignment.CENTRE); // 设置水平对齐
			wcfN.setWrap(true); // 设置自动换行
			wcfN.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 设置垂直对齐
			CellView cellView = new CellView();
			// cellView.setSize(5000);
			// 设置列宽自动大小
			cellView.setAutosize(true);
			// 在 Label 对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为 test
			// 将定义好的单元格添加到工作表中
			sheet.setColumnView(0, cellView);
			Label label0 = new Label(0, 0, " 主键 ", wcfN);
			sheet.addCell(label0);
			sheet.setColumnView(1, cellView);
			Label label1 = new Label(1, 0, " 标题 ", wcfN);
			sheet.addCell(label1);
			sheet.setColumnView(2, cellView);
			Label label2 = new Label(2, 0, " 提交时间 ", wcfN);
			sheet.addCell(label2);
			sheet.setColumnView(3, cellView);
			Label label3 = new Label(3, 0, " 财务明细 ", wcfN);
			sheet.addCell(label3);
			sheet.setColumnView(4, cellView);
			Label label4 = new Label(4, 0, " 备注 ", wcfN);
			sheet.addCell(label4);

			int i = 0;
			for (Finance e : finances) {
				i += 1;
				jxl.write.Number llabel0 = new jxl.write.Number(0, i,
						e.getId(), wcfN);
				sheet.addCell(llabel0);
				Label llabel1 = new Label(1, i, e.getName(), wcfN);
				sheet.addCell(llabel1);
				Label llabel2 = new Label(2, i, SystemFunction.dateToString(e
						.getSettime()), wcfN);
				sheet.addCell(llabel2);
				Label llabel3 = new Label(3, i, e.getMingxi(), wcfN);
				sheet.addCell(llabel3);
				Label llabel4 = new Label(4, i, e.getDescp(), wcfN);
				sheet.addCell(llabel4);
			}

			// 写入数据并关闭文件
			book.write();
			book.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		request.setAttribute("page", PageContext.getPage());
		request.getRequestDispatcher(
				"/backend/finance/finance_dayin_success.jsp").forward(request,
				response);
	}

	public void setFinanceService(FinanceService financeService) {
		this.financeService = new JDBCHandler<FinanceService>()
				.createProxy(financeService);
	}
}