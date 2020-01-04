package org.bs.model;

import java.util.Date;

public class Express {
	private int id;
	private String name;
	private Member member;
	private String chufa;
	private String shoujianname;
	private String tel;
	private String place;
	private String leibie;
	private Date settime;
	private String descp;
	private String state;

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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getChufa() {
		return chufa;
	}

	public void setChufa(String chufa) {
		this.chufa = chufa;
	}

	public String getShoujianname() {
		return shoujianname;
	}

	public void setShoujianname(String shoujianname) {
		this.shoujianname = shoujianname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getLeibie() {
		return leibie;
	}

	public void setLeibie(String leibie) {
		this.leibie = leibie;
	}

	public Date getSettime() {
		return settime;
	}

	public void setSettime(Date settime) {
		this.settime = settime;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String toString() {
		return "Express [id=" + id + ", name=" + name + ", member=" + member
				+ ", chufa=" + chufa + ", shoujianname=" + shoujianname
				+ ", tel=" + tel + ", place=" + place + ", leibie=" + leibie
				+ ", settime=" + settime + ", descp=" + descp + ", state="
				+ state + "]";
	}
}