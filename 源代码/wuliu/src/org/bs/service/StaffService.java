package org.bs.service;

import java.util.List;
import org.bs.model.Staff;

public interface StaffService {
	public void add(Staff staff);

	public void delete(Staff staff);

	public void update(Staff staff);

	public Staff findById(int id);

	public Staff findByUserId(int id);

	public List<Staff> search(String str);
}