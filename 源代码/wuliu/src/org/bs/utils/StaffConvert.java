package org.bs.utils;

import org.apache.commons.beanutils.Converter;
import org.bs.model.Staff;

public class StaffConvert implements Converter {
	public Object convert(Class targetClass, Object value) {
		Staff staff = null;
		if (targetClass == Staff.class) {
			staff = new Staff();
			staff.setId(Integer.parseInt((String) value));
		}
		return staff;
	}
}