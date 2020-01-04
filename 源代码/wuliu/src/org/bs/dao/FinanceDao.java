package org.bs.dao;

import java.util.List;
import org.bs.model.Finance;

public interface FinanceDao {
	public void save(Finance finance);

	public void delete(int id);

	public void update(Finance finance);

	public Finance getById(int id);

	public List<Finance> query(String str);

	public Finance getByUserId(int id);
}