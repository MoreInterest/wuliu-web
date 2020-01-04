package org.bs.service.impl;

import java.util.List;
import org.bs.dao.ExpressDao;
import org.bs.model.Express;
import org.bs.service.ExpressService;

public class ExpressServiceImpl implements ExpressService {
	ExpressDao expressDao;

	public void add(Express express) {
		expressDao.save(express);
	}

	public void delete(Express express) {
		expressDao.delete(express.getId());
	}

	public void update(Express express) {
		expressDao.update(express);
	}

	public void updateState(Express express) {
		expressDao.updateState(express);
	}

	public Express findById(int id) {
		return expressDao.getById(id);
	}

	public Express findByUserId(int id) {
		return expressDao.getByUserId(id);
	}

	public List<Express> search(String str) {
		return expressDao.query(str);
	}

	public void setExpressDao(ExpressDao expressDao) {
		this.expressDao = expressDao;
	}
}