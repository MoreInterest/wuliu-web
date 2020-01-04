package org.bs.dao;

import java.util.List;
import org.bs.model.Cars;

public interface CarsDao {
	public void save(Cars cars);

	public void delete(int id);

	public void update(Cars cars);

	public Cars getById(int id);

	public List<Cars> query(String str);

	public Cars getByUserId(int id);
}