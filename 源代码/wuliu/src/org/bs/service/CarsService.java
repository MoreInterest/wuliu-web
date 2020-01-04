package org.bs.service;

import java.util.List;
import org.bs.model.Cars;

public interface CarsService {
	public void add(Cars cars);

	public void delete(Cars cars);

	public void update(Cars cars);

	public Cars findById(int id);

	public Cars findByUserId(int id);

	public List<Cars> search(String str);
}