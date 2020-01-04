package org.bs.service.impl;

import java.util.List;
import org.bs.dao.StaffDao;
import org.bs.model.Staff;
import org.bs.service.StaffService;

public class StaffServiceImpl implements StaffService {
	StaffDao staffDao;

	public void add(Staff staff) {
		staffDao.save(staff);
	}

	public void delete(Staff staff) {
		staffDao.delete(staff.getId());
	}

	public void update(Staff staff) {
		staffDao.update(staff);
	}

	public Staff findById(int id) {
		return staffDao.getById(id);
	}

	public Staff findByUserId(int id) {
		return staffDao.getByUserId(id);
	}

	public List<Staff> search(String str) {
		return staffDao.query(str);
	}

	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}
}