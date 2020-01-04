package org.bs.model;

import java.util.Date;

public class Message {
	private int id;
	private Staff staff;
	private Date settime;
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Date getSettime() {
		return settime;
	}

	public void setSettime(Date settime) {
		this.settime = settime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String toString() {
		return "Message [id=" + id + ", staff=" + staff + ", settime="
				+ settime + ", content=" + content + "]";
	}
}