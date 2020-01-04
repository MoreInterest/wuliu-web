package org.bs.service.impl;

import java.util.List;
import org.bs.dao.FinanceDao;
import org.bs.model.Finance;
import org.bs.service.FinanceService;

public class FinanceServiceImpl implements FinanceService {
	FinanceDao financeDao;

	public void add(Finance finance) {
		financeDao.save(finance);
	}

	public void delete(Finance finance) {
		financeDao.delete(finance.getId());
	}

	public void update(Finance finance) {
		financeDao.update(finance);
	}

	public Finance findById(int id) {
		return financeDao.getById(id);
	}

	public Finance findByUserId(int id) {
		return financeDao.getByUserId(id);
	}

	public List<Finance> search(String str) {
		return financeDao.query(str);
	}

	public void setFinanceDao(FinanceDao financeDao) {
		this.financeDao = financeDao;
	}
}