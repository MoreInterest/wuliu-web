package org.bs.dao;

import java.util.List;
import org.bs.model.Staff;

public interface StaffDao {
	public void save(Staff staff);

	public void delete(int id);

	public void update(Staff staff);

	public Staff getById(int id);

	public List<Staff> query(String str);

	public Staff getByUserId(int id);
}