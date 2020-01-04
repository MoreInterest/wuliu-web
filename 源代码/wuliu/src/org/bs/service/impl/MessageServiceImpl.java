package org.bs.service.impl;

import java.util.List;
import org.bs.dao.MessageDao;
import org.bs.model.Message;
import org.bs.service.MessageService;

public class MessageServiceImpl implements MessageService {
	MessageDao messageDao;

	public void add(Message message) {
		messageDao.save(message);
	}

	public void delete(Message message) {
		messageDao.delete(message.getId());
	}

	public void update(Message message) {
		messageDao.update(message);
	}

	public Message findById(int id) {
		return messageDao.getById(id);
	}

	public Message findByUserId(int id) {
		return messageDao.getByUserId(id);
	}

	public List<Message> search(String str) {
		return messageDao.query(str);
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
}