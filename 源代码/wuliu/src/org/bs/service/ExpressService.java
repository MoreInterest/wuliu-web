package org.bs.service;

import java.util.List;
import org.bs.model.Express;

public interface ExpressService {
	public void add(Express express);

	public void delete(Express express);

	public void update(Express express);

	public void updateState(Express express);

	public Express findById(int id);

	public Express findByUserId(int id);

	public List<Express> search(String str);
}