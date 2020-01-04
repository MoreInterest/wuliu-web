package org.bs.dao;

import java.util.List;
import org.bs.model.Message;

public interface MessageDao {
	public void save(Message message);

	public void delete(int id);

	public void update(Message message);

	public Message getById(int id);

	public List<Message> query(String str);

	public Message getByUserId(int id);
}