package org.bs.servlet.backend;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bs.model.Express;
import org.bs.service.ExpressService;
import org.bs.utils.JDBCHandler;
import org.bs.utils.PageContext;
import org.bs.utils.ResultUtils;
import org.bs.model.Member;
import org.bs.service.MemberService;
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

public class ExpressServlet extends BaseServlet {
	private ExpressService expressService;
	private MemberService memberService;

	@Override
	public void addInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("memberList", memberService.search(""));
		request.getRequestDispatcher("/backend/express/add_express.jsp")
				.forward(request, response);
	}

	public void prepare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("expressList",
				expressService.search(""));
	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Express express = ResultUtils.copyParams(Express.class, request);
		expressService.add(express);
		request.getRequestDispatcher("/backend/express/add_express_success.jsp")
				.forward(request, response);
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		expressService.delete(expressService.findById(id));
		request.getRequestDispatcher("ExpressServlet?method=list").forward(
				request, response);
	}

	public void deleteBetch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] expressIds = request.getParameterValues("expressCheckbox");
		for (int i = 0; i < expressIds.length; i++) {
			expressService.delete(expressService.findById(Integer
					.parseInt(expressIds[i])));
		}
		request.getRequestDispatcher("ExpressServlet?method=list").forward(
				request, response);
	}

	@Override
	public void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("express", expressService.findById(id));
		request.setAttribute("memberList", memberService.search(""));
		request.getRequestDispatcher(
				"/backend/express/update_express_input.jsp").forward(request,
				response);
	}

	public void show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("express", expressService.findById(id));
		request.setAttribute("memberList", memberService.search(""));
		request.getRequestDispatcher("/backend/express/show_express.jsp")
				.forward(request, response);
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Express express = ResultUtils.copyParams(Express.class, request);
		expressService.update(express);
		request.getRequestDispatcher("ExpressServlet?method=list").forward(
				request, response);
	}

	public void modifyState(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("express", expressService.findById(id));
		request.setAttribute("memberList", memberService.search(""));
		request.getRequestDispatcher(
				"/backend/express/updateState_express_input.jsp").forward(
				request, response);
	}

	public void updateState(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Express express = ResultUtils.copyParams(Express.class, request);
		expressService.updateState(express);
		request.getRequestDispatcher("ExpressServlet?method=list").forward(
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
		String chaxun = request.getParameter("chaxun");
		if (!"".equals(chaxun) && chaxun != null) {
			if ("ri".equals(chaxun)) {
				request.setAttribute("expressList",
						expressService.search("ri" + key));
			}
			if ("wancheng".equals(chaxun)) {
				request.setAttribute("expressList",
						expressService.search("wancheng" + key));
			}
		} else {
			if (!"".equals(key) && key != null) {
				request.setAttribute("expressList", expressService.search(key));
			} else {
				request.setAttribute("expressList", expressService.search(""));
			}
		}
		request.setAttribute("page", PageContext.getPage());
		request.getRequestDispatcher("/backend/express/express_list.jsp")
				.forward(request, response);
	}

	public void dayin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Express> expresss = expressService.search("");

		String realPath = this.getServletContext().getRealPath("/upload_file");
		String realPathImage = this.getServletContext().getRealPath(
				"/upload_image");
		try {
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(new File(realPath
					+ "/" + "快件信息表.xls"));
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
			Label label1 = new Label(1, 0, " 快件编号 ", wcfN);
			sheet.addCell(label1);
			sheet.setColumnView(2, cellView);
			Label label2 = new Label(2, 0, " 发件人 ", wcfN);
			sheet.addCell(label2);
			sheet.setColumnView(3, cellView);
			Label label3 = new Label(3, 0, " 出发地 ", wcfN);
			sheet.addCell(label3);
			sheet.setColumnView(4, cellView);
			Label label4 = new Label(4, 0, " 收件人姓名 ", wcfN);
			sheet.addCell(label4);
			sheet.setColumnView(5, cellView);
			Label label5 = new Label(5, 0, " 收件人联系方式 ", wcfN);
			sheet.addCell(label5);
			sheet.setColumnView(6, cellView);
			Label label6 = new Label(6, 0, " 目的地 ", wcfN);
			sheet.addCell(label6);
			sheet.setColumnView(7, cellView);
			Label label7 = new Label(7, 0, " 快件类别 ", wcfN);
			sheet.addCell(label7);
			sheet.setColumnView(8, cellView);
			Label label8 = new Label(8, 0, " 下单时间 ", wcfN);
			sheet.addCell(label8);
			sheet.setColumnView(9, cellView);
			Label label9 = new Label(9, 0, " 备注 ", wcfN);
			sheet.addCell(label9);
			sheet.setColumnView(10, cellView);
			Label label10 = new Label(10, 0, " 状态 ", wcfN);
			sheet.addCell(label10);

			int i = 0;
			for (Express e : expresss) {
				i += 1;
				jxl.write.Number llabel0 = new jxl.write.Number(0, i,
						e.getId(), wcfN);
				sheet.addCell(llabel0);
				Label llabel1 = new Label(1, i, e.getName(), wcfN);
				sheet.addCell(llabel1);
				Label llabel2 = new Label(2, i, e.getMember().getName(), wcfN);
				sheet.addCell(llabel2);
				Label llabel3 = new Label(3, i, e.getChufa(), wcfN);
				sheet.addCell(llabel3);
				Label llabel4 = new Label(4, i, e.getShoujianname(), wcfN);
				sheet.addCell(llabel4);
				Label llabel5 = new Label(5, i, e.getTel(), wcfN);
				sheet.addCell(llabel5);
				Label llabel6 = new Label(6, i, e.getPlace(), wcfN);
				sheet.addCell(llabel6);
				Label llabel7 = new Label(7, i, e.getLeibie(), wcfN);
				sheet.addCell(llabel7);
				Label llabel8 = new Label(8, i, SystemFunction.dateToString(e
						.getSettime()), wcfN);
				sheet.addCell(llabel8);
				Label llabel9 = new Label(9, i, e.getDescp(), wcfN);
				sheet.addCell(llabel9);
				Label llabel10 = new Label(10, i, e.getState(), wcfN);
				sheet.addCell(llabel10);
			}

			// 写入数据并关闭文件
			book.write();
			book.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		request.setAttribute("page", PageContext.getPage());
		request.getRequestDispatcher(
				"/backend/express/express_dayin_success.jsp").forward(request,
				response);
	}

	public void setExpressService(ExpressService expressService) {
		this.expressService = new JDBCHandler<ExpressService>()
				.createProxy(expressService);
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = new JDBCHandler<MemberService>()
				.createProxy(memberService);
	}
}