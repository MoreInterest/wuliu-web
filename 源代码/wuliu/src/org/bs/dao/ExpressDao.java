package org.bs.dao;

import java.util.List;
import org.bs.model.Express;

public interface ExpressDao {
	public void save(Express express);

	public void delete(int id);

	public void update(Express express);

	public void updateState(Express express);

	public Express getById(int id);

	public List<Express> query(String str);

	public Express getByUserId(int id);
}