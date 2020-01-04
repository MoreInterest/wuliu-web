package org.bs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.bs.dao.FinanceDao;
import org.bs.model.Finance;
import org.bs.utils.ConnContext;
import org.bs.utils.DB;
import org.bs.utils.PageContext;

public class FinanceDaoImpl extends BaseDao implements FinanceDao {
	public void save(Finance finance) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "insert into t_finance (name,settime,mingxi,descp) values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finance.getName());
			pstmt.setTimestamp(2, new java.sql.Timestamp(finance.getSettime()
					.getTime()));
			pstmt.setString(3, finance.getMingxi());
			pstmt.setString(4, finance.getDescp());
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
		String sql = "delete from t_finance where id =?";
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

	public void update(Finance finance) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		String sql = "update t_finance set name=?,settime=?,mingxi=?,descp=?"
				+ " where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, finance.getName());
			pstmt.setTimestamp(2, new java.sql.Timestamp(finance.getSettime()
					.getTime()));
			pstmt.setString(3, finance.getMingxi());
			pstmt.setString(4, finance.getDescp());
			pstmt.setInt(5, finance.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
		}
	}

	public Finance getById(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Finance finance = new Finance();
		String sql = "select * from t_finance where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				finance.setId(rs.getInt("id"));
				finance.setName(rs.getString("name"));
				finance.setSettime(rs.getTimestamp("settime"));
				finance.setMingxi(rs.getString("mingxi"));
				finance.setDescp(rs.getString("descp"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return finance;
	}

	public Finance getByUserId(int id) {
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Finance finance = new Finance();
		String sql = "select * from t_finance where users = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				finance.setId(rs.getInt("id"));
				finance.setName(rs.getString("name"));
				finance.setSettime(rs.getTimestamp("settime"));
				finance.setMingxi(rs.getString("mingxi"));
				finance.setDescp(rs.getString("descp"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DB.close(pstmt);
			DB.close(rs);
		}
		return finance;
	}

	public List<Finance> query(String str) {
		List<Finance> list = new ArrayList<Finance>();
		Connection conn = ConnContext.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from t_finance where 1=1 ";
		if (!"".equals(str) && str != null) {
			sql += " and descp = '" + str + "' ";
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
				Finance finance = new Finance();
				finance.setId(rs.getInt("id"));
				finance.setName(rs.getString("name"));
				finance.setSettime(rs.getTimestamp("settime"));
				finance.setMingxi(rs.getString("mingxi"));
				finance.setDescp(rs.getString("descp"));
				list.add(finance);
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