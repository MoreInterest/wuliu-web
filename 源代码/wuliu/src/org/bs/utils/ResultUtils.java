package org.bs.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.bs.model.Member;
import org.bs.model.Role;
import org.bs.model.Staff;
import org.bs.model.User;

public class ResultUtils {
	static {
		ConvertUtils.register(new DateConvert(), Date.class);
		ConvertUtils.register(new RoleConvert(), Role.class);
		ConvertUtils.register(new UserConvert(), User.class);
		ConvertUtils.register(new MemberConvert(), Member.class);
		ConvertUtils.register(new StaffConvert(), Staff.class);
	}

	public static <T> T copyParams(Class<T> targetClass,
			HttpServletRequest request) {
		T target = null;
		try {
			target = targetClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		Map params = request.getParameterMap();
		for (Iterator iterator = params.entrySet().iterator(); iterator
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String key = (String) entry.getKey();
			String[] values = (String[]) entry.getValue();
			if (values.length == 1) {
				try {
					BeanUtils.copyProperty(target, key, values[0]);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					System.out.println("目标对象:" + target.getClass() + "中找不到相应对象"
							+ key);
					e.printStackTrace();
				}
			} else {
				try {
					BeanUtils.copyProperty(target, key, values);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return target;
	}
}