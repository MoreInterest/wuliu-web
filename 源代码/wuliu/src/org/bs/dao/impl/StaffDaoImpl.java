package org.bs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.bs.dao.StaffDao;
import org.bs.model.Staff;
import org.bs.utils.ConnContext;
import org.bs.utils.DB;
import org.bs.utils.PageContext;
import org.bs.dao.UserDao;

public class StaffDaoImpl extends BaseDao implements StaffDao {
	private UserDao userDao = new UserDaoImpl();

	public void save(Staff staff) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "insert into t_staff (name,sex,age,tel,address,email,users,zhandian,gongzi) values(?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, staff.getName());
			pstmt.setString(2, staff.getSex());
			pstmt.setString(3, staff.getAge());
			pstmt.setString(4, staff.getTel());
			pstmt.setString(5, staff.getAddress());
			pstmt.setString(6, staff.getEmail());
			pstmt.setInt(7, staff.getUsers().getId());
			pstmt.setString(8, staff.getZhandian());
			pstmt.setString(9, staff.getGongzi());
			pstmt.executeUpdate();
			ResultSet results = pstmt.getGeneratedKeys();
			int num = -1;
			if (results.next()) {
				num = results.getInt(1);
			}
			staff.setId(num);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public void delete(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "delete from t_staff where id =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public void update(Staff staff) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "update t_staff set name=?,sex=?,age=?,tel=?,address=?,email=?,users=?,zhandian=?,gongzi=?"
				+ " where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, staff.getName());
			pstmt.setString(2, staff.getSex());
			pstmt.setString(3, staff.getAge());
			pstmt.setString(4, staff.getTel());
			pstmt.setString(5, staff.getAddress());
			pstmt.setString(6, staff.getEmail());
			pstmt.setInt(7, staff.getUsers().getId());
			pstmt.setString(8, staff.getZhandian());
			pstmt.setString(9, staff.getGongzi());
			pstmt.setInt(10, staff.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public Staff getById(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Staff staff = new Staff();
		String sql = "select * from t_staff where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				staff.setId(rs.getInt("id"));
				staff.setName(rs.getString("name"));
				staff.setSex(rs.getString("sex"));
				staff.setAge(rs.getString("age"));
				staff.setTel(rs.getString("tel"));
				staff.setAddress(rs.getString("address"));
				staff.setEmail(rs.getString("email"));
				staff.setUsers(userDao.getById(rs.getInt("users")));
				staff.setZhandian(rs.getString("zhandian"));
				staff.setGongzi(rs.getString("gongzi"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return staff;
	}

	public Staff getByUserId(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Staff staff = new Staff();
		String sql = "select * from t_staff where users = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				staff.setId(rs.getInt("id"));
				staff.setName(rs.getString("name"));
				staff.setSex(rs.getString("sex"));
				staff.setAge(rs.getString("age"));
				staff.setTel(rs.getString("tel"));
				staff.setAddress(rs.getString("address"));
				staff.setEmail(rs.getString("email"));
				staff.setUsers(userDao.getById(rs.getInt("users")));
				staff.setZhandian(rs.getString("zhandian"));
				staff.setGongzi(rs.getString("gongzi"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return staff;
	}

	public List<Staff> query(String str) {
		List<Staff> list = new ArrayList<Staff>();
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_staff where 1=1 ";
		if (!"".equals(str) && str != null) {
			sql += " and name like '%" + str + "%' ";
		}
		String sqlRecordsCount = "select count(*)"
				+ sql.substring(sql.indexOf("from"));
		sql += " limit ?,?";
		PageContext.getPage().setRecordsCount(
				getRecordsCount(sqlRecordsCount, new Class[] {},
						new Object[] {}));
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, PageContext.getPage().getPageIndex());
			pstmt.setInt(2, PageContext.getPage().getPageSize());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Staff staff = new Staff();
				staff.setId(rs.getInt("id"));
				staff.setName(rs.getString("name"));
				staff.setSex(rs.getString("sex"));
				staff.setAge(rs.getString("age"));
				staff.setTel(rs.getString("tel"));
				staff.setAddress(rs.getString("address"));
				staff.setEmail(rs.getString("email"));
				staff.setUsers(userDao.getById(rs.getInt("users")));
				staff.setZhandian(rs.getString("zhandian"));
				staff.setGongzi(rs.getString("gongzi"));
				list.add(staff);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return list;
	}
}