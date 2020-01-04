package org.bs.model;

public class Staff {
	private int id;
	private Role roles;
	private String username;
	private String password;
	private String name;
	private String sex;
	private String age;
	private String tel;
	private String address;
	private String email;
	private User users;
	private String zhandian;
	private String gongzi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public String getZhandian() {
		return zhandian;
	}

	public void setZhandian(String zhandian) {
		this.zhandian = zhandian;
	}

	public String getGongzi() {
		return gongzi;
	}

	public void setGongzi(String gongzi) {
		this.gongzi = gongzi;
	}

	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", sex=" + sex + ", age="
				+ age + ", tel=" + tel + ", address=" + address + ", email="
				+ email + ", users=" + users + ", zhandian=" + zhandian
				+ ", gongzi=" + gongzi + "]";
	}
}