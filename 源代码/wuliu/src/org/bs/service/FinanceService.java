package org.bs.service;

import java.util.List;
import org.bs.model.Finance;

public interface FinanceService {
	public void add(Finance finance);

	public void delete(Finance finance);

	public void update(Finance finance);

	public Finance findById(int id);

	public Finance findByUserId(int id);

	public List<Finance> search(String str);
}