package org.bs.model;

import java.util.Date;

public class Finance {
	private int id;
	private String name;
	private Date settime;
	private String mingxi;
	private String descp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getSettime() {
		return settime;
	}

	public void setSettime(Date settime) {
		this.settime = settime;
	}

	public String getMingxi() {
		return mingxi;
	}

	public void setMingxi(String mingxi) {
		this.mingxi = mingxi;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String toString() {
		return "Finance [id=" + id + ", name=" + name + ", settime=" + settime
				+ ", mingxi=" + mingxi + ", descp=" + descp + "]";
	}
}