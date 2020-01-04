package org.bs.service.impl;

import java.util.List;
import org.bs.dao.CarsDao;
import org.bs.model.Cars;
import org.bs.service.CarsService;

public class CarsServiceImpl implements CarsService {
	CarsDao carsDao;

	public void add(Cars cars) {
		carsDao.save(cars);
	}

	public void delete(Cars cars) {
		carsDao.delete(cars.getId());
	}

	public void update(Cars cars) {
		carsDao.update(cars);
	}

	public Cars findById(int id) {
		return carsDao.getById(id);
	}

	public Cars findByUserId(int id) {
		return carsDao.getByUserId(id);
	}

	public List<Cars> search(String str) {
		return carsDao.query(str);
	}

	public void setCarsDao(CarsDao carsDao) {
		this.carsDao = carsDao;
	}
}