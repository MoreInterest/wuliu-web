package org.bs.service;

import java.util.List;
import org.bs.model.Message;

public interface MessageService {
	public void add(Message message);

	public void delete(Message message);

	public void update(Message message);

	public Message findById(int id);

	public Message findByUserId(int id);

	public List<Message> search(String str);
}