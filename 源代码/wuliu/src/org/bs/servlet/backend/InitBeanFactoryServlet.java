package org.bs.servlet.backend;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.bs.utils.BeanFactory;
import org.bs.utils.ClassPathXmlBeanFactory;

public class InitBeanFactoryServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		long begin = System.currentTimeMillis();
		BeanFactory factory = new ClassPathXmlBeanFactory();
		this.getServletContext().setAttribute("beanFactory", factory);
		long end = System.currentTimeMillis();
		System.out.println("系统启动时间" + (end - begin) + "ms");

		// 修改log4j.properties的存储路径
		String webInfPath = this.getServletContext().getRealPath("/WEB-INF");
		String webRootPath = this.getServletContext().getRealPath("/");

		Properties pro = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(webInfPath
					+ "/classes/log4j.properties"));
			pro.load(in);
			// 重新写入配置文件
			FileOutputStream file = new FileOutputStream(webInfPath
					+ "/classes/log4j.properties");

			pro.put("log4j.appender.D.File", webRootPath + "/log.log");

			System.out.println("得到属性key:"
					+ pro.getProperty("log4j.appender.D.File"));
			pro.store(file, "系统配置修改"); // 这句话表示重新写入配置文件
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
