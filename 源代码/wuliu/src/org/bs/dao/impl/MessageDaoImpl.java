package org.bs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.bs.dao.MessageDao;
import org.bs.model.Message;
import org.bs.utils.ConnContext;
import org.bs.utils.DB;
import org.bs.utils.PageContext;
import org.bs.dao.StaffDao;

public class MessageDaoImpl extends BaseDao implements MessageDao {
	private StaffDao staffDao = new StaffDaoImpl();

	public void save(Message message) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "insert into t_message (staff,settime,content) values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, message.getStaff().getId());
			pstmt.setTimestamp(2, new java.sql.Timestamp(message.getSettime()
					.getTime()));
			pstmt.setString(3, message.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public void delete(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "delete from t_message where id =?";
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

	public void update(Message message) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "update t_message set staff=?,settime=?,content=?"
				+ " where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, message.getStaff().getId());
			pstmt.setTimestamp(2, new java.sql.Timestamp(message.getSettime()
					.getTime()));
			pstmt.setString(3, message.getContent());
			pstmt.setInt(4, message.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public Message getById(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Message message = new Message();
		String sql = "select * from t_message where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				message.setId(rs.getInt("id"));
				message.setStaff(staffDao.getById(rs.getInt("staff")));
				message.setSettime(rs.getTimestamp("settime"));
				message.setContent(rs.getString("content"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return message;
	}

	public Message getByUserId(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Message message = new Message();
		String sql = "select * from t_message where users = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				message.setId(rs.getInt("id"));
				message.setStaff(staffDao.getById(rs.getInt("staff")));
				message.setSettime(rs.getTimestamp("settime"));
				message.setContent(rs.getString("content"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return message;
	}

	public List<Message> query(String str) {
		List<Message> list = new ArrayList<Message>();
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_message where 1=1 ";
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
				Message message = new Message();
				message.setId(rs.getInt("id"));
				message.setStaff(staffDao.getById(rs.getInt("staff")));
				message.setSettime(rs.getTimestamp("settime"));
				message.setContent(rs.getString("content"));
				list.add(message);
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